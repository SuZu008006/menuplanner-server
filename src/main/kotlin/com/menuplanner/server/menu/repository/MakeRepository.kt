package com.menuplanner.server.menu.repository

import com.menuplanner.server.menu.entity.MakeRecord
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MakeRepository : JpaRepository<MakeRecord, Int> {
    fun findDistinctById(id: Int): List<MakeRecord>
}