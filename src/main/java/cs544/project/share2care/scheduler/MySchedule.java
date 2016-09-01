/**
 * 
 */
package cs544.project.share2care.scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cs544.project.share2care.domain.User;
import cs544.project.share2care.email.SmtpGmailSender;
import cs544.project.share2care.service.IUserService;

/**
 * @author Dilip
 *
 */
// @Component
public class MySchedule {

	Logger logger = Logger.getLogger(MySchedule.class);
	@Autowired
	IUserService userService;

	@Autowired
	private SmtpGmailSender smtpGmailSender;

	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy HH:mm:SS");

	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		// logger.info("The time is now {} " + sdf.format(new Date()));
		// System.out.println("The time is now {} " + sdf.format(new Date()));
	}

	// @Scheduled(cron = "0 59 23 * * *")
	@Scheduled(cron = "0/5 * * * * *")
	public void deleteInActiveUserFromDatabaseCron() throws MessagingException {

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			List<User> users = userService.getAllUsers();
			DateTime da = new DateTime();
			for (User u : users) {
				if (u.getEnabled() == 0) {
					if (u.getCreatedDate() != null) {
						Date date = sdf.parse(u.getCreatedDate().toString());
						DateTime dt = new DateTime(date);
						if (da.minus(1).isAfter(dt)) {
							logger.info("Disabled Users presents. \n" + "User Name:  " + u.getUsername()
									+ "\nEmail Address: " + u.getEmail());
							// can send email notification to user to confirm.
							String str = "Please confirm your email by click on following link "
									+ "http://localhost:8080/user/verify/" + u.getUserId();
							smtpGmailSender.send(u.getEmail(), "Please confirm your registration", str);
							System.out.println("User successfully signed up and email already sent");
						}
					}
				}
			}

		} catch (ParseException ex) {
			ex.printStackTrace();
		}
	}
}
