package com.example.restapi.implement.customer;

import com.example.restapi.domain.customer.CustomerRepository;
import com.example.restapi.domain.customer.CustomerService;
import com.example.restapi.implement.security.JWTAuthResponse;
import com.example.restapi.implement.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:3000")
@RestController
public class CustomerRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private JwtTokenProvider tokenProvider;



    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @PostMapping("/api/auth/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
        //It make authentication instant by inputData,Email and Password
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token form tokenProvider
        String token = tokenProvider.generateToken(authentication);

        return  ResponseEntity.ok(new JWTAuthResponse(token));
    }




    @PostMapping("/api/auth/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequestBody signupRequestBody){

        if(customerRepository.existsByEmail(signupRequestBody.getEmail())){
            return new ResponseEntity<>("すでに登録されているEmailです。",HttpStatus.BAD_REQUEST);
        }
        customerService.registerCustomer(signupRequestBody.getEmail(), signupRequestBody.getPassword());


        return new ResponseEntity<>("Customer registered successfully",HttpStatus.OK);
    }

    @PutMapping("/api/auth/verify")
    public ResponseEntity<?> verifyUser(  @RequestBody VerifyRequestBody verifyRequestBody ){
        if(!customerService.verify(verifyRequestBody.verifyCode)){
            return new ResponseEntity<>("認証コードが正しくありません",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("認証コードを承認しました。",HttpStatus.OK);
    }

}
