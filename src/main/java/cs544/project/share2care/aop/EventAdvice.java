package cs544.project.share2care.aop;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import cs544.project.share2care.domain.Event;
import cs544.project.share2care.domain.Member;
import cs544.project.share2care.domain.User;
import cs544.project.share2care.email.SmtpGmailSender;
import cs544.project.share2care.service.IEventService;
import cs544.project.share2care.service.IMemberService;

/*
 * @Author Solomon Kassahun
 */
@Aspect
@Component
public class EventAdvice {
	@Autowired
	private SmtpGmailSender smtpGmailSender;
	@Autowired
	IEventService eventService;
	@Autowired
	IMemberService memberService;
	
	@Before("execution(* cs544.project.share2care.controller.EventController.deleteEvent(..))")
	public String traceDeleteEvent(JoinPoint joinPoint) throws Throwable{
		int eventId = (Integer)joinPoint.getArgs()[1];
		Principal principal = (Principal)joinPoint.getArgs()[1];
		String memberName = principal.getName();
		Event event = eventService.findById(eventId);
		Member member = memberService.getLoggedInMemeberByMemberName(memberName);
		if(event.getOwner() != member){
			String str1 = "Unauthorized action! You can only delete events that you created.";
			smtpGmailSender.send(member.getEmail(), "Unauthorized action", str1);
			
			String str2 = "Someone just tried to delete your event. Make sure you're in secure network";
			smtpGmailSender.send(event.getOwner().getEmail(), "Unauthorized action", str2);
		}
		
		System.out.println("Suspicious activity detected");
		return "error_message";
	}

}
