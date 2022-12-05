package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.*
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
        val targetID = 1
        val expectMenuStructList = listOf(
            MenuStructBuilder()
                .withMenuRecord(
                    MenuRecord(
                        id = targetID,
                        title = "menuTitleOne",
                        image = "menuImageOne"
                    )
                )
                .build()
        )


        menuImportService.importMenu(expectMenuStructList)


        assertEquals(1, menuRepository.count())
        assertEquals(expectMenuStructList[0].menuRecord, menuRepository.findAll()[0])
    }

    @Test
    fun `importMenu() transforms MenuStruct from ingredient repository`() {
        val targetID = 1
        val expectMenuStructList = listOf(
            MenuStructBuilder()
                .withMenuRecord(
                    MenuRecord(id = targetID)
                )
                .withIngredientRecord(
                    listOf(
                        IngredientRecord(
                            id = targetID,
                            ingredientId = targetID,
                            item = "itemOneOne",
                            quantity = 1.1,
                            scale = "scaleOneOne"
                        ),
                        IngredientRecord(
                            id = targetID,
                            ingredientId = targetID + 1,
                            item = "itemOneTwo",
                            quantity = 1.2,
                            scale = "scaleOneTwo"
                        ),
                    ),
                )
                .build()
        )


        menuImportService.importMenu(expectMenuStructList)


        assertEquals(1, menuRepository.count())
        assertEquals(2, ingredientRepository.count())
        assertEquals(expectMenuStructList[0].ingredientRecord, ingredientRepository.findDistinctById(targetID))
    }

    @Test
    fun `importMenu() transforms MenuStruct from seasoning repository`() {
        val targetID = 1
        val expectedMenuStructList = listOf(
            MenuStructBuilder()
                .withMenuRecord(
                    MenuRecord(id = targetID)
                )
                .withSeasoningRecord(
                    listOf(
                        SeasoningRecord(
                            id = targetID,
                            seasoningId = targetID,
                            item = "itemOneOne",
                            quantity = 1.1,
                            scale = "scaleOneOne"
                        ),
                        SeasoningRecord(
                            id = targetID,
                            seasoningId = targetID + 1,
                            item = "itemOneTwo",
                            quantity = 1.2,
                            scale = "scaleOneTwo"
                        ),
                    ),
                )
                .build()
        )


        menuImportService.importMenu(expectedMenuStructList)


        assertEquals(1, menuRepository.count())
        assertEquals(2, seasoningRepository.count())
        assertEquals(expectedMenuStructList[0].seasoningRecord, seasoningRepository.findDistinctById(targetID))
    }

    @Test
    fun `importMenu() transforms MenuStruct from make repository`() {
        val targetID = 1
        val expectMenuStructList = listOf(
            MenuStructBuilder()
            .withMenuRecord(
                MenuRecord(id = targetID)
            )
            .withMakeRecord(
                listOf(
                    MakeRecord(
                        id = targetID,
                        makeId = targetID,
                        content = "makeOneOne"
                    ),
                    MakeRecord(
                        id = targetID,
                        makeId = targetID + 1,
                        content = "makeOneTwo"
                    ),
                )
            )
            .build()
        )


        menuImportService.importMenu(expectMenuStructList)


        assertEquals(1,menuRepository.count())
        assertEquals(2,makeRepository.count())
        assertEquals(expectMenuStructList[0].makeRecord,makeRepository.findDistinctById(targetID))
    }
}