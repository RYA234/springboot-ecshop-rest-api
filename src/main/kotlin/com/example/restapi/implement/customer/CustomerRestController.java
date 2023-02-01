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

    @Autowired
    private MailSender mailSender;

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

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

        if(customerRepository.existsByEmail(signupDto.getEmail())){
            return new ResponseEntity<>("すでに登録されているEmailです。",HttpStatus.BAD_REQUEST);
        }
        customerService.registerCustomer(signupDto.getEmail(),signupDto.getPassword());
        return new ResponseEntity<>("Customer registered successfully",HttpStatus.OK);
    }

    @PutMapping("/api/auth/verify")
    public ResponseEntity<?> verifyUser(  @RequestBody VerifyDto verifyDto){
        if(!customerService.verify(verifyDto.verifyCode)){
            return new ResponseEntity<>("認証コードが正しくありません",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("認証コードを承認しました。",HttpStatus.OK);
    }
    @PostMapping(value = "/api/auth/sendmail")
    @ResponseBody
    public void sendTestMail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("j264663@kanatech-west.ac.jp");// To
        msg.setFrom("mryuabc@gmail.com");
        String insertMessage = "Test from Spring Mail" + LINE_SEPARATOR;
        insertMessage += "Test from Spring Mail" + LINE_SEPARATOR;

        msg.setSubject("Test from Spring Mail");// Set Title
        msg.setText(insertMessage);// Set Message
        mailSender.send(msg);

    }
}
