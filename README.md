# Loan-Application-System

## Summary

A Loan Application System developed with Spring Boot and uses Restful. Receives __*loan application requests*__ and returns
the __*loan application result*__ according to the __*customer info*__ and __*credit score*__ of the customer. Also sends a __*sms*__ to the
customer about loan application result.

## Details

* A system that user can *add*, *update*, *delete* **Customer**.

* When a **Customer** is added to the system, a **Credit Score** will be generated automatically according to the last
  digit of tckn.

* User can *apply for loan* for **Customer**.

* **Loan application result** will be calculated depending on **Credit Score** and **Customer's monthly income**.

* **Loan application results** can be *inquired* by tckn of the **Customer**

* **Loan application result** will be sent to Customer's phone number.

## Requirements

* **JDK 1.8** *Installed*
* **git** *Installed*
* **Apache Maven** *Installed*
* **MongoDB** *Installed and Running*
* ```Optional``` **MongoDB Compass** *Installed and Running* for checking sent sms logs.

## Used Technologies

* IntelliJ IDEA `2021.2`
    * Spring Initializr
        * Java `1.8`
        * Spring Boot as a Maven Project `2.5.5`
        * Dependencies
        * Plugins

## Steps to Setup & Run

### Clone the project to your local

```bash
git clone git@github.com:113-GittiGidiyor-Java-Spring-Bootcamp/gittigidiyor-graduation-project-Quixotic95.git
```

### Go into project folder

```bash
cd .\gittigidiyor-graduation-project-Quixotic95\
```

### Since the project consists of two modules:

#### You need to run 2 bash windows at the same time.

* One for **loan-app (api backend)**
* One for **loan-app-thymeleaf (frontend)**

##### First, run the backend.

```bash
cd .\loan-app\
```

```bash
mvn spring-boot:run
```

*Let the bash(cmd, powershell, git bash, etc.) run spring boot application.*

##### Then, run the frontend.

```bash
cd .\loan-app-thymeleaf\
```

```bash
mvn spring-boot:run
```

*Let the bash(cmd, powershell, git bash, etc.) run spring boot application.*

## Usage

### Now the project is ready to use.

Go to: http://localhost:8081 from your browser.

Feel free to use :)!

### Detailed Usage

* http://localhost:8080/swagger-ui.html to see list of all endpoints & sending requests.

* http://localhost:8080/h2-console/ for embedded H2 relational database that saves _**Customers, Credit Scores, Loan
  Application Results**_ and _**Exception Logs**_ .
    * Driver Class: org.h2.Driver
    * JDBC URL: jdbc:h2:mem:userdb
    * User Name: sa
    * Password:

* MongoDB Compass to see sent _**Sms**_ logging.
    * Hostname: localhost
    * Port: 27017
    * db: sms
    * collection: sms
* If you don't have MongoDB Compass, to see sms db collections just follow:
    * Go to location where mongo DB is installed “C:\Program Files\MongoDB\Server\ {version} \bin” and open PowerShell at that location. And execute these commands:
```batch
.\mongo.exe
```
```batch
use sms
```
```batch
db.sms.find({})
```

### Whenever you want to terminate the application completely

Just simply press Ctrl + C on the all running bash windows.








