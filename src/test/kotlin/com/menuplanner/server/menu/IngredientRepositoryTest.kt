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
class IngredientRepositoryTest {
    @Autowired
    private lateinit var entityManager: TestEntityManager

    @Autowired
    private lateinit var ingredientRepository: IngredientRepository

    @Test
    fun `return ingredient of target menu`() {
        entityManager.persist(IngredientRecord(id = 1, item = "ingredientNameOne", quantity = 1, weight = 10))
        entityManager.persist(IngredientRecord(id = 1, item = "ingredientNameTwo", quantity = 1, weight = 10))
        entityManager.persist(IngredientRecord(id = 2, item = "ingredientName", quantity = 1, weight = 10))


        val allIngredient = ingredientRepository.findDistinctById(1)


        assertEquals(allIngredient.size, 2)
        assertEquals(allIngredient[0].item, "ingredientNameOne")
        assertEquals(allIngredient[0].quantity, 1)
        assertEquals(allIngredient[0].weight, 10)
        assertEquals(allIngredient[1].item, "ingredientNameTwo")
    }
}