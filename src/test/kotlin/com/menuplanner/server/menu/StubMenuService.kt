package com.menuplanner.server.menu

class StubMenuService : MenuService {
    var allMenu_return: List<MenuRecord> = emptyList()
    override fun allMenu(): List<MenuRecord> {
        return allMenu_return
    }

    override fun allIngredient(id: Long): List<IngredientRecord> {
        return emptyList()
    }
}