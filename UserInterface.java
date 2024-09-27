import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The UserInterface class represents the user interface for interacting with the RecipeBook
 * application. It provides methods for adding, removing, and suggesting recipes based on user
 * input.
 * 
 * @author Hunter McDonald, Dami Gesinde, Mark Nazarenko, Josh Paskett, Rhea Carillo
 */
public class UserInterface {

  /**
   * The main method is the entry point of the program. It allows the user to interact with the
   * RecipeBook application by adding, removing, or suggesting recipes. The user is prompted for
   * input and the corresponding actions are performed based on the user's choice.
   *
   * @param args ignored
   * @throws FileNotFoundException if the specified file path for the RecipeBook is not found
   */
  public static void main(String[] args) throws FileNotFoundException {
    Scanner scanner = new Scanner(System.in);
    RecipeBook reverseRecipeBook = new RecipeBook("src\\data.csv");

    while (true) {
      Recipe recipe = null;
      System.out.println("Would you like to add a recipe, remove a recipe, or get a suggested recipe?");
      System.out.println("'add', 'remove', 'suggest', or 'exit' to quit. To see all recipes, enter 'all recipes'.");
      String input = scanner.nextLine();

      if ("exit".equalsIgnoreCase(input)) {
        break;
      }

      if ("add".equalsIgnoreCase(input)) {
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Directions: ");
        String directions = scanner.nextLine();

        recipe = new Recipe(category, title, directions);

        while (true) {
          System.out.print(
              "Enter Ingredient Quantity (or 'done' to finish adding ingredients): ");
          String quantity = scanner.nextLine();

          if ("done".equalsIgnoreCase(quantity)) {
            break;
          }

          System.out.print("Enter Ingredient Unit: ");
          String unit = scanner.nextLine();

          System.out.print("Enter Ingredient Name: ");
          String name = scanner.nextLine();

          recipe.addIngredient(quantity, unit, name);
        }

        try {
          addRecipeToCSV(recipe, "src\\data.csv");
        } catch (IOException e) {
          System.out.println(
              "An error occurred while trying to add the recipe to the CSV file.");
          e.printStackTrace();
        }
      } else if ("suggest".equalsIgnoreCase(input)) {
        Scanner ingredientScanner = new Scanner(System.in);
        System.out.println("Enter the ingredient you want suggestions for:");
        String ingredient = ingredientScanner.nextLine();

        List<Recipe> suggestedRecipes = reverseRecipeBook.suggestRecipes(ingredient);

        if (suggestedRecipes.isEmpty()) {
          System.out.println("No recipes found for that ingredient.");
        } else {
          System.out.println("Here are some recipes that use " + ingredient + ":");
          int counter = 0;
          for (Recipe suggestedRecipe : suggestedRecipes) {
            if (counter >= 3) {
              break;
            }
            System.out.println(suggestedRecipe);
            counter++;
          }
        }
      } else if ("remove".equalsIgnoreCase(input)) {
        System.out.print("Enter the title of the recipe you want to remove: ");
        String title = scanner.nextLine();
        try {
          boolean isRemoved = removeRecipeFromCSV(title, "src\\data.csv");
          if (isRemoved) {
            System.out.println("Recipe removed successfully.");
          }
        } catch (IOException e) {
          System.out.println("An error occurred while trying to remove the recipe.");
          e.printStackTrace();
        }
      } else if ("all recipes".equalsIgnoreCase(input)) {
        System.out.println("Here are the names of all recipes:");
        for (Recipe currentRecipe : reverseRecipeBook.getAllRecipes()) {
          System.out.println(currentRecipe.getTitle());
        }
        System.out.print("Enter the title of the recipe you want to view: ");
        String title = scanner.nextLine();
        Recipe selectedRecipe = reverseRecipeBook.getRecipeByTitle(title);
        if (selectedRecipe != null) {
          System.out.println(selectedRecipe);
        } else {
          System.out.println("Recipe not found.");
        }
      }
    }
  }

  /**
   * Adds a recipe to a CSV file.
   *
   * @param recipe The recipe to be added.
   * @param filePath The path of the CSV file.
   * @throws IOException If an I/O error occurs while writing to the file.
   */
  public static void addRecipeToCSV(Recipe recipe, String filePath) throws IOException {
    try (FileWriter writer = new FileWriter(filePath, true)) {
      writer.write(recipe.toCSV());
    }
  }

  /**
   * Removes a recipe with the specified title from a CSV file.
   *
   * @param recipeTitle the title of the recipe to be removed
   * @param filePath the path to the CSV file
   * @return true if the recipe was successfully removed, false otherwise
   * @throws IOException if an I/O error occurs while reading or writing the file
   */
  public static boolean removeRecipeFromCSV(String recipeTitle, String filePath)
      throws IOException {
    List<Recipe> allRecipes = Recipe.loadRecipes(filePath);
    boolean isRemoved = allRecipes.removeIf(recipe -> recipe.getTitle().equals(recipeTitle));

    if (!isRemoved) {
      System.out.println("Recipe not found.");
      return false;
    }

    try (FileWriter writer = new FileWriter(filePath)) {
      for (Recipe recipe : allRecipes) {
        writer.write(recipe.toCSV());
        writer.write("\n");
      }
    }

    return true;
  }
}
