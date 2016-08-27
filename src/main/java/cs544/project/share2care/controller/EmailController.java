/**
 * 
 */
package cs544.project.share2care.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cs544.project.share2care.email.SmtpGmailSender;

/**
 * @author Dilip
 *
 */
@RestController
public class EmailController {

	@Autowired
	private SmtpGmailSender smtpGmailSender;

	@RequestMapping("/send-mail")
	public void sendMail() throws MessagingException {
		smtpGmailSender.send("dilip.nepali.231@gmail.com", "test message", "This is a test message usnig javamail");
	}
}
