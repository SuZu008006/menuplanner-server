package com.menuplanner.server.getMenu.repository

import com.menuplanner.server.getMenu.entity.IngredientRecord
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.query.FluentQuery
import java.util.*
import java.util.function.Function

@Suppress("SpringDataRepositoryMethodParametersInspection", "WRONG_NULLABILITY_FOR_JAVA_OVERRIDE",
    "OVERRIDE_DEPRECATION", "SpringDataMethodInconsistencyInspection")
class SpyStubIngredientRepository() : IngredientRepository {
    lateinit var allIngredient_returnValue: List<IngredientRecord>

    override fun findDistinctById(id: Int): List<IngredientRecord> {
        return allIngredient_returnValue
    }

    override fun <S : IngredientRecord?> save(entity: S): S {
        TODO("Not yet implemented")
    }

    override fun <S : IngredientRecord?> saveAll(entities: MutableIterable<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableList<IngredientRecord> {
        TODO("Not yet implemented")
    }

    override fun findAll(sort: Sort): MutableList<IngredientRecord> {
        TODO("Not yet implemented")
    }

    override fun <S : IngredientRecord?> findAll(example: Example<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun <S : IngredientRecord?> findAll(example: Example<S>, sort: Sort): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun findAll(pageable: Pageable): Page<IngredientRecord> {
        TODO("Not yet implemented")
    }

    override fun <S : IngredientRecord?> findAll(example: Example<S>, pageable: Pageable): Page<S> {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<Int>): MutableList<IngredientRecord> {
        TODO("Not yet implemented")
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun <S : IngredientRecord?> count(example: Example<S>): Long {
        TODO("Not yet implemented")
    }

    override fun delete(entity: IngredientRecord) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<Int>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<IngredientRecord>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun <S : IngredientRecord?> findOne(example: Example<S>): Optional<S> {
        TODO("Not yet implemented")
    }

    override fun <S : IngredientRecord?> exists(example: Example<S>): Boolean {
        TODO("Not yet implemented")
    }

    override fun <S : IngredientRecord?, R : Any?> findBy(
        example: Example<S>,
        queryFunction: Function<FluentQuery.FetchableFluentQuery<S>, R>
    ): R {
        TODO("Not yet implemented")
    }

    override fun flush() {
        TODO("Not yet implemented")
    }

    override fun <S : IngredientRecord?> saveAndFlush(entity: S): S {
        TODO("Not yet implemented")
    }

    override fun <S : IngredientRecord?> saveAllAndFlush(entities: MutableIterable<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun deleteAllInBatch(entities: MutableIterable<IngredientRecord>) {
        TODO("Not yet implemented")
    }

    override fun deleteAllInBatch() {
        TODO("Not yet implemented")
    }

    override fun deleteAllByIdInBatch(ids: MutableIterable<Int>) {
        TODO("Not yet implemented")
    }

    override fun getReferenceById(id: Int): IngredientRecord {
        TODO("Not yet implemented")
    }

    override fun getById(id: Int): IngredientRecord {
        TODO("Not yet implemented")
    }

    override fun getOne(id: Int): IngredientRecord {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }

    override fun existsById(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): Optional<IngredientRecord> {
        TODO("Not yet implemented")
    }
}
