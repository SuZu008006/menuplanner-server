package com.menuplanner.server.menu

import com.menuplanner.server.menu.entity.MenuStruct

class SpyStubMenuImportService : MenuImportService {
    var importMenu_wasCalled: Boolean = false

    override fun importMenu(menuStruct: MenuStruct): MenuStruct {
        importMenu_wasCalled = true
        return menuStruct
    }

}