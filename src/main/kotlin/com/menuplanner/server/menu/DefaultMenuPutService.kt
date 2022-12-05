package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.MenuStruct
import com.menuplanner.server.menu.repository.IngredientRepository
import com.menuplanner.server.menu.repository.MakeRepository
import com.menuplanner.server.menu.repository.MenuRepository
import com.menuplanner.server.menu.repository.SeasoningRepository
import org.springframework.stereotype.Service

@Service
class DefaultMenuPutService(
    private val menuRepository: MenuRepository,
    private val ingredientRepository: IngredientRepository,
    private val seasoningRepository: SeasoningRepository,
    private val makeRepository: MakeRepository,
): MenuPutService {
    override fun putTargetMenu(menuStruct: MenuStruct) {
        menuRepository.save(menuStruct.menuRecord)
        ingredientRepository.saveAll(menuStruct.ingredientRecord)
        seasoningRepository.saveAll(menuStruct.seasoningRecord)
        makeRepository.saveAll(menuStruct.makeRecord)
    }

}

interface MenuPutService {
    fun putTargetMenu(menuStruct: MenuStruct)
}