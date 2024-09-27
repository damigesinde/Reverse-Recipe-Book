import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Recipe class represents a recipe and provides methods for interacting with it. It contains a
 * category, title, directions, and a list of ingredients.
 * 
 * @author Hunter McDonald, Dami Gesinde, Mark Nazarenko, Josh Paskett, Rhea Carillo
 */
public class Recipe {

  private String category; // The category of the recipe
  private String title; // The title of the recipe
  private String directions; // The directions of the recipe
  private List<Ingredient> ingredients = new ArrayList<>(); // The list of ingredients in the recipe

  /**
   * Constructs a new Recipe object with the specified category, title, and directions.
   * @param category the category of the recipe
   * @param title the title of the recipe
   * @param directions the directions of the recipe
   */
  public Recipe(String category, String title, String directions) {
    this.category = category;
    this.title = title;
    this.directions = directions;
  }

  /**
   * Returns the category of the recipe.
   * @return the category of the recipe
   */
  public String getCategory() {
    return this.category;
  }

  /**
   * Returns the title of the recipe.
   * @return the title of the recipe
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Returns the directions of the recipe.
   * @return the directions of the recipe
   */
  public String getDirections() {
    return this.directions;
  }

  /**
   * Sets the category of the recipe.
   * @param category the category of the recipe
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * Sets the title of the recipe.
   * @param title the title of the recipe
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Sets the directions of the recipe.
   * @param directions the directions of the recipe
   */
  public void setDirections(String directions) {
    this.directions = directions;
  }

  /**
   * Adds an ingredient to the recipe.
   * @param quantity the quantity of the ingredient
   * @param unit the unit of the quantity
   * @param name the name of the ingredient
   */
  public void addIngredient(String quantity, String unit, String name) {
    this.ingredients.add(new Ingredient(quantity, unit, name));
  }

  /**
   * Returns the list of ingredients in the recipe.
   * @return the list of ingredients in the recipe
   */
  public List<Ingredient> getIngredients() {
    return this.ingredients;
  }

  /**
   * Loads recipes from a file and returns a list of Recipe objects.
   * @param filePath the path of the CSV file
   * @return a list of Recipe objects
   * @throws FileNotFoundException if the file is not found
   */
  public static List<Recipe> loadRecipes(String filePath) throws FileNotFoundException {
    List<Recipe> recipes = new ArrayList<>();
    File file = new File(filePath);
    Scanner scanner = new Scanner(file);
    // Skip the header line
    if (scanner.hasNextLine()) {
      scanner.nextLine();
    }
    Recipe recipe = null;
    String lastCategory = "";
    String lastTitle = "";
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      String[] parts = line.split(",");
      if (!parts[0].isEmpty() && !parts[1].isEmpty()
          && (!parts[0].equals(lastCategory) || !parts[1].equals(lastTitle))) {
        recipe = new Recipe(parts[0], parts[1], parts[5]);
        recipes.add(recipe);
        lastCategory = parts[0];
        lastTitle = parts[1];
      }
      if (recipe != null && parts.length >= 5 && !parts[2].isEmpty() && !parts[3].isEmpty()
          && !parts[4].isEmpty()) {
        recipe.addIngredient(parts[2], parts[3], parts[4]);
      }
    }
    scanner.close();
    return recipes;
  }

  /**
   * Returns a string representation of the Recipe object in CSV format.
   * @return a string representation of the Recipe object in CSV format
   */
  public String toCSV() {
    StringBuilder sb = new StringBuilder();
    int counter = 0;
    for (Ingredient ingredient : this.getIngredients()) {
      if (counter == 0) {
        sb.append(this.getCategory());
        sb.append(',');
        sb.append(this.getTitle());
        sb.append(',');
      } else {
        sb.append(",,");
      }
      sb.append(ingredient.getQuantity());
      sb.append(',');
      sb.append(ingredient.getUnit());
      sb.append(',');
      sb.append(ingredient.getName());
      if (counter == 0) {
        sb.append(',');
        sb.append(this.getDirections());
      }
      sb.append('\n');
      counter++;
    }
    return sb.toString();
  }

  /**
   * Creates & returns a string representation of the Recipe object.
   * @return a string representation of the Recipe object
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\nTitle: ").append(title).append("\n");
    sb.append("Category: ").append(category).append("\n");
    sb.append("Ingredients: ").append("\n");
    for (Ingredient ingredient : ingredients) {
      sb.append("  - ").append(ingredient).append("\n");
    }
    sb.append("\nDirections: ").append(directions).append("\n");
    sb.append("--------------------------------------------------\n");
    return sb.toString();
  }
}
