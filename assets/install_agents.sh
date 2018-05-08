#!/bin/bash
echo "Accessing jenkins remotely..."
env
echo $JENKINS_API_TOKEN_USER
echo $JENKINS_API_TOKEN_PASSWORD
echo $JENKINS_SERVER_URL

curl --user """$JENKINS_API_TOKEN_USER:$JENKINS_API_TOKEN_PASSWORD""" --data-urlencode "script=$(< /opt/my_jenkins_home/workspace/_jervis_generator/jobs/install_agents.groovy)" """$JENKINS_SERVER_URL"""/scriptText