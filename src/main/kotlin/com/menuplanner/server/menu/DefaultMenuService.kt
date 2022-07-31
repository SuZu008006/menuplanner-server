package com.menuplanner.server.menu

import org.springframework.stereotype.Service

@Service
class DefaultMenuService(private val menuRepository: MenuRepository) : MenuService {
    override fun allMenu(): List<MenuRecord> {
        return menuRepository.findAll()
    }
}

interface MenuService {
    fun allMenu(): List<MenuRecord>
}