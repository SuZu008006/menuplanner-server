package com.menuplanner.server.menu.repository

import com.menuplanner.server.menu.entity.MenuRecord
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuRepository : JpaRepository<MenuRecord, Int> {
    override fun findAll(): MutableList<MenuRecord>

    override fun <S : MenuRecord?> saveAll(entities: MutableIterable<S>): MutableList<S>
}