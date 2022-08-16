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
class SeasoningRepositoryTest {
    @Autowired
    private lateinit var entityManager: TestEntityManager

    @Autowired
    private lateinit var seasoningRepository: SeasoningRepository

    @Test
    fun `return seasoning of target menu`() {
        entityManager.persist(SeasoningRecord(id = 1, item = "ingredientNameOne", quantity = 110, scale = "ml"))
        entityManager.persist(SeasoningRecord(id = 1, item = "ingredientNameTwo", quantity = 120, scale = "ml"))
        entityManager.persist(SeasoningRecord(id = 2, item = "ingredientName", quantity = 210, scale = "ml"))


        val allSeasoning = seasoningRepository.findDistinctById(1)


        assertEquals(allSeasoning.size, 2)
        assertEquals(allSeasoning[0].id, 1)
        assertEquals(allSeasoning[0].item, "ingredientNameOne")
        assertEquals(allSeasoning[0].quantity, 110)
        assertEquals(allSeasoning[0].scale, "ml")
        assertEquals(allSeasoning[1].id, 1)
        assertEquals(allSeasoning[1].item, "ingredientNameTwo")
    }

    @Test
    fun `post seasoning of target menu`() {
        seasoningRepository.saveAll(
            mutableListOf(
                SeasoningRecord(
                    id = 1, item = "ingredientNameOne", quantity = 110, scale = "ml"
                ),
                SeasoningRecord(
                    id = 1, item = "ingredientNameTwo", quantity = 120, scale = "ml"
                )
            )
        )


        val allSeasoning = seasoningRepository.findAll()


        assertEquals(allSeasoning.size, 2)
        assertEquals(allSeasoning[0].id, 1)
        assertEquals(allSeasoning[0].item, "ingredientNameOne")
        assertEquals(allSeasoning[0].quantity, 110)
        assertEquals(allSeasoning[0].scale, "ml")
        assertEquals(allSeasoning[1].id, 1)
        assertEquals(allSeasoning[1].item, "ingredientNameTwo")
    }
}