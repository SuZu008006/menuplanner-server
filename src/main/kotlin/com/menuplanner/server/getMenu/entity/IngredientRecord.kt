package com.menuplanner.server.getMenu.entity

import javax.persistence.*

@Entity
@Table(name = "ingredient")
data class IngredientRecord(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    var ingredient_id: Int = 0,
    @Column(name = "id")
    var id: Int = 0,
    @Column(name = "item")
    var item: String = "",
    @Column(name = "quantity")
    var quantity: Double = 0.0,
    @Column(name = "scale")
    var scale: String = "",
)