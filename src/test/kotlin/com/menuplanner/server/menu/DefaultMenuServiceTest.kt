package com.menuplanner.server.menu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DefaultMenuServiceTest {
    private lateinit var spyStubMenuRepository: SpyStubMenuRepository
    private lateinit var spyStubIngredientRepository: SpyStubIngredientRepository
    private lateinit var menuService: DefaultMenuService

    @Test
    fun `allMenu() transforms MenuRecord from MenuRepository`() {
        spyStubMenuRepository = SpyStubMenuRepository()
        spyStubMenuRepository.allMenu_returnValue = listOf(
            MenuRecord(title = "menuTitleOne")
        )
        spyStubIngredientRepository = SpyStubIngredientRepository()
        spyStubIngredientRepository.allIngredient_returnValue = listOf(
        )

        menuService = DefaultMenuService(spyStubMenuRepository, spyStubIngredientRepository)


        val actualMenu = menuService.allMenu()


        val expectedMenu = listOf(
            MenuRecord(title = "menuTitleOne")
        )
        assertEquals(expectedMenu, actualMenu)
    }

    @Test
    fun `allIngredient() transforms IngredientRecord from IngredientRepository`() {
        spyStubMenuRepository = SpyStubMenuRepository()
        spyStubMenuRepository.allMenu_returnValue = listOf(
        )
        spyStubIngredientRepository = SpyStubIngredientRepository()
        spyStubIngredientRepository.allIngredient_returnValue = listOf(
            IngredientRecord(id = 1, item = "ingredientItemOne", quantity = 10, weight = 100),
            IngredientRecord(id = 1, item = "ingredientItemTwo", quantity = 20, weight = 200),
        )

        menuService = DefaultMenuService(spyStubMenuRepository, spyStubIngredientRepository)


        val actualIngredient = menuService.allIngredient(1)


        val expectedIngredient = listOf(
            IngredientRecord(id = 1, item = "ingredientItemOne", quantity = 10, weight = 100),
            IngredientRecord(id = 1, item = "ingredientItemTwo", quantity = 20, weight = 200),
        )
        assertEquals(expectedIngredient, actualIngredient)
    }
}