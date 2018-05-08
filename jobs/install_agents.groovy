import com.github.kostyasha.yad.DockerCloud
import com.github.kostyasha.yad.DockerConnector
import com.github.kostyasha.yad.DockerContainerLifecycle
import com.github.kostyasha.yad.DockerSlaveTemplate
import com.github.kostyasha.yad.commons.DockerCreateContainer
import com.github.kostyasha.yad.commons.DockerImagePullStrategy
import com.github.kostyasha.yad.commons.DockerPullImage
import com.github.kostyasha.yad.commons.DockerRemoveContainer
import com.github.kostyasha.yad.commons.DockerStopContainer
import com.github.kostyasha.yad.launcher.DockerComputerJNLPLauncher
import com.github.kostyasha.yad.launcher.DockerComputerLauncher
import com.github.kostyasha.yad.launcher.DockerComputerSSHLauncher
import com.github.kostyasha.yad.other.ConnectorType
import com.github.kostyasha.yad.strategy.DockerOnceRetentionStrategy

import hudson.model.Node
import hudson.plugins.sshslaves.SSHConnector
import hudson.slaves.EnvironmentVariablesNodeProperty
import hudson.slaves.NodeProperty
import hudson.slaves.RetentionStrategy
import hudson.tools.ToolLocationNodeProperty
import jenkins.model.Jenkins
import net.sf.json.JSONArray
import net.sf.json.JSONObject

String setup_cloudName = 'docker-local'
String setup_dockerUrl = 'tcp://localhost:2375'
String setup_pullStrategy = 'pull_never'

