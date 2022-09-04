package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.IngredientRecord
import com.menuplanner.server.menu.entity.MenuRecord
import com.menuplanner.server.menu.entity.MenuStruct
import com.menuplanner.server.menu.entity.SeasoningRecord
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

class MenuControllerTest {
    private lateinit var spyStubMenuService: SpyStubMenuService

    @BeforeEach
    fun setUp() {
        spyStubMenuService = SpyStubMenuService()
    }

    @Test
    fun `when there are menu, menu endpoint returns list of menu`() {
        spyStubMenuService.menu_return = listOf(
            MenuRecord(title = "menuTitleOne", image = "menuImageOne")
        )


        standaloneSetup(MenuController(spyStubMenuService))
            .build()
            .perform(get("/api/menu"))


            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].title", equalTo("menuTitleOne")))
            .andExpect(jsonPath("$[0].image", equalTo("menuImageOne")))
    }

    @Test
    fun `when there are ingredient, menu_{id} endpoint returns menuStruct`() {
        spyStubMenuService.menuStruct_return =
            MenuStruct(
                MenuRecord(title = "titleOne", image = "imageOne"),
                listOf(
                    IngredientRecord(item = "itemOne", quantity = 1.0, scale = "scaleOne")
                ),
                listOf(
                    SeasoningRecord(item = "itemTwo", quantity = 2.0, scale = "scaleTwo")
                ),
                emptyList()
            )

        val ID = 9999


        standaloneSetup(MenuController(spyStubMenuService))
            .build()
            .perform(get("/api/menu/${ID}"))


            .andExpect(status().isOk)
            .andExpect(
                jsonPath(
                    buildString { append("$.menuRecord.title") }, equalTo("titleOne")
                )
            )
            .andExpect(
                jsonPath(
                    buildString { append("$.menuRecord.image") }, equalTo("imageOne")
                )
            )
            .andExpect(
                jsonPath(
                    buildString { append("$.ingredientRecord[0].item") }, equalTo("itemOne")
                )
            )
            .andExpect(
                jsonPath(
                    buildString { append("$.ingredientRecord[0].quantity") }, equalTo(1.0)
                )
            )
            .andExpect(
                jsonPath(
                    buildString { append("$.ingredientRecord[0].scale") }, equalTo("scaleOne")
                )
            )
            .andExpect(
                jsonPath(
                    buildString { append("$.seasoningRecord[0].item") }, equalTo("itemTwo")
                )
            )
            .andExpect(
                jsonPath(
                    buildString { append("$.seasoningRecord[0].quantity") }, equalTo(2.0)
                )
            )
            .andExpect(
                jsonPath(
                    buildString { append("$.seasoningRecord[0].scale") }, equalTo("scaleTwo")
                )
            )
    }

    @Test
    fun `when there are ingredient, menu_{id1}+{id2}+{id3}+{id4}+{id5}+{id6}+{id7} endpoint returns list of ingredient`() {
        spyStubMenuService.sevenDaysIngredient_return = listOf(
            IngredientRecord(item = "ingredientItemOne", quantity = 1.0, scale = "g"),
        )

        val idList = listOf(1111, 2222, 3333, 4444, 5555, 6666, 7777)
        var sevenDaysIdList = ""
        idList.forEach {
            sevenDaysIdList = "${sevenDaysIdList}+${it}"
        }
        sevenDaysIdList = sevenDaysIdList.drop(1)


        standaloneSetup(MenuController(spyStubMenuService))
            .build()
            .perform(get("/api/menu/summary/${sevenDaysIdList}"))


            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].item", equalTo("ingredientItemOne")))
            .andExpect(jsonPath("$[0].quantity", equalTo(1.0)))
            .andExpect(jsonPath("$[0].scale", equalTo("g")))
    }
}