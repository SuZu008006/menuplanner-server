package com.menuplanner.server.menu.entity

import javax.persistence.*
import com.menuplanner.server.menu.entity.MenuImpl as MenuImpl

@Entity
@Table(name = "menu")
data class MenuRecord(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Int = 0,
    override val title: String = "",
    override val image: String = "",
) : Menu by MenuImpl()

class MenuImpl : Menu {
    override val id: Int = 0
    override val title: String = ""
    override val image: String = ""
}

interface Menu {
    val id: Int
    val title: String
    val image: String
}