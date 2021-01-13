# interview-babcock-2021-01
Author: Nilesh Sampat

I have created the following two projects for vehicle hire system.
  - rest-api
  - web-client

I have worked as an backend/API developer in majority of the projects I have worked.
I have very basic level of knowledge in using front-end technologies 
I have never used vue.js before and this was my first time I attempted to use this.

I am very much confident about the rest-api solution and about 70-75% happy with the
web-client solution.

I have managed to create a basice vue.js app calling both the rest api.
  - the list of available cars today is displayed on the page
  - calling calculate cost rest api is demonstrated by clicking a button.
    - I could not manage to pass query parameters in the url call to the calculate.
    - This is because of my lack of knowledge in the technology.
    - However, I have hard coded the values and a call is being made 
      so in principle I have demonstrated actual call to both the APIs

## Source Code
// clone the project
  $ git@github.com:sampat-master-dev/interview-2021-01-12-babcock.git

## Running Instructions

  // Run Java Code on 8080
  $ mvn clean install spring-boot:run
  
  // To Run Vue app 
  $ cd web-client/src/main/ui
  $ npm install
  $ npm run build
  $ npm run serve

## Supporting Information, Assumptions
- I have used spring boot 2, jpa, h2 in memory database
- I have relevant unit/integration tests
- I would like to add more tests with different scenario e.g negative tests etc.
- I have some test data added in data.sql

## Further Enhancements
  - Vehicle pricing can be made flexible
     - daily, hourly, 
       - solution: add another column in vehicle_category_price table to indicate 
         price is per day, hour, weekly
     - business pricing
       - this will require further thinking on how flexible we need to devise a solution
         around pricing
         - different pricing from personal customer
         - different pricing or discount on personal pricing
         - business wide standard rate
         - volume based pricing
  - getting list of all cars with customer (if already hired) and pricing information in one call
    - solution: complex database query to fetch information and model to return data
  - new rest endpoints 
    - fetch available vehicles based on make, model, dates required
      solution: changes to repository, service and controller
    - fetch available vehicles based on category
      solution: changes to repository, service and controller
    - fetch cars going to be made available in next couple of days
      solution: changes to repository, service and controller

