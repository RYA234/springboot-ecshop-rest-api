package com.example.restapi.implement.security;


import com.example.restapi.domain.customer.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
/**
 * @brief:  UserDetail for Customer Entity.
 *
 * @description CustomerUserDetails is customized in fit this ECshop project.
 *              Username in default UserDetail change to email in CustomerUserDetails.
 *              Password in CustomerUserDetail is same as default UserDetail(not change).
 *
 * @Auther RYA234
 */
public class CustomerUserDetails implements UserDetails {
    private Customer customer;

    public CustomerUserDetails(Customer customer){
        this.customer = customer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return customer.getPassword();
    }

    @Override
    public String getUsername() {
        return customer.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
