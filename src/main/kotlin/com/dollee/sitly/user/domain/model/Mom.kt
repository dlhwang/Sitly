package com.dollee.sitly.user.domain.model

class Mom(
    val requestMessage: String,
    private val child: MutableList<Child>
) {

    fun getChildren(): List<Child> = child.toList()

    fun updateChild(childId: String, childDetail: UserDetail) {
        val child = child.find { childId == it.id }
            ?: throw IllegalArgumentException("해당 자녀를 찾을 수 없습니다: $childId")
        child.modify(childDetail)
    }

    fun addChild(child: Child) {
        this.child.add(child)
    }

    fun removeChild(childId: String) {
        child.removeIf { it.id == childId }
    }
}