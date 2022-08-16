package com.menuplanner.server.getMenu.repository

import com.menuplanner.server.getMenu.entity.SeasoningRecord
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SeasoningRepository : JpaRepository<SeasoningRecord, Int> {
    fun findDistinctById(id: Int): List<SeasoningRecord>

    override fun findAll(): MutableList<SeasoningRecord>

    override fun <S : SeasoningRecord?> saveAll(entities: MutableIterable<S>): MutableList<S>
}