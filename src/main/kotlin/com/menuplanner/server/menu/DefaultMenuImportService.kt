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
    override fun importMenu(menuStruct: MenuStruct): MenuStruct {
        menuRepository.save(menuStruct.menuRecord)

        val importMenuStruct = menuStruct.ingredientRecord.map {
            IngredientRecord(
                it.ingredient_id,
                menuStruct.menuRecord.id,
                it.item,
                it.quantity,
                it.scale,
            )
        }

        ingredientRepository.saveAll(importMenuStruct)

        val menuRecordList = menuRepository.findDistinctById(menuStruct.menuRecord.id)
        val ingredientRecordList = ingredientRepository.findDistinctById(menuStruct.menuRecord.id)

        return MenuStruct(menuRecordList, ingredientRecordList)
    }
}

interface MenuImportService {
    fun importMenu(menuStruct: MenuStruct): MenuStruct
}