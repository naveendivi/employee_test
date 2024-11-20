package com.test.employee.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailsForSecurity {

	@Bean
	public InMemoryUserDetailsManager userDetailsForCheck() {

		UserDetails user = User.withUsername("user").password(new BCryptPasswordEncoder().encode("admin"))
				.roles("admin").build();

		return new InMemoryUserDetailsManager(user);

	}

}
