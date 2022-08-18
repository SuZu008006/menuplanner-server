package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.IngredientRecord
import com.menuplanner.server.menu.entity.MenuRecord

class SpyStubMenuService : MenuService {
    var allMenu_return: List<MenuRecord> = emptyList()
    var allIngredient_return: List<IngredientRecord> = emptyList()

    override fun allMenu(): List<MenuRecord> {
        return allMenu_return
    }

    override fun allIngredient(id: Int): List<IngredientRecord> {
        return allIngredient_return
    }
}