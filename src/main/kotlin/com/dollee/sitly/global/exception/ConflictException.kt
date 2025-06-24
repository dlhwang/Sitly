package com.dollee.sitly.global.exception

import java.io.Serial

/** 이미 데이터가 존재하는 경우 예외  */
class ConflictException : RuntimeException {
    constructor()

    constructor(message: String?) : super(message)

    companion object {
        @Serial
        private val serialVersionUID = -3272087119569179942L
    }
}
