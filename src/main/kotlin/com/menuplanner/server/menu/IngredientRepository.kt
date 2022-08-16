package com.menuplanner.server.menu

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IngredientRepository : JpaRepository<IngredientRecord, Int> {
    fun findDistinctById(id: Int): List<IngredientRecord>

    override fun findAll(): MutableList<IngredientRecord>

    override fun <S : IngredientRecord?> saveAll(entities: MutableIterable<S>): MutableList<S>
}