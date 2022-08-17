package com.menuplanner.server.menu.entity

import javax.persistence.*

@Entity
@Table(name = "ingredient")
data class IngredientRecord(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    override var ingredient_id: Int = 0,
    @Column(name = "id")
    override var id: Int = 0,
    @Column(name = "item")
    override var item: String = "",
    @Column(name = "quantity")
    override var quantity: Double = 0.0,
    @Column(name = "scale")
    override var scale: String = "",
) : Ingredient by IngredientImpl()

class IngredientImpl : Ingredient {
    override var ingredient_id: Int = 0
    override var id: Int = 0
    override var item: String = ""
    override var quantity: Double = 0.0
    override var scale: String = ""
}

interface Ingredient {
    var ingredient_id: Int
    var id: Int
    var item: String
    var quantity: Double
    var scale: String
}