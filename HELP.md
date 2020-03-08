# I know your name!
A DDD practical example first version
### Celebrity Challenge
Inside a team exist a celebrity, the application will discover a celebrity using a vote system

### How Run the application?
Celebrity Challenge is a Spring Boot Application,

Run the following command in a terminal window (in the complete) directory:

mvn package

mvn spring-boot:run

### Steps
* [See the team list and don't forget the person Id](http://localhost:8080/team)
* [Vote for the Id Person that you know his name](http://localhost:8080/votes)
Please use a POST tool for use a Vote API http://localhost:8080/votes: 
request body example {
                       "id": "",
                       "id_person": "101"
                     }
* [See the most voted person](http://localhost:8080/celebrityInTeam)


### References
Some references for this example:

* [Hexagonal Architecture](https://codely.tv/blog/screencasts/arquitectura-hexagonal-ddd/)
* [Domain Model](https://dev.to/colaru/creating-a-domain-model-rapidly-with-java-and-spring-boot-i85)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

