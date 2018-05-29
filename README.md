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

