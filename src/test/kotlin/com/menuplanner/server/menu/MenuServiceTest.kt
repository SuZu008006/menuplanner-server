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


        val expectedMenu = mutableListOf(
            MenuRecord(id = 1, title = "menuTitleOne")
        )
        assertEquals(expectedMenu, actualMenu)
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


        val expectedIngredient = mutableListOf(
            IngredientRecord(id = 1, ingredient_id = 1, item = "ingredientItemOne", quantity = 110.0, scale = "g"),
            IngredientRecord(id = 1, ingredient_id = 2, item = "ingredientItemTwo", quantity = 120.0, scale = "g"),
        )
        assertEquals(expectedIngredient, actualIngredient)
    }
}