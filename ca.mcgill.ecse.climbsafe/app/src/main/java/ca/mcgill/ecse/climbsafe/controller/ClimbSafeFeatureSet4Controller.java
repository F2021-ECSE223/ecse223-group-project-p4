package ca.mcgill.ecse.climbsafe.controller;

// Imports
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;


public class ClimbSafeFeatureSet4Controller {
  /**
   * @author Tinetendo Makata
   * @param name
   * @param weight
   * @param pricePerWeek
   * @throws InvalidInputException This method creates a new piece of equipment in the ClimbSafe
   *         system with a unique name, a weight, and a price per week If the input for the piece is
   *         not correct, then the method outputs an invalid input error
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

    ClimbSafe system = ClimbSafeApplication.getClimbSafe();

    // add the equipment item to the system if it passes all the previous tests
    system.addEquipment(new Equipment(name, weight, pricePerWeek, system));

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
      if (existingItem instanceof Equipment item && item.getWeight() == newWeight
          && item.getPricePerWeek() == newPricePerWeek)
        throw new InvalidInputException("The piece of equipment already exists");
      if (existingItem instanceof EquipmentBundle)
        throw new InvalidInputException("An equipment bundle with the same name already exists");
    }

    // Updating the equipment by setting a new name, weight and price per week
    toChange.setName(newName);
    ((Equipment) toChange).setWeight(newWeight);
    ((Equipment) toChange).setPricePerWeek(newPricePerWeek);

  }

  /**
   * This method checks the common conditions that are tested whether the item is being added or
   * updated
   * 
   * @author Tinetendo Makata
   * @param weight
   * @param name
   * @param pricePerWeek
   * @throws InvalidInputException
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
