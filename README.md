# REST System Stat Broadcaster

This project is a micro-service which uses the [Spark Framework](http://sparkjava.com) and the [Sigar API](http://sigar.hyperic.com) to broadcast system information (CPU load, Memory Usage, Network Usage) through a REST API.

## Uses

For people or organizations who want to monitor or keep track of server load, deploying instances of this application can let them easily create a REST api that they can then listen to in other applications.

### Deployment

The application is built as a jar. It has a built-in Jetty server and all required files for the Sigar API, so it *should* work on all platforms specified [here](https://support.hyperic.com/display/SIGAR/Home#Home-binaries).

### API Usage

//Todo

## Dependencies

- [Spark Framework](http://sparkjava.com)
- [Sigar API](http://sigar.hyeric.com)
