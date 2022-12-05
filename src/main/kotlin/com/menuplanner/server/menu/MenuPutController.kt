package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.MenuStruct
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = ["http://localhost:3000", "https://menuplanner-web.herokuapp.com"])
class MenuPutController(private val menuPutService: MenuPutService) {
    @PutMapping("/update")
    @ResponseStatus(value = CREATED)
    fun updateTargetMenu(
        @RequestBody menuStruct: MenuStruct
    ) {
        menuPutService.putTargetMenu(menuStruct)
    }
}