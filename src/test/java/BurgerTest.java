import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Spy
    private Burger burger = new Burger();

    @Mock
    private Bun bun;

    @Mock
    Ingredient ingredient1;

    @Mock
    Ingredient ingredient2;

    private final List<Ingredient> mockIngridients = new ArrayList<>();

    @Before
    public void init() {
        this.mockIngridients.add(ingredient1);
        this.mockIngridients.add(ingredient2);
    }

    @Test
    public void setBuns() {
        burger.setBuns(bun);
        Assert.assertNotNull("Не удалось добавить булочку бургеру", burger.bun);
    }

    @Test
    public void addIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        Assert.assertEquals("Число добавленных ингридиентов не равно ингридиенту бургера", 2, burger.ingredients.size());
    }

    @Test
    public void removeIngredient() {
        burger.ingredients = mockIngridients;
        burger.removeIngredient(1);
        Assert.assertEquals("Ингридиент не удалился", 1, burger.ingredients.size());
    }

    @Test
    public void moveIngredient() {
        burger.ingredients = mockIngridients;
        System.out.println(burger.ingredients);
        burger.moveIngredient(0, 1);
        Assert.assertEquals(0, burger.ingredients.indexOf(ingredient2));
        Assert.assertEquals(1, burger.ingredients.indexOf(ingredient1));
    }

    //проверили что возвращается цена булки * 2 плюс ингридиенты
    @Test
    public void getPrice() {
        burger.bun = bun;
        Mockito.when(bun.getPrice()).thenReturn(2F);
        burger.ingredients = mockIngridients;
        Mockito.when(ingredient1.getPrice()).thenReturn(3F);
        Mockito.when(ingredient2.getPrice()).thenReturn(4F);
        float price = burger.getPrice();
        //price = 2.0 * 2 + 3.0 + 4.0 = 11
        Assert.assertEquals(11F, price, 0);
    }

    @Test
    public void getReceipt() {
        burger.bun = bun;
        burger.ingredients = mockIngridients;
        Mockito.when(bun.getName()).thenReturn("bunName");

        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient2.getType()).thenReturn(IngredientType.SAUCE);

        Mockito.when(ingredient1.getName()).thenReturn("ingredient1");
        Mockito.when(ingredient2.getName()).thenReturn("ingredient2");

        Mockito.doReturn(10F).when(burger).getPrice();

        String receipt = burger.getReceipt();

        //проверили что вызвался метод стоимости
        Mockito.verify(burger, Mockito.times(1)).getPrice();
        //проверили что метод получения имени булочки вызвали 2 раза
        Mockito.verify(bun, Mockito.times(2)).getName();
        //проверили что вызвался метод получения имени каждого ингридиента и его тип
        burger.ingredients.forEach(ingredient -> {
            Mockito.verify(ingredient, Mockito.times(1)).getName();
            Mockito.verify(ingredient, Mockito.times(1)).getType();
        });
        //собралась ожидаемая строка
        Assert.assertEquals(getExpectedReceipt(), receipt);
        System.out.println(receipt);
    }

    private String getExpectedReceipt(){
        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", "bunName"));
        for (Ingredient ingredient : mockIngridients) {
            receipt.append(String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(),
                    ingredient.getName()));
        }
        receipt.append(String.format("(==== %s ====)%n", "bunName"));
        receipt.append(String.format("%nPrice: %f%n", 10F));
        return receipt.toString();
    }

}
