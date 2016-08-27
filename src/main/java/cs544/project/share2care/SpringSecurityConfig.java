package cs544.project.share2care;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * @author Dilip
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

	  auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
				"select username as username, password as password, enabled as enabled from users where username=?")
		.authoritiesByUsernameQuery(
				"select username as username, role as role from users where username=?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/","/login/**","/user/verify/**").permitAll()
		.antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/user/**")
				.hasAnyRole("USER, ADMIN").antMatchers("/login")
				.permitAll().antMatchers("/resources/**","/login/signup").permitAll()
				.anyRequest().authenticated().and().formLogin().loginPage("/login")
				.successForwardUrl("/login/handleLogin").permitAll().and().logout().permitAll().and().sessionManagement().invalidSessionUrl("/login");
	}

	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN").and().withUser("user")
				.password("user").roles("USER");
	}*/

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resource/**");
	}
}
