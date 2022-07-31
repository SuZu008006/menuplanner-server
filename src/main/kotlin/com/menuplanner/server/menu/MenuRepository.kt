package com.menuplanner.server.menu

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuRepository : JpaRepository<MenuRecord, Long> {
    override fun findAll(): MutableList<MenuRecord>
}