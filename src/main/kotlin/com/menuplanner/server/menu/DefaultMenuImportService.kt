package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.IngredientRecord
import com.menuplanner.server.menu.repository.IngredientRepository
import com.menuplanner.server.menu.entity.MenuStruct
import com.menuplanner.server.menu.repository.MenuRepository
import org.springframework.stereotype.Service

@Service
class DefaultMenuImportService(
    private val menuRepository: MenuRepository,
    private val ingredientRepository: IngredientRepository,
) : MenuImportService {
    override fun importMenu(menuStructList: List<MenuStruct>): List<MenuStruct> {
        return menuStructList.map {
            menuRepository.save(it.menuRecord)

            val importMenuStruct = it.ingredientRecord.map { ingredientRecord ->
                IngredientRecord(
                    ingredientRecord.ingredient_id,
                    it.menuRecord.id,
                    ingredientRecord.item,
                    ingredientRecord.quantity,
                    ingredientRecord.scale,
                )
            }

            ingredientRepository.saveAll(importMenuStruct)

            val menuRecord =
                menuRepository.findDistinctById(it.menuRecord.id)
            val ingredientRecordList =
                ingredientRepository.findDistinctById(it.menuRecord.id)

            return@map MenuStruct(menuRecord, ingredientRecordList, emptyList())
        }
    }
}

interface MenuImportService {
    fun importMenu(menuStructList: List<MenuStruct>): List<MenuStruct>
}