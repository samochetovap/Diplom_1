import org.junit.Assert;
import org.junit.Test;
import praktikum.IngredientType;

public class IngredientTypeTest {

    @Test
    public void checkSauce() {
        Assert.assertEquals("SAUCE", IngredientType.SAUCE.name());
    }

    @Test
    public void checkFilling() {
        Assert.assertEquals("FILLING", IngredientType.FILLING.name());
    }
}
