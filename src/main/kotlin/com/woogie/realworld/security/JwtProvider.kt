package com.woogie.realworld.security

import com.woogie.realworld.domain.user.domain.User
import com.woogie.realworld.domain.user.service.TokenGenerator
import io.jsonwebtoken.*
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Function

@Component
class JwtProvider : TokenGenerator {

    override fun generate(user: User): String {
        val now = Date()
        val expiration = Date(now.time + 300000L)

        return Jwts.builder()
            .setSubject("Real World API Token")
            .setIssuer("Real World API")
            .setIssuedAt(now)
            .setId(user.email.toString())
            .setAudience(user.username.toString())
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS256, "R2@lw0r1d")
            .compact()
    }

    fun <R> getClaims(token: String, claimsResolver: Function<Claims, R>): R {
        val claims: Claims = parseClaimsJws(token)
        return claimsResolver.apply(claims)
    }

    private fun parseClaimsJws(token: String): Claims {
        return try {
            Jwts.parser()
                .setSigningKey("R2@lw0r1d")
                .parseClaimsJws(token)
                .body
        } catch (e: ExpiredJwtException) {
            throw BadCredentialsException("인증이 만료되었습니다.")
        } catch (e: UnsupportedJwtException) {
            throw BadCredentialsException("지원하지 않는 형식입니다.")
        } catch (e: MalformedJwtException) {
            throw BadCredentialsException("잘못된 형식이 포함 되었습니다.")
        } catch (e: SignatureException) {
            throw BadCredentialsException("잘못된 서명입니다.")
        } catch (e: IllegalArgumentException) {
            throw BadCredentialsException("유효하지 않은 값이 포함 되었습니다.")
        }
    }
}
