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
        spyStubMenuRepository.allMenu_returnValue = mutableListOf(
            MenuRecord(title = "menuTitleOne")
        )


        val actualMenu = menuService.allMenu()


        val expectedMenu = mutableListOf(
            MenuRecord(title = "menuTitleOne")
        )
        assertEquals(expectedMenu, actualMenu)
    }

    @Test
    fun `allIngredient() transforms IngredientRecord from IngredientRepository`() {
        spyStubIngredientRepository.allIngredient_returnValue = mutableListOf(
            IngredientRecord(item = "ingredientItemOne", quantity = 110.0, scale = "g"),
            IngredientRecord(item = "ingredientItemTwo", quantity = 120.0, scale = "g"),
        )


        val actualIngredient = menuService.allIngredient(9999)


        val expectedIngredient = mutableListOf(
            IngredientRecord(item = "ingredientItemOne", quantity = 110.0, scale = "g"),
            IngredientRecord(item = "ingredientItemTwo", quantity = 120.0, scale = "g"),
        )
        assertEquals(expectedIngredient, actualIngredient)
    }
}