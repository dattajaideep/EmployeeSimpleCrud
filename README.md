# EmployeeDepartmentCrudApplication
## Description
A getting started with boiler-plate application, learn 
Back-end CRUD(Employee-Department Model) application in Spring Boot with Junit 5-Test cases
## Installation
### Pre-requisites
* Java IDE
* JDK - 17(Suggestion)
* Maven
### Java Development Kit
#### Mac(using Terminal)
* Run the command `curl -s "https://get.sdkman.io" | bash` to install sdkman.
* Then open a new Terminal
* Run `sdk list java` to get the list available java version for download.
* Download JDK from any vendor(Suggestion: Corretto,Oracle,Temurin) and any version(Suggestion: 17 and above)
Example command : `sdk install java 19.0.2-amzn`
* Version check `java -version`
### Maven Installation
#### Mac(using Terminal)
* Verify the Java installation `java -version`
* Head over to [Apache Maven Site](https://maven.apache.org/download.cgi) download the latest version of Apache Maven [apache-maven-3.9.2-bin.tar.gz](https://dlcdn.apache.org/maven/maven-3/3.9.2/binaries/apache-maven-3.9.2-bin.tar.gz)
* Open terminal and cd to the installed location on your system and Run cmd `gunzip -c`_apache-maven*_`| tar xopf -` to extract files
* Change permissions to own `chown -R root:wheel Downloads/`apache-maven*
* And move the generated folder into system folder opt, use cmd `mv Downloads/apache-maven* /opt/apache-maven`
* Save using `exit`
* Set path Variables in mac using the cmd `nano $HOME/.profile` and `export PATH=$PATH:/opt/apache-maven/bin`
* Exit the nano editor using CTRL+X
* Load the new Setup using cmd `bash`
* Check version `mvn -version`
## Boilerplate Implementation
### Spring-CRUD and MVC model
### JUnit Mockito Test casing
### Custom Exceptions
### One-Many Mapping
### Lombok
### Spring Custom Banner
### Mapstruct
### Swagger Open API implementation
### Spring Jpa Specification
### Spring Cloud Eureka Client
### Jasypt-Set-Up Guide
* Extract the installed zip from [Jasypt GitHub page](https://github.com/jasypt/jasypt?tab=readme-ov-file)
* From wsl terminal or mac terminal change directory to /jasypt-1.9.3/bin/ and run the following as needed for encryption
* `./encrypt.sh ivGeneratorClassName=org.jasypt.iv.RandomIvGenerator input="postges" password=sj2102 algorithm=PBEWITHHMACSHA512ANDAES_256`
* For decryption refer [Official Jasypt Webpage](http://www.jasypt.org/cli.html)
