package com.dollee.sitly.global.exception

import java.io.Serial

class NotInvalidException : RuntimeException {
    constructor(message: String?) : super(message)

    constructor()

    companion object {
        @Serial
        private const val serialVersionUID = 6371307594005060125L
    }
}
