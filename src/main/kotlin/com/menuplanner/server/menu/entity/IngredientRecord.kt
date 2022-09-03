package com.menuplanner.server.menu.entity

import javax.persistence.*

@Entity
@Table(name = "ingredient")
data class IngredientRecord(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val ingredientId: Int = 0,
    override val id: Int = 0,
    override val item: String = "",
    override val quantity: Double = 0.0,
    override val scale: String = "",
) : Ingredient by IngredientImpl()

class IngredientImpl : Ingredient {
    override val ingredientId: Int = 0
    override val id: Int = 0
    override val item: String = ""
    override val quantity: Double = 0.0
    override val scale: String = ""
}

interface Ingredient {
    val ingredientId: Int
    val id: Int
    val item: String
    val quantity: Double
    val scale: String
}