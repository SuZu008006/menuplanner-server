package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.IngredientRecord
import com.menuplanner.server.menu.entity.MakeRecord
import com.menuplanner.server.menu.entity.MenuRecord
import com.menuplanner.server.menu.entity.SeasoningRecord
import com.menuplanner.server.menu.repository.IngredientRepository
import com.menuplanner.server.menu.repository.MakeRepository
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
class MenuImportServiceTest {
    @Autowired
    private lateinit var menuRepository: MenuRepository

    @Autowired
    private lateinit var ingredientRepository: IngredientRepository

    @Autowired
    private lateinit var seasoningRepository: SeasoningRepository

    @Autowired
    private lateinit var makeRepository: MakeRepository

    private lateinit var menuImportService: DefaultMenuImportService

    @BeforeEach
    fun setUp() {
        menuImportService = DefaultMenuImportService(
            menuRepository,
            ingredientRepository,
            seasoningRepository,
            makeRepository,
        )
    }

    @Test
    fun `importMenu() transforms MenuStruct from menu repository`() {
        val menuStructOne = MenuStructBuilder()
            .withMenuRecord(
                MenuRecord(title = "menuTitleOne", image = "menuImageOne")
            )
            .build()
        val menuStructTwo = MenuStructBuilder()
            .withMenuRecord(
                MenuRecord(title = "menuTitleTwo", image = "menuImageTwo")
            )
            .build()

        val menuStructList = listOf(menuStructOne, menuStructTwo)


        menuImportService.importMenu(menuStructList)


        for ((index, it) in menuStructList.withIndex()) {
            assertEquals(it.menuRecord.title, menuRepository.findAll()[index].title)
            assertEquals(it.menuRecord.image, menuRepository.findAll()[index].image)
        }
    }

    @Test
    fun `importMenu() transforms MenuStruct from ingredient repository`() {
        val menuStructOne = MenuStructBuilder()
            .withIngredientRecord(
                listOf(
                    IngredientRecord(item = "itemOneOne", quantity = 1.1, scale = "scaleOneOne"),
                    IngredientRecord(item = "itemOneTwo", quantity = 1.2, scale = "scaleOneTwo"),
                ),
            )
            .build()
        val menuStructTwo = MenuStructBuilder()
            .withIngredientRecord(
                listOf(
                    IngredientRecord(item = "itemTwoOne", quantity = 2.1, scale = "scaleOneOne"),
                    IngredientRecord(item = "itemTwoTwo", quantity = 2.2, scale = "scaleOneTwo"),
                ),
            )
            .build()

        val menuStructList = listOf(menuStructOne, menuStructTwo)


        menuImportService.importMenu(listOf(menuStructOne, menuStructTwo))


        for ((indexMenuStruct, menuStruct) in menuStructList.withIndex()) {
            for ((indexIngredient, ingredient) in menuStruct.ingredientRecord.withIndex()) {
                assertEquals(
                    menuRepository.findAll()[indexMenuStruct].id,
                    ingredientRepository.findDistinctById(menuStruct.menuRecord.id)[indexIngredient].id
                )
                assertEquals(
                    ingredient.item,
                    ingredientRepository.findDistinctById(menuStruct.menuRecord.id)[indexIngredient].item
                )
                assertEquals(
                    ingredient.quantity,
                    ingredientRepository.findDistinctById(menuStruct.menuRecord.id)[indexIngredient].quantity
                )
                assertEquals(
                    ingredient.scale,
                    ingredientRepository.findDistinctById(menuStruct.menuRecord.id)[indexIngredient].scale
                )
            }
        }
    }

    @Test
    fun `importMenu() transforms MenuStruct from seasoning repository`() {
        val menuStructOne = MenuStructBuilder()
            .withSeasoningRecord(
                listOf(
                    SeasoningRecord(item = "itemOneOne", quantity = 1.1, scale = "scaleOneOne"),
                    SeasoningRecord(item = "itemOneTwo", quantity = 1.2, scale = "scaleOneTwo"),
                ),
            )
            .build()
        val menuStructTwo = MenuStructBuilder()
            .withSeasoningRecord(
                listOf(
                    SeasoningRecord(item = "itemTwoOne", quantity = 2.1, scale = "scaleTwoOne"),
                    SeasoningRecord(item = "itemTwoTwo", quantity = 2.2, scale = "scaleTwoTwo"),
                ),
            )
            .build()

        val menuStructList = listOf(menuStructOne, menuStructTwo)


        menuImportService.importMenu(listOf(menuStructOne, menuStructTwo))


        for ((indexMenuStruct, menuStruct) in menuStructList.withIndex()) {
            for ((indexSeasoning, seasoning) in menuStruct.seasoningRecord.withIndex()) {
                assertEquals(
                    menuRepository.findAll()[indexMenuStruct].id,
                    seasoningRepository.findDistinctById(menuStruct.menuRecord.id)[indexSeasoning].id
                )
                assertEquals(
                    seasoning.item,
                    seasoningRepository.findDistinctById(menuStruct.menuRecord.id)[indexSeasoning].item
                )
                assertEquals(
                    seasoning.quantity,
                    seasoningRepository.findDistinctById(menuStruct.menuRecord.id)[indexSeasoning].quantity
                )
                assertEquals(
                    seasoning.scale,
                    seasoningRepository.findDistinctById(menuStruct.menuRecord.id)[indexSeasoning].scale
                )
            }
        }
    }

    @Test
    fun `importMenu() transforms MenuStruct from make repository`() {
        val menuStructOne = MenuStructBuilder()
            .withMakeRecord(
                listOf(
                    MakeRecord(content = "makeOneOne"),
                    MakeRecord(content = "makeOneTwo"),
                )
            )
            .build()
        val menuStructTwo = MenuStructBuilder()
            .withMakeRecord(
                listOf(
                    MakeRecord(content = "makTwoOne"),
                    MakeRecord(content = "makTwoTwo"),
                )
            )
            .build()

        val menuStructList = listOf(menuStructOne, menuStructTwo)


        menuImportService.importMenu(menuStructList)


        for ((indexMenuStruct, menuStruct) in menuStructList.withIndex()) {
            for ((indexMake, make) in menuStruct.makeRecord.withIndex()) {
                assertEquals(
                    menuRepository.findAll()[indexMenuStruct].id,
                    makeRepository.findDistinctById(menuStruct.menuRecord.id)[indexMake].id
                )
                assertEquals(
                    make.content,
                    makeRepository.findDistinctById(menuStruct.menuRecord.id)[indexMake].content
                )
            }
        }
    }
}