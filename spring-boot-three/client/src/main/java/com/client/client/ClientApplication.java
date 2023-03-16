package com.client.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener(CustomerClient cc) {
        return event -> cc.all().subscribe(System.out::println);
    }

    @Bean
    CustomerClient customerClient(WebClient.Builder builder) {
        return HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(
                        builder.baseUrl("http://localhost:8080").build()
                ))
                .build()
                .createClient(CustomerClient.class);
    }

    /**
     * It enables the route http://localhost:9090/proxy which will forward to the endpoint
     * http://localhost:8080/customers, calling directly the server module
     *
     * @param rlb RouteLocatorBuilder
     * @return an instance of RouteLocator from spring cloud gateway that configures the behavior described above
     */
    @Bean
    RouteLocator gateway(RouteLocatorBuilder rlb) {
        return rlb
                .routes()
                .route(rs -> rs
                        .path("/proxy")
                        .filters(fs -> fs
                                .setPath("/customers")
                                .addResponseHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                        )
                        .uri("http://localhost:8080/"))
                .build();
    }

}

interface CustomerClient {

    @GetExchange("/customers")
    Flux<Customer> all();

    @GetExchange("/customers/{name}")
    Flux<Customer> byName(String name);
}

// no more lombok!
record Customer(Integer id, String name) {

}