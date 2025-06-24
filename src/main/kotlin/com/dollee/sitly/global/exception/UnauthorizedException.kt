package com.dollee.sitly.global.exception

import org.springframework.security.core.AuthenticationException

class UnauthorizedException(msg: String) : AuthenticationException(msg) {
}