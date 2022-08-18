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
        val menuStruct =
            listOf(
                MenuStruct(
                    MenuRecord(title = "menuTitleOne"),
                    listOf(
                        IngredientRecord(
                            item = "ingredientItemOneOne",
                            quantity = 1.1,
                            scale = "scaleOneOne"
                        ),
                        IngredientRecord(
                            item = "ingredientItemOneTwo",
                            quantity = 1.2,
                            scale = "scaleOneTwo"
                        ),
                    )
                ),
                MenuStruct(
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
            )


        val actualMenuStructList = menuImportService.importMenu(menuStruct)


        val expectedMenuRecord = listOf(
            MenuRecord(
                title = "menuTitleOne",
            ),
            MenuRecord(
                title = "menuTitleTwo",
            ),
        )


        val expectedIngredientRecord = listOf(
            listOf(
                IngredientRecord(
                    item = "ingredientItemOneOne",
                    quantity = 1.1,
                    scale = "scaleOneOne"
                ),
                IngredientRecord(
                    item = "ingredientItemOneTwo",
                    quantity = 1.2,
                    scale = "scaleOneTwo"
                ),
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
            )
        )

        for ((structIndex, struct) in actualMenuStructList.withIndex()) {
            assertEquals(
                struct.menuRecord.id,
                struct.ingredientRecord[0].id
            )

            assertEquals(
                expectedMenuRecord[structIndex].title,
                struct.menuRecord.title
            )
            for ((ingredientIndex, ingredient) in struct.ingredientRecord.withIndex()) {
                assertEquals(
                    expectedIngredientRecord[structIndex][ingredientIndex].item,
                    ingredient.item
                )
                assertEquals(
                    expectedIngredientRecord[structIndex][ingredientIndex].quantity,
                    ingredient.quantity
                )
                assertEquals(
                    expectedIngredientRecord[structIndex][ingredientIndex].scale,
                    ingredient.scale
                )
            }
        }

    }
}