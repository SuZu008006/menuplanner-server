package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.IngredientRecord
import com.menuplanner.server.menu.entity.MenuRecord
import com.menuplanner.server.menu.entity.SeasoningRecord
import com.menuplanner.server.menu.repository.IngredientRepository
import com.menuplanner.server.menu.repository.MenuRepository
import com.menuplanner.server.menu.repository.SeasoningRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class MenuServiceTest {
    @Autowired
    private lateinit var menuRepository: MenuRepository

    @Autowired
    private lateinit var ingredientRepository: IngredientRepository

    @Autowired
    private lateinit var seasoningRepository: SeasoningRepository

    private lateinit var menuService: DefaultMenuService

    val menuRecordList = listOf(
        MenuRecord(title = "menuTitleOne", image = "menuImageOne"),
        MenuRecord(title = "menuTitleTwo", image = "menuImageTwo"),
        MenuRecord(title = "menuTitleThree", image = "menuImageThree"),
        MenuRecord(title = "menuTitleFour", image = "menuImageFour"),
        MenuRecord(title = "menuTitleFive", image = "menuImageFive"),
        MenuRecord(title = "menuTitleSix", image = "menuImageSix"),
        MenuRecord(title = "menuTitleSeven", image = "menuImageSeven"),
        MenuRecord(title = "menuTitleEight", image = "menuImageEight"),
    )
    val sevenDays = listOf(1, 2, 3, 4, 5, 6, 7)

    @BeforeEach
    fun setUpEach() {
        menuService = DefaultMenuService(
            menuRepository,
            ingredientRepository,
            seasoningRepository
        )
    }

    @Test
    fun `getTargetMenu() transforms MenuRecord from MenuRepository`() {
        menuRepository.save(MenuRecord(title = "titleOne", image = "imageOne"))
        val actualMenuId = menuRepository.findAll()[0].id
        ingredientRepository.save(
            IngredientRecord(
                id = actualMenuId,
                item = "itemOneOne",
                quantity = 1.1,
                scale = "scaleOne"
            ),
        )
        seasoningRepository.save(
            SeasoningRecord(
                id = actualMenuId,
                item = "itemTwoOne",
                quantity = 2.1,
                scale = "scaleTwo"
            ),
        )
        val actualMenu = menuService.getTargetMenu(actualMenuId)


        assertEquals(1, menuRepository.findAll().size)
        assertEquals("titleOne", actualMenu.menuRecord.title)
        assertEquals("imageOne", actualMenu.menuRecord.image)
        assertEquals(1, actualMenu.ingredientRecord.size)
        assertEquals("itemOneOne", actualMenu.ingredientRecord[0].item)
        assertEquals(1.1, actualMenu.ingredientRecord[0].quantity)
        assertEquals("scaleOne", actualMenu.ingredientRecord[0].scale)
        assertEquals(1, actualMenu.seasoningRecord.size)
        assertEquals("itemTwoOne", actualMenu.seasoningRecord[0].item)
        assertEquals(2.1, actualMenu.seasoningRecord[0].quantity)
        assertEquals("scaleTwo", actualMenu.seasoningRecord[0].scale)
    }

    @Test
    fun `getSevenDaysMenu() transforms MenuRecord from MenuRepository`() {
        menuRepository.saveAll(menuRecordList)


        val actualMenu = menuService.getSevenDaysMenu()


        assertEquals(7, actualMenu.size)
        assertNotEquals(menuRecordList, actualMenu)

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
        ingredientRepository.save(
            IngredientRecord(
                id = 1,
                item = "ingredientItem",
                quantity = 1.1,
                scale = "scaleOne"
            ),
        )


        val actualIngredient = menuService.getTargetIngredient(1)


        val expectedIngredient = IngredientRecord(
            id = 1,
            item = "ingredientItem",
            quantity = 1.1,
            scale = "scaleOne"
        )
        assertEquals(expectedIngredient.id, actualIngredient[0].id)
        assertEquals(expectedIngredient.item, actualIngredient[0].item)
        assertEquals(expectedIngredient.quantity, actualIngredient[0].quantity)
        assertEquals(expectedIngredient.scale, actualIngredient[0].scale)
    }

    @Test
    fun `getSevenDaysIngredient() transforms IngredientRecord from IngredientRepository`() {
        sevenDays.forEach {
            ingredientRepository.save(
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