JSONArray clouds_yadocker = [
    [
        cloud_name: setup_cloudName,
        docker_url: setup_dockerUrl,
        docker_api_version: '',
        host_credentials_id: '',
        connection_type: 'netty',
        connect_timeout: 0,
        max_containers: 50,
        docker_templates: [
            [
                max_instances: 10,
                docker_image_name: 'registry2.swarm.devfactory.com/aurea/central/jervis/sandbox-java:latest',
                pull_strategy: setup_pullStrategy,
                pull_registry_credentials_id: '',
                docker_command: '',
                hostname: '',
                dns: '',
                volumes: '',
                volumes_from: '',
                environment: '',
                port_bindings: '',
                bind_all_declared_ports: false,
                memory_limit_in_mb: 0,
                cpu_shares: 0,
                run_container_privileged: false,
                allocate_pseudo_tty: false,
                mac_address: '',
                extra_hosts: '',
                network_mode: '',
                devices: '',
                cpuset_constraint_cpus: '',
                cpuset_constraint_mems: '',
                links: '',
                stop_container_timeout: 10,
                remove_volumes: true,
                force_remove_containers: true,
                remote_fs_root: '/home/jenkins',
                labels: 'jervis_generator stable docker ubuntu1604 sudo language:shell language:groovy language:java env jdk',
                usage: 'exclusive',
                availability_strategy: 'docker_once_retention_strategy',
                availability_idle_timeout: 10,
                executors: 1,
                launch_method: 'launch_jnlp',
                launch_ssh_credentials_id: '',
                launch_ssh_port: 22,
                launch_ssh_java_path: '',
                launch_ssh_jvm_options: '',
                launch_ssh_prefix_start_slave_command: '',
                launch_ssh_suffix_start_slave_command: '',
                launch_ssh_connection_timeout: 120,
                launch_ssh_max_num_retries: 10,
                launch_ssh_time_wait_between_retries: 10,
                launch_jnlp_linux_user: 'jenkins',
                launch_jnlp_lauch_timeout: 120,
                launch_jnlp_slave_jar_options: '-workDir /home/jenkins',
                launch_jnlp_slave_jvm_options: '',
                launch_jnlp_different_jenkins_master_url: 'http://172.17.0.1:8080',
                launch_jnlp_ignore_certificate_check: false,
                environment_variables: [:],
                tool_locations: [:],
                remote_fs_root_mapping: ''
            ],
            [
                max_instances: 10,
                docker_image_name: 'registry2.swarm.devfactory.com/aurea/central/jervis/agents/ansible-base:latest',
                pull_strategy: setup_pullStrategy,
                pull_registry_credentials_id: '',
                docker_command: '',
                hostname: '',
                dns: '',
                volumes: '',
                volumes_from: '',
                environment: '',
                port_bindings: '',
                bind_all_declared_ports: false,
                memory_limit_in_mb: 0,
                cpu_shares: 0,
                run_container_privileged: false,
                allocate_pseudo_tty: false,
                mac_address: '',
                extra_hosts: '',
                network_mode: '',
                devices: '',
                cpuset_constraint_cpus: '',
                cpuset_constraint_mems: '',
                links: '',
                stop_container_timeout: 10,
                remove_volumes: true,
                force_remove_containers: true,
                remote_fs_root: '/home/jenkins',
                labels: 'docker stable linux ansible',
                usage: 'exclusive',
                availability_strategy: 'docker_once_retention_strategy',
                availability_idle_timeout: 10,
                executors: 1,
                launch_method: 'launch_jnlp',
                launch_ssh_credentials_id: '',
                launch_ssh_port: 22,
                launch_ssh_java_path: '',
                launch_ssh_jvm_options: '',
                launch_ssh_prefix_start_slave_command: '',
                launch_ssh_suffix_start_slave_command: '',
                launch_ssh_connection_timeout: 120,
                launch_ssh_max_num_retries: 10,
                launch_ssh_time_wait_between_retries: 10,
                launch_jnlp_linux_user: 'jenkins',
                launch_jnlp_lauch_timeout: 120,
                launch_jnlp_slave_jar_options: '-workDir /home/jenkins',
                launch_jnlp_slave_jvm_options: '',
                launch_jnlp_different_jenkins_master_url: 'http://172.17.0.1:8080',
                launch_jnlp_ignore_certificate_check: false,
                environment_variables: [:],
                tool_locations: [:],
                remote_fs_root_mapping: ''
            ],
            [
                max_instances: 10,
                docker_image_name: 'registry2.swarm.devfactory.com/aurea/central/jervis/agents/java-agent:latest',
                pull_strategy: setup_pullStrategy,
                pull_registry_credentials_id: '',
                docker_command: '',
                hostname: '',
                dns: '',
                volumes: '',
                volumes_from: '',
                environment: '',
                port_bindings: '',
                bind_all_declared_ports: false,
                memory_limit_in_mb: 0,
                cpu_shares: 0,
                run_container_privileged: false,
                allocate_pseudo_tty: false,
                mac_address: '',
                extra_hosts: '',
                network_mode: '',
                devices: '',
                cpuset_constraint_cpus: '',
                cpuset_constraint_mems: '',
                links: '',
                stop_container_timeout: 10,
                remove_volumes: true,
                force_remove_containers: true,
                remote_fs_root: '/home/jenkins',
                labels: 'stable docker ubuntu1604 sudo language:java env jdk unstable',
                usage: 'exclusive',
                availability_strategy: 'docker_once_retention_strategy',
                availability_idle_timeout: 10,
                executors: 1,
                launch_method: 'launch_jnlp',
                launch_ssh_credentials_id: '',
                launch_ssh_port: 22,
                launch_ssh_java_path: '',
                launch_ssh_jvm_options: '',
                launch_ssh_prefix_start_slave_command: '',
                launch_ssh_suffix_start_slave_command: '',
                launch_ssh_connection_timeout: 120,
                launch_ssh_max_num_retries: 10,
                launch_ssh_time_wait_between_retries: 10,
                launch_jnlp_linux_user: 'jenkins',
                launch_jnlp_lauch_timeout: 120,
                launch_jnlp_slave_jar_options: '-workDir /home/jenkins',
                launch_jnlp_slave_jvm_options: '',
                launch_jnlp_different_jenkins_master_url: 'http://172.17.0.1:8080',
                launch_jnlp_ignore_certificate_check: false,
                environment_variables: [:],
                tool_locations: [:],
                remote_fs_root_mapping: ''
            ]


        ]

    ]
] as JSONArray

