docker buildx build -t swift_vapor_md .
docker run --rm -it   -v $(pwd):/src -v ./db:/db   -e SWIFT_BACKTRACE=enable=yes   -p 8080:8080   swift_vapor_md