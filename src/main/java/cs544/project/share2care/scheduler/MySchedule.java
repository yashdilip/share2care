/**
 * 
 */
package cs544.project.share2care.scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cs544.project.share2care.domain.User;
import cs544.project.share2care.service.IUserService;

/**
 * @author Dilip
 *
 */
//@Component
public class MySchedule {

	Logger logger = Logger.getLogger(MySchedule.class);
	@Autowired
	IUserService userService;
	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy HH:mm:SS");

	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		// logger.info("The time is now {} " + sdf.format(new Date()));
		// System.out.println("The time is now {} " + sdf.format(new Date()));
	}

	@Scheduled(cron = "0 59 23 * * *")
	public void deleteInActiveUserFromDatabaseCron() {
		// logger.info("The time is now {} " + sdf.format(new Date()));
		// System.out.println("The time is now {} " + sdf.format(new Date()));

		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			Date date1 = sdf.parse(date.toString());

			System.out.println(sdf.format(date1));

			List<User> users = userService.getAllUsers();

			for (User u : users) {
				Date d = u.getCreatedDate();

				if (d.compareTo(date1) > 0) {
					if (u.getEnabled() == 0) {
						// delete methods;
						System.out.println("Disabled Users deleted.");
						logger.info("Disabled Users deleted.");
					}
				}
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
	}
}
