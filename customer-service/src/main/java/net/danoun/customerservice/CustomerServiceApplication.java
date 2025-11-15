package net.danoun.customerservice;

import net.danoun.customerservice.config.CustomerConfigParams;
import net.danoun.customerservice.entities.Customer;
import net.danoun.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(Customer.builder()
                    .name("danoun")
                    .email("danoun@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("adam")
                    .email("adam@gmail.com")
                    .build());
            customerRepository.save(Customer.builder()
                    .name("reda")
                    .email("reda@gmail.com")
                    .build());
            customerRepository.findAll().forEach(c ->{
                System.out.println("=============================");
                System.out.println(c.getId());
                System.out.println(c.getName());
                System.out.println(c.getEmail());
            });
        };
    }

}
