package com.example.restapi.implement.customer;

import com.example.restapi.domain.customer.Customer;
import com.example.restapi.domain.customer.CustomerRepository;
import com.example.restapi.implement.security.JWTAuthResponse;
import com.example.restapi.implement.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;
    @PostMapping("/api/auth/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        //It make authentication instant by inputData,Email and Password
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);

        return  ResponseEntity.ok(new JWTAuthResponse(token));
        //return new ResponseEntity<>("success", HttpStatus.OK);
    }




    @PostMapping("/api/auth/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupDto signupDto){
        // add check for email in a DB
        if(customerRepository.existsByEmail(signupDto.getEmail())){
            return new ResponseEntity<>("Email is already taken",HttpStatus.BAD_REQUEST);
        }

        // create customer object

        Customer customer = new Customer();
        customer.setEmail(signupDto.getEmail());
        customer.setPassword(passwordEncoder.encode(signupDto.getPassword()));

        customerRepository.save(customer);

        return new ResponseEntity<>("Customer registered successfully",HttpStatus.OK);
    }

}
