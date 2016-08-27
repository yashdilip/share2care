/**
 * 
 */
package cs544.project.share2care.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Dilip
 *
 */
@Component
public class SmtpGmailSender {
	@Autowired
	private JavaMailSender javaMailSender;
	@Async
	public void send(String mail_to, String subject, String msg) throws MessagingException{
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		
		helper = new MimeMessageHelper(message, true);
		
		helper.setSubject(subject);
		helper.setTo(mail_to);
		helper.setText(msg, true);
	
		javaMailSender.send(message);
	}
}
