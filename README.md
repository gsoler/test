# Content:
- [test](https://github.com/gsoler/test#readme#test)
- [test-client](https://github.com/gsoler/test#readme#test-client)

# test

This part is the server application developed in Java and using Spring Boot 2.7.0. To run the server application you have to run StartApplication.java.
The application imports next dependencies:
- Spring Boot Starter Web to create the API.
- Spring Boot Starter Validation to validate the request of the API.
- Spring Boot Starter Test to create tests.
- Spring Boot Security to allow Cross-Domain connections from client. 
- Gson to parse the read JSON.
- Lombok to make objects cleaner avoiding getters and setters functions declarations.

IMPORTANT: to make project compiles with Lombok Getters and Setters, you need to install a plugin in Eclipse or STS from https://projectlombok.org/p2

The application is based in 3 layers:
- Persistance: The service who gets the JSON is declared as Repository because is the service who recovers the data of the application. The funcion is cached to avoid call the external server each time and depends the response time of an external server.
- Business: The service who manages the request and returns the required processed data to the controller.
- Presentation: The service ho declares the API and checks the input request data.

To execute the tests you have to run TestingApplication.java, it will check different cases in the API.


# test-client

This part is the web site developed in React. To start the application first of all you have to download all dependencies using npm install, then you have to run npm start to run it.
The application has two main parts:
- App.js with his App.css, has the main part of the web site. The filters and the table. Also have the error page if can't connect to the server or data is empty.
- Pagination.js with his Pagination.css, has the pagination part of the table, and implements the management of pages shown in the paginator and the behaviour of itself.
