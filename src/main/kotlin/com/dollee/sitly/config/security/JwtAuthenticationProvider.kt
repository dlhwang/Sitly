package com.dollee.sitly.config.security

import com.dollee.sitly.global.exception.JwtInvalidException
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import lombok.Getter
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.security.Key
import java.security.SignatureException
import java.util.*


@RequiredArgsConstructor
@Component
class JwtAuthenticationProvider(

    @Value("\${jwt.secretKey}")
    private val secretKey: String,

    @Value("\${jwt.refresh-secretKey}")
    private val refreshSecretKey: String,

    @Getter
    @Value("\${jwt.access-token-expire-time}")
    private val accessExpiredMinutes: Long = 0

) {
    /** 토큰에서 Claim 추출  */
    private fun getClaimsFormToken(token: String): Claims {
        val claims: Claims?
        try {
            claims =
                Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).body
        } catch (unsupportedJwtException: UnsupportedJwtException) {
            throw JwtInvalidException("지원하지 않는 토큰입니다.", unsupportedJwtException)
        } catch (expiredJwtException: ExpiredJwtException) {
            throw JwtInvalidException("토큰의 유효기간이 만료되었습니다.", expiredJwtException)
        } catch (signatureException: SignatureException) {
            throw JwtInvalidException("유효하지 않은 토큰입니다.", signatureException)
        } catch (malformedJwtException: MalformedJwtException) {
            throw JwtInvalidException("잘못된 형식의 토큰 입니다.", malformedJwtException)
        } catch (illegalArgumentException: IllegalArgumentException) {
            throw JwtInvalidException("유효하지 않은 정보입니다.", illegalArgumentException)
        } catch (e: Exception) {
            throw JwtInvalidException("알 수 없는 오류입니다.", e)
        }

        return claims
    }

    /** 토큰에서 인증 subject 추출  */
    private fun getSubject(token: String): String {
        return getClaimsFormToken(token).subject
            ?: throw IllegalArgumentException("토큰에서 subject를 추출할 수 없습니다.")
    }

    /** 토큰에서 인증 정보 추출  */
    fun getAuthentication(token: String): Authentication {
        val user: AuthUser = AuthUser.to(getClaimsFormToken(token))
        return UsernamePasswordAuthenticationToken(
            user,
            null,
            user.authorities
        )
    }

    /** 토큰 발급  */
    fun generateJwtToken(userDetails: UserDetails): String {
        val now = Date()

        return Jwts.builder()
            .setIssuedAt(now)
            .setSubject(userDetails.username)
            .claim("id", userDetails.username)
            .claim("roles", userDetails.authorities)
            .setExpiration(Date(now.time + (accessExpiredMinutes * 60 * 1000)))
            .signWith(getKeyFromBase64EncodedKey(this.secretKey), SignatureAlgorithm.HS256)
            .compact()
    }

    /** 토큰 검증  */
    fun isValidToken(token: String): Boolean {
        getClaimsFormToken(token)
        return true
    }

    fun getKeyFromBase64EncodedKey(base64EncodedSecretKey: String?): Key {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(base64EncodedSecretKey))
    }

}
