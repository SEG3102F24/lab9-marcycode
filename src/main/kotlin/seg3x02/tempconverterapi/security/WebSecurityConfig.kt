package seg3x02.tempconverterapi.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.config.Customizer

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    @Bean
    fun userDetailsService(): UserDetailsService {
        val user1 = User.withUsername("user1")
                .password("pass1")
                .roles("USER")
                .build()
        val user2 = User.withUsername("user2")
                .password("pass2")
                .roles("USER")
                .build()
        return InMemoryUserDetailsManager(user1, user2)
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
                .authorizeHttpRequests {
                    it.anyRequest().authenticated()
                }
                .httpBasic(Customizer.withDefaults())
                .csrf { csrf -> csrf.disable() }
        return http.build()
    }
}
