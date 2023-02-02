package com.example.restapi.implement.customer;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    ResourceLoader resourceLoader;

    public boolean sendTestMail(Map<String, String> sendMailInfo) {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;

        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(sendMailInfo.get("fromAddress"));
            helper.setTo(sendMailInfo.get("toAddress"));
            helper.setSubject("[" + sendMailInfo.get("action") + "] " + sendMailInfo.get("subject"));

            String insertMessage = "<html>"
                    + "<head></head>"
                    + "<body>"
                    + "<h3>Hello " + sendMailInfo.get("toName") + "</h3>"
                    + "<p><a href='" + sendMailInfo.get("link") + "'><img src='cid:logo'></a></p>"
                    +"<div>ECショップのユーザー仮登録が完了しました。下リンクをクリックして有効化してください</div>"
                    +"<a href=\"http://dadasdfasdada/\">ユーザの有効化</a>"
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