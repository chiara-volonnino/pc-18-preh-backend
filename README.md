# Pre-Hospital
## A Pervasive Computing Project

https://www.unibo.it/en/teaching/course-unit-catalogue/course-unit/2018/412647

## Use Requirements

In order to use the system all that is needed is the Android application, found at the following link:  
https://github.com/loveeclipse/pc-18-preh-frontend/releases  

The services found on this repository are designed to be uploaded on Heroku, and must be activated in order for the system to work.
The services can also be run locally, executed by default on *localhost* on the following ports: 

```
  discovery-service on port 5150 
  events on port 10000
  missions on port 10001
  patients on port 10002

```
In order to run these services their jars are found in the release tab.
Before running them, the environment variable **MONGO_CONNECTION_STRING** must be added with the following format:  
mongodb://{user}:{password}@{host}:{port}/{database}

```
  java -jar pc-18-preh-backend-1.0-discovery-service.jar
  java -jar pc-18-preh-backend-1.0-events.jar
  java -jar pc-18-preh-backend-1.0-missions.jar
  java -jar pc-18-preh-backend-1.0-patients.jar  

```
Note: run the discovery service **before** the others.

## Releases and Project Report
It is possible to download the source code and all release executable jars, along with the project report
at the following page :  
https://github.com/loveeclipse/pc-18-preh-backend/releases  

## API Documentation
All the API documentation, concenring the available REST calls can be viewed at the following link:  
https://app.swaggerhub.com/apis-docs/candoz/pre-hospital/1  


## Team members
Nicola Atti (nicola.atti@studio.unibo.it)  
Marco Canducci (marco.canducci@studio.unibo.it)  
Chiara Volonnino (chiara.volonnino@studio.unibo.it)  
Daniele Schiavi (daniele.schiavi@studio.unibo.it)         
