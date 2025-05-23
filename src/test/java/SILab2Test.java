import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    @Test
    public void testEveryStatement() {

        RuntimeException e = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, "1234567812345678"));
        assertEquals("allItems list can't be null!", e.getMessage());


        Item item1 = new Item(null, 1, 100, 0);
        e = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(item1), "1234567812345678"));
        assertEquals("Invalid item!", e.getMessage());


        Item item2 = new Item("ItemA", 2, 100, 0.1); // 2 * 100 * 0.9 = 180
        double result = SILab2.checkCart(List.of(item2), "1234567812345678");
        assertEquals(180.0, result);


        Item item3 = new Item("ItemB", 1, 100, 0);
        e = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(item3), "abcd"));
        assertEquals("Invalid card number!", e.getMessage());
    }

    @Test
    public void testMultipleCondition() {

        Item i1 = new Item("HighPrice", 1, 350, 0); // -30
        double result = SILab2.checkCart(List.of(i1), "1234567812345678");
        assertEquals(350 - 30, result);


        Item i2 = new Item("Discounted", 1, 100, 0.2); // -30 + 80
        result = SILab2.checkCart(List.of(i2), "1234567812345678");
        assertEquals(50.0, result);


        Item i3 = new Item("Bulk", 11, 10, 0); // -30 + 110
        result = SILab2.checkCart(List.of(i3), "1234567812345678");
        assertEquals(80.0, result);


        Item i4 = new Item("Normal", 1, 100, 0); // no -30
        result = SILab2.checkCart(List.of(i4), "1234567812345678");
        assertEquals(100.0, result);
    }
}
