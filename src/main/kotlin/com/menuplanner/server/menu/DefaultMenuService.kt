package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.IngredientRecord
import com.menuplanner.server.menu.repository.IngredientRepository
import com.menuplanner.server.menu.entity.MenuRecord
import com.menuplanner.server.menu.repository.MenuRepository
import org.springframework.stereotype.Service

@Service
class DefaultMenuService(
    private val menuRepository: MenuRepository,
    private val ingredientRepository: IngredientRepository,
) : MenuService {
    override fun getTargetMenu(id: Int): MenuRecord {
        return menuRepository.findDistinctById(id)
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
    fun getTargetMenu(id: Int): MenuRecord
    fun getSevenDaysMenu(): List<MenuRecord>
    fun getTargetIngredient(id: Int): List<IngredientRecord>
    fun getSevenDaysIngredient(idList: List<Int>): List<IngredientRecord>
}