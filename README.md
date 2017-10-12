# 14-practice-ssl-withouttrust
ssl tls springboot resttemplate restassured

This task means there is no trusted cert in the client side. How client side call https

Notice: client-auth: need is NOT enabled in this example

# Try it
https://localhost:8443/user
http://localhost:8080/door?uri=https://localhost:8443/user
http://localhost:8080/restassure?uri=https://localhost:8443/user
