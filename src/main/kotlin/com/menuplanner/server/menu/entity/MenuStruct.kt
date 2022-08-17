package com.menuplanner.server.menu.entity

data class MenuStruct(
    var menuRecord: MenuRecord,
    var ingredientRecord: List<IngredientRecord>,
)