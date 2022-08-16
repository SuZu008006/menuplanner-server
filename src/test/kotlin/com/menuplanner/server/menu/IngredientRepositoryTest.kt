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


        assertEquals(2, allIngredient.size)

        assertEquals(1, allIngredient[0].id)
        assertEquals("ingredientNameOne", allIngredient[0].item)
        assertEquals(0.25, allIngredient[0].quantity, 0.001)
        assertEquals("g", allIngredient[0].scale)

        assertEquals(1, allIngredient[1].id)
        assertEquals("ingredientNameTwo", allIngredient[1].item)
        assertEquals(0.5, allIngredient[1].quantity, 0.001)
        assertEquals("g", allIngredient[1].scale)
    }

    @Test
    fun `post ingredient of target menu`() {
        ingredientRepository.saveAll(
            mutableListOf(
                IngredientRecord(
                    id = 1,
                    item = "ingredientNameOne",
                    quantity = 0.25,
                    scale = "g"
                ),
                IngredientRecord(
                    id = 1,
                    item = "ingredientNameTwo",
                    quantity = 0.5,
                    scale = "g"
                )
            )
        )


        val allIngredient = ingredientRepository.findAll()


        assertEquals(2, allIngredient.size)

        assertEquals(1, allIngredient[0].id)
        assertEquals("ingredientNameOne", allIngredient[0].item)
        assertEquals(0.25, allIngredient[0].quantity, 0.001)
        assertEquals("g", allIngredient[0].scale)

        assertEquals(1, allIngredient[1].id)
        assertEquals("ingredientNameTwo", allIngredient[1].item)
        assertEquals(0.5, allIngredient[1].quantity, 0.001)
        assertEquals("g", allIngredient[1].scale)
    }
}