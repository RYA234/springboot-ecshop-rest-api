package com.example.restapi.domain.customer;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE c.email = ?1")
    public Customer findByEmail(String email);
    boolean existsByEmail(String email);
    Customer save(Customer customer);
    @Query("SELECT c FROM Customer c WHERE c.verificationCode = ?1")
    Customer  findByVerificationCode(String verificationCode);

    @Query("UPDATE Customer c SET  c.verificationCode = null,c.enabled= true  WHERE c.id = ?1")
    @Modifying(clearAutomatically = true)
    public void enabled(Integer id);
}
