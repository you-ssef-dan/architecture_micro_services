package net.danoun.customerservice.repository;

import net.danoun.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface CustomerRepository
        extends JpaRepository<Customer,Long> {
}
