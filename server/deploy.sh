#!/bin/bash

# Connect to oslo1 server
ssh ubuntu@oslo1 << EOF

# Change directory to hrApplication
cd /home/ubuntu/hrApplication

git pull

# Run gradle build
./gradlew build

# Restart teoriproven service
sudo systemctl restart teoriproven.service

EOF