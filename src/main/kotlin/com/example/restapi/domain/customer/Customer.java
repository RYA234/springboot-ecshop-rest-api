package com.example.restapi.domain.customer;

import com.example.restapi.domain.product.ProductService;
import lombok.Data;

import javax.persistence.*;
/**
 * @brief: This Class is Entity in Customer.
 *
 * @description　   Customer is
 *
 *
 * @Auther RYA234
 *
 * @UseCase: {@link  CustomerService}
 * @UseCase_including_springSecurity: {@link ProductService}
 */
@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    // Loginするときのパスワードして使う
    @Column(nullable = false, length = 64)
    private String password;

    // ログインするときのIDとして使用する
    @Column(nullable = false, unique = true, length = 45)
    private String email;
}
