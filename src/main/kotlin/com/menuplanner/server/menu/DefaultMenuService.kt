package com.menuplanner.server.menu

class DefaultMenuService(private val menuRepository: MenuRepository) : MenuService {
    override fun allMenu(): List<MenuRecord> {
        return menuRepository.findAll()
    }
}

interface MenuService {
    fun allMenu(): List<MenuRecord>
}