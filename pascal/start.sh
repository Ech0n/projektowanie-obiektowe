#!/bin/bash

IMAGE_NAME="my_pascal"
IMAGE_TAG="default"

if [ ! -z "$1" ]; then
    IMAGE_NAME="$1"
fi

if [ ! -z "$2" ]; then
    IMAGE_TAG="$2"
fi

if docker images --format '{{.Repository}}:{{.Tag}}' | grep -q "^${IMAGE_NAME}:${IMAGE_TAG}$"; then
    echo "Image $IMAGE_NAME:${IMAGE_TAG} already exists. Skip docker build."
else
    echo "Image $IMAGE_NAME:${IMAGE_TAG} does not exist."
    docker buildx build -t $IMAGE_NAME:${IMAGE_TAG} .
fi

docker run -v ./:/home/student/projobj --rm $IMAGE_NAME:${IMAGE_TAG} bash -c 'mkdir build && fpc -o./build/a.out bubbleSortTests.pas && ./build/a.out'