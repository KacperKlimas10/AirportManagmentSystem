FROM ubuntu:latest
LABEL authors="Kacper"

ENTRYPOINT ["top", "-b"]