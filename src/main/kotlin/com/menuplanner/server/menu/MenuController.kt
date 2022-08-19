package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.IngredientRecord
import com.menuplanner.server.menu.entity.MenuRecord
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = ["http://localhost:3000", "https://menuplanner-web.herokuapp.com"])
class MenuController(private val menuService: MenuService) {
    @GetMapping
    fun getAllMenu(): List<MenuRecord> {
        return menuService.getSevenDaysMenu()
    }

    @GetMapping("/{menuCode}")
    fun getAllIngredient(@PathVariable menuCode: Int): List<IngredientRecord> {
        return menuService.allIngredient(menuCode)
    }
}