# Akka HTTP Example

This project is an Akka HTTP example implementation based on the following blog posts.

https://blog.knoldus.com/introduction-to-akka-http-2/

Please also read the latest Akka HTTP documentation:

https://doc.akka.io/docs/akka-http/current/introduction.html

The example implements three routes including

- a single get route and
- multiple post routes.

The example uses Spray Json and the Akka HTTP Json Support for marshalling and unmarshalling the domain objects to and
from json.

## Curl Requests

### Http Post: Create a Vehicle

`curl -H "Content-Type: application/json" -X POST -d '{"vin":"UIFJ39394","mileage":42}' http://localhost:8080/add-vehicle`

`curl -H "Content-Type: application/json" -X POST -d '{"vin":"UIFJ39394","mileage":53}' http://localhost:8080/update-vehicle`

`curl http://localhost:8080/get-vehicle\?vin=UIFJ39394`

## Spray Json

Spray Json is a Scala JSON implementation. Please check the following GitHub project for more inofmration about Spray
Json.

https://github.com/spray/spray-json

## Akka HTTP Json Support

Akka HTTP has a built-in support for JSON to make the use of Spray Json very convenient. Please check the following page
form more information about the Akka HTTP built-in support for JSON.

https://doc.akka.io/docs/akka-http/current/common/json-support.html