package com.gmail.arthurstrokov.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.out;

@SpringBootApplication
public class MongodbApplication implements CommandLineRunner {

    private final CustomerRepository repository;

    @Autowired
    public MongodbApplication(CustomerRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MongodbApplication.class, args);
    }

    @Override
    public void run(String... args) {
        repository.deleteAll();

        // save a couple of customers
        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));

        // fetch all customers
        out.println("Customers found with findAll():");
        out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
            out.println(customer);
        }
        out.println();

        // fetch an individual customer
        out.println("Customer found with findByFirstName('Alice'):");
        out.println("--------------------------------");
        out.println(repository.findByFirstName("Alice"));

        out.println("Customers found with findByLastName('Smith'):");
        out.println("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            out.println(customer);
        }
    }
}