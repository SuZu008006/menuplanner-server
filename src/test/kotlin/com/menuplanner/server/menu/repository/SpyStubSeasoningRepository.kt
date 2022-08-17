package com.menuplanner.server.menu.repository

import com.menuplanner.server.menu.entity.SeasoningRecord
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.query.FluentQuery
import java.util.*
import java.util.function.Function

@Suppress(
    "SpringDataRepositoryMethodParametersInspection", "WRONG_NULLABILITY_FOR_JAVA_OVERRIDE",
    "OVERRIDE_DEPRECATION", "SpringDataMethodInconsistencyInspection"
)
class SpyStubSeasoningRepository() : SeasoningRepository {
    lateinit var allSeasoning_returnValue: List<SeasoningRecord>

    override fun findDistinctById(id: Int): List<SeasoningRecord> {
        return allSeasoning_returnValue
    }

    override fun <S : SeasoningRecord?> save(entity: S): S {
        TODO("Not yet implemented")
    }

    override fun <S : SeasoningRecord?> saveAll(entities: MutableIterable<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun findAll(): MutableList<SeasoningRecord> {
        TODO("Not yet implemented")
    }

    override fun findAll(sort: Sort): MutableList<SeasoningRecord> {
        TODO("Not yet implemented")
    }

    override fun <S : SeasoningRecord?> findAll(example: Example<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun <S : SeasoningRecord?> findAll(example: Example<S>, sort: Sort): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun findAll(pageable: Pageable): Page<SeasoningRecord> {
        TODO("Not yet implemented")
    }

    override fun <S : SeasoningRecord?> findAll(example: Example<S>, pageable: Pageable): Page<S> {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<Int>): MutableList<SeasoningRecord> {
        TODO("Not yet implemented")
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun <S : SeasoningRecord?> count(example: Example<S>): Long {
        TODO("Not yet implemented")
    }

    override fun delete(entity: SeasoningRecord) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<Int>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<SeasoningRecord>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun <S : SeasoningRecord?> findOne(example: Example<S>): Optional<S> {
        TODO("Not yet implemented")
    }

    override fun <S : SeasoningRecord?> exists(example: Example<S>): Boolean {
        TODO("Not yet implemented")
    }

    override fun <S : SeasoningRecord?, R : Any?> findBy(
        example: Example<S>,
        queryFunction: Function<FluentQuery.FetchableFluentQuery<S>, R>
    ): R {
        TODO("Not yet implemented")
    }

    override fun flush() {
        TODO("Not yet implemented")
    }

    override fun <S : SeasoningRecord?> saveAndFlush(entity: S): S {
        TODO("Not yet implemented")
    }

    override fun <S : SeasoningRecord?> saveAllAndFlush(entities: MutableIterable<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun deleteAllInBatch(entities: MutableIterable<SeasoningRecord>) {
        TODO("Not yet implemented")
    }

    override fun deleteAllInBatch() {
        TODO("Not yet implemented")
    }

    override fun deleteAllByIdInBatch(ids: MutableIterable<Int>) {
        TODO("Not yet implemented")
    }

    override fun getReferenceById(id: Int): SeasoningRecord {
        TODO("Not yet implemented")
    }

    override fun getById(id: Int): SeasoningRecord {
        TODO("Not yet implemented")
    }

    override fun getOne(id: Int): SeasoningRecord {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }

    override fun existsById(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): Optional<SeasoningRecord> {
        TODO("Not yet implemented")
    }
}
