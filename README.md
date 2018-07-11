# MyRetail REST API

This RESTful service provides two APIs
1) Get the Product Details
2) Update price for specific product.

This application connects to query a mongodb datastore which has product information. The connection parameters for mongo db can be found in appliation.properties file in src/main/resources directory.

<b>ProductController.class</b> is the rest controller that has the api methods exposed as rest resources.

<b>ProductManagerService.class</b> is the Service class to call internal source and update the product information.

<b>ProductRepository.class</b> is MongoRepository to fetch and update the product information.

#Build Details:
Import the project in to Eclipse, Intellij or any IDE as a maven project.
Run: Maven clean install
Run: java -jar MyRetail-0.0.1-SNAPSHOT.war

An embedded tomcat server is ready to receive the request either from browser or command line.


#Test the API from Command Line:
To GET:
curl -i -H "Accept: application/json" localhost:8080/product/13860428

To UPDATE:

curl -X POST -d "{ \"value\" : 12.23, \"currency_code\" : \"USD\" }" http://localhost:8080/product/13860428


<b>Test using POSTman:</b>

To Get: 
http://localhost:8080/product/13860428

To Update:
http://localhost:8080/product/13860428

JSON Body
 {
        "value": 8.12,
        "currency_code": "INR"
 }
 
#Test Cases:<br>
Run: mvn test
