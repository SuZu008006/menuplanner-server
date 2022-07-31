package com.menuplanner.server.menu

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = ["http://localhost:3000", "https://menuplanner-web.herokuapp.com"])
class MenuController(private val menuService: MenuService) {
    @GetMapping
    fun getAllMenu(): List<MenuRecord> {
        return menuService.allMenu()
    }
}