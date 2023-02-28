package com.example.restapi.implement.checkout;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://127.0.0.1:3000")
public class CheckoutRestController {
    // todo ビジネスロジックをServiceに移動する
    @PostMapping("/api/auth/checkout")
    public void  registerUser(){
//        port(4242);
//
//        // This is a public sample test API key.
//        // Don’t submit any personally identifiable information in requests made with this key.
//        // Sign in to see your own test API key embedded in code samples.
//        Stripe.apiKey = "sk_test_09l3shTSTKHYCzzZZsiLl2vA";
//
//        staticFiles.externalLocation(
//                Paths.get("public").toAbsolutePath().toString());
//
//        post("/create-checkout-session", (request, response) -> {
//            String YOUR_DOMAIN = "http://localhost:4242";
//            SessionCreateParams params =
//                    SessionCreateParams.builder()
//                            .setMode(SessionCreateParams.Mode.PAYMENT)
//                            .setSuccessUrl(YOUR_DOMAIN + "?success=true")
//                            .setCancelUrl(YOUR_DOMAIN + "?canceled=true")
//                            .addLineItem(
//                                    SessionCreateParams.LineItem.builder()
//                                            .setQuantity(1L)
//                                            // Provide the exact Price ID (for example, pr_1234) of the product you want to sell
//                                            .setPrice("{{PRICE_ID}}")
//                                            .build())
//                            .build();
//            Session session = Session.create(params);
//
//            response.redirect(session.getUrl(), 303);
//            return "";
//        });

    }


}