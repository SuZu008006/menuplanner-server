package com.menuplanner.server.menu.entity

import javax.persistence.*

@Entity
@Table(name = "seasoning")
data class SeasoningRecord(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seasoning_id")
    var seasoning_id: Int = 0,
    @Column(name = "id")
    var id: Int = 0,
    @Column(name = "item")
    var item: String = "",
    @Column(name = "quantity")
    var quantity: Int = 0,
    @Column(name = "scale")
    var scale: String = "",
)