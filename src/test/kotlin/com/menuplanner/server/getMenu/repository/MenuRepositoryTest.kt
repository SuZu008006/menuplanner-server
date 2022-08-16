package com.menuplanner.server.getMenu.repository

import com.menuplanner.server.getMenu.entity.MenuRecord
import com.menuplanner.server.getMenu.repository.MenuRepository
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
    fun `return all menu list`() {
        entityManager.persist(MenuRecord(title = "menuTitleOne"))
        entityManager.persist(MenuRecord(title = "menuTitleTwo"))


        val allMenu = menuRepository.findAll()


        assertEquals(2, allMenu.size)
        assertEquals(1, allMenu[0].id)
        assertEquals("menuTitleOne", allMenu[0].title)
        assertEquals(2, allMenu[1].id)
        assertEquals("menuTitleTwo", allMenu[1].title)
    }

    @Test
    fun `post menu list`() {
        menuRepository.saveAll(
            mutableListOf(
                MenuRecord(title = "menuTitleOne"),
                MenuRecord(title = "menuTitleTwo"),
            )
        )


        val allMenu = menuRepository.findAll()


        assertEquals(2, allMenu.size)
        assertEquals("menuTitleOne", allMenu[0].title)
        assertEquals("menuTitleTwo", allMenu[1].title)
    }
}