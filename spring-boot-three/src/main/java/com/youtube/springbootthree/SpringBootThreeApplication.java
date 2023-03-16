package com.youtube.springbootthree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

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

@Service
class CustomerService {
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Customer> customerRowMapper =
            (rs, rowNum) -> new Customer(rs.getInt("id"), rs.getString("name"));

    CustomerService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    Customer byId(Integer id) {
        return this.jdbcTemplate.queryForObject("select * from customers where id=?",
                this.customerRowMapper, id);
    }

    Collection<Customer> all() {
        return this.jdbcTemplate.query("select * from customers",
                this.customerRowMapper);
    }
}

// no more lombok!
record Customer(Integer id, String name) {

}