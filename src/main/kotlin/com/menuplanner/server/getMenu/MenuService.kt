package com.menuplanner.server.getMenu

import com.menuplanner.server.getMenu.entity.IngredientRecord
import com.menuplanner.server.getMenu.repository.IngredientRepository
import com.menuplanner.server.getMenu.entity.MenuRecord
import com.menuplanner.server.getMenu.repository.MenuRepository
import org.springframework.stereotype.Service

@Service
class DefaultMenuService(
    private val menuRepository: MenuRepository,
    private val ingredientRepository: IngredientRepository,
) : MenuService {
    override fun allMenu(): List<MenuRecord> {
        return menuRepository.findAll()
    }

    override fun allIngredient(id: Int): List<IngredientRecord> {
        return ingredientRepository.findDistinctById(id)
    }
}

interface MenuService {
    fun allMenu(): List<MenuRecord>
    fun allIngredient(id: Int): List<IngredientRecord>
}