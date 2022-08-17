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
        entityManager.persist(MenuRecord(title = "menuTitleOne"))


        val actualMenu = menuService.allMenu()


        val expectedMenu = listOf(
            MenuRecord(title = "menuTitleOne")
        )
        assertEquals(expectedMenu[0].title, actualMenu[0].title)
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