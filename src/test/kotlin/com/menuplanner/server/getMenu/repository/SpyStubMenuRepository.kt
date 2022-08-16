package com.menuplanner.server.getMenu.repository

import com.menuplanner.server.getMenu.entity.MenuRecord
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.query.FluentQuery
import java.util.*
import java.util.function.Function

@Suppress("SpringDataRepositoryMethodParametersInspection", "WRONG_NULLABILITY_FOR_JAVA_OVERRIDE",
    "OVERRIDE_DEPRECATION", "SpringDataMethodInconsistencyInspection")
class SpyStubMenuRepository() : MenuRepository {
    lateinit var allMenu_returnValue: MutableList<MenuRecord>

    override fun findAll(): MutableList<MenuRecord> {
        return allMenu_returnValue
    }

    override fun findAll(sort: Sort): MutableList<MenuRecord> {
        TODO("Not yet implemented")
    }

    override fun <S : MenuRecord?> findAll(example: Example<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun <S : MenuRecord?> findAll(example: Example<S>, sort: Sort): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun findAll(pageable: Pageable): Page<MenuRecord> {
        TODO("Not yet implemented")
    }

    override fun <S : MenuRecord?> findAll(example: Example<S>, pageable: Pageable): Page<S> {
        TODO("Not yet implemented")
    }

    override fun <S : MenuRecord?> saveAll(entities: MutableIterable<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun <S : MenuRecord?> save(entity: S): S {
        TODO("Not yet implemented")
    }

    override fun findAllById(ids: MutableIterable<Int>): MutableList<MenuRecord> {
        TODO("Not yet implemented")
    }

    override fun count(): Long {
        TODO("Not yet implemented")
    }

    override fun <S : MenuRecord?> count(example: Example<S>): Long {
        TODO("Not yet implemented")
    }

    override fun delete(entity: MenuRecord) {
        TODO("Not yet implemented")
    }

    override fun deleteAllById(ids: MutableIterable<Int>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: MutableIterable<MenuRecord>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun <S : MenuRecord?> findOne(example: Example<S>): Optional<S> {
        TODO("Not yet implemented")
    }

    override fun <S : MenuRecord?> exists(example: Example<S>): Boolean {
        TODO("Not yet implemented")
    }

    override fun <S : MenuRecord?, R : Any?> findBy(
        example: Example<S>,
        queryFunction: Function<FluentQuery.FetchableFluentQuery<S>, R>
    ): R {
        TODO("Not yet implemented")
    }

    override fun flush() {
        TODO("Not yet implemented")
    }

    override fun <S : MenuRecord?> saveAndFlush(entity: S): S {
        TODO("Not yet implemented")
    }

    override fun <S : MenuRecord?> saveAllAndFlush(entities: MutableIterable<S>): MutableList<S> {
        TODO("Not yet implemented")
    }

    override fun deleteAllInBatch(entities: MutableIterable<MenuRecord>) {
        TODO("Not yet implemented")
    }

    override fun deleteAllInBatch() {
        TODO("Not yet implemented")
    }

    override fun deleteAllByIdInBatch(ids: MutableIterable<Int>) {
        TODO("Not yet implemented")
    }

    override fun getReferenceById(id: Int): MenuRecord {
        TODO("Not yet implemented")
    }

    override fun getById(id: Int): MenuRecord {
        TODO("Not yet implemented")
    }

    override fun getOne(id: Int): MenuRecord {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int) {
        TODO("Not yet implemented")
    }

    override fun existsById(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): Optional<MenuRecord> {
        TODO("Not yet implemented")
    }

}