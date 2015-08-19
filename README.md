# springmvc-tomcat-spike

Spike to work out what's required to run a Spring MVC application using embedded Tomcat, where the application is configured only with code and annotations, no XML files.

After launch, you should be able to go to <http://localhost:8080/app/hello> and see "Hello World".

The application port can be controlled using the PORT environment variable.

Run the tests using `mvn verify`.
