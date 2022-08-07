package com.menuplanner.server.menu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DefaultMenuServiceTest {
    private lateinit var spyStubMenuRepository: SpyStubMenuRepository
    private lateinit var spyStubIngredientRepository: SpyStubIngredientRepository
    private lateinit var menuService: DefaultMenuService

    @BeforeEach
    fun setUp() {
        spyStubMenuRepository = SpyStubMenuRepository()
        spyStubIngredientRepository = SpyStubIngredientRepository()

        menuService = DefaultMenuService(
            spyStubMenuRepository,
            spyStubIngredientRepository
        )
    }

    @Test
    fun `allMenu() transforms MenuRecord from MenuRepository`() {
        spyStubMenuRepository.allMenu_returnValue = listOf(
            MenuRecord(title = "menuTitleOne")
        )


        val actualMenu = menuService.allMenu()


        val expectedMenu = listOf(
            MenuRecord(title = "menuTitleOne")
        )
        assertEquals(expectedMenu, actualMenu)
    }

    @Test
    fun `allIngredient() transforms IngredientRecord from IngredientRepository`() {
        spyStubIngredientRepository.allIngredient_returnValue = listOf(
            IngredientRecord(item = "ingredientItemOne", quantity = 10, weight = 100),
            IngredientRecord(item = "ingredientItemTwo", quantity = 20, weight = 200),
        )


        val actualIngredient = menuService.allIngredient(9999)


        val expectedIngredient = listOf(
            IngredientRecord(item = "ingredientItemOne", quantity = 10, weight = 100),
            IngredientRecord(item = "ingredientItemTwo", quantity = 20, weight = 200),
        )
        assertEquals(expectedIngredient, actualIngredient)
    }
}