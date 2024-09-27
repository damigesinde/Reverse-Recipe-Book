import java.io.FileNotFoundException;

public class RecipeBookTester {
    public static void main(String[] args) {
        try {
            // Create a new RecipeBook object
            RecipeBook recipebook = new RecipeBook("src\\data.csv");

            // Print out the entire RecipeBook
          
            recipebook.displayAllRecipes();

            // Print out the number of recipes in the RecipeBook
           // System.out.println("Number of recipes: " + recipebook.displayRecipes().size());

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
