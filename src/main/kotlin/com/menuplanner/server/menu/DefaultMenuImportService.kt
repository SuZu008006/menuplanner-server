package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.IngredientRecord
import com.menuplanner.server.menu.entity.MakeRecord
import com.menuplanner.server.menu.repository.IngredientRepository
import com.menuplanner.server.menu.entity.MenuStruct
import com.menuplanner.server.menu.entity.SeasoningRecord
import com.menuplanner.server.menu.repository.MakeRepository
import com.menuplanner.server.menu.repository.MenuRepository
import com.menuplanner.server.menu.repository.SeasoningRepository
import org.springframework.stereotype.Service

@Service
class DefaultMenuImportService(
    private val menuRepository: MenuRepository,
    private val ingredientRepository: IngredientRepository,
    private val seasoningRepository: SeasoningRepository,
    private val makeRepository: MakeRepository,
) : MenuImportService {
    override fun importMenu(menuStructList: List<MenuStruct>) {
        menuStructList.map {
            menuRepository.save(it.menuRecord)

            val importIngredient = it.ingredientRecord.map { ingredientRecord ->
                IngredientRecord(
                    ingredientRecord.ingredientId,
                    it.menuRecord.id,
                    ingredientRecord.item,
                    ingredientRecord.quantity,
                    ingredientRecord.scale,
                )
            }
            ingredientRepository.saveAll(importIngredient)

            val importSeasoning = it.seasoningRecord.map { seasoningRecord ->
                SeasoningRecord(
                    seasoningRecord.seasoningId,
                    it.menuRecord.id,
                    seasoningRecord.item,
                    seasoningRecord.quantity,
                    seasoningRecord.scale,
                )
            }
            seasoningRepository.saveAll(importSeasoning)

            val importMake = it.makeRecord.map { makeRecord ->
                MakeRecord(
                    makeRecord.makeId,
                    it.menuRecord.id,
                    makeRecord.content,
                )
            }
            makeRepository.saveAll(importMake)
        }
    }
}

interface MenuImportService {
    fun importMenu(menuStructList: List<MenuStruct>)
}