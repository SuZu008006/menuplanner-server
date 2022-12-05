package com.menuplanner.server.menu.entity

import javax.persistence.*

@Entity
@Table(name = "make")
data class MakeRecord(
    @Id
    override val makeId: Int = 0,
    override val id: Int = 0,
    override val content: String = "",
) : Make by MakeImpl()

class MakeImpl : Make {
    override val makeId: Int = 0
    override val id: Int = 0
    override val content: String = ""
}

interface Make {
    val makeId: Int
    val id: Int
    val content: String
}