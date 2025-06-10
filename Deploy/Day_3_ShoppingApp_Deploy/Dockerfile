FROM tomcat:9.0-jdk11

# Copy WAR file to Tomcat (this already contains MySQL connector jar & Jakarta jar)
COPY Day_3_ShoppingApp.war /usr/local/tomcat/webapps/

# COPY mysql-connector-j-9.3.0.jar /usr/local/tomcat/lib/
# COPY jakarta.servlet-api_6.0.0.jar /usr/local/tomcat/lib/

EXPOSE 8080
CMD ["catalina.sh", "run"]