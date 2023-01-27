#!/bin/bash

# Update the system
sudo apt-get update

# Install Git
sudo apt-get install git -y

# Clone the repository
git clone git@github.com:gregjotau/learningApp.git

# Change directory to the project
cd learningApp

# Install Java 19
sudo apt-get install openjdk-19-jdk -y

# Install Gradle
sudo apt-get install gradle -y

# Build the project
gradle build

# Start the application
./gradlew bootRun
