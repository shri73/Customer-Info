# Customer-Info
Whenever Customer updates its address in profile, a email is sent to the customer notifying that address has been changed.

Stack
Netflix OSS (Eureka), Kafka, SpringBoot, H2 DB, Junit 5, Swagger2.

Eureka Server (Service registry) : All services will register themselves on this registry

Customer service: This service will create a new customer and allow them to update their address (integration with Kafka).
                  Whenever customer updates their address, the microservice will send a message “CUSTOMER_UPDATED” to the message broker (Kafka).

Email service: This service will send a confirmation to the specified email on that customer, when they update their address (using integration with Kafka)
