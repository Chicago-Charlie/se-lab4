package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertTrue(result);
        assertEquals(0, store.getTorpedoCount());
    }

    @Test
    void fire_Failure() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1, 1.0);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertFalse(result);
        assertEquals(1, store.getTorpedoCount());
    }

    @Test
    void fire_ZeroTorpedos_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
    }

    @Test
    void fire_NegativeTorpedos_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5);

        // Act & Assert
        assertThrows(
                IllegalArgumentException.class, () -> store.fire(-1)
        );
    }

    @Test
    void fire_MoreThanAvailable_ThrowsException() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> store.fire(6)
        );
    }

    @Test
    void isEmpty_True_WhenNoTorpedos() {
        // Arrange
        TorpedoStore store = new TorpedoStore(0);

        // Act & Assert
        assertTrue(store.isEmpty());
    }

    @Test
    void isEmpty_False_WhenHasTorpedos() {
        // Arrange
        TorpedoStore store = new TorpedoStore(5);

        // Act & Assert
        assertFalse(store.isEmpty());
    }

    @Test
    void getTorpedoCount_ReturnsInitialValue() {
        // Arrange
        TorpedoStore store = new TorpedoStore(10);

        // Act
        int count = store.getTorpedoCount();

        // Assert
        assertEquals(10, count);
    }

    @Test
    void fire_LastTorpedo_MakesStoreEmpty() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1, 0.0);

        // Act
        store.fire(1);

        // Assert
        assertTrue(store.isEmpty());
        assertEquals(0, store.getTorpedoCount());
    }

    @Test
    void fire_PartialConsumption_DecreasesCountCorrectly() {
        // Arrange
        TorpedoStore store = new TorpedoStore(10, 0.0);

        // Act
        store.fire(3);

        // Assert
        assertEquals(7, store.getTorpedoCount());
    }
}