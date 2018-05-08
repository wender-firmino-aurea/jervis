#!/bin/bash
echo "Accessing jenkins remotely..."

curl --user 'admin:01f27f492862850a529084c3f71d6428' --data-urlencode "script=$(< ../jobs/setup_docker_slave.groovy)" http://localhost:8080/scriptText