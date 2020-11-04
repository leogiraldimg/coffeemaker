package coffeemaker;

import coffeemaker.exceptions.InvalidValueException;
import coffeemaker.exceptions.RecipeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RecipeTest {
    @Test
    public void testOneIngredientAmountBiggerThanZero() throws InvalidValueException {
        Recipe recipe = new Recipe("Coffee",50,4,0,0,0);
    }

    @Test
    public void testCreateRecipeWithZeroAmmountOfIngredients() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () ->new Recipe("Coffee",50,0,0,0,0));
    }

    @Test
    public void testPriceBiggerThanZero() throws InvalidValueException {
       Recipe recipe = new Recipe("Coffee",30,1,2,0,0);
    }

    @Test
    public void testPriceEqualsOne() throws InvalidValueException {
        Recipe recipe = new Recipe("Coffee",1,1,2,0,0);
    }

    @Test
    public void testCreateRecipeWithPriceEqualsToZero() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () ->new Recipe("Coffee",0,1,1,1,1));
    }

    @Test
    public void testPriceEqualsMinusOne() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () ->new Recipe("Coffee",-1,1,2,0,0));
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
    public void testCreateRecipeWithChocolateAmountEqualsZero() throws InvalidValueException {
        Recipe recipe = new Recipe("Coffee",50,1,1,1,0);
    }

    @Test
    public void testCreateRecipeWithChocolateAmountEqualsOne() throws InvalidValueException {
        Recipe recipe = new Recipe("Coffee",50,0,0,0,1);
    }

    @Test
    public void testCreateRecipeWithNegativeChocolateAmount() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () ->new Recipe("Coffee",50,1,1,1,-1));
    }

    @Test
    public void testCreateRecipeWithCoffeeAmountEqualsZero() throws InvalidValueException {
        Recipe recipe = new Recipe("Coffee",50,0,1,1,1);
    }

    @Test
    public void testCreateRecipeWithCoffeeAmountEqualsOne() throws InvalidValueException {
        Recipe recipe = new Recipe("Coffee",50,1,0,0,0);
    }

    @Test
    public void testCreateRecipeWithNegativeCoffeeAmount() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> new Recipe("Coffee", 50, -1, 1, 1, 1));
    }

    @Test
    public void testCreateRecipeWithMilkAmountEqualsZero() throws InvalidValueException {
        Recipe recipe = new Recipe("Coffee",50,1,0,1,1);
    }

    @Test
    public void testCreateRecipeWithMilkAmountEqualsOne() throws InvalidValueException {
        Recipe recipe = new Recipe("Coffee",50,0,1,0,0);
    }

    @Test
    public void testCreateRecipeWithNegativeMilkAmount() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () ->new Recipe("Coffee",50,1,-1,1,1));
    }

    @Test
    public void testCreateRecipeWithSugarAmountEqualsZero() throws InvalidValueException {
        Recipe recipe = new Recipe("Coffee", 50, 1, 1, 0, 1);
    }

    @Test
    public void testCreateRecipeWithSugarAmountEqualsOne() throws InvalidValueException {
        Recipe recipe = new Recipe("Coffee", 50, 0, 0, 1, 0);
    }

    @Test
    public void testCreateRecipeWithNegativeSugarAmount() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> new Recipe("Coffee", 50, 1, 1, -1, 1));
    }
}
