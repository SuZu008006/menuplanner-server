package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.MenuStruct

class SpyStubMenuImportService : MenuImportService {
    var importMenu_wasCalled: Boolean = false
    var importMenuFromJSON_argument_JsonFile: List<MenuStruct> = emptyList()

    override fun importMenu(menuStructList: List<MenuStruct>) {
        importMenu_wasCalled = true
        importMenuFromJSON_argument_JsonFile = menuStructList
    }

}