# Use the Eclipse Temurin image with JDK 21
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Install Ant and Python
RUN apt-get update && \
    apt-get install -y ant python3 python3-pip && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Copy the project files into the container
COPY . /app

# Show versions (optional for debug/info)
RUN java -version && ant -version && python3 --version

# Default command
CMD ["/bin/bash"]
