package com.menuplanner.server.menu

import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class MenuRepositoryTest {
    @Autowired
    private lateinit var entityManager: TestEntityManager

    @Autowired
    private lateinit var menuRepository: MenuRepository

    @Test
    fun `return all menu data`() {
        entityManager.persist(MenuRecord(title = "唐揚げ"))
        entityManager.persist(MenuRecord(title = "ステーキ"))


        val allMenu = menuRepository.findAll()


        assertEquals(allMenu.size, 2)
        assertEquals(allMenu[0].title, "唐揚げ")
        assertEquals(allMenu[1].title, "ステーキ")
    }
}