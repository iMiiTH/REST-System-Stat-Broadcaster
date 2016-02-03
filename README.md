# REST System Stat Broadcaster

This project is a micro-service which uses the [Spark Framework](http://sparkjava.com) and the [Sigar API](http://sigar.hyperic.com) to broadcast system information (CPU load, Memory Usage, Network Usage) through a REST API.

## Uses

For people or organizations who want to monitor or keep track of server load, deploying instances of this application can let them easily create a REST api that they can then listen to in other applications.

### Deployment

The application is built as a fat jar, with all dependencies included. When executing the jar, a lib directory will be made in the directory which is where the application will write the necessary library files required to interact with the system.

### API Usage

Data can be fetched via simple GET requests. The data returned is in XML format. A full reference of routes can be found in the wiki, eventually.

## Dependencies

- [Spark Framework](http://sparkjava.com)
- [Sigar API](http://sigar.hyeric.com)