def detectGlobalToolExists(String location) {
    def (toolType, toolName) = location.split('@')
    boolean found_installation = Jenkins.instance.getExtensionList(toolType)[0].installations.findAll { it.name == toolName } as boolean
    return found_installation
}

def selectLauncher(String launcherType, JSONObject obj) {
    switch(launcherType) {
        case 'launch_ssh':
            SSHConnector sshConnector = new SSHConnector(obj.optInt('launch_ssh_port', 22),
                    obj.optString('launch_ssh_credentials_id'),
                    obj.optString('launch_ssh_jvm_options'),
                    obj.optString('launch_ssh_java_path'),
                    obj.optString('launch_ssh_prefix_start_slave_command'),
                    obj.optString('launch_ssh_suffix_start_slave_command'),
                    obj.optInt('launch_ssh_connection_timeout'),
                    obj.optInt('launch_ssh_max_num_retries'),
                    obj.optInt('launch_ssh_time_wait_between_retries')
                    )
            return new DockerComputerSSHLauncher(sshConnector)
        case 'launch_jnlp':
            DockerComputerJNLPLauncher dockerComputerJNLPLauncher = new DockerComputerJNLPLauncher()
            dockerComputerJNLPLauncher.setUser(obj.optString('launch_jnlp_linux_user','jenkins'))
            dockerComputerJNLPLauncher.setLaunchTimeout(obj.optLong('launch_jnlp_lauch_timeout', 120L))
            dockerComputerJNLPLauncher.setSlaveOpts(obj.optString('launch_jnlp_slave_jar_options'))
            dockerComputerJNLPLauncher.setJvmOpts(obj.optString('launch_jnlp_slave_jvm_options'))
            dockerComputerJNLPLauncher.setJenkinsUrl(obj.optString('launch_jnlp_different_jenkins_master_url'))
            dockerComputerJNLPLauncher.setNoCertificateCheck(obj.optBoolean('launch_jnlp_ignore_certificate_check', false))
            return dockerComputerJNLPLauncher
        default:
            return null
    }
}

def newDockerCloud(JSONObject obj) {
    DockerConnector connector = new DockerConnector(obj.optString('docker_url', 'tcp://localhost:2375'))
    if(obj.optInt('connect_timeout')) {
        connector.setConnectTimeout(obj.optInt('connect_timeout'))
    }
    connector.setApiVersion(obj.optString('docker_api_version'))
    connector.setCredentialsId(obj.optString('host_credentials_id'))
    List<String> connection_types = ['NETTY', 'JERSEY']
    String connection_type_default = 'NETTY'
    String user_selected_connection_type = obj.optString('connection_type', connection_type_default).toUpperCase()
    String connection_type = (user_selected_connection_type in connection_types)? user_selected_connection_type : connection_type_default
    connector.setConnectorType(ConnectorType."${connection_type}")

    DockerCloud cloud = new DockerCloud(obj.optString('cloud_name'),
                       bindJSONToList(DockerSlaveTemplate.class, obj.opt('docker_templates')),
                       obj.optInt('max_containers', 50),
                       connector)

    return cloud
}

