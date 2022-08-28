package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.IngredientRecord
import com.menuplanner.server.menu.entity.MenuRecord
import com.menuplanner.server.menu.entity.MenuStruct

class SpyStubMenuService : MenuService {
    lateinit var menuStruct_return: MenuStruct
    var menu_return: List<MenuRecord> = emptyList()
    var ingredient_return: List<IngredientRecord> = emptyList()
    var sevenDaysIngredient_return: List<IngredientRecord> = emptyList()

    override fun getTargetMenu(id: Int): MenuStruct {
        return menuStruct_return
    }

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