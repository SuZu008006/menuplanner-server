package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.IngredientRecord
import com.menuplanner.server.menu.entity.MenuRecord
import com.menuplanner.server.menu.entity.MenuStruct
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = ["http://localhost:3000", "https://menuplanner-web.herokuapp.com"])
class MenuController(private val menuService: MenuService) {
    @GetMapping
    fun getAllMenu(): List<MenuRecord> {
        return menuService.getSevenDaysMenu()
    }

    @GetMapping("/{id}")
    fun getTargetMenuStruct(@PathVariable id: Int): MenuStruct {
        return menuService.getTargetMenu(id)
    }

    @GetMapping("/summary/{id1}+{id2}+{id3}+{id4}+{id5}+{id6}+{id7}")
    fun getSevenDaysIngredient(
        @PathVariable id1: Int,
        @PathVariable id2: Int,
        @PathVariable id3: Int,
        @PathVariable id4: Int,
        @PathVariable id5: Int,
        @PathVariable id6: Int,
        @PathVariable id7: Int,
    ): List<IngredientRecord> {
        return menuService.getSevenDaysIngredient(
            listOf(id1, id2, id3, id4, id5, id6, id7)
        )
    }
}