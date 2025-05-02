# This is pre built java docker image 
FROM eclipse-temurin:21-jdk

# set working directory
WORKDIR /app

# install ant and pyhton
RUN apt-get update && \
    apt-get install -y ant python3 python3-pip && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# copy the project files into the container
COPY . /app

RUN java -version && ant -version && python3 --version

CMD ["/bin/bash"]
