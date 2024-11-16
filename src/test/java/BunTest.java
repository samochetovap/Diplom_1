import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import java.util.Random;

@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getParamsData() {
        Random random = new Random();
        return new Object[][]{{"BunSomeName1", random.nextFloat()},
                {"BunSomeName2", random.nextFloat()},
                {"BunSomeName3", random.nextFloat()},};
    }


    @Test
    public void createBun() {
        Bun bun = new Bun(name, price);
        Assert.assertEquals(name, bun.name);
        Assert.assertEquals(price, bun.price, 0);
    }

    @Test
    public void getName() {
        Bun bun = new Bun(name, price);
        Assert.assertEquals(name, bun.getName());
    }

    @Test
    public void getPrice() {
        Bun bun = new Bun(name, price);
        Assert.assertEquals(price, bun.getPrice(), 0);
    }

}
