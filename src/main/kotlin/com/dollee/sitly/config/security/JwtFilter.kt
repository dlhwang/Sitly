package com.dollee.sitly.config.security

import com.dollee.sitly.global.exception.JwtInvalidException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException

@RequiredArgsConstructor
@Component
class JwtFilter(val jwtAuthenticationProvider: JwtAuthenticationProvider) : OncePerRequestFilter() {
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain
    ) {
        // Request Header에서 토큰 추출
        val jwt = resolveToken(request)
        // Token 유효성 검사
        if (StringUtils.hasText(jwt) && jwtAuthenticationProvider.isValidToken(jwt)) {
            try {
                // 토큰으로 인증 정보를 추출
                val authentication = jwtAuthenticationProvider.getAuthentication(jwt)
                // SecurityContext에 저장
                SecurityContextHolder.getContext().authentication = authentication
            } catch (authenticationException: AuthenticationException) {
                SecurityContextHolder.clearContext()
                throw authenticationException
            } catch (e: Exception) {
                throw JwtInvalidException(e.message, e)
            }
        }
        filterChain.doFilter(request, response)
    }

    /** Request Header에서 토큰 추출  */
    private fun resolveToken(request: HttpServletRequest): String {
        val bearerToken = request.getHeader(TOKEN_HEADER)
        if (StringUtils.hasText(bearerToken) && bearerToken!!.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(7)
        }
        return ""
    }

    companion object {
        private const val TOKEN_HEADER = "Authorization"
        private const val TOKEN_PREFIX = "Bearer "
    }
}
