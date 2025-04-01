#!/bin/bash

cd my_project_name
symfony server:start --no-tls -d
sleep 10
echo "Running endpoint tests..."
./endpointTests.sh

read -p "Press any turnoff the server and docker container..." -n1 -s
symfony server:stop