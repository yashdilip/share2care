/**
 * 
 */
package cs544.project.share2care;


import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.annotation.EnableJms;

/**
 * @author Dilip
 *
 */
@EnableJms
@ImportResource("classpath:JMSMessagingConfig.xml")
public class JMSMessageConfig {

}
