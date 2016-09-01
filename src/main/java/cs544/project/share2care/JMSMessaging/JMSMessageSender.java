/**
 * 
 */
package cs544.project.share2care.JMSMessaging;

import org.springframework.jms.core.JmsTemplate;

/**
 * @author Dilip
 *
 */
public class JMSMessageSender {

	private JmsTemplate jmsTemplateSender;

    public void send(final String message) {
    	jmsTemplateSender.send(session -> session.createObjectMessage(message));
        System.out.println("Message has been sent to new member");
    }

	public void setJmsTemplateSender(JmsTemplate jmsTemplateSender) {
		this.jmsTemplateSender = jmsTemplateSender;
	}

    
}
