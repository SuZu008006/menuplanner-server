package com.menuplanner.server.menu

import com.google.gson.Gson
import com.menuplanner.server.menu.entity.IngredientRecord
import com.menuplanner.server.menu.entity.MakeRecord
import com.menuplanner.server.menu.entity.MenuRecord
import com.menuplanner.server.menu.entity.SeasoningRecord
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class MenuPutControllerTest {
    private lateinit var spyStubMenuPutService: SpyStubMenuPutService
    private lateinit var menuPutController: MenuPutController
    private lateinit var resultActions: ResultActions

    private val expectedMenuStruct = MenuStructBuilder()
        .withMenuRecord(
            MenuRecord(title = "menuTitleOne", image = "menuImageOne")
        )
        .withIngredientRecord(
            listOf(
                IngredientRecord(item = "itemOneOne", quantity = 1.1, scale = "scaleOneOne"),
                IngredientRecord(item = "itemOneTwo", quantity = 1.2, scale = "scaleOneTwo"),
            ),
        )
        .withSeasoningRecord(
            listOf(
                SeasoningRecord(item = "itemOneOne", quantity = 11.1, scale = "scaleOneOne"),
                SeasoningRecord(item = "itemOneTwo", quantity = 11.2, scale = "scaleOneTwo"),
            ),
        )
        .withMakeRecord(
            listOf(
                MakeRecord(content = "makeOneOne"),
                MakeRecord(content = "makeOneTwo"),
            )
        )
        .build()

    @BeforeEach
    fun setUp() {
        spyStubMenuPutService = SpyStubMenuPutService()
        menuPutController = MenuPutController(spyStubMenuPutService)

        val jsonMenuStruct = Gson().toJson(expectedMenuStruct)

        resultActions = MockMvcBuilders.standaloneSetup(menuPutController)
            .build()
            .perform(
                MockMvcRequestBuilders
                    .put("/api/menu/update")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonMenuStruct)
            )
    }

    @Test
    fun `put update menu returns ok`() {
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated)
    }

    @Test
    fun `calls service(putMenu)`() {
        assertEquals(true, spyStubMenuPutService.putMenu_wasCalled)
    }

    @Test
    fun `put update menu passes JSON file of menu data to service for MenuStruct`() {
        assertEquals(expectedMenuStruct, spyStubMenuPutService.putMenuFromJSON_argument_JsonFile)
    }
}