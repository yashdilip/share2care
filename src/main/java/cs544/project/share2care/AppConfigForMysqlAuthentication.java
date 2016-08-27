/**
 * 
 */
package cs544.project.share2care;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @author Dilip
 *
 */
@EnableWebMvc
@Configuration
@ComponentScan("cs544.project.share2care.*")
@PropertySource(value={"classpath:application.properties"})
public class AppConfigForMysqlAuthentication {
	@Autowired
	private Environment environment;
	
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName(environment.getProperty("spring.database.driverClassName"));
	    driverManagerDataSource.setUrl(environment.getProperty("spring.datasource.url"));
	    driverManagerDataSource.setUsername(environment.getProperty("spring.datasource.username"));
	    driverManagerDataSource.setPassword(environment.getProperty("spring.datasource.password"));
	    return driverManagerDataSource;
	}

	/*@Bean
	public InternalResourceViewResolver viewResolver() {
	    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setViewClass(JstlView.class);
	    viewResolver.setPrefix("/WEB-INF/pages/");
	    viewResolver.setSuffix(".jsp");
	    return viewResolver;
	}*/
}
