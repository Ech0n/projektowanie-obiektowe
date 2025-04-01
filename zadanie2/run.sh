docker pull kprzystalski/projobj-php:latest
docker run -it --rm -p 8000:8000 -v $(pwd):/home/student/projobj kprzystalski/projobj-php:latest ./startServer.sh
