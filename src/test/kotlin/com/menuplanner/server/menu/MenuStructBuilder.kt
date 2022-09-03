package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.IngredientRecord
import com.menuplanner.server.menu.entity.MenuRecord
import com.menuplanner.server.menu.entity.MenuStruct
import com.menuplanner.server.menu.entity.SeasoningRecord

val MenuStruct.Companion.builder: MenuStructBuilder
    get() = MenuStructBuilder()

class MenuStructBuilder {
    private var menuRecord: MenuRecord = MenuRecord()
    private var ingredientRecord: List<IngredientRecord> = emptyList()
    private var seasoningRecord: List<SeasoningRecord> = emptyList()

    fun withMenuRecord(newValue: MenuRecord): MenuStructBuilder {
        menuRecord = newValue
        return this
    }

    fun ingredientRecord(newValue: List<IngredientRecord>): MenuStructBuilder {
        ingredientRecord = newValue
        return this
    }

    fun seasoningRecord(newValue: List<SeasoningRecord>): MenuStructBuilder {
        seasoningRecord = newValue
        return this
    }

    fun build(): MenuStruct {
        return MenuStruct(
            menuRecord,
            ingredientRecord,
            seasoningRecord,
        )
    }
}