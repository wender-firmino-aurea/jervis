/*
   Copyright 2014-2018 Sam Gleske - https://github.com/samrocketman/jervis

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   */

//this code should be at the beginning of every script included which requires bindings
require_bindings('jobs/is_pipeline.groovy', ['git_service', 'project', 'project_folder', 'global_threadlock'])

/*
   This will determine if a branch given branch (or default branch) is
   compatible with Jenkins pipeline multibranch jobs.
 */

import net.gleske.jervis.exceptions.SecurityException
import net.gleske.jervis.lang.lifecycleGenerator

is_pipeline = null
is_pipeline = { String JERVIS_BRANCH = '' ->
    List<String> folder_listing = git_service.getFolderListing("${project}", '/', JERVIS_BRANCH)
    List<String> aurea_central_listing
    try {
        aurea_central_listing = git_service.getFolderListing("${project}", '/aurea-central-jervis', JERVIS_BRANCH)
    } catch (Exception ex) {
        aurea_central_listing = []
    }

    String main_jervis_yaml
    List<String> jervis_yamls = new ArrayList<>();
    for(String file: folder_listing) {
        if (file.startsWith(".jervis") && file.endsWith(".yml")) {
            jervis_yamls.add(git_service.getFile(project, file, JERVIS_BRANCH))
            jervisFileExists = true
            if (file.equals(".jervis.yml")) {
                main_jervis_yaml = git_service.getFile(project, file, JERVIS_BRANCH)
            }
        }
    }

    if(! jervisFileExists) {
        if('.jervis-default.yml' in aurea_central_listing) {
            println "Using default jervis"
            jervis_yaml = git_service.getFile(project, '/aurea-central-jervis/.jervis-default.yml', JERVIS_BRANCH)
        }
        else {
            //skip creating the job for this branch
            println "Skipping branch: ${JERVIS_BRANCH} because jervisFile does not exist"
            return
        }
    }

    boolean currentBranchJobNeedsToBeGenerated = false
    for(String jervis_yaml: jervis_yamls) {
        def generator = new lifecycleGenerator()
        generator.loadPlatformsString(parent_job.readFileFromWorkspace('resources/platforms.json').toString())
        generator.preloadYamlString(jervis_yaml)
        //could optionally read lifecycles and toolchains files by OS
        def os_stability = "${generator.label_os}-${generator.label_stability}"
        generator.loadLifecyclesString(parent_job.readFileFromWorkspace("resources/lifecycles-${os_stability}.json").toString())
        generator.loadToolchainsString(parent_job.readFileFromWorkspace("resources/toolchains-${os_stability}.json").toString())
        generator.loadYamlString(jervis_yaml)
        generator.folder_listing = folder_listing
        //check for branch filters
        if(JERVIS_BRANCH && generator.isGenerateBranch(JERVIS_BRANCH)) {
            currentBranchJobNeedsToBeGenerated = true
            break
        }
    }
    if(! currentBranchJobNeedsToBeGenerated) {
        println "Skipping branch: ${JERVIS_BRANCH} because branch is not in jervis files"
        return
    }

    def main_jervis_generator = new lifecycleGenerator()
    if(main_jervis_yaml.indexOf('language:') < 0) {
        //travis ci defaults to ruby when no language is specified
        main_jervis_generator.yaml_language = 'ruby'
    }
    main_jervis_generator.loadPlatformsString(parent_job.readFileFromWorkspace('resources/platforms.json').toString())
    main_jervis_generator.preloadYamlString(main_jervis_yaml)
    //could optionally read lifecycles and toolchains files by OS
    def os_stability = "${main_jervis_generator.label_os}-${main_jervis_generator.label_stability}"
    main_jervis_generator.loadLifecyclesString(parent_job.readFileFromWorkspace("resources/lifecycles-${os_stability}.json").toString())
    main_jervis_generator.loadToolchainsString(parent_job.readFileFromWorkspace("resources/toolchains-${os_stability}.json").toString())
    main_jervis_generator.loadYamlString(main_jervis_yaml)
    main_jervis_generator.folder_listing = folder_listing

    //attempt to get the private key else return an empty string
    //force detecting decryption failures before attempting to create the job
    String credentials_id = main_jervis_generator.getObjectValue(main_jervis_generator.jervis_yaml, 'jenkins.secrets_id', '')
    String private_key_contents = getFolderRSAKeyCredentials(project_folder, credentials_id)

    if(credentials_id && !private_key_contents) {
        throw new SecurityException("Branch: ${JERVIS_BRANCH}.  Could not find private key using Jenkins Credentials ID: ${credentials_id}")
    }
    if(private_key_contents) {
        println "Branch: ${JERVIS_BRANCH?:'default branch in GitHub (typically master but not always)'}.  Attempting to decrypt jenkins.secrets using Jenkins Credentials ID ${credentials_id}."
        main_jervis_generator.setPrivateKey(private_key_contents)
        main_jervis_generator.decryptSecrets()
        println "Branch: ${JERVIS_BRANCH}.  Decrypted the following properties (indented):"
        println '    ' + main_jervis_generator.plainlist*.get('key').join('\n    ')
    }
    if(!JERVIS_BRANCH) {
        //default branch is being referenced so save it for referencing later
        default_generator = main_jervis_generator
        return
    }
    //we've made it this far so it must be legit
    if(!default_generator || (default_generator && !default_generator.isFilteredByRegex(JERVIS_BRANCH))) {
        global_threadlock.withLock {
            branches << JERVIS_BRANCH
        }
    }
}
