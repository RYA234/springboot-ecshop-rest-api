package com.example.restapi.security.jwt;


import com.example.restapi.implement.security.JwtTokenProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
// todo test code is yet
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class JwtTokenProviderTest {

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration)
            throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
   public  JwtTokenProvider jwtTokenProvider(){
        return new JwtTokenProvider();
    };


    @Test
    @DisplayName("dasasdgeddddassat")
    public void EqualgenerateToken(){
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("test@gmail.com","password"));
//       String token = tokenProvider.generateToken(authentication);
        System.out.print("teata");
        //    jwtTokenProvider.generateToken();

    }
}
