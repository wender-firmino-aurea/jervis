#!/bin/bash
echo "Accessing jenkins remotely..."
ls -al
pwd
curl --user 'admin:01f27f492862850a529084c3f71d6428' --data-urlencode "script=$(< /opt/my_jenkins_home/workspace/_jervis_generator/jobs/install_agents.groovy)" http://localhost:8080/scriptText