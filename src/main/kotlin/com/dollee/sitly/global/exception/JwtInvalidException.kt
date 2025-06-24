package com.dollee.sitly.global.exception

import org.springframework.security.core.AuthenticationException
import java.io.Serial

class JwtInvalidException : AuthenticationException {
    constructor(msg: String?) : super(msg)

    constructor(msg: String?, cause: Throwable?) : super(msg, cause)

    companion object {
        @Serial
        private const val serialVersionUID = 255383291024496773L
    }
}
