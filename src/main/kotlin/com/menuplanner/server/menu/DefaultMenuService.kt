package com.menuplanner.server.menu

import org.springframework.stereotype.Service

@Service
class DefaultMenuService(
    private val menuRepository: MenuRepository,
    private val ingredientRepository: IngredientRepository,
) : MenuService {
    override fun allMenu(): List<MenuRecord> {
        return menuRepository.findAll()
    }

    override fun allIngredient(id: Long): List<IngredientRecord> {
        return ingredientRepository.findDistinctById(id)
    }
}

interface MenuService {
    fun allMenu(): List<MenuRecord>
    fun allIngredient(id: Long): List<IngredientRecord>
}