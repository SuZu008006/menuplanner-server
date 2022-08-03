package com.menuplanner.server.menu

import javax.persistence.*

@Entity
@Table(name = "ingredient")
data class IngredientRecord(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    var ingredient_id: Long = 0,
    @Column(name = "id")
    var id: Long = 0,
    @Column(name = "item")
    var item: String = "",
    @Column(name = "quantity")
    var quantity: Long = 0,
    @Column(name = "weight")
    var weight: Long = 0,
)