def newDockerSlaveTemplate(JSONObject obj) {
    DockerPullImage pullImage = new DockerPullImage()
    String pullStrategy = obj.optString('pull_strategy', 'PULL_LATEST').toUpperCase()
    if(pullStrategy in ['PULL_LATEST', 'PULL_ALWAYS', 'PULL_ONCE', 'PULL_NEVER']) {
        pullImage.setPullStrategy(DockerImagePullStrategy."${pullStrategy}")
    } else {
        pullImage.setPullStrategy(DockerImagePullStrategy.PULL_LATEST)
    }
    pullImage.setCredentialsId(obj.optString('pull_registry_credentials_id'))

    DockerCreateContainer createContainer = new DockerCreateContainer()
    createContainer.setBindPorts(obj.optString('port_bindings'))
    createContainer.setBindAllPorts(obj.optBoolean('bind_all_declared_ports', false))
    createContainer.setDnsString(obj.optString('dns'))
    createContainer.setHostname(obj.optString('hostname'))
    if(obj.optLong('memory_limit_in_mb')) {
        createContainer.setMemoryLimit(obj.optLong('memory_limit_in_mb'))
    }
    createContainer.setPrivileged(obj.optBoolean('run_container_privileged', false))
    createContainer.setTty(obj.optBoolean('allocate_pseudo_tty', false))
    if(obj.optJSONArray('volumes')) {
        createContainer.setVolumes(obj.optJSONArray('volumes') as List<String>)
    } else {
        createContainer.setVolumesString(obj.optString('volumes'))
    }
    if(obj.optJSONArray('volumes_from')) {
        createContainer.setVolumesFrom(obj.optJSONArray('volumes_from') as List<String>)
    } else {
        createContainer.setVolumesFromString(obj.optString('volumes_from'))
    }
    createContainer.setMacAddress(obj.optString('mac_address'))
    if(obj.optInt('cpu_shares')) {
        createContainer.setCpuShares(obj.optInt('cpu_shares'))
    }
    createContainer.setCommand(obj.optString('docker_command'))
    if(obj.optJSONArray('environment')) {
        createContainer.setEnvironment(obj.optJSONArray('environment') as List<String>)
    } else {
        createContainer.setEnvironmentString(obj.optString('environment'))
    }
    if(obj.optJSONArray('extra_hosts')) {
        createContainer.setExtraHosts(obj.optJSONArray('extra_hosts') as List<String>)
    } else {
        createContainer.setExtraHostsString(obj.optString('extra_hosts'))
    }
    createContainer.setNetworkMode(obj.optString('network_mode'))
    if(obj.optJSONArray('devices')) {
        createContainer.setDevices(obj.optJSONArray('devices'))
    } else {
        createContainer.setDevicesString(obj.optString('devices'))
    }
    createContainer.setCpusetCpus(obj.optString('cpuset_constraint_cpus'))
    createContainer.setCpusetMems(obj.optString('cpuset_constraint_mems'))
    if(obj.optJSONArray('links')) {
        createContainer.setLinks(obj.optJSONArray('links'))
    } else {
        createContainer.setLinksString(obj.optString('links'))
    }

    DockerStopContainer stopContainer = new DockerStopContainer()
    stopContainer.setTimeout(obj.optInt('stop_container_timeout', 10))

    DockerRemoveContainer removeContainer = new DockerRemoveContainer()
    removeContainer.setRemoveVolumes(obj.optBoolean('remove_volumes', false))
    removeContainer.setForce(obj.optBoolean('force_remove_containers', false))

    DockerContainerLifecycle dockerContainerLifecycle = new DockerContainerLifecycle()
    dockerContainerLifecycle.setImage(obj.optString('docker_image_name'))
    dockerContainerLifecycle.setPullImage(pullImage)
    dockerContainerLifecycle.setCreateContainer(createContainer)
    dockerContainerLifecycle.setStopContainer(stopContainer)
    dockerContainerLifecycle.setRemoveContainer(removeContainer)

    RetentionStrategy retentionStrategy = new DockerOnceRetentionStrategy(obj.optInt('availability_idle_timeout', 10))

    List<String> launch_methods = ['launch_ssh', 'launch_jnlp']
    String default_launch_method = 'launch_jnlp'
    String user_selected_launch_method = obj.optString('launch_method', default_launch_method).toLowerCase()
    String launch_method = (user_selected_launch_method in launch_methods)? user_selected_launch_method : default_launch_method
    DockerComputerLauncher launcher = selectLauncher(launch_method, obj)

    DockerSlaveTemplate dockerSlaveTemplate = new DockerSlaveTemplate()
    dockerSlaveTemplate.setDockerContainerLifecycle(dockerContainerLifecycle)
    dockerSlaveTemplate.setLabelString(obj.optString('labels', 'docker'))
    String node_usage = (obj.optString('usage', 'EXCLUSIVE').toUpperCase().equals('NORMAL'))? 'NORMAL' : 'EXCLUSIVE'
    dockerSlaveTemplate.setMode(Node.Mode."${node_usage}")
    dockerSlaveTemplate.setNumExecutors(obj.optInt('executors', 1))
    dockerSlaveTemplate.setRetentionStrategy(retentionStrategy)
    dockerSlaveTemplate.setLauncher(launcher)
    dockerSlaveTemplate.setRemoteFs(obj.optString('remote_fs_root', '/srv/jenkins'))
    dockerSlaveTemplate.setMaxCapacity(obj.optInt('max_instances', 10))
    List<NodeProperty> nodeProperties = [] as List<NodeProperty>
    if(obj.optJSONObject('environment_variables')) {
        HashMap<String,String> env = obj.optJSONObject('environment_variables') as HashMap<String,String>
        List<EnvironmentVariablesNodeProperty.Entry> envEntries = [] as List<EnvironmentVariablesNodeProperty.Entry>
        (env.keySet() as String[]).each { var ->
            envEntries << (new EnvironmentVariablesNodeProperty.Entry(var, env[var]))
        }
        nodeProperties << (new EnvironmentVariablesNodeProperty(envEntries))
    }
    if(obj.optJSONObject('tool_locations')) {
        HashMap<String,String> tool = obj.optJSONObject('tool_locations') as HashMap<String,String>
        List<ToolLocationNodeProperty.ToolLocation> toolLocations = [] as List<ToolLocationNodeProperty.ToolLocation>
        (tool.keySet() as String[]).each { location ->
            if(location.contains('@') && detectGlobalToolExists(location)) {
                toolLocations << (new ToolLocationNodeProperty.ToolLocation(location, tool[location]))
            } else {
                println "WARNING: Invalid tool: '${location}'.  Format should be 'type@name' where both type and name already exist in global tool configurations."
            }
        }
        nodeProperties << (new ToolLocationNodeProperty(toolLocations))
    }
    if(nodeProperties) {
        dockerSlaveTemplate.setNodeProperties(nodeProperties)
    }
    return dockerSlaveTemplate
}

