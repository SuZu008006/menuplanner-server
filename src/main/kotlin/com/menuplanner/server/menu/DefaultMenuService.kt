package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.IngredientRecord
import com.menuplanner.server.menu.repository.IngredientRepository
import com.menuplanner.server.menu.entity.MenuRecord
import com.menuplanner.server.menu.entity.MenuStruct
import com.menuplanner.server.menu.repository.MakeRepository
import com.menuplanner.server.menu.repository.MenuRepository
import com.menuplanner.server.menu.repository.SeasoningRepository
import org.springframework.stereotype.Service

@Service
class DefaultMenuService(
    private val menuRepository: MenuRepository,
    private val ingredientRepository: IngredientRepository,
    private val seasoningRepository: SeasoningRepository,
    private val makeRepository: MakeRepository,
) : MenuService {
    override fun getTargetMenu(id: Int): MenuStruct {
        return MenuStruct(
            menuRecord = menuRepository.findDistinctById(id),
            ingredientRecord = ingredientRepository.findDistinctById(id),
            seasoningRecord = seasoningRepository.findDistinctById(id),
            makeRecord = makeRepository.findDistinctById(id),
        )
    }

    override fun getSevenDaysMenu(): List<MenuRecord> {
        return menuRepository.findAll()
            .shuffled().slice(0..6)
    }

    override fun getTargetIngredient(id: Int): List<IngredientRecord> {
        return ingredientRepository.findDistinctById(id)
    }

    override fun getSevenDaysIngredient(idList: List<Int>): List<IngredientRecord> {
        val ingredientList: MutableList<IngredientRecord> = arrayListOf()

        idList.forEach {
            ingredientList
                .addAll(ingredientRepository.findDistinctById(it))
        }

        return ingredientList
    }
}

interface MenuService {
    fun getTargetMenu(id: Int): MenuStruct
    fun getSevenDaysMenu(): List<MenuRecord>
    fun getTargetIngredient(id: Int): List<IngredientRecord>
    fun getSevenDaysIngredient(idList: List<Int>): List<IngredientRecord>
}