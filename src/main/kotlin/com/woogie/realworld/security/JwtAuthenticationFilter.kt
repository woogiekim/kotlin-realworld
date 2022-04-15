package com.woogie.realworld.security

import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AccountStatusUserDetailsChecker
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationFilter(
    private val jwtProvider: JwtProvider,
    private val customUserDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        val token = req.getHeader(HttpHeaders.AUTHORIZATION)

        if (token.isNullOrBlank()) {
            chain.doFilter(req, res)

            return
        }

        val email = jwtProvider.getClaims(token) { it.id }
        val userDetails =
            customUserDetailsService.loadUserByUsername(email) ?: throw UsernameNotFoundException("유효하지 않은 인증정보 입니다.")

        AccountStatusUserDetailsChecker().check(userDetails)

        val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities).apply {
            details = WebAuthenticationDetailsSource().buildDetails(req)
        }

        SecurityContextHolder.getContext().authentication = authentication

        chain.doFilter(req, res)
    }
}
