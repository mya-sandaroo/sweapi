# sweapi
The mini project for team lunch collection 

# Introduction
The application implements backend APIs using Spring boot, spring JPA and H2 as the data source.
This documentation provides a guide on setting up and running on local.
### Environment Setup Guides
1. Java Development Kit (JDK) 8 or later
2. Maven
3. Your preferred IDE (Eclipse, IntelliJ, etc.)

### Project Structure
src
|-- main
|   |-- java
|       |-- com.swe
|           |-- controller
|           |-- entity
|           |-- repository
|           |-- service
|           |-- Application.java
|-- resources
|   |-- application.properties
|   |-- data.sql
|   |-- schema.sql

### Running the Application
mvn clean install
mvn spring-boot:run

### Application URL in local
http://localhost:8080 

### H2 database URL and login information
http://localhost:8080/h2-console/
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Password: 1

### Table and data Structure
1. User table [SWEUser]
2. Meeting 
3. Attendee 
4. Suggestion 

Once the applcation started, the user information in data.sql file will be inserted into user table automatically.

### API information
1. Get all user IDs API 
Usage: Implement this API for fetching all user Ids information to be populated in attendee's dropdown in UI.
Method: GET
Endpoint: http://localhost:8080/sweapi/findAllUsers
No parameter or body required.
Response:
   [
   "summer",
   "steffi",
   "lilly",
   "helen",
   "laura",
   "kate",
   "mike"
   ]

2. Login API
   Usage: UI application login.
   Method: POST
   Endpoint: http://localhost:8080/sweapi/login
   Content-Type: application/json
   Request:
   {
   "loginId": "summer",
   "loginPassword": "123"
   }
   Success Response:
   {
   "id": 1,
   "loginId": "summer",
   "loginName": "Summer",
   "loginPassword": "123"
   }
   Error Response:
   <Response body is empty>
   
3. Fetch all meetings for applicable user
   Method: POST
   Endpoint: http://localhost:8080/sweapi/findAllMeetings
   Content-Type: application/json
   Request:
   {
   "loginId": "summer",
   "loginPassword": "123"
   }
   Success Response:
   [{}] - list of data
   Error Response:
   <body><h1>HTTP Status 400 – Bad Request</h1></body>

4. Save meeting API 
   Method: POST
   Endpoint: http://localhost:8080/sweapi/saveMeeting/{{loginId}}
   Content-Type: application/json
   Request:
   Meeting object
   Success Response:
   [{}] - list of data

5. Delete meeting API - It will be deleted permentally.
   Method: POST
   Endpoint: http://localhost:8080/sweapi/deleteMeeting/{{loginId}}
   Content-Type: application/json
   Request:
   Meeting object
   Success Response:
   [{}] - list of data

6. Join meeting API 
   Method: POST
   Endpoint: http://localhost:8080/sweapi/joinMeeting/{{loginId}}
   Content-Type: application/json
   Request:
   Meeting object
   Success Response:
   [{}] - list of data

7. Close meeting API
   Method: POST
   Endpoint: http://localhost:8080/sweapi/closeMeeting/{{loginId}}
   Content-Type: application/json
   Request:
   Meeting object
   Success Response:
   [{}] - list of data
