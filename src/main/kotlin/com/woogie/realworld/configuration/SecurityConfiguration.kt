package com.woogie.realworld.configuration

import com.woogie.realworld.security.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableWebSecurity
class SecurityConfiguration(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(http: HttpSecurity) {
        http.run {
            cors { "/**" }
            csrf { it.disable() }

            sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            }

            authorizeRequests()
                .antMatchers("/api/users/**").permitAll()
                .antMatchers("/api/profile/**/follow").permitAll()
                .anyRequest().authenticated()

            addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
        }
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()

        configuration.allowedOriginPatterns = mutableListOf("*")
        configuration.allowCredentials = true
        configuration.allowedMethods = listOf("HEAD", "GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH")
        configuration.allowedHeaders = listOf("Authorization", "Cache-Control", "Content-Type", "lang")

        val source = UrlBasedCorsConfigurationSource()

        source.registerCorsConfiguration("/**", configuration)

        return source
    }
}
