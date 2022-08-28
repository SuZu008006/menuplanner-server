package com.menuplanner.server.menu

import com.google.gson.Gson
import com.menuplanner.server.menu.entity.IngredientRecord
import com.menuplanner.server.menu.entity.MenuRecord
import com.menuplanner.server.menu.entity.MenuStruct
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

        val jsonMenuStruct = Gson().toJson(
            listOf(
                MenuStruct(
                    MenuRecord(
                        title = "titleOne",
                        image = "imageOne",
                    ),
                    listOf(
                        IngredientRecord(
                            item = "itemOneOne",
                            quantity = 1.1,
                            scale = "scaleOneOne"
                        ),
                        IngredientRecord(
                            item = "itemOneTwo",
                            quantity = 1.2,
                            scale = "scaleOneTwo"
                        ),
                    ),
                ),
                MenuStruct(
                    MenuRecord(
                        title = "titleTwo",
                        image = "imageTwo",
                    ),
                    listOf(
                        IngredientRecord(
                            item = "ingredientItemTwoOne",
                            quantity = 2.1,
                            scale = "scaleTwoOne"
                        ),
                        IngredientRecord(
                            item = "ingredientItemTwoTwo",
                            quantity = 2.2,
                            scale = "scaleTwoTwo"
                        ),
                    ),
                ),
            )
        )

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