package com.example.restapi.customer;


import com.example.restapi.domain.customer.Customer;
import com.example.restapi.domain.customer.CustomerRepository;
import com.example.restapi.implement.customer.CustomerServiceImplement;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
   PasswordEncoder passwordEncoder;

    // コンストラクタ インジェクション
    @InjectMocks
    private CustomerServiceImplement customerServiceImplement;

    @Test
    @DisplayName("Emailアドレス、パスワードを引数とし、registerCustomerを実行したとき、データベースにユーザー情報が保存される。パスワードは暗号化され、認証コードが生成される")
    public void givenEmailAndPassword_whenRegisterCustomer_thenSaveCustomer() {
        // given-precondition or Setup
        String expectedEmailAddress = "test@gmail.com";
        String expectedPassword = "password";
        Customer newCustomer = new Customer(expectedEmailAddress, expectedPassword);
        BCryptPasswordEncoder mockPasswordEncoder = new BCryptPasswordEncoder();
        // Repositoryをモック化する。
        Mockito.doReturn(null).when(customerRepository).save(newCustomer);
        Mockito.doReturn(mockPasswordEncoder.encode(expectedPassword)).when(passwordEncoder).encode(expectedPassword);
        //when - action or the behavior that we are going test
        Customer  actualCustomer = customerServiceImplement.registerCustomer(expectedEmailAddress, expectedPassword);
        //then - verify the output
        assertThat(actualCustomer.getEmail()).isEqualTo(expectedEmailAddress);
        assertThat(actualCustomer.getPassword()).isNotEqualTo(expectedPassword);
        assertThat(actualCustomer.isEnabled()).isFalse();
        assertThat(actualCustomer.getVerificationCode().length()).isEqualTo(64);
    }


    // positivePattern
    @Test
    @DisplayName("認証コードを引数とし、Verifyを実行したとき、成功する")
    public void givenVerificationCode_whenVerify_thenReturnTrue() {
        // given-precondition or Setup
        String expectedVerificationCode = RandomString.make(64);
        Customer beforeVerificationCustomer = new Customer("test@gmail", "pass", expectedVerificationCode, false);
        // リポジトリレイヤーのメソッドをモック化する。
        Mockito.doReturn(beforeVerificationCustomer).when(customerRepository).findByVerificationCode(expectedVerificationCode);
        //when - action or the behavior that we are going test
        boolean actualResult = customerServiceImplement.verify(expectedVerificationCode);
        //then - verify the output
        assertThat(actualResult).isEqualTo(true);
    }

    // negativePattern
    @Test
    @DisplayName("間違った認証コードを引数とし、Verifyを実行したとき、失敗する。")
    public void givenNoVerificationCode_whenVerify_thenReturnFalse() {
        // given-precondition or Setup
        String badVerificationCode = "badExample";
        Mockito.doReturn(null).when(customerRepository).findByVerificationCode(badVerificationCode);
        //when - action or the behavior that we are going test
        boolean actualResult = customerServiceImplement.verify(badVerificationCode);
        //then - verify the output
        assertThat(actualResult).isEqualTo(false);
    }
}
