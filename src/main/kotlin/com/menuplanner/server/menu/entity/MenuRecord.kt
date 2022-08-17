package com.menuplanner.server.menu.entity

import javax.persistence.*
import com.menuplanner.server.menu.entity.MenuImpl as MenuImpl

@Entity
@Table(name = "menu")
data class MenuRecord(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    override var id: Int = 0,
    @Column(name = "title")
    override var title: String = "",
) : Menu by MenuImpl()

class MenuImpl : Menu {
    override var id: Int = 0
    override var title: String = ""
}

interface Menu {
    var id: Int
    var title: String
}