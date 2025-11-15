package net.danoun.inventoryservice;

import net.danoun.inventoryservice.entities.Product;
import net.danoun.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            productRepository.save(
                    Product.builder()
                            .id(UUID.randomUUID().toString())
                            .name("laptop")
                            .price(100.0)
                            .quantity(10)
                            .build());
            productRepository.save(
                    Product.builder()
                            .id(UUID.randomUUID().toString())
                            .name("printer")
                            .price(200.0)
                            .quantity(15)
                            .build());
            productRepository.save(
                    Product.builder()
                            .id(UUID.randomUUID().toString())
                            .name("laptop")
                            .price(560.0)
                            .quantity(20)
                            .build());
            productRepository.findAll().forEach(p -> {
                System.out.println(p.toString());
            });
        };
    }

}
