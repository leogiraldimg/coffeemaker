package coffeemaker;

import coffeemaker.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class CoffeeMakerTest {
    private Vector<Recipe> recipeArray;
    private CoffeeMaker CM;
    private Inventory IN;
    private Recipe r1;
    private Recipe r2;
    private Recipe r3;
    private Recipe r4;
    private Recipe r5;
    private Recipe r6;
    private Recipe r7;

    @BeforeEach
    public  void setUp() throws Exception {
        CM = new CoffeeMaker();
        IN = new Inventory();
        r1 = new Recipe("Coffee",50,4,0,1,0);
        r2 = new Recipe("Hot Chocolate",75,0,3,1,3);
        r3 = new Recipe("Latte",75,3,1,1,0);
        r4 = new Recipe("Mix",100,1,2,1,2);
        r5 = new Recipe("Just Milk",100,0,21,0,0);
        r6 = new Recipe("Just Sugar",100,0,0,21,0);
        r7 = new Recipe("Just Chocolate",100,0,0,0,21);
        recipeArray = new Vector<Recipe>();
    }

    @Test
    public void testAddOneRecipe() throws AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
    }

    @Test
    public void testAddThreeRecipes() throws AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        ok = CM.addRecipe(r2);
        assertTrue(ok);
        ok = CM.addRecipe(r3);
        assertTrue(ok);
    }

    @Test
    public void testAddFourRecipes() throws AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        ok = CM.addRecipe(r2);
        assertTrue(ok);
        ok = CM.addRecipe(r3);
        assertTrue(ok);
        assertThrows(AmountOfRecipeException.class, () -> CM.addRecipe(r4));
    }

    @Test
    public void testAddTwoRecipesSameName() throws AmountOfRecipeException, DuplicatedRecipeException, InvalidValueException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        r2.setName(r1.getName());
        assertThrows(DuplicatedRecipeException.class, () -> CM.addRecipe(r2));
    }

    @Test
    public void testAddTwoRecipesWithSameAmmountOfIngredients() throws AmountOfRecipeException, DuplicatedRecipeException, InvalidValueException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        r1.setName("Milk");
        assertThrows(DuplicatedRecipeException.class, () -> CM.addRecipe(r1));
    }

    @Test
    public void testDeleteRecipe() throws RecipeException, AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        ok = CM.deleteRecipe("Coffee");
        assertTrue(ok);
    }

    @Test
    public void testDeleteRecipeWithTwoRecipesRegistered() throws RecipeException, AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        ok = CM.addRecipe(r2);
        assertTrue(ok);
        ok = CM.deleteRecipe("Coffee");
        assertTrue(ok);
    }

    @Test
    public void testDeleteRecipeNotRegistered() throws RecipeException {
        assertThrows(RecipeException.class, () -> CM.deleteRecipe("Coffee"));
    }

    @Test
    public void testAddAcceptableAmmountOfCoffeeToInventory() throws InvalidValueException {
        CM.addCoffeeInventory(80);
        assertEquals(CM.checkCoffeeInventory(), 100);
    }

    @Test
    public void testAddWrongAmmountOfCoffeeToInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> CM.addCoffeeInventory(81));
    }

    @Test
    public void testSetWrongAmmountOfCoffeeToInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> CM.addCoffeeInventory(101));
    }

    @Test
    public void testAddNegativeAmmountOfCoffeeToInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> CM.addCoffeeInventory(-1));
    }

    @Test
    public void testAddAcceptableAmmountOfMilkToInventory() throws InvalidValueException {
        CM.addMilkInventory(80);
        assertEquals(CM.checkMilkInventory(), 100);
    }

    @Test
    public void testAddWrongAmmountOfMilkToInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> CM.addMilkInventory(81));
    }

    @Test
    public void testSetWrongAmmountOfMilkToInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> CM.addMilkInventory(101));
    }

    @Test
    public void testAddNegativeAmmountOfMilkToInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> CM.addMilkInventory(-1));
    }

    @Test
    public void testAddAcceptableAmmountOfSugarToInventory() throws InvalidValueException {
        CM.addSugarInventory(80);
        assertEquals(CM.checkSugarInventory(), 100);
    }

    @Test
    public void testAddWrongAmmountOfSugarToInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> CM.addSugarInventory(81));
    }

    @Test
    public void testSetWrongAmmountOfSugarToInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> CM.addSugarInventory(101));
    }

    @Test
    public void testAddNegativeAmmountOfSugarToInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> CM.addSugarInventory(-1));
    }

    @Test
    public void testAddAcceptableAmmountOfChocolateToInventory() throws InvalidValueException {
        CM.addChocolateInventory(80);
        assertEquals(CM.checkChocolateInventory(), 100);
    }

    @Test
    public void testAddWrongAmmountOfChocolateToInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> CM.addChocolateInventory(81));
    }

    @Test
    public void testSetWrongAmmountOfChocolateToInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> CM.addChocolateInventory(101));
    }

    @Test
    public void testAddNegativeAmmountOfChocolateToInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> CM.addChocolateInventory(-1));
    }

    @Test
    public void testMakeCoffee() throws InvalidValueException,InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        int change = CM.makeCoffee("Coffee", 50);
        assertEquals(change, 0);
    }

    @Test
    public void testMakeCoffeeWithARecipeThatDoesNotExists() throws InvalidValueException,InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException {
        assertThrows(RecipeException.class, () -> CM.makeCoffee("Coffee", 50));
    }

    @Test
    public void testMakeCoffeeWithNotSufficientAmmountPaid() throws InvalidValueException,InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        assertThrows(InsufficientAmountOfMoneyException.class, () -> CM.makeCoffee("Coffee", 49));
    }

    @Test
    public void testMakeCoffeeWithInsuficientCoffee() throws InvalidValueException,InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        int change = CM.makeCoffee("Coffee", 50);
        assertEquals(change, 0);
        change = CM.makeCoffee("Coffee", 50);
        assertEquals(change, 0);
        change = CM.makeCoffee("Coffee", 50);
        assertEquals(change, 0);
        change = CM.makeCoffee("Coffee", 50);
        assertEquals(change, 0);
        change = CM.makeCoffee("Coffee", 50);
        assertEquals(change, 0);
        assertThrows(InventoryException.class, () -> CM.makeCoffee("Coffee", 50));
    }

    @Test
    public void testMakeCoffeeWithInsuficientMilk() throws InvalidValueException,InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r5);
        assertTrue(ok);
        assertThrows(InventoryException.class, () -> CM.makeCoffee("Just Milk", 100));
    }

    @Test
    public void testMakeCoffeeWithInsuficientSugar() throws InvalidValueException,InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r6);
        assertTrue(ok);
        assertThrows(InventoryException.class, () -> CM.makeCoffee("Just Sugar", 100));
    }

    @Test
    public void testMakeCoffeeWithInsufficientChocolate() throws InvalidValueException,InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r7);
        assertTrue(ok);
        assertThrows(InventoryException.class, () -> CM.makeCoffee("Just Chocolate", 100));
    }

    @Test
    public void testGetRecipes() throws AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        recipeArray = CM.getRecipes();
        assertTrue(recipeArray.contains(r1));
    }

    @Test
    public void testSetInvalidAmountOfChocolateInInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> IN.setChocolate(-1));
        assertThrows(InvalidValueException.class, () -> IN.setChocolate(101));
    }

    @Test
    public void testSetInvalidAmountOfCoffeeInInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> IN.setCoffee(-1));
        assertThrows(InvalidValueException.class, () -> IN.setCoffee(101));
    }

    @Test
    public void testSetInvalidAmountOfMilkInInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> IN.setMilk(-1));
        assertThrows(InvalidValueException.class, () -> IN.setMilk(101));
    }

    @Test
    public void testSetInvalidAmountOfSugarInInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> IN.setSugar(-1));
        assertThrows(InvalidValueException.class, () -> IN.setSugar(101));
    }
}
