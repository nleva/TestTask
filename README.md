# TestTask
RainHills calculator

System requirements
-------------------

* Java 8 (JDK 1.8) 
* maven 3.3.9
* Wildfly 10+

Download project
----------------

``` bash
  git clone https://github.com/nleva/TestTask.git
  cd ./TestTask/
```
3 variants of building application
----------------------------------

* (ejbAndEar)Build ejb with websocket and rest wrapped into EAR
``` bash
  mvn install -P ejbAndEar
```
* (web) Build web modules wrapped into WAR
``` bash
  mvn install -P web 
```
* (all) (default) Build all modles ejb + web wrapped into EAR
``` bash
  mvn install
```

Target files
------------

Target ear file location: TestTask/TestTask-ear/target/TestTask-ear-0.0.1-SNAPSHOT.ear

Target war file location (if needed): TestTask/TestTask-web-static/target/TestTask-web-static-0.0.1-SNAPSHOT.war

Application server
------------------

To start application you need wildfly 10+ 
[wildfly download page](http://wildfly.org/downloads/)

Unpack and start aplication server. Command line:

        For Linux:   JBOSS_HOME/bin/standalone.sh
        For Windows: JBOSS_HOME\bin\standalone.bat
Deploy
------
To deploy application use command line 

         JBOSS_HOME/bin/jboss-cli.sh
or web admin console

IDE
---
To use Eclipse, IDEA or Netbeans it`s nessasery to install lombok plugin
* [Eclipse](https://projectlombok.org/setup/eclipse)
* [IDEA](https://projectlombok.org/setup/intellij)
* [Netbeans](https://projectlombok.org/setup/netbeans)

Access the application 
----------------------

The application is running at the following URL: <http://localhost:8080/> by default.

Demo
----

Online demo is avaliable at https://send-to.ru/

APIs
---

websoket api is avaliable at ws(s)://host:port/ws
send json like this
``` json
{"@": "cvr", "levels":[3,2,4,1,2]}
```
to get message with volume of holes
``` json
{"@":"u","r":{"@":"cvr"},"l":[{"@":"vol","value":2}]}
```

rest api is avaliable at http(s)://host:port/rst
only POST requests supported and content type: application/json
request is just the same as websoket's one
``` json
{"@": "cvr", "levels":[3,2,4,1,2]}
```
but structure of response is a bit different 
``` json
[{"@":"vol","value":2}]
```
