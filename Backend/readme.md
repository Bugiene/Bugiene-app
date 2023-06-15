# Fruit Freshness Detection API

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

API Documentation for Fruit Freshness Detection. This API allows users to register, login, perform fruit freshness detection, and view detection history.

## Table of Contents

- [API Documentation](#api-documentation)
  - [Endpoints](#endpoints)
    - [Register](#register)
    - [Login](#login)
    - [Get User](#get-history)
    - [Post Detection](#post-detection)
    - [Get History](#get-history)
- [Deployment to Cloud Run](#deployment-to-cloud-run)
- [Installation and Project Setup](#installation-and-project-setup)
- [Usage Instructions](#usage-instructions)
- [Contributor Acknowledgment](#contributor-acknowledgment)
- [License](#license)

## Endpoints

### Register

- Endpoint: `/register`
- Method: `POST`
- Description: Register a new user.
- Request Body:
  - `username` (string): User's username.
  - `email` (string): User's email address.
  - `password` (string): User's password.
- Response:
  - Success:
    - Status Code: `200`
    - Body:
      ```
      {
        "status": "success",
        "message": "User Created"
      }
      ```

  - Error:
    - Status Code: `400` if invalid password or missing fields, `409` if email already exists, `500` if internal server error.
    - Body:

      ```
      {
        "error": "Error message"
      }
      ```

### Login

- Endpoint: `/login`
- Method: `POST`
- Description: Authenticate user and generate access token.
- Request Body:
  - `email` (string): User's email address.
  - `password` (string): User's password.
- Response:
  - Success:
    - Status Code: `200`
    - Body:

      ```
      {
        "status": "success",
        "message": "Login successful",
        "data": {
          "userId": "user_id",
          "username": "username",
          "email": "email",
          "accessToken": "access_token"
        }
      }
      ```

  - Error:
    - Status Code: `401` if invalid email or password, `500` if internal server error.
    - Body:

      ```
      {
        "error": "error message"
      }
      ```

### Get User

- Endpoint: `/getUser`
- Method: `GET`
- Description: Get user profile data.
- Request Header:
  - `Authorization`: Bearer token.
- Response:
  - Success:
    - Status Code: `200`
    - Body:

      ```
      {
        "status": "success",
        "message": "User details fetched successfully",
        "data": {
          "userId": "user_id",
          "username": "username",
          "email": "email"
        }
      }
      ```

  - Error:
    - Status Code: `401` if access token not provided, `403` if invalid access token, `404` if user not found, `500` if internal server error.
    - Body:

      ```
      {
        "error": "Error message"
      }
      ```

### Post Detection

- Endpoint: `/detection`
- Method: `POST`
- Description: Perform fruit freshness detection.
- Request Header:
  - `Authorization`: Bearer token.
- Request Body:
  - `image` (file): Image file to be analyzed.
- Response:
  - Success:
    - Status Code: `200`
    - Body:

      ```
      {
        "status": "success",
        "message": "Fruit detection successful",
        "result": {
          "detectId": "detection_id",
          "freshness": true,
          "accuracy": 0.9
        }
      }
      ```

  - Error:
    - Status Code: `401` if access token not provided, `403` if invalid access token, `400` if invalid image file.
    - Body:

      ```
      {
        "error": "Error message"
      }
      ```

### Get History

- Endpoint: `/history`
- Method: `GET`
- Description: Get detection history for the authenticated user.
- Request Header:
  - `Authorization`: Bearer token.
- Response:
  - Success:
    - Status Code: `200`
    - Body:

      ```
      {
        "status": "success",
        "message": "Detection history fetched successfully",
        "data": [
          {
            "id": "history_id",
            "date": "YYYY-MM-DD",
            "result": "fresh/rotten"
          }
        ]
      }
      ```

  - Error:
    - Status Code: `401` if access token not provided, `403` if invalid access token, `404` if user not found, `500` if internal server error.
    - Body:

      ```
      {
        "error": "Error message"
      }
      ```

## Deployment to Cloud Run

To deploy this project to Cloud Run, follow these steps:

1. Create a new project in the Google Cloud Console.
2. Enable the Cloud Run API.
3. Build and push the container image to Google Container Registry.
4. Deploy the container image to Cloud Run.

## Installation and Project Setup

To install and set up this project locally, follow these steps:

1. Clone the repository: `git clone https://github.com/Bugiene`
2. Install the dependencies: `npm install`
3. Set up the environment variables:
   - `DATABASE_URL`: URL for the MySQL database
   - `JWT_SECRET`: Secret key for JWT token generation
4. Run the database migrations: `npm run migrate`
5. Start the server: `npm start`

## Usage Instructions

To use this project, follow these instructions:

1. Register a new user using the `/register` endpoint.
2. Log in using the `/login` endpoint to obtain an access token.
3. Use the access token in the `Authorization` header for authenticated endpoints.
4. Perform fruit freshness detection by uploading an image using the `/PostDetection` endpoint.
5. View detection history using the `/getHistory` and `/getHistory/:UserId` endpoints.

## Contributor Acknowledgment

We would like to acknowledge the following contributors for their valuable contributions to this project:

- Muhammad Faiz Al-Azmi (GitHub: Pokrek2709)
- Nazwa Anandia (GitHub: nazwanandia)

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).
