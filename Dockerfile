# Use the official Tomcat image with Java 11
FROM tomcat:9.0-jdk11-openjdk

# Remove the default Tomcat webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your project files into the Tomcat webapps directory
# Since you're using a Dynamic Web Project, we copy the webapp folder
COPY ./src/main/webapp /usr/local/tomcat/webapps/ROOT

# Start Tomcat
EXPOSE 8080
CMD ["catalina.sh", "run"]