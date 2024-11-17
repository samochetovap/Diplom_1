import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Random;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getParamsData() {
        Random random = new Random();
        return new Object[][]{
                {IngredientType.SAUCE, "ingredientSomeName1", random.nextFloat()},
                {IngredientType.SAUCE, "ingredientName2", random.nextFloat()},
                {IngredientType.FILLING, "ingredientName3", random.nextFloat()},
        };
    }

    @Test
    public void createIngredient() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals(type, ingredient.type);
        Assert.assertEquals(name, ingredient.name);
        Assert.assertEquals(price, ingredient.price, 0);
    }

    @Test
    public void getType() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals(type, ingredient.getType());
    }

    @Test
    public void getName() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals(name, ingredient.getName());
    }

    @Test
    public void getPrice() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertEquals(price, ingredient.getPrice(), 0);
    }
}
