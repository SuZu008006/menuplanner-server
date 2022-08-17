package com.menuplanner.server.menu.entity

data class MenuStruct(
    var menuRecord: MutableIterable<MenuRecord>,
    var ingredientRecord: MutableIterable<IngredientRecord>,
)