package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.IngredientRecord
import com.menuplanner.server.menu.entity.MenuRecord

class SpyStubMenuService : MenuService {
    var menu_return: List<MenuRecord> = emptyList()
    var ingredient_return: List<IngredientRecord> = emptyList()
    var sevenDaysIngredient_return: List<IngredientRecord> = emptyList()

    override fun getSevenDaysMenu(): List<MenuRecord> {
        return menu_return
    }

    override fun getTargetIngredient(id: Int): List<IngredientRecord> {
        return ingredient_return
    }

    override fun getSevenDaysIngredient(idList: List<Int>): List<IngredientRecord> {
        return sevenDaysIngredient_return
    }
}