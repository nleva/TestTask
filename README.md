# TestTask
RainHills calculator

Download project
----------------

``` bash
  git clone https://github.com/nleva/TestTask.git
  cd ./TestTask/
```
3 variants of building application
----------------------------------

* (default) Build ejb with websocket and rest wrapped into EAR
``` bash
  mvn install 
```
* (web) Build web modules wrapped into WAR
``` bash
  mvn install -P web 
```
* (all) Build all modles ejb + web wrapped into EAR
``` bash
  mvn install -P all 
```

Target files
------------

Target ear file location: TestTask/TestTask-ear/target/TestTask-ear-0.0.1-SNAPSHOT.ear

Target war file location (if needed): TestTask/TestTask-web-static/target/TestTask-web-static-0.0.1-SNAPSHOT.war

Application server
------------------

To start application you need wildfly 10+ 
[wildfly download page](http://wildfly.org/downloads/)

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
