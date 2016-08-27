/**
 * 
 */
package cs544.project.share2care.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs544.project.share2care.domain.User;
import cs544.project.share2care.email.SmtpGmailSender;

/**
 * @author Dilip
 *
 */
@Aspect
@Component
public class UserAdvice {
	@Autowired
	private SmtpGmailSender smtpGmailSender;
	@After("execution(* cs544.project.share2care.service.impl.*.save*(..)) && args(user)")
	public String traceSignupMethod(JoinPoint joinPoint, User user) throws Throwable{
		String str = "You have successfully signed up in to the system. Please confirm your email by click on following link "+"http://localhost:8080/user/verify/"+user.getUserId();
		smtpGmailSender.send(user.getEmail(), "Sign Up Successful",
				str);
		System.out.println("User successfully signed up and email already sent");
		return "success_msg";
	}
}
