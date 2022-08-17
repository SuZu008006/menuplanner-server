package com.menuplanner.server.menu.repository

import com.menuplanner.server.menu.entity.IngredientRecord
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IngredientRepository : JpaRepository<IngredientRecord, Int> {
    fun findDistinctById(id: Int): List<IngredientRecord>
}