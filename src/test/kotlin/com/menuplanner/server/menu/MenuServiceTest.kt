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

    @BeforeEach
    fun setUp() {
        menuService = DefaultMenuService(
            menuRepository,
            ingredientRepository
        )
    }

    @Test
    fun `allMenu() transforms MenuRecord from MenuRepository`() {
        val menuRecordList = listOf(
            MenuRecord(id = 1, title = "menuTitleOne"),
            MenuRecord(id = 2, title = "menuTitleTwo"),
            MenuRecord(id = 3, title = "menuTitleThree"),
            MenuRecord(id = 4, title = "menuTitleFour"),
            MenuRecord(id = 5, title = "menuTitleFive"),
            MenuRecord(id = 6, title = "menuTitleSix"),
            MenuRecord(id = 7, title = "menuTitleSeven"),
            MenuRecord(id = 8, title = "menuTitleEight"),
        )
        menuRecordList.forEach {
            entityManager.persist(MenuRecord(title = it.title))
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
    fun `allIngredient() transforms IngredientRecord from IngredientRepository`() {
        entityManager.persist(
            IngredientRecord(
                id = 1,
                item = "ingredientItemOne",
                quantity = 110.0,
                scale = "g"
            )
        )
        entityManager.persist(
            IngredientRecord(
                id = 1,
                item = "ingredientItemTwo",
                quantity = 120.0,
                scale = "g"
            )
        )
        entityManager.persist(
            IngredientRecord(
                id = 2,
                item = "ingredientTwoOne",
                quantity = 210.0,
                scale = "g"
            )
        )


        val actualIngredient = menuService.allIngredient(1)


        val expectedIngredient = listOf(
            IngredientRecord(
                id = 1,
                item = "ingredientItemOne",
                quantity = 110.0,
                scale = "g"
            ),
            IngredientRecord(
                id = 1,
                item = "ingredientItemTwo",
                quantity = 120.0,
                scale = "g"
            ),
        )
        assertEquals(expectedIngredient[0].id, actualIngredient[0].id)
        assertEquals(expectedIngredient[0].item, actualIngredient[0].item)
        assertEquals(expectedIngredient[0].quantity, actualIngredient[0].quantity)
        assertEquals(expectedIngredient[0].quantity, actualIngredient[0].quantity)
        assertEquals(expectedIngredient[0].scale, actualIngredient[0].scale)
    }
}