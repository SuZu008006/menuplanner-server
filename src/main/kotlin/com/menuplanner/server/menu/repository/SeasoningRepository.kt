package com.menuplanner.server.menu.repository

import com.menuplanner.server.menu.entity.SeasoningRecord
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SeasoningRepository : JpaRepository<SeasoningRecord, Int> {
}