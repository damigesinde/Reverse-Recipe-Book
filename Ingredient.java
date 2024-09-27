/**
 * The Ingredient class represents an ingredient used in a recipe. Each ingredient has a
 * quantity, a unit, and a name. The quantity represents the amount of the ingredient
 * needed. The unit represents the measurement unit for the quantity. The name is the
 * name of the ingredient.
 * 
 * For example, for an ingredient represented as "1 cup sugar", "1" is the quantity,
 * "cup" is the unit, and "sugar" is the name.
 * 
 * @author Hunter McDonald, Dami Gesinde, Mark Nazarenko, Josh Paskett, Rhea Carillo
 */
public class Ingredient {
  
  String quantity;
  String unit;
  String name;

  /**
   * Creates a new Ingredient object with the specified quantity, unit, and name.
   * @param quantity the quantity of the ingredient
   * @param unit the unit of the ingredient
   * @param name the name of the ingredient
   */
  public Ingredient(String quantity, String unit, String name) {
    this.quantity = quantity;
    this.unit = unit;
    this.name = name;
  }

  /**
   * Returns the quantity of the ingredient.
   * @return the quantity of the ingredient
   */
  public String getQuantity() {
    return this.quantity;
  }

  /**
   * Returns the unit of the ingredient.
   * @return the unit of the ingredient
   */
  public String getUnit() {
    return this.unit;
  }

  /**
   * Returns the name of the ingredient.
   * @return the name of the ingredient
   */ 
  public String getName() {
    return this.name;
  }

  /**
   * Sets the quantity of the ingredient.
   * @param quantity the quantity of the ingredient
   */
  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  /**
   * Sets the unit of the ingredient.
   * @param unit the unit of the ingredient
   */
  public void setUnit(String unit) {
    this.unit = unit;
  }

  /**
   * Sets the name of the ingredient.
   * @param name the name of the ingredient
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns a string representation of the ingredient.
   * @return a string representation of the ingredient
   */
  @Override
  public String toString() {
    return quantity + " " + unit + " " + name;
  }
}
