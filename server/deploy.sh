#!/bin/bash

# Connect to oslo1 server
ssh ubuntu@oslo1 << EOF

# Change directory to learningApp
cd /home/ubuntu/learningApp

git pull

# Run gradle build
./gradlew build

# Restart teoriproven service
sudo systemctl restart teoriproven.service

EOF