package id.co.ogya.rest.simple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.ogya.rest.simple.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public Customer findByEmail(String email);
}
