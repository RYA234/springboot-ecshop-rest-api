package com.example.restapi.customer;


import com.example.restapi.domain.customer.Customer;
import com.example.restapi.domain.customer.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    @DisplayName("Customerを引数とし、saveを実行したとき、Customerが保存される。")
    public void givenCustomer_whenSave_thenSaveDataBase() {
        // given-precondition or Setup
        Customer newCustomer = new Customer("pass","test@gmail.com","hogehoge",false);
        //when - action or the behavior that we are going test
        customerRepository.save(newCustomer);
        //then - verify the output
        assertThat(customerRepository.count()).isGreaterThan(0);
        customerRepository.deleteAll();
    }

    // positive pattern
    // todo メソッド実行するとOKになるが、クラスで実行するとNGになる
    @Test
    @DisplayName("認証コードをを引数とすると、findByVerificatoinCodeを実行したとき、対応するcustomerが返される。。")
    public void givenVerificationCode_whenFindByVerificationCode_thenCustomer() {
        // given-precondition or Setup
        String newVerificationCode = "hogehoge";
        customerRepository.save(new Customer(1,"password","test@gmail.com","hogehoge",false));
        //when - action or the behavior that we are going test
        Customer verifiedCustomer = customerRepository.findByVerificationCode(newVerificationCode);
        //then - verify the output
        assertThat(verifiedCustomer.getId()).isEqualTo(1);
        customerRepository.deleteAll();
    }

    // negative pattern
    @Test
    @DisplayName("存在しない認証コードを引数とし、findByVerificatoinCodeを実行したとき、該当するCustomerは存在しない。")
    public void givenNoVerificationCode_whenFindByVerificationCode_thenNoCustomer() {
        // given-precondition or Setup
        String badVerificationCode = "hogehoge";
        customerRepository.save(new Customer(1,"password","testaaa@gmail.com","hogehogeaa",false));
        customerRepository.save(new Customer(2,"passworda","testA@gmail.com","hssssss",false));
        //when - action or the behavior that we are going test
        Customer verificedCustomer = customerRepository.findByVerificationCode(badVerificationCode);
        //then - verify the output
        assertThat(verificedCustomer).isEqualTo(null);
        customerRepository.deleteAll();
    }

    @Test
    @DisplayName("idを引数とし、enabledを実行したとき、enabledの値がfalseからtrue,VerificationCodeはnullに更新される。")
    public void givenCustomerId_whenEnabled_thenUpdateEnabledAndVerificationCode() {
        // given-precondition or Setup
        Integer customerId = 1;
        customerRepository.save(new Customer(customerId,"password","testaaa@gmail.com","hogehogeaa",false));
        //when - action or the behavior that we are going test
        customerRepository.enabled(1);
        //then - verify the output
        Optional<Customer> enabledCustomer = customerRepository.findById(customerId);
        assertThat(enabledCustomer.get().getVerificationCode()).isEqualTo(null);
        assertThat(enabledCustomer.get().isEnabled()).isTrue();
        customerRepository.deleteAll();
    }

    @Test
    public void LogCompore(){
        final Logger logger = Logger.getLogger("SampleLogging");
        logger.log(Level.FINEST, "ログ出力テスト：finest");
    }
}
