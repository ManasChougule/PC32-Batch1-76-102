# from node:alpine

# workdir /app

# copy one.js .

# cmd ["node", "one.js"]

FROM openjdk:17-jdk-slim

workdir /app

copy app.java .

run javac app.java

cmd ["java", "Main"]







