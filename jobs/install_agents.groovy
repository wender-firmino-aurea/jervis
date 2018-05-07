/*
This is an example settings.groovy file which can be used by the Jervis
bootstrap.

The only required change is updating GITHUB_USER and GITHUB_TOKEN.
*/

//GitHub username
String GITHUB_USER = "paulokinjo"
//GitHub personal access token associated with the GitHub username
//Recommended scopes: repo, read:org, admin:repo_hook, user:email
String GITHUB_TOKEN = "c3823464726bf60ac25acf4267df31ad07274542"
//Used by webhook callback URLs to increase security
String SHARED_SECRET = "CHANGEME"

//GitHub credential required for Jervis API communication with api.github.com
credentials = [
    [
        'credential_type': 'StringCredentialsImpl',
        'credentials_id': 'github-token',
        'description': 'GitHub Personal Access Token used by Jervis for the GitHub API',
        'secret': GITHUB_TOKEN
    ],
    [
        'credential_type': 'UsernamePasswordCredentialsImpl',
        'credentials_id': 'github-user-and-token',
        'description': 'GitHub user and personal access token used by multibranch pipeline jobs for the GitHub API',
        'user': GITHUB_USER,
        'password': GITHUB_TOKEN
    ],
    [
        'credential_type': 'StringCredentialsImpl',
        'credentials_id': 'webhook-shared-secret',
        'description': 'Webhook shared secret.  Protects Jenkins from webhook callback payloads not including this secret.',
        'secret': SHARED_SECRET
    ]
]
//docker settings for slave agents
clouds_yadocker = [
    //YET ANOTHER DOCKER CLOUD (this is an item in a list of clouds)
    [
        cloud_name: "docker-local-test",
        docker_url: "tcp://localhost:2375",
        docker_templates: [
            //DOCKER TEMPLATE (this is an item in a list of templates)
            [
                //DOCKER CONTAINER LIFECYCLE
                docker_image_name: "jervis-docker-jvm:latest",
                //PULL IMAGE SETTINGS
                //valid values: pull_latest, pull_always, pull_once, pull_never
                pull_strategy: "pull_never",
                //volumes can be a string or list of strings
                //volumes: [ '/opt/jenkins-cache:/home/jenkins/.jenkins:rw', '/opt/gradle-cache:/home/jenkins/.gradle:rw' ],
                //volumes_from can be a string or list of strings
                //volumes_from: '',
                //environment can be a string or list of strings
                //environment: '',
                //JENKINS SLAVE CONFIG
                remote_fs_root: "/home/jenkins",
                labels: "jervis_generator stable docker ubuntu1604 sudo language:shell language:groovy language:java env jdk",
                //valid values: exclusive or normal
                usage: "exclusive",
                //LAUNCH METHOD
                //valid values: launch_ssh or launch_jnlp
                launch_method: "launch_jnlp",
                //settings specific to launch_jnlp
                launch_jnlp_linux_user: "jenkins",
                launch_jnlp_slave_jar_options: "-workDir /home/jenkins",
                launch_jnlp_lauch_timeout: 120,
                launch_jnlp_different_jenkins_master_url: "http://172.17.0.1:8080",
                launch_jnlp_ignore_certificate_check: false,
            ]
        ]

    ]
]

github_plugin = [
    //hookUrl: 'http://localhost:8080/github-webhook/',
    hookSharedSecretId: 'webhook-shared-secret',
    servers: [
        'Public GitHub.com': [
            apiUrl: 'https://api.github.com',
            manageHooks: true,
            credentialsId: 'github-token',
        ]
    ]
]

/*
//configure the GitHub security realm for authentication
//web_uri and api_uri can be deleted if not using GitHub Enterprise
github_realm = [
    web_uri: '',
    api_uri: '',
    client_id: '',
    client_secret: '',
    oauth_scopes: 'read:org,user:email,repo'
]
*/