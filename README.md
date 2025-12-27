# Twitter Clone API

Backend REST API for a Twitter-like application built with Spring Boot.

Note: This repository contains the backend implementation of the project.
The frontend was developed separately using React and integrated via REST APIs.

## 🚀 Features
- User registration and login
- Authentication and authorization using Spring Security
- Create, read, update and delete tweets
- Comment, like and retweet functionality
- Custom exception handling with meaningful error responses
- Layered architecture (Controller -> Service -> Repository)
- DTO and Mapper pattern for clean data transfer

## 🧱 Tech Stack
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Maven
- PostgreSQL

## 📁 Project Structure
src/
config
controller
dto
request
response
entity
exception
mapper
repository
security
service

## 📥 Getting Started

### Prerequisites
- Java 17 or higher
- Maven
- PostgreSQL
- Git

### Installation


Clone the repository
git clone https://github.com/ezgiasilsoy/Twitter-Clone-Api.git

Navigate into the project directory
cd Twitter-Clone-Api

Configure database settings in application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/twitter_db
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=${DB_PASSWORD}

Set environment variable
DB_PASSWORD=your_database_password

Run the application
mvn spring-boot:run

🔗 API Endpoints (Overview)

Authentication
POST /register -> Register a new user
POST /login -> Login user

Tweets
POST /tweet -> Create a tweet
GET /tweet/findByUserId -> Get all tweets of logged-in user
GET /tweet/findById -> Get tweet by id
PUT /tweet/{id} -> Update tweet
DELETE /tweet/{id} -> Delete tweet

Comments
POST /comment -> Add comment
PUT /comment/{id} -> Update comment
DELETE /comment/{id} -> Delete comment

Likes
POST /like/{tweetId} -> Like a tweet
DELETE /like/{tweetId} -> Remove like

Retweets
POST /retweet/{tweetId} -> Retweet
DELETE /retweet/{tweetId} -> Remove retweet

🔐 Security
- Spring Security is used for authentication
- User identity is resolved using Principal
- Only authenticated users can perform protected actions
- Users can only update or delete their own resources

⚠️ Notes
- Sensitive data should not be committed to the repository
- Environment variables are used for secrets like database passwords
- target and IDE files are ignored via gitignore

📄 License
This project is for educational purposes.
