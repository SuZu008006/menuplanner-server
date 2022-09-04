package com.menuplanner.server.menu.entity

data class MenuStruct(
    val menuRecord: MenuRecord,
    val ingredientRecord: List<IngredientRecord>,
    val seasoningRecord: List<SeasoningRecord>,
    val makeRecord: List<MakeRecord>,
) {
    companion object {}
}