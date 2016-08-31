/**
 * 
 */
package cs544.project.share2care;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cs544.project.share2care.service.ICircleService;

/**
 * @author Dilip
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = com.cuisine_mart.configuration.AppConfig.class)
//@Transactional
public class CircleTest {

	
	
		@Autowired
		ICircleService circleService;
		
		@Test
		public void testUpdate() {
			
		}
		
}
