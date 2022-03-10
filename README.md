Welcome to the Backend of The aplication Browse a book. This part is built with the JAva framework Springboot and database used is PostgreSQl. 
It is part of a full stack application with CRUD functionalities with the goal to allow users to login on the website by using valud creendentials where they will be able to navigate on website and save prefered books for later.

Below are some of the endpoints for our API requests to the database containing three controllers.

BOOKS:

1. search books by title:
   method: GET endpoint:/book/search?title => allow use to search for any book in our db.

2. get all books in our db
   method: GET endpoint:/books    => retrieve all books

3.  method: GET book by id : /books/1 

4. save book in our db
   method: POST endpoint:/books

5. update book 
method: PUT ENDPOINT  /books/{}


USERS:
 
1. get all users
method: GET endpoint /users

2. get user by id
method: GET endpoint /users/{}

3. create user
   method: POST endpoint: /users

4. Login
   method: POST endpoint:/login

4. Update
   method: PUT endpoint:users/{}

5. Delete:
   method: DELETE endpoint:users/{}


SELECTION:

1. Create user selection of books
   method: POST endpoint:/selection
2. Delete user selection of books
   method: DELETE endpoint:/selection