def bindJSONToList(Class type, Object src) {
    if(!(type == DockerCloud) && !(type == DockerSlaveTemplate)) {
        throw new Exception("Must use DockerCloud or DockerSlaveTemplate class.")
    }
    ArrayList<?> docker_array
    if(type == DockerCloud){
        docker_array = new ArrayList<DockerCloud>()
    } else {
        docker_array = new ArrayList<DockerSlaveTemplate>()
    }
    if (src instanceof JSONObject) {
        docker_array.add("new${type.getSimpleName()}"(src))
    } else if(src instanceof JSONArray) {
        for (Object o : src) {
            if (o instanceof JSONObject) {
                docker_array.add("new${type.getSimpleName()}"(o))
            }
        }
    }
    return docker_array
}

if(!Jenkins.instance.isQuietingDown()) {
    ArrayList<DockerCloud> clouds = new ArrayList<DockerCloud>()
    clouds = bindJSONToList(DockerCloud.class, clouds_yadocker)
    if(clouds.size() > 0) {
        dockerConfigUpdated = true
        Jenkins.instance.clouds.removeAll(DockerCloud)
        Jenkins.instance.clouds.addAll(clouds)
        clouds*.name.each { cloudName ->
            println "Configured Yet Another Docker cloud ${cloudName}"
        }
        Jenkins.instance.save()
    } else {
        println 'Nothing changed.  No Yet Another docker clouds to configure.'
    }
} else {
    println 'Shutdown mode enabled.  Configure Yet Another Docker clouds SKIPPED.'
}

null
