package com.menuplanner.server.menu

import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

class MenuControllerTest {
    private lateinit var stubMenuService: StubMenuService

    @BeforeEach
    fun setUp() {
        stubMenuService = StubMenuService()
    }

    @Test
    fun `when there are menu, menu endpoint returns list of menu`() {
        stubMenuService.allMenu_return = listOf(
            MenuRecord(title = "menuTitleOne")
        )


        standaloneSetup(MenuController(stubMenuService))
            .build()
            .perform(get("/api/menu"))


            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].title", equalTo("menuTitleOne")))
    }

    @Test
    fun `when there are ingredient, menu_{menuCode} endpoint returns list of ingredient`() {
        stubMenuService.allIngredient_return = listOf(
            IngredientRecord(item = "ingredientItemOne", quantity = "大さじ1"),
            IngredientRecord(item = "ingredientItemTwo", quantity = "大さじ2"),
        )

        val MENU_CODE = 9999


        standaloneSetup(MenuController(stubMenuService))
            .build()
            .perform(get("/api/menu/${MENU_CODE}"))


            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].item", equalTo("ingredientItemOne")))
            .andExpect(jsonPath("$[0].quantity", equalTo("大さじ1")))
            .andExpect(jsonPath("$[1].item", equalTo("ingredientItemTwo")))
            .andExpect(jsonPath("$[1].quantity", equalTo("大さじ2")))
    }
}