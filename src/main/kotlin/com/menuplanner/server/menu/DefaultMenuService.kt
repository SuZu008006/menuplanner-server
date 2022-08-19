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
    override fun getSevenDaysMenu(): List<MenuRecord> {
        return menuRepository.findAll().shuffled().slice(0..6)
    }

    override fun allIngredient(id: Int): List<IngredientRecord> {
        return ingredientRepository.findDistinctById(id)
    }
}

interface MenuService {
    fun getSevenDaysMenu(): List<MenuRecord>
    fun allIngredient(id: Int): List<IngredientRecord>
}