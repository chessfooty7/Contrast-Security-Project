# Contrast-Security-Project

This is a Java Web App using Maven, Tomcat as the server, Eclipse. It is a simple web app which displays my stock portfolio, but has a metrics filter extension which monitors all http requests and calculates various attributes like response size and request time. This data is processed in thread safe data structures and displayed for the user.

I built this project for my Contrast Security Interview and to also enhance my knowledge about web apps and web development tools like Github Actions.

To deploy simply clone this repository with link https://github.com/chessfooty7/Contrast-Security-Project.git and import as a maven project in Eclipse or any other IDE's. Make sure you have all the required dependencies listed in the pom.xml, then run the mvn INSTALL and mvn tomcat:deploy commands.
