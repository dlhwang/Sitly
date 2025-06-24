package com.dollee.sitly.global.exception

import java.io.Serial

class DataNotFoundException : RuntimeException {
    constructor()

    constructor(message: String?) : super(message)

    companion object {
        @Serial
        private val serialVersionUID = -1529128907688752733L
    }
}
