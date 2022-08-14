package com.menuplanner.server.menu

import javax.persistence.*

@Entity
@Table(name = "menu")
data class MenuRecord(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int = 0,
    @Column(name = "title")
    var title: String = "",
)