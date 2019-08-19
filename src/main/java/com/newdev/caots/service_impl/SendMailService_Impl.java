package com.newdev.caots.service_impl;

import com.newdev.caots.entities.UserMail;
import com.newdev.caots.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SendMailService_Impl implements SendMailService {

    private static final Logger LOGGER = Logger.getLogger(SendMailService_Impl.class.getName());

    /*
     * Trên thực tế, Java cung cấp hai lớp để gửi thư. Nếu bạn muốn gửi thư đơn giản mà không có tệp đính kèm thì đối
     * tượng của SimpleMailMessage được sử dụng
     * nếu không sử dụng Đối tượng của MimeMessage .
     */

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String emailAdminAddress;


    @Override
    public boolean sendEmail(UserMail userMail) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(userMail.getEmailAddress());
            mail.setFrom(emailAdminAddress);
            mail.setSubject(userMail.getTitle());
            mail.setText(userMail.getContent());
            javaMailSender.send(mail);
            return true;
        } catch (MailException ex) {
            LOGGER.log(Level.SEVERE, "send-mail-error : {0}", ex.getMessage());
        }
        return false;

    }

   /* private UserMail userSendMail(String email) {
        String content = "Mã xác thực: " + generateCode();
        UserMail userMail = new UserMail();
        userMail.setEmailAddress(email);
        userMail.setTitle("BkSoftwarevn thân gửi");
        userMail.setContent(content);
        return userMail;
    }
    private String generateCode() {
        Random random = new Random();
        int numberOne = random.nextInt();
        int numberTwo = random.nextInt();
        int numberThree = random.nextInt();
        int numberFour = random.nextInt();
        return MD5.encode(numberOne + "C" + numberTwo + "A" + numberThree + "O" + numberFour);
    }*/


    //Gửi mail với tệp đính kèm
/*
    public void sendEmailWithAttachment(UserMail user) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(user.getEmailAddress());
            helper.setSubject("Testing Mail API with Attachment");
            helper.setText("Please find the attached document below.");
            FileSystemResource file = new FileSystemResource("/home/caots/Desktop/abc.pdf");
            helper.addAttachment(file.getFilename(), file);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException ex) {
            LOGGER.log(Level.SEVERE, "send-mail-with-attachment-error : {0}", ex.getMessage());
        }
    }
*/
}
