package com.example.restapi.implement.customer;

import com.example.restapi.domain.customer.Customer;
import com.example.restapi.domain.customer.CustomerRepository;
import com.example.restapi.domain.customer.CustomerService;
import com.example.restapi.implement.security.JwtTokenProvider;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
@Transactional
public class CustomerServiceImplement implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public LoginDto findByEmail() {
        return null;
    }

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    ResourceLoader resourceLoader;

    // application.propertyから値を取得
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${frontend.URL}")
    private String frontendURL;
    @Override
    public Customer registerCustomer(String email, String password) {
        String randomCode = RandomString.make(64);
        Customer newCustomer = new Customer(email, passwordEncoder.encode(password), randomCode, false);
        customerRepository.save(newCustomer);
        sendVerifyMail(email,randomCode);

        return newCustomer;
    }
    //
    @Override
    public boolean verify(String verificationCode) {
        Customer customer = customerRepository.findByVerificationCode(verificationCode);
        if (customer == null || customer.isEnabled()) {
            return false;
        } else {
            customerRepository.enabled(customer.getId());
            return true;
        }

    }


    public Integer getIdfromJwtToken(HttpServletRequest request) {
        // get Jwt from Response
        String jwtToken = getJWTformRequest(request);
        // get email from jwt
        String email = jwtTokenProvider.getCustomernameFromJWT(jwtToken);

        return customerRepository.findByEmail(email).getId();
    }

    private String getJWTformRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
    private boolean sendVerifyMail(String customerEmail,String verificationCode) {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;


        // リンク先のページはフロントエンド(Next.js)側で実装する(dynamicRouting使う)
        // リンク先に飛んだときにverifyApiを実行する.
        // apiを実行する際にRequestBodyにverifyCodeを詰める

        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromEmail);
            helper.setTo(customerEmail);
            helper.setSubject("Ecshop Verification");
            String insertMessage = "<html>"
                    + "<head></head>"
                    + "<body>"
                    + "<h3>Hello " + customerEmail + "</h3>"
                    +"<div>ECショップのユーザー仮登録が完了しました。下リンクをクリックして有効化してください</div>"
                    +"<a href="+frontendURL+"/verify/"+verificationCode+">ユーザの有効化</a>"
                    + "</body>"
                    + "</html>";

            helper.setText(insertMessage, true);
            javaMailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
