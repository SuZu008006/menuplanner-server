package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.*
import com.menuplanner.server.menu.repository.IngredientRepository
import com.menuplanner.server.menu.repository.MakeRepository
import com.menuplanner.server.menu.repository.MenuRepository
import com.menuplanner.server.menu.repository.SeasoningRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class MenuPutServiceTest {
    @Autowired
    private lateinit var menuRepository: MenuRepository
    @Autowired
    private lateinit var ingredientRepository: IngredientRepository
    @Autowired
    private lateinit var seasoningRepository: SeasoningRepository
    @Autowired
    private lateinit var makeRepository: MakeRepository

    private lateinit var menuPutService: DefaultMenuPutService

    @BeforeEach
    fun setUp() {
        menuPutService = DefaultMenuPutService(
            menuRepository = menuRepository,
            ingredientRepository = ingredientRepository,
            seasoningRepository = seasoningRepository,
            makeRepository = makeRepository,
        )
    }

    @Nested
    inner class `when putMenu() is overwrite initial data` {
        @Test
        fun `menuRepository with update data`() {
            val targetID = 1
            val initialMenuRecord = MenuRecord(
                id = targetID,
                title = "initialTitle",
                image = "initialImage"
            )
            menuRepository.save(initialMenuRecord)

            val updatedMenuStruct = MenuStructBuilder()
                .withMenuRecord(
                    MenuRecord(
                        id = targetID,
                        title = "updatedTitle",
                        image = "updatedImage"
                    )
                )
                .build()


            menuPutService.putTargetMenu(menuStruct = updatedMenuStruct)


            assertEquals(
                updatedMenuStruct.menuRecord, menuRepository.findDistinctById(targetID)
            )
        }

        @Test
        fun `ingredientRepository with update data`() {
            val targetID = 1
            val initialIngredientRecordList = listOf(
                IngredientRecord(
                    id = targetID,
                    ingredientId = 1,
                    item = "initialItemOne",
                    quantity = 0.1,
                    scale = "initialScaleOne"
                ),
                IngredientRecord(
                    id = targetID,
                    ingredientId = 2,
                    item = "initialItemTwo",
                    quantity = 0.1,
                    scale = "initialScaleTwo"
                ),
            )
            ingredientRepository.saveAll(initialIngredientRecordList)

            val updatedMenuStruct = MenuStructBuilder()
                .withIngredientRecord(
                    listOf(
                        IngredientRecord(
                            id = targetID,
                            ingredientId = 1,
                            item = "updatedItemOne",
                            quantity = 0.1,
                            scale = "updatedScaleOne"
                        ),
                        IngredientRecord(
                            id = targetID,
                            ingredientId = 2,
                            item = "updatedItemTwo",
                            quantity = 0.1,
                            scale = "updatedScaleTwo"
                        ),
                    )
                )
                .build()


            menuPutService.putTargetMenu(menuStruct = updatedMenuStruct)


            assertEquals(updatedMenuStruct.ingredientRecord, ingredientRepository.findDistinctById(targetID))
        }

        @Test
        fun `seasoningRepository with update data`() {
            val targetID = 1
            val initialSeasoningRecordList = listOf(
                SeasoningRecord(
                    id = targetID,
                    seasoningId = 1,
                    item = "initialItemOne",
                    quantity = 0.1,
                    scale = "initialScaleOne"
                ),
                SeasoningRecord(
                    id = targetID,
                    seasoningId = 2,
                    item = "initialItemTwo",
                    quantity = 0.1,
                    scale = "initialScaleTwo"
                ),
            )
            seasoningRepository.saveAll(initialSeasoningRecordList)

            val updatedMenuStruct = MenuStructBuilder()
                .withSeasoningRecord(
                    listOf(
                        SeasoningRecord(
                            id = targetID,
                            seasoningId = 1,
                            item = "updatedItemOne",
                            quantity = 0.1,
                            scale = "updatedScaleOne"
                        ),
                        SeasoningRecord(
                            id = targetID,
                            seasoningId = 2,
                            item = "updatedItemTwo",
                            quantity = 0.1,
                            scale = "updatedScaleTwo"
                        ),
                    )
                )
                .build()


            menuPutService.putTargetMenu(menuStruct = updatedMenuStruct)


            assertEquals(updatedMenuStruct.seasoningRecord, seasoningRepository.findDistinctById(targetID))
        }

        @Test
        fun `makeRepository with update data`() {
            val targetID = 1
            val initialMakeRecordList = listOf(
                MakeRecord(
                    id = targetID,
                    makeId = 1,
                    content = "initialContentOne",
                ),
                MakeRecord(
                    id = targetID,
                    makeId = 2,
                    content = "initialContentTwo",
                ),
            )
            makeRepository.saveAll(initialMakeRecordList)

            val updatedMenuStruct = MenuStructBuilder()
                .withMakeRecord(
                    listOf(
                        MakeRecord(
                            id = targetID,
                            makeId = 1,
                            content = "updatedContentOne",
                        ),
                        MakeRecord(
                            id = targetID,
                            makeId = 2,
                            content = "updatedContentTwo",
                        ),
                    )
                )
                .build()


            menuPutService.putTargetMenu(menuStruct = updatedMenuStruct)


            assertEquals(updatedMenuStruct.makeRecord, makeRepository.findDistinctById(targetID))
        }
    }
}