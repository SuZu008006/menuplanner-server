package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.MenuStruct

class SpyStubMenuPutService: MenuPutService {
    var putMenu_wasCalled: Boolean = false
    var putMenuFromJSON_argument_JsonFile: MenuStruct? = null

    override fun putTargetMenu(menuStruct: MenuStruct) {
        putMenu_wasCalled = true
        putMenuFromJSON_argument_JsonFile = menuStruct
    }
}