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
        entityManager.persist(IngredientRecord(id = 1, item = "ingredientNameOne", quantity = 0.25, scale = "g"))
        entityManager.persist(IngredientRecord(id = 1, item = "ingredientNameTwo", quantity = 0.5, scale = "g"))
        entityManager.persist(IngredientRecord(id = 2, item = "ingredientName", quantity = 1.0, scale = "g"))


        val allIngredient = ingredientRepository.findDistinctById(1)


        assertEquals(allIngredient.size, 2)
        assertEquals(allIngredient[0].ingredient_id, 1)
        assertEquals(allIngredient[0].id, 1)
        assertEquals(allIngredient[0].item, "ingredientNameOne")
        assertEquals(allIngredient[0].quantity, 0.25, 0.001)
        assertEquals(allIngredient[0].scale, "g")
        assertEquals(allIngredient[1].ingredient_id, 2)
        assertEquals(allIngredient[1].id, 1)
        assertEquals(allIngredient[1].item, "ingredientNameTwo")
        assertEquals(allIngredient[1].quantity, 0.5, 0.001)
        assertEquals(allIngredient[1].scale, "g")

    }
}