package dev.benjaminh.products_api.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import dev.benjaminh.products_api.controller.ProductController;
import dev.benjaminh.products_api.entity.Product;
import dev.benjaminh.products_api.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductRepository repo;

    @InjectMocks
    private ProductController controller;

    @Test
    public void testSave() {
        // Arrange
        Product product = new Product(); // create a sample product

        // Act
        controller.save(product);

        // Assert
        verify(repo, times(1)).save(product);
    }

    @Test
    public void testGetAll() {
        // Act
        controller.getAll();

        // Assert
        verify(repo, times(1)).findAll();
    }

    @Test
    public void testUpdateCallsRepoSaveWithCorrectId() {
        // Arrange
        Long id = 1L;
        Product product = new Product();

        // Act
        Product result = controller.update(id, product);

        // Assert
        verify(repo, times(1)).save(argThat(p -> p.getId().equals(id)));
    }

    @Test
    public void testDeleteCallsRepoDeleteById() {
        // Arrange
        Long id = 2L;

        // Act
        controller.delete(id);

        // Assert
        verify(repo, times(1)).deleteById(id);
    }

    @Test
    public void testHealthReturnsStatusMap() {
        // Act
        Map<String, String> status = controller.health();

        // Assert
        assertEquals("UP", status.get("status"));
        assertTrue(status.containsKey("timestamp"));
    }
}

