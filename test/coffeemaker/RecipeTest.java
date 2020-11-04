package coffeemaker;

import coffeemaker.exceptions.InvalidValueException;
import coffeemaker.exceptions.RecipeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RecipeTest {

    private Recipe r3;

    @BeforeEach
    public  void setUp() throws Exception {
        r3 = new Recipe("Latte",75,3,1,1,0);
    }

    @Test
    public void testCreateValidRecipe() throws InvalidValueException {
        Recipe recipe = new Recipe("Coffee",50,4,0,1,0);
    }

    @Test
    public void testCreateInvalidRecipeAmtCoffee() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () ->new Recipe("Coffee",50,-1,0,1,0));
    }

    @Test
    public void testCreateRecipeWithZeroAmmountOfIngredients() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () ->new Recipe("Coffee",50,0,0,0,0));
    }

    @Test
    public void testCreateRecipeWithPriceEqualsToZero() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () ->new Recipe("Coffee",0,1,1,1,1));
    }

    @Test
    public void testCreateRecipeWithNegativePrice() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () ->new Recipe("Coffee",-1,1,1,1,1));
    }

    @Test
    public void testCreateRecipeWithEmptyName() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () ->new Recipe("",50,1,1,1,1));
    }

    @Test
    public void testCreateRecipeWithNullName() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () ->new Recipe(null,50,1,1,1,1));
    }

    @Test
    public void testCreateRecipeWithNegativeChocolateAmount() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () ->new Recipe("Coffee",50,1,1,1,-1));
    }

    @Test
    public void testCreateRecipeWithNegativeMilkAmount() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () ->new Recipe("Coffee",50,1,-1,1,1));
    }

    @Test
    public void testCreateRecipeWithNegativeSugarAmount() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () ->new Recipe("Coffee",50,1,1,-1,1));
    }

    @Test
    public void testCreateRecipeWithNegativeCoffeeAmount() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> new Recipe("Coffee", 50, -1, 1, 1, 1));
    }
}
