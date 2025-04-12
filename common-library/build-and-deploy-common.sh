#!/bin/bash

# Script to build and deploy common-0.0.1-SNAPSHOT.jar to local Maven repository

# Variables
COMMON_DIR="/Users/mattclarson/dev/microservicesDemo/common-library"  # Adjust this path if needed
TARGET_JAR="common-0.0.1-SNAPSHOT.jar"
LOCAL_REPO="/Users/mattclarson/.m2/repository/com/corncrakeconsulting/common/0.0.1-SNAPSHOT"

# Colors for output
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m' # No Color

echo -e "${GREEN}Starting build and deploy process for common-0.0.1-SNAPSHOT.jar${NC}"

# Step 1: Navigate to the common module directory
if [ ! -d "$COMMON_DIR" ]; then
    echo -e "${RED}Error: Directory $COMMON_DIR not found. Please check the path.${NC}"
    exit 1
fi

cd "$COMMON_DIR" || {
    echo -e "${RED}Error: Failed to change to $COMMON_DIR${NC}"
    exit 1
}

# Step 2: Clean and build the project
echo "Running 'mvn clean install'..."
mvn clean install
if [ $? -ne 0 ]; then
    echo -e "${RED}Error: Maven build failed.${NC}"
    exit 1
fi

# Step 3: Verify the JAR was created
JAR_PATH="target/$TARGET_JAR"
if [ ! -f "$JAR_PATH" ]; then
    echo -e "${RED}Error: $JAR_PATH not found after build.${NC}"
    exit 1
fi

echo -e "${GREEN}Build successful: $JAR_PATH created.${NC}"

# Step 4: Check if the JAR is in the local Maven repository
REPO_JAR="$LOCAL_REPO/$TARGET_JAR"
if [ -f "$REPO_JAR" ]; then
    echo -e "${GREEN}JAR already deployed to $REPO_JAR${NC}"
else
    echo -e "${RED}Error: JAR not found in $REPO_JAR. Deploy may have failed.${NC}"
    echo "Manually installing JAR to local repository..."
    mvn install:install-file -Dfile="$JAR_PATH" -DgroupId=com.corncrakeconsulting -DartifactId=common -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar
    if [ $? -eq 0 ]; then
        echo -e "${GREEN}Manually deployed $TARGET_JAR to $REPO_JAR${NC}"
    else
        echo -e "${RED}Error: Manual deploy failed.${NC}"
        exit 1
    fi
fi

echo -e "${GREEN}Process complete! common-0.0.1-SNAPSHOT.jar is in $LOCAL_REPO${NC}"
