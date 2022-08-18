package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.MenuStruct
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = ["http://localhost:3000", "https://menuplanner-web.herokuapp.com"])
class MenuImportController(private val menuImportService: MenuImportService) {
    @PostMapping("/import", consumes = [APPLICATION_JSON_VALUE])
    @ResponseStatus(value = CREATED)
    fun importMenuStruct(@RequestBody menuStruct: MenuStruct) {
        println(menuStruct)
        menuImportService.importMenu(menuStruct)
    }
}