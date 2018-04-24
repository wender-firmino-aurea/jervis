pipeline {
    agent any
    stages {
        stage('CHECK') {
            steps
                Unknown macro: { sh 'echo $DOCKER_LOGIN'}
        }

        stage('Build') {
            steps
                Unknown macro: { sh 'docker build . -t $IMAGE'}
        }
    }
    environment
        Unknown macro: { IMAGE = "aurea-demojervis" }
}