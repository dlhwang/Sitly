package com.dollee.sitly.user.infra.entity

import jakarta.persistence.*

@Entity
@Table(name = "users_mom")
class MomEntity(
    @Id
    val id: String? = null,
    @MapsId @OneToOne
    @JoinColumn(name = "user_id")
    private var user: UserEntity,
    @Column(name = "request_message", columnDefinition = "TEXT", nullable = false)
    private var requestMessage: String,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "mom_id") // FK를 children 테이블에 유지
    private val children: MutableList<ChildEntity> = mutableListOf()
) {
    fun modifyRequestMessage(requestMessage: String) {
        this.requestMessage = requestMessage
    }

    fun getChildRen(): MutableList<ChildEntity> = children
    fun getRequestMessage(): String = requestMessage

    fun addChild(child: ChildEntity) {
        children.add(child)
    }

    fun addChild(child: List<ChildEntity>) {
        children.addAll(child)
    }

    fun removeChild(child: ChildEntity) {
        children.remove(child)
    }

    fun clearChildren() {
        children.clear()
    }
}