package com.tng.timesheetapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(10));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.authorizeRequests().antMatchers("**/**").authenticated().anyRequest().permitAll()
				//
				.and().formLogin().permitAll().successForwardUrl("/home")
				//
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
	}

//	@Bean
//	public AuthenticationProvider authProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(userDetailsService);
//
//		// provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//		provider.setPasswordEncoder(new BCryptPasswordEncoder(10));
//		return provider;
//
//	};

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
//		;
//
//	}

}
