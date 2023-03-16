package com.youtube.springbootthree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@SpringBootApplication
public class SpringBootThreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootThreeApplication.class, args);
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener(CustomerService cs) {
        return event -> cs.all().forEach(System.out::println);
    }
}

@Controller
@ResponseBody
class CustomerHttpController {

    private final CustomerService customerService;

    CustomerHttpController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    Collection<Customer> all() {
        return this.customerService.all();
    }

    @GetMapping("/customers/{name}")
    Customer byName(@PathVariable String name) {
        Assert.state(Character.isUpperCase(name.charAt(0)), "the name must start with a capital letter!");
        return this.customerService.byName(name);
    }
}

@ControllerAdvice
class ErrorHandlingControllerAdvice {

    @ExceptionHandler
    public ProblemDetail handleIllegalStateException(IllegalStateException illegalStateException) {
        var pd = ProblemDetail.forStatus(HttpStatusCode.valueOf(422));
        pd.setDetail(illegalStateException.getMessage());
        return pd;
    }
}

@Service
class CustomerService {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Customer> customerRowMapper =
            (rs, rowNum) -> new Customer(rs.getInt("id"), rs.getString("name"));

    CustomerService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    Customer byName(String name) {
        return this.jdbcTemplate.queryForObject("select * from customers where name=?",
                this.customerRowMapper, name);
    }

    Collection<Customer> all() {
        return this.jdbcTemplate.query("select * from customers",
                this.customerRowMapper);
    }
}

// no more lombok!
record Customer(Integer id, String name) {

}