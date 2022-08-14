package com.menuplanner.server.menu

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SeasoningRepository : JpaRepository<SeasoningRecord, Int> {
    fun findDistinctById(id: Int): List<SeasoningRecord>
}