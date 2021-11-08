package ca.mcgill.ecse.climbsafe.controller;

// Imports
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;


public class ClimbSafeFeatureSet4Controller {
  /**
   * @author Tinetendo Makata
   * @param name name inputed by the admin for a new or updated equipment
   * @param weight weight of the equipment inputed by the admin
   * @param pricePerWeek weekly cost of the equipment for the equipment specified by the admin
   * @throws InvalidInputException This method creates a new piece of equipment in the ClimbSafe
   *         system with a unique name, a weight, and a price per week. If the input for the piece
   *         is not correct, then the method outputs an invalid input error
   */
  public static void addEquipment(String name, int weight, int pricePerWeek)

      throws InvalidInputException {

    checkCommonConditions(weight, name, pricePerWeek);

    // Checks to see if equipment with same name already exists in the system and outputs an error
    // if that is the case

    BookableItem existingItem = BookableItem.getWithName(name);

    if (existingItem != null) {

      if (existingItem instanceof Equipment)

        throw new InvalidInputException("The piece of equipment already exists");

      if (existingItem instanceof EquipmentBundle)
        throw new InvalidInputException("The equipment bundle already exists");
    }
    // Putting the system we're working on in a local variable
    ClimbSafe system = ClimbSafeApplication.getClimbSafe();

    // add the equipment item to the system if it passes all the previous tests
    system.addEquipment(new Equipment(name, weight, pricePerWeek, system));
    
    ClimbSafePersistence.save(system);

  }

  public static void updateEquipment(String oldName, String newName, int newWeight,
      int newPricePerWeek) throws InvalidInputException {

    checkCommonConditions(newWeight, newName, newPricePerWeek);

    BookableItem toChange = BookableItem.getWithName(oldName);

    // Checks to see if the item to change exists in the system and that it is not a bundle

    if (toChange == null || toChange instanceof EquipmentBundle)
      throw new InvalidInputException("The piece of equipment does not exist");

    // Checks to see if equipment or bundle with same name already exists in the system

    BookableItem existingItem = BookableItem.getWithName(newName);

    if (existingItem != null) {
      // this allows for the user to update an item and keep the same name without causing an error
      // but
      // prevents a different item to be set to an existing name
      if (existingItem instanceof Equipment && !newName.equals(oldName))
        throw new InvalidInputException("The piece of equipment already exists");
      if (existingItem instanceof EquipmentBundle)
        throw new InvalidInputException("An equipment bundle with the same name already exists");
    }

    // Updates the equipment by setting a new name, weight and price per week
    if (newName != oldName)
      toChange.setName(newName);
    ((Equipment) toChange).setWeight(newWeight);
    ((Equipment) toChange).setPricePerWeek(newPricePerWeek);
    
    ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());

  }

  /**
   * @author Tinetendo Makata
   * @param name name inputed by the admin for a new or updated equipment
   * @param weight weight of the equipment inputed by the admin
   * @param pricePerWeek weekly cost of the equipment for the equipment specified by the admin
   * @throws InvalidInputException when the values for weight are less or equal to than 0, the name is already
   *         in the system and the price per week is less than 0 This is a helper method which
   *         checks for the common invalid input exceptions
   */


  private static void checkCommonConditions(int weight, String name, int pricePerWeek)
      throws InvalidInputException {

    // Checking for Invalid Input in for name
    if (name.trim().equals("")) {
      throw new InvalidInputException("The name must not be empty");
    }

    // Checks for invalid weight inputs
    if (weight <= 0) {

      throw new InvalidInputException("The weight must be greater than 0");
    }
    // Checks for invalid price per week inputs
    if (pricePerWeek < 0) {

      throw new InvalidInputException("The price per week must be greater than or equal to 0");
    }
  }

}