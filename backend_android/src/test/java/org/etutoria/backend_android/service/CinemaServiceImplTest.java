import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdditionTest {

    @Test
    public void testAddition() {
        // Arrange
        int a = 5;
        int b = 3;
        
        // Act
        int result = add(a, b);
        
        // Assert
        assertEquals(8, result);
    }

    public int add(int a, int b) {
        return a + b;
    }
}
