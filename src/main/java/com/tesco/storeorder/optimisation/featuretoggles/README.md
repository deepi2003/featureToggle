# About
This is a custom implementation of feature flags (or feature toggles) that can be added as a dependency in other module which need the feature toggle functionality.

# Adding dependency
## module dependency 
```groovy
implementation project(':feature-toggle')
```
## application.yml
```yaml
featureToggles:
    someFeature: true
    someOtherFeature: false
```

## component scan
featureToggle is at `org.deepti.featuretoggle`. In case springbootapplication is at `org.deepti.<anotherPackage>`, you need to add `@ComponentScan(basePackages = {"org.deepti"})` to your springboot application like so:
```java
@SpringBootApplication
@ComponentScan(basePackages = {"org.deepti"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```
In case springbootapplication is at `org.deepti` level, then there is no need to add `@ComponentScan(basePackages = {"org.deepti"})`

# Exposed endpoint(s)
``` http request
GET http://<applicationhost>:<applicationport>/v1/features

HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 20 Aug 2020 08:33:15 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "someFeature": true,
  "someOtherFeature": false
}
```

