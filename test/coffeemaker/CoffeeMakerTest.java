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
        Recipe testRecipe = new Recipe("Coffee",30,7,3,1,0);
        assertThrows(DuplicatedRecipeException.class, () -> CM.addRecipe(testRecipe));
    }

    @Test
    public void testAddTwoRecipesWithSameAmmountOfIngredients() throws AmountOfRecipeException, DuplicatedRecipeException, InvalidValueException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        Recipe testRecipe = new Recipe("Milk",30,4,0,1,0);
        assertThrows(DuplicatedRecipeException.class, () -> CM.addRecipe(testRecipe));
    }

    @Test
    public void testAddTwoRecipesWithAmtCoffeeDifferentOthersSame() throws AmountOfRecipeException, DuplicatedRecipeException, InvalidValueException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        Recipe testRecipe = new Recipe("Milk",30,5,0,1,0);
        ok = CM.addRecipe(testRecipe);
        assertTrue(ok);
    }

    @Test
    public void testAddTwoRecipesWithAmtMilkDifferentOthersSame() throws AmountOfRecipeException, DuplicatedRecipeException, InvalidValueException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        Recipe testRecipe = new Recipe("Milk",30,4,1,1,0);
        ok = CM.addRecipe(testRecipe);
        assertTrue(ok);
    }

    @Test
    public void testAddTwoRecipesWithAmtSugarDifferentOthersSame() throws AmountOfRecipeException, DuplicatedRecipeException, InvalidValueException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        Recipe testRecipe = new Recipe("Milk",30,4,0,2,0);
        ok = CM.addRecipe(testRecipe);
        assertTrue(ok);
    }

    @Test
    public void testAddTwoRecipesWithAmtChocolateDifferentOthersSame() throws AmountOfRecipeException, DuplicatedRecipeException, InvalidValueException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        Recipe testRecipe = new Recipe("Milk",30,4,0,1,1);
        ok = CM.addRecipe(testRecipe);
        assertTrue(ok);
    }

    @Test
    public void testDeleteRecipeWithOneRecipeRegistered() throws RecipeException, AmountOfRecipeException, DuplicatedRecipeException {
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
    public void testDeleteRecipeNotRegistered() throws RecipeException, DuplicatedRecipeException, AmountOfRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        assertThrows(RecipeException.class, () -> CM.deleteRecipe("Milk"));
    }

    @Test
    public void testDeleteRecipeWithZeroRecipesRegistered() throws RecipeException, DuplicatedRecipeException, AmountOfRecipeException {
        assertThrows(RecipeException.class, () -> CM.deleteRecipe("Milk"));
    }

    @Test
    public void testGetRecipeRegistered() throws DuplicatedRecipeException, AmountOfRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
    }

    @Test
    public void testInventoryStartsWith20Amount() throws RecipeException, DuplicatedRecipeException, AmountOfRecipeException {
        assertEquals(CM.checkCoffeeInventory(), 20);
        assertEquals(CM.checkChocolateInventory(), 20);
        assertEquals(CM.checkSugarInventory(), 20);
        assertEquals(CM.checkMilkInventory(), 20);
    }

    @Test
    public void testAddAcceptableAmmountOfCoffeeToInventory() throws InvalidValueException {
        CM.addCoffeeInventory(80);
        assertEquals(CM.checkCoffeeInventory(), 100);
    }

    @Test
    public void testAddWrongAmmountOfCoffeeLimitExcededByOne() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> CM.addCoffeeInventory(81));
    }

    @Test
    public void testAddNegativeAmmountOfCoffeeToInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> CM.addCoffeeInventory(-1));
    }

    @Test
    public void testAddZeroAmmountOfCoffeeToInventory() throws InvalidValueException {
        CM.addCoffeeInventory(0);
        assertEquals(CM.checkCoffeeInventory(), 20);
    }

    @Test
    public void testAddAcceptableAmmountOfMilkToInventory() throws InvalidValueException {
        CM.addMilkInventory(80);
        assertEquals(CM.checkMilkInventory(), 100);
    }

    @Test
    public void testAddWrongAmmountOfMilkLimitExcededByOne() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> CM.addMilkInventory(81));
    }

    @Test
    public void testAddNegativeAmmountOfMilkToInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> CM.addMilkInventory(-1));
    }

    @Test
    public void testAddZeroAmmountOfMilkToInventory() throws InvalidValueException {
        CM.addMilkInventory(0);
        assertEquals(CM.checkMilkInventory(), 20);
    }

    @Test
    public void testAddAcceptableAmmountOfSugarToInventory() throws InvalidValueException {
        CM.addSugarInventory(80);
        assertEquals(CM.checkSugarInventory(), 100);
    }

    @Test
    public void testAddWrongAmmountOfSugarLimitExcededByOne() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> CM.addSugarInventory(81));
    }

    @Test
    public void testAddNegativeAmmountOfSugarToInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> CM.addSugarInventory(-1));
    }

    @Test
    public void testAddZeroAmmountOfSugarToInventory() throws InvalidValueException {
        CM.addSugarInventory(0);
        assertEquals(CM.checkSugarInventory(), 20);
    }

    @Test
    public void testAddAcceptableAmmountOfChocolateToInventory() throws InvalidValueException {
        CM.addChocolateInventory(80);
        assertEquals(CM.checkChocolateInventory(), 100);
    }

    @Test
    public void testAddWrongAmmountOfChocolateLimitExcededByOne() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> CM.addChocolateInventory(81));
    }

    @Test
    public void testAddNegativeAmmountOfChocolateToInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> CM.addChocolateInventory(-1));
    }

    @Test
    public void testAddZeroAmmountOfChocolateToInventory() throws InvalidValueException {
        CM.addSugarInventory(0);
        assertEquals(CM.checkSugarInventory(), 20);
    }

    @Test
    public void testMakeCoffeeWithoutChange() throws InvalidValueException,InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        int change = CM.makeCoffee("Coffee", 50);
        assertEquals(change, 0);
    }

    @Test
    public void testMakeCoffeeWithChange() throws InvalidValueException,InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        int change = CM.makeCoffee("Coffee", 51);
        assertEquals(change, 1);
    }

    @Test
    public void testMakeCoffeeWithARecipeThatDoesNotExists() throws InvalidValueException,InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        assertThrows(RecipeException.class, () -> CM.makeCoffee("Milk", 50));
    }

    @Test
    public void testMakeCoffeeWithNotSufficientAmmountPaid() throws InvalidValueException,InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        assertThrows(InsufficientAmountOfMoneyException.class, () -> CM.makeCoffee("Coffee", 49));
    }

    @Test
    public void testMakeCoffeeWithInsufficientCoffeeDifferenceByOne() throws InvalidValueException,InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        CM.addCoffeeInventory(3);
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
    public void testMakeCoffeeWithExactSufficientCoffee() throws InvalidValueException,InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        CM.addCoffeeInventory(4);
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
        change = CM.makeCoffee("Coffee", 50);
        assertEquals(change, 0);
        int coffeeAmount = CM.checkCoffeeInventory();
        assertEquals(coffeeAmount, 0);
    }

    @Test
    public void testMakeCoffeeWithInsufficientMilkDifferenceByOne() throws InvalidValueException,InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r5);
        assertTrue(ok);
        assertThrows(InventoryException.class, () -> CM.makeCoffee("Just Milk", 100));
    }

    @Test
    public void testMakeCoffeeWithExactSufficientMilk() throws InvalidValueException,InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r5);
        assertTrue(ok);
        CM.addMilkInventory(1);
        int change = CM.makeCoffee("Just Milk", 100);
        assertEquals(change, 0);
        int milkAmount = CM.checkMilkInventory();
        assertEquals(milkAmount, 0);
    }

    @Test
    public void testMakeCoffeeWithInsufficientSugarDifferenceByOne() throws InvalidValueException,InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r6);
        assertTrue(ok);
        assertThrows(InventoryException.class, () -> CM.makeCoffee("Just Sugar", 100));
    }

    @Test
    public void testMakeCoffeeWithExactSufficientSugar() throws InvalidValueException,InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r6);
        assertTrue(ok);
        CM.addSugarInventory(1);
        int change = CM.makeCoffee("Just Sugar", 100);
        assertEquals(change, 0);
        int sugarAmount = CM.checkSugarInventory();
        assertEquals(sugarAmount, 0);
    }

    @Test
    public void testMakeCoffeeWithInsufficientChocolateDifferenceByOne() throws InvalidValueException,InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r7);
        assertTrue(ok);
        assertThrows(InventoryException.class, () -> CM.makeCoffee("Just Chocolate", 100));
    }

    @Test
    public void testMakeCoffeeWithExactSufficientChocolate() throws InvalidValueException,InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r7);
        assertTrue(ok);
        CM.addChocolateInventory(1);
        int change = CM.makeCoffee("Just Chocolate", 100);
        assertEquals(change, 0);
        int chocolateAmount = CM.checkChocolateInventory();
        assertEquals(chocolateAmount, 0);
    }

    @Test
    public void testGetRecipesWithOneRecipeRegistered() throws AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        recipeArray = CM.getRecipes();
        assertTrue(recipeArray.contains(r1));
    }

    @Test
    public void testGetRecipesWithMoreThanOneRecipeRegistered() throws AmountOfRecipeException, DuplicatedRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        ok = CM.addRecipe(r2);
        assertTrue(ok);
        recipeArray = CM.getRecipes();
        assertTrue(recipeArray.contains(r1));
        assertTrue(recipeArray.contains(r2));
    }

    @Test
    public void testSetValidAmountOfChocolateInInventory() throws InvalidValueException {
        IN.setChocolate(0);
        assertEquals(IN.getChocolate(), 0);
        IN.setChocolate(100);
        assertEquals(IN.getChocolate(), 100);
    }


    @Test
    public void testSetInvalidAmountOfChocolateInInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> IN.setChocolate(-1));
        assertThrows(InvalidValueException.class, () -> IN.setChocolate(101));
    }

    @Test
    public void testSetValidAmountOfCoffeeInInventory() throws InvalidValueException {
        IN.setCoffee(0);
        assertEquals(IN.getCoffee(), 0);
        IN.setCoffee(100);
        assertEquals(IN.getCoffee(), 100);
    }

    @Test
    public void testSetInvalidAmountOfCoffeeInInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> IN.setCoffee(-1));
        assertThrows(InvalidValueException.class, () -> IN.setCoffee(101));
    }

    @Test
    public void testSetValidAmountOfMilkInInventory() throws InvalidValueException {
        IN.setMilk(0);
        assertEquals(IN.getMilk(), 0);
        IN.setMilk(100);
        assertEquals(IN.getMilk(), 100);
    }

    @Test
    public void testSetInvalidAmountOfMilkInInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> IN.setMilk(-1));
        assertThrows(InvalidValueException.class, () -> IN.setMilk(101));
    }

    @Test
    public void testSetValidAmountOfSugarInInventory() throws InvalidValueException {
        IN.setSugar(0);
        assertEquals(IN.getSugar(), 0);
        IN.setSugar(100);
        assertEquals(IN.getSugar(), 100);
    }

    @Test
    public void testSetInvalidAmountOfSugarInInventory() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> IN.setSugar(-1));
        assertThrows(InvalidValueException.class, () -> IN.setSugar(101));
    }

    @Test
    public void testGetAmountOfCoffeeInInventory() {
        assertEquals(IN.getCoffee(), 20);
    }

    @Test
    public void testGetAmountOfMilkInInventory() { assertEquals(IN.getMilk(), 20); }

    @Test
    public void testGetAmountOfSugarInInventory() {
        assertEquals(IN.getSugar(), 20);
    }

    @Test
    public void testGetAmountOfChocolateInInventory() {
        assertEquals(IN.getChocolate(), 20);
    }
}
