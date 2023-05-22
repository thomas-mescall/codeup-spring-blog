package com.codeup.codeupspringblog.services;

import com.codeup.codeupspringblog.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

    @Service("mailService")
    public class EmailService {

        @Autowired
        public JavaMailSender emailSender;

        @Value("${spring.mail.from}")
        private String from;

        public void prepareAndSend(Post post, String title, String description) {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(from);
            msg.setTo(post.getUser().getEmail());
            msg.setSubject(title);
            msg.setText(description);

            try{
                this.emailSender.send(msg);
            }
            catch (MailException ex) {
                // simply log it and go on...
                System.err.println(ex.getMessage());
            }
        }
    }


