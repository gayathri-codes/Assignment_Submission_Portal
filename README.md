# Assignment Submission Portal

This project is a backend system for an assignment submission portal. It allows users to register and submit assignments, while admins can view, accept, or reject these submissions. The system integrates OAuth2 authentication, allowing users to log in through OAuth providers like Google. The backend is built with Spring Boot and uses MongoDB as the database.

## Features
- **User Functionality**:
  - Register and log in
  - Upload assignments
  - Fetch a list of available admins

- **Admin Functionality**:
  - Register and log in
  - View assignments assigned to them
  - Accept or reject assignments

## Technologies Used
- **Spring Boot** - Framework for Java backend development
- **Spring Security** - Provides security configurations, including OAuth2
- **MongoDB** - NoSQL database
- **OAuth2** - Authentication mechanism with providers like Google
- **Swagger** - API documentation tool

## Prerequisites
- **Java 17** - Ensure Java Development Kit 17 is installed on your system.
- **MongoDB** - Install MongoDB and have it running locally on the default port (27017).
- **Google OAuth2 Client ID and Secret** - You will need to create a Google project and set up OAuth credentials.
  
  To set up OAuth credentials, visit the [Google Developer Console](https://console.developers.google.com/):
  1. Create a new project.
  2. Navigate to **APIs & Services** > **Credentials**.
  3. Create an **OAuth2 Client ID**.
  4. Configure the **Redirect URI** as `http://localhost:8080/login/oauth2/code/google`.

## Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/gayathri-codes/Assignment_Submission_Portal.git
cd growthx_project
```

### 2. Set Up the Application Properties
In the `src/main/resources/application.yml` file or `src/main/resources/application.properties`, configure the MongoDB connection and OAuth2 properties. Replace the placeholders with your actual MongoDB details and Google OAuth credentials.

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/assignment_portal
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: YOUR_GOOGLE_CLIENT_ID
            client-secret: YOUR_GOOGLE_CLIENT_SECRET
            redirect-uri: "{baseUrl}/login/oauth2/code/{registrationId}"
            scope: profile, email
            client-name: Google
        provider:
          google:
            authorization-uri: https://accounts.google.com/o/oauth2/auth
            token-uri: https://oauth2.googleapis.com/token
            user-info-uri: https://www.googleapis.com/oauth2/v3/userinfo
```
or  
```properties
spring.application.name=GrowthX_Project
#server.port=8080
spring.data.mongodb.uri=mongodb://localhost:27017/growthx_assignment_portal


# OAuth2 Configuration for Google
spring.security.oauth2.client.registration.google.client-id=#YOUR_GOOGLE_CLIENT_ID

spring.security.oauth2.client.registration.google.client-secret=#YOUR_GOOGLE_CLIENT_SECRET

spring.security.oauth2.client.registration.google.redirect-uri=http://localhost:8080/login/oauth2/code/google

spring.security.oauth2.client.registration.google.scope=email,profile
spring.security.oauth2.client.registration.google.authorization-grant-type=authorization_code
spring.security.oauth2.client.registration.google.client-name=Google

# Application-specific configurations
spring.security.oauth2.client.provider.google.authorization-uri=https://accounts.google.com/o/oauth2/auth
spring.security.oauth2.client.provider.google.token-uri=https://oauth2.googleapis.com/token
spring.security.oauth2.client.provider.google.user-info-uri=https://www.googleapis.com/oauth2/v3/userinfo
spring.security.oauth2.client.provider.google.user-name-attribute=sub

```
### 3. Build the Project
Navigate to the project directory and run the following command to build the project with Maven:
```bash
./mvnw clean install
```

### 4. Run the Application
Start the Spring Boot application with the following command:
```bash
./mvnw spring-boot:run
```

The application will start on [http://localhost:8080](http://localhost:8080).

### 5. Access API Documentation
Once the application is running, you can access the Swagger documentation to test the API:
- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Endpoints Overview

#### User Endpoints
- **POST /user/register**: Register a new user.
- **POST /user/upload**: Upload an assignment.
- **GET /admins**: Fetch all available admins.

#### Admin Endpoints
- **POST /admin/register**: Register a new admin.
- **GET /admin/assignments**: View all assignments tagged to the admin.
- **POST /admin/assignments/{id}/accept**: Accept an assignment.
- **POST /admin/assignments/{id}/reject**: Reject an assignment.

### Additional Configuration (Optional)
#### Running MongoDB on a Different Port
If your MongoDB instance is on a different port or requires authentication, update the `mongodb.uri` in the `application.yml` file accordingly.

