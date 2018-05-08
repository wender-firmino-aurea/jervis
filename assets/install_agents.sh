#!/bin/bash
echo "Accessing jenkins remotely..."
env
echo $JENKINS_API_TOKEN_TEST

curl --user 'admin:dd27742ddefca924dfac4d0d4f1354ef' --data-urlencode "script=$(< /opt/my_jenkins_home/workspace/_jervis_generator/jobs/install_agents.groovy)" http://localhost:8080/scriptText