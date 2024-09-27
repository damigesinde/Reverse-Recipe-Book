Run the code from “UserInterface.java” class

This code works with the Recipe, RecipeBook, and Ingredient Java classes.

When the program starts, it displays a prompt for the user to enter a command. The available commands are "add", "all recipes", "remove", and "suggest"


To add a recipe, the user enters "add" and follows the prompts to enter the recipe details. The recipe is then added to the CSV file with proper formatting. 

To add an ingredient: First, it will prompt the user to input the quantity, unit of measurement, and then the ingredient name. 


To view all recipes in the RecipeBook, the user enters "all recipes". The program displays the titles of all recipes, and the user can enter a title to view the full details of a recipe


To remove a recipe, the user enters "remove" and then enters the title of the recipe to be removed. The program removes the recipe from the CSV file


//Main function
To get recipe suggestions, the user enters "suggest" and then enters one or multiple ingredients. The program then suggests recipes that contain those ingredients. Note that it will only print out recipes if there are matching recipes that contain ALL the ingredients entered. 


