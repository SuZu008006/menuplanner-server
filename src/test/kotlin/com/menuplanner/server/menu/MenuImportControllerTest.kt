package com.menuplanner.server.menu

import com.google.gson.Gson
import com.menuplanner.server.menu.entity.IngredientRecord
import com.menuplanner.server.menu.entity.MenuRecord
import com.menuplanner.server.menu.entity.SeasoningRecord
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class MenuImportControllerTest {
    private lateinit var spyStubMenuImportService: SpyStubMenuImportService
    private lateinit var menuImportController: MenuImportController
    private lateinit var resultActions: ResultActions

    @BeforeEach
    fun setUp() {
        spyStubMenuImportService = SpyStubMenuImportService()
        menuImportController = MenuImportController(spyStubMenuImportService)

        val menuStructOne = MenuStructBuilder()
            .withMenuRecord(
                MenuRecord(title = "menuTitleOne", image = "menuImageOne")
            )
            .ingredientRecord(
                listOf(
                    IngredientRecord(item = "itemOneOne", quantity = 1.1, scale = "scaleOneOne"),
                    IngredientRecord(item = "itemOneTwo", quantity = 1.2, scale = "scaleOneTwo"),
                ),
            )
            .seasoningRecord(
                listOf(
                    SeasoningRecord(item = "itemOneOne", quantity = 11.1, scale = "scaleOneOne"),
                    SeasoningRecord(item = "itemOneTwo", quantity = 11.2, scale = "scaleOneTwo"),
                ),
            )
            .build()
        val menuStructTwo = MenuStructBuilder()
            .withMenuRecord(
                MenuRecord(title = "menuTitleTwo", image = "menuImageTwo")
            )
            .ingredientRecord(
                listOf(
                    IngredientRecord(item = "itemTwoOne", quantity = 2.1, scale = "scaleOneOne"),
                    IngredientRecord(item = "itemTwoTwo", quantity = 2.2, scale = "scaleOneTwo"),
                ),
            )
            .seasoningRecord(
                listOf(
                    SeasoningRecord(item = "itemTwoOne", quantity = 12.1, scale = "scaleTwoOne"),
                    SeasoningRecord(item = "itemTwoTwo", quantity = 12.2, scale = "scaleTwoTwo"),
                ),
            )
            .build()
        val menuStructList = listOf(menuStructOne, menuStructTwo)

        val jsonMenuStruct = Gson().toJson(menuStructList)

        resultActions = MockMvcBuilders.standaloneSetup(menuImportController)
            .build()
            .perform(
                MockMvcRequestBuilders
                    .post("/api/menu/import")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonMenuStruct)
            )
    }

    @Test
    fun `calls service(importMenu)`() {
        assertEquals(true, spyStubMenuImportService.importMenu_wasCalled)
    }
}