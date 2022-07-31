package com.menuplanner.server.menu

import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

class MenuControllerTest {
    private lateinit var stubMenuService: StubMenuService

    @Test
    fun `when there are menu, menu endpoint returns list of menu`() {
        stubMenuService = StubMenuService()
        stubMenuService.allMenu_return = listOf(
            MenuRecord(title = "menuTitleOne")
        )

        standaloneSetup(MenuController(stubMenuService))
            .build()
            .perform(get("/api/menu"))


            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].title", equalTo("menuTitleOne")))
    }
}