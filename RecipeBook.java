import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * The RecipeBook class represents a collection of recipes and provides methods to interact with
 * them.
 * 
 * @author Hunter McDonald, Dami Gesinde, Mark Nazarenko, Josh Paskett, Rhea Carillo
 */
public class RecipeBook {

  private final Map<String, List<Recipe>> ingredientToRecipes;
  private final List<Recipe> recipes;

  /**
   * Constructs a new RecipeBook object with the specified CSV file path.
   *
   * @param csvFilePath the path to the CSV file containing recipe data
   * @throws FileNotFoundException if the specified file path is invalid or the file cannot be found
   */
  public RecipeBook(String csvFilePath) throws FileNotFoundException {
    this.ingredientToRecipes = new HashMap<>();
    this.recipes = Recipe.loadRecipes(csvFilePath);
    for (Recipe recipe : recipes) {
      for (Ingredient ingredient : recipe.getIngredients()) {
        List<Recipe> ingredientRecipes = this.ingredientToRecipes.get(ingredient.getName());
        if (ingredientRecipes == null) {
          ingredientRecipes = new ArrayList<>();
          this.ingredientToRecipes.put(ingredient.getName(), ingredientRecipes);
        }
        ingredientRecipes.add(recipe);
      }
    }
  }
  
  /**
   * Returns a list of all the recipes in the recipe book.
   * @return a list of all the recipes in the recipe book
   */
  public List<Recipe> getAllRecipes() {
    return this.recipes;
  }

  /**
   * Returns a recipe with the specified title.
   * @param title the title of the recipe to search for
   * @return the recipe with the specified title, or null if no such recipe exists
   */
  public Recipe getRecipeByTitle(String title) {
    for (Recipe recipe : recipes) {
      if (recipe.getTitle().equalsIgnoreCase(title)) {
        return recipe;
      }
    }
    return null;
  }

  /**
   * Displays all the recipes in the recipe book.
   */
  public void displayAllRecipes() {
    Set<Recipe> uniqueRecipes = new HashSet<>();
    for (List<Recipe> recipes : ingredientToRecipes.values()) {
      uniqueRecipes.addAll(recipes);
    }
    for (Recipe recipe : uniqueRecipes) {
      System.out.println(recipe);
    }
  }

  /**
   * Displays the recipes that contain the specified ingredient.
   * @param ingredient the ingredient to search for
   */
  public void displayRecipes(String ingredient) {
    List<Recipe> recipes = suggestRecipes(ingredient);
    for (Recipe recipe : recipes) {
      System.out.println(recipe);
    }
  }

  /**
   * Returns a list of recipes that contain the specified ingredient.
   * @param ingredient the ingredient to search for
   * @return a list of recipes that contain the specified ingredient
   */
  public List<Recipe> suggestRecipes(String ingredient) {
    List<Recipe> suggestedRecipes = new ArrayList<>();
    if (recipes != null) {
      for (Recipe recipe : recipes) {
        List<Ingredient> ingredients = recipe.getIngredients();
        if (ingredients != null) {
          for (Ingredient recipeIngredient : ingredients) {
            if (recipeIngredient.getName().toLowerCase().contains(ingredient.toLowerCase())) {
              suggestedRecipes.add(recipe);
              break;
            }
          }
        }
      }
    }
    return suggestedRecipes;
  }

  /**
   * Adds a new recipe to the recipe book.
   * @param recipe the recipe to add
   */
  public void addRecipe(Recipe recipe) {
    for (Ingredient ingredient : recipe.getIngredients()) {
      List<Recipe> recipes = this.ingredientToRecipes.get(ingredient.getName());
      if (recipes == null) {
        recipes = new ArrayList<>();
        this.ingredientToRecipes.put(ingredient.getName(), recipes);
      }
      recipes.add(recipe);
    }
  }

  /**
   * Deletes a recipe from the recipe book.
   * @param recipe the recipe to delete
   */
  public void deleteRecipe(Recipe recipe) {
    for (Ingredient ingredient : recipe.getIngredients()) {
      List<Recipe> recipes = this.ingredientToRecipes.get(ingredient.getName());
      if (recipes != null) {
        recipes.remove(recipe);
      }
    }
  }

  /**
   * Edits a recipe in the recipe book by replacing it with a new recipe.
   * @param oldRecipe the recipe to replace
   * @param newRecipe the new recipe
   */
  public void editRecipe(Recipe oldRecipe, Recipe newRecipe) {
    deleteRecipe(oldRecipe);
    addRecipe(newRecipe);
  }

  /**
   * Displays the recipes that contain the specified ingredient.
   * @param ingredient the ingredient to search for
   */
  public void displayRecipe(String ingredient) {
    List<Recipe> recipes = suggestRecipes(ingredient);
    for (Recipe recipe : recipes) {
      System.out.println(recipe);
    }
  }

  /**
   * Searches for recipes that contain the specified ingredient.
   * @param ingredient the ingredient to search for
   * @return a list of recipes that contain the specified ingredient
   */
  public List<Recipe> searchRecipe(String ingredient) {
    return suggestRecipes(ingredient);
  }
}
