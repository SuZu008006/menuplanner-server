package com.menuplanner.server.menu

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DefaultMenuServiceTest {
    private lateinit var spyStubMenuRepository: SpyStubMenuRepository
    private lateinit var menuService: DefaultMenuService

    @Test
    fun `allMenu() transforms MenuRecord from MenuRepository`() {
        spyStubMenuRepository = SpyStubMenuRepository()
        spyStubMenuRepository.allMenu_returnValue = listOf(
            MenuRecord(title = "menuTitleOne")
        )

        menuService = DefaultMenuService(spyStubMenuRepository)


        val actualMenu = menuService.allMenu()


        val expectedMenu = listOf(
            MenuRecord(title = "menuTitleOne")
        )
        assertEquals(expectedMenu, actualMenu)
    }
}