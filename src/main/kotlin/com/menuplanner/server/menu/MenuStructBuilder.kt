package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.*

val MenuStruct.Companion.builder: MenuStructBuilder
    get() = MenuStructBuilder()

class MenuStructBuilder {
    private var menuRecord: MenuRecord = MenuRecord()
    private var ingredientRecord: List<IngredientRecord> = emptyList()
    private var seasoningRecord: List<SeasoningRecord> = emptyList()
    private var makeRecord: List<MakeRecord> = emptyList()

    fun withMenuRecord(newValue: MenuRecord): MenuStructBuilder {
        menuRecord = newValue
        return this
    }

    fun withIngredientRecord(newValue: List<IngredientRecord>): MenuStructBuilder {
        ingredientRecord = newValue
        return this
    }

    fun withSeasoningRecord(newValue: List<SeasoningRecord>): MenuStructBuilder {
        seasoningRecord = newValue
        return this
    }

    fun withMakeRecord(newValue: List<MakeRecord>): MenuStructBuilder {
        makeRecord = newValue
        return this
    }

    fun build(): MenuStruct {
        return MenuStruct(
            menuRecord,
            ingredientRecord,
            seasoningRecord,
            makeRecord,
        )
    }
}