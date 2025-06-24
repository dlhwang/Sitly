package com.dollee.sitly.global.utills

import de.huxhorn.sulky.ulid.ULID
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.IdentifierGenerator
import java.io.Serial
import java.io.Serializable

class UlidGenerator : IdentifierGenerator, Serializable {
    override fun generate(
        sharedSessionContractImplementor: SharedSessionContractImplementor?, o: Any?
    ): Serializable {
        return ULID().nextULID()
    }

    companion object {
        @Serial
        private val serialVersionUID = -7858266607422294454L
    }
}
