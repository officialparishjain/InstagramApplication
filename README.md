# Project Name
Instagram Application

# Frameworks and Language Used
**Spring Boot** 2.1.0
**Java** 20.0
**Maven** 3.8.1

# Data Flow
The following functions are used in the data flow of this project:

# Mappings

The following are the mappings available in the Instagram Application controller:

**CREATE POST:**

  HTTP Method: POST
  URL: /posts
  Request Body: InstaPost object
  Response: ResponseEntity<InstaPost>

**GET ALL POSTS:**

  HTTP Method: GET
  URL: /posts/all/{email}/{token}
  Path Variables: email and token
  Response: ResponseEntity<List<InstaPost>>
  
**INSTA USER SIGN UP:**

  HTTP Method: POST
  URL: /signup
  Request Body: SignUpInput object
  Response: ResponseEntity<SignUpOutput>
  
**INSTA USER SIGN IN:**

  HTTP Method: POST
  URL: /signin
  Request Body: SignInInput object
  Response: ResponseEntity<SignInOutput>
  
**UPDATE USER DETAIL:**

  HTTP Method: PUT
  URL: /update/mobile/{mobile}/{email}/{token}
  Path Variables: mobile, email, and token
  Response: ResponseEntity<String>

## Models

The InstaPost class represents a post in an Instagram-like application. It contains fields for the post ID, creation and update timestamps, and the post data. Additionally, it has a Many-to-One relationship with InstaUser class, which represents the user who created the post.

The InstaUser class represents a user in the application. It contains fields for user ID, first name, last name, age, email, phone number, and password.

The AuthenticationToken class represents an authentication token used to authenticate users in the application. It contains fields for the token ID, token value, and creation date. Additionally, it has a One-to-One relationship with InstaUser class, indicating that each token can be associated with only one user.

Overall, these classes represent a simple data model for an Instagram-like application with basic user authentication functionality.


## Controller


The InstaPostController class handles HTTP requests related to Instagram-like posts. It includes methods for creating a new post and retrieving all posts. The createPost() method creates a new post by calling the InstaPostService and returns the created post in the HTTP response. The getAllPost() method retrieves all posts for a given user email and authentication token by calling the InstaPostService and AuthenticationService and returns the list of posts in the HTTP response.

The InstaUserController class handles HTTP requests related to Instagram-like users. It includes methods for user signup, user sign-in, and updating user details. The signup() method creates a new user by calling the InstaUserService and returns the user details in the HTTP response. The signin() method authenticates the user by calling the InstaUserService and returns the authentication token in the HTTP response. The updateUserMobileByEmail() method updates the user's mobile number for a given user email and authentication token by calling the InstaUserService and AuthenticationService and returns the HTTP response indicating whether the update was successful.


## Services

The InstaUser class contains attributes such as first name, last name, age, email, phone number, and password, which are used to register and authenticate users. The AuthenticationToken class represents a user token that is generated upon successful authentication. The AuthenticationService class provides methods to save and retrieve user tokens from the database.

The InstaPostService class is used to manage posts made by users. It contains methods to create and retrieve user posts. The createPost method is used to create a new post, and the fetchAllPost method is used to retrieve all posts by a particular user.

The InstaUserService class provides methods for user registration and authentication. The signUpUser method is used to register a new user, while the signInUser method is used to authenticate a user. The generateEncryptedPassword method is used to encrypt user passwords using the MD5 algorithm. Additionally, the getInstaUserById method is used to retrieve a user by their ID, and the findInstaUserByUserEmail method is used to retrieve a user by their email address.

_**Repository:**_ The repository layer is responsible for interacting with the database. It uses Spring Data JPA to perform CRUD (create, read, update, delete) operations on entities.


# Database Structure Used
I have used MySql as Database.

# Project Summary

This is an ecommerce application that allow user to place order and having differnt fetures also



