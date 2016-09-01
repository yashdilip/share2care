/**
 * 
 */
package cs544.project.share2care.aop;

import javax.mail.MessagingException;
import javax.naming.NamingException;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import cs544.project.share2care.JMSMessageConfig;
import cs544.project.share2care.JMSMessaging.JMSMessageSender;
import cs544.project.share2care.domain.Circle;
import cs544.project.share2care.domain.Event;
import cs544.project.share2care.domain.Member;

/**
 * @author Dilip
 *
 */
@Aspect
@Component
public class JMSMessageAdvice {
	ApplicationContext context = new AnnotationConfigApplicationContext(JMSMessageConfig.class);

	@After("execution(* cs544.project.share2care.service.impl.CircleService.saveNewCircle(..)) && args(circle, member)")
	public void sendMessageToAll(Circle circle, Member member) throws MessagingException, NamingException {
		printMessage("sending message to all participants ....");
		String msg = "New Event is created : " + circle.getCircleName();
		msg += "Details: ";
		msg += " \n created by Name: " + circle.getOwner().getFirstName();

		JMSMessageSender jmsSender = context.getBean("jmsSender", JMSMessageSender.class);
		jmsSender.send(msg);
		printMessage("Completed sending the message..");
	}

	public void printMessage(String message) {
		System.out.println(message);
	}

}
