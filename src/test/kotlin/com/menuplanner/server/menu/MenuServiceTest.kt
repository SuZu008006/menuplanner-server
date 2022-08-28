package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.IngredientRecord
import com.menuplanner.server.menu.entity.MenuRecord
import com.menuplanner.server.menu.repository.IngredientRepository
import com.menuplanner.server.menu.repository.MenuRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class MenuServiceTest {
    @Autowired
    private lateinit var entityManager: TestEntityManager

    @Autowired
    private lateinit var menuRepository: MenuRepository

    @Autowired
    private lateinit var ingredientRepository: IngredientRepository

    private lateinit var menuService: DefaultMenuService

    val menuRecordList = listOf(
        MenuRecord(id = 1, title = "menuTitleOne", image = "menuImageOne"),
        MenuRecord(id = 2, title = "menuTitleTwo", image = "menuImageTwo"),
        MenuRecord(id = 3, title = "menuTitleThree", image = "menuImageThree"),
        MenuRecord(id = 4, title = "menuTitleFour", image = "menuImageFour"),
        MenuRecord(id = 5, title = "menuTitleFive", image = "menuImageFive"),
        MenuRecord(id = 6, title = "menuTitleSix", image = "menuImageSix"),
        MenuRecord(id = 7, title = "menuTitleSeven", image = "menuImageSeven"),
        MenuRecord(id = 8, title = "menuTitleEight", image = "menuImageEight"),
    )
    val sevenDays = listOf(1, 2, 3, 4, 5, 6, 7)

    @BeforeEach
    fun setUpEach() {
        menuService = DefaultMenuService(
            menuRepository,
            ingredientRepository
        )
    }

    @Test
    fun `getTargetMenu() transforms MenuRecord from MenuRepository`() {
        //ToDo: ingredientの確認ができていないoneToManyで作り直す必要あり？
        entityManager.persist(
            MenuRecord(title = "menuTitle", image = "menuImage")
        )
        entityManager.persist(
            IngredientRecord(
                item = "ingredientItem",
                quantity = 1.0,
                scale = "g"
            )
        )


        val actualMenu = menuService.getTargetMenu(expectedMenu()[0].id)


        assertEquals(1, expectedMenu().size)
        assertEquals("menuTitle", actualMenu.menuRecord.title)
        assertEquals("menuImage", actualMenu.menuRecord.image)
//        assertEquals(1, actualMenu.ingredientRecord.size)
//        assertEquals("ingredientItem", actualMenu.ingredientRecord[0].item)
//        assertEquals(1.0, actualMenu.ingredientRecord[0].quantity)
//        assertEquals("g", actualMenu.ingredientRecord[0].scale)
    }
    private fun expectedMenu() = menuRepository.findAll()

    @Test
    fun `getSevenDaysMenu() transforms MenuRecord from MenuRepository`() {
        menuRecordList.forEach {
            entityManager.persist(MenuRecord(title = it.title, image = it.image))
        }

        val actualMenu = menuService.getSevenDaysMenu()


        assertEquals(7, actualMenu.size)

        fun includeMenuRecordCount(expectedMenu: List<MenuRecord>, actualMenu: List<MenuRecord>): Int {
            var count = 0

            expectedMenu.forEach { expectedIt ->
                actualMenu.forEach { actualIt ->
                    if (expectedIt.title == actualIt.title) {
                        count += 1
                    }
                }
            }

            return count
        }
        assertEquals(7, includeMenuRecordCount(menuRecordList, actualMenu))
    }

    @Test
    fun `getTargetIngredient() transforms IngredientRecord from IngredientRepository`() {
        entityManager.persist(
            IngredientRecord(
                id = 1,
                item = "ingredientItem",
                quantity = (1 + 0.1),
                scale = "g"
            )
        )

        val actualIngredient = menuService.getTargetIngredient(1)


        val expectedIngredient = listOf(
            IngredientRecord(
                id = 1,
                item = "ingredientItem",
                quantity = 1.1,
                scale = "g"
            ),
        )
        assertEquals(expectedIngredient[0].id, actualIngredient[0].id)
        assertEquals(expectedIngredient[0].item, actualIngredient[0].item)
        assertEquals(expectedIngredient[0].quantity, actualIngredient[0].quantity)
        assertEquals(expectedIngredient[0].scale, actualIngredient[0].scale)
    }

    @Test
    fun `getSevenDaysIngredient() transforms IngredientRecord from IngredientRepository`() {
        sevenDays.forEach {
            entityManager.persist(
                IngredientRecord(
                    id = it,
                    item = "ingredientItem",
                    quantity = (it + 0.1),
                    scale = "g"
                )
            )
        }


        val actualIngredient = menuService.getSevenDaysIngredient(
            listOf(1, 2, 3, 4, 5, 6, 7)
        )


        sevenDays.map {
            assertEquals(it, actualIngredient[it - 1].id)
            assertEquals("ingredientItem", actualIngredient[it - 1].item)
            assertEquals(it + 0.1, actualIngredient[it - 1].quantity)
            assertEquals("g", actualIngredient[it - 1].scale)
        }
    }
}