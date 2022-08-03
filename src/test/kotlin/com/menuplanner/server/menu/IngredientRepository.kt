package com.menuplanner.server.menu

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IngredientRepository : JpaRepository<IngredientRecord, Long> {
    fun findDistinctById(id: Long): List<IngredientRecord>
}