package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.IngredientRecord
import com.menuplanner.server.menu.entity.MenuRecord
import com.menuplanner.server.menu.entity.MenuStruct
import com.menuplanner.server.menu.repository.IngredientRepository
import com.menuplanner.server.menu.repository.MenuRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class MenuImportServiceTest {
    @Autowired
    private lateinit var menuRepository: MenuRepository

    @Autowired
    private lateinit var ingredientRepository: IngredientRepository

    private lateinit var menuImportService: DefaultMenuImportService

    @BeforeEach
    fun setUp() {
        menuImportService = DefaultMenuImportService(
            menuRepository,
            ingredientRepository
        )
    }

    @Test
    fun `importMenu() transforms MenuStruct from Repository(Menu and Ingredient)`() {
        val menuStruct = MenuStruct(
            MenuRecord(title = "menuTitleTwo"),
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
            )
        )


        val actualMenu = menuImportService.importMenu(menuStruct)


        val expectedMenuRecord = MenuRecord(
            title = "menuTitleTwo",
        )

        val expectedIngredientRecord = listOf(
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
        )

        assertEquals(actualMenu.ingredientRecord[0].id, actualMenu.ingredientRecord[0].id)

        assertEquals(expectedMenuRecord.title, actualMenu.menuRecord.title)
        assertEquals(expectedIngredientRecord[0].item, actualMenu.ingredientRecord[0].item)
        assertEquals(expectedIngredientRecord[0].quantity, actualMenu.ingredientRecord[0].quantity)
        assertEquals(expectedIngredientRecord[0].scale, actualMenu.ingredientRecord[0].scale)
    }
}