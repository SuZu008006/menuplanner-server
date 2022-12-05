package com.menuplanner.server.menu.entity

import javax.persistence.*

@Entity
@Table(name = "seasoning")
data class SeasoningRecord(
    @Id
    override val seasoningId: Int = 0,
    override val id: Int = 0,
    override val item: String = "",
    override val quantity: Double = 0.0,
    override val scale: String = "",
) : Seasoning by SeasoningImpl()

class SeasoningImpl : Seasoning {
    override val seasoningId: Int = 0
    override val id: Int = 0
    override val item: String = ""
    override val quantity: Double = 0.0
    override val scale: String = ""
}

interface Seasoning {
    val seasoningId: Int
    val id: Int
    val item: String
    val quantity: Double
    val scale: String
}