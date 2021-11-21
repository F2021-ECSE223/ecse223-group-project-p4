package ca.mcgill.ecse.climbsafe.controller;

import java.util.*;

import ca.mcgill.ecse.climbsafe.application.*;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

public class ClimbSafeFeatureSet5Controller {



  /**
   * 
   * The addEquipmentBundle method creates a new bundle with specified name, discount, equipment
   * items and their quantities The method throws an InvalidInputException whenever any of the input
   * is invalid
   * 
   * 
   * @author Matthieu Hakim
   * @param name - The name of the new bundle
   * @param discount - The discount for the bundle
   * @param equipmentNames - The list of names for all the desired equipment items
   * @param equipmentQuantities - The list of quantities for each equipment item
   * @throws InvalidInputException - throws invalid input exception if there's an error
   * 
   */
  public static void addEquipmentBundle(String name, int discount, List<String> equipmentNames,
      List<Integer> equipmentQuantities) throws InvalidInputException {

    ClimbSafe system = ClimbSafeApplication.getClimbSafe(); // The system instance

    if (equipmentNames.size() < 2) {
      throw new InvalidInputException(
          "Equipment bundle must contain at least two distinct types of equipment");
    }
    // There are at least 2 equipment items

    if (name.trim().isEmpty()) {
      throw new InvalidInputException("Equipment bundle name cannot be empty");
    } else if (BookableItem.hasWithName(name)) {
      throw new InvalidInputException("A bookable item called " + name + " already exists");
    }
    // name is now valid

    if (discount < 0) {
      throw new InvalidInputException("Discount must be at least 0");
    } else if (discount > 100) {
      throw new InvalidInputException("Discount must be no more than 100");
    }
    // discount is now valid

    for (String equipmentName : equipmentNames) {
      if (!Equipment.hasWithName(equipmentName)
          || !(Equipment.getWithName(equipmentName) instanceof Equipment)) {
        throw new InvalidInputException("Equipment " + equipmentName + " does not exist");
      }
    }
    // all names have a corresponding equipment


    if (equipmentQuantities.size() == equipmentNames.size()) {

      for (Integer equipmentQuantity : equipmentQuantities) {
        if (equipmentQuantity <= 0) {
          throw new InvalidInputException(
              "Each bundle item must have quantity greater than or equal to 1");
        }
      }

    } else {
      throw new InvalidInputException(
          "Each bundle item must have quantity greater than or equal to 1");
    }
    // Each item has a corresponding quantity


    boolean hasTwoDifferentEquipments = false;

    for (int i = 1; i < equipmentNames.size(); i++) {
      if (!equipmentNames.get(0).equals(equipmentNames.get(i))) {
        hasTwoDifferentEquipments = true;
      }
    }
    if (!hasTwoDifferentEquipments) {
      throw new InvalidInputException(
          "Equipment bundle must contain at least two distinct types of equipment");
    }
    // There are at least 2 distinct types of equipment

    EquipmentBundle newBundle = new EquipmentBundle(name, discount, system);

    for (int i = 0; i < equipmentNames.size(); i++) {
      BundleItem item = new BundleItem(equipmentQuantities.get(i), system, newBundle,
          (Equipment) (BookableItem.getWithName(equipmentNames.get(i))));
      newBundle.addBundleItem(item);
    }
    system.addBundle(newBundle);
    // Added Bundle with name, discount, bundle items and quantities

    ClimbSafePersistence.save(system);

  }



  /**
   * 
   * The updateEquipmentBundle method modifies an existing bundle and changes its name, discount,
   * equipment items and their quantities The method throws an InvalidInputException whenever any of
   * the input is invalid
   * 
   * 
   * @author Matthieu Hakim
   * @param oldName - The name of the bundle to be updated
   * @param newName - The new name of the bundle
   * @param newDiscount - The new discount for the bundle
   * @param newEquipmentNames - The new list of names for all the desired equipment items
   * @param newEquipmentQuantities - The new list of quantities for each equipment item
   * @throws InvalidInputException - throws invalid input exception if there's an error
   * 
   */
  public static void updateEquipmentBundle(String oldName, String newName, int newDiscount,
      List<String> newEquipmentNames, List<Integer> newEquipmentQuantities)
      throws InvalidInputException {

    ClimbSafe system = ClimbSafeApplication.getClimbSafe(); // The system instance

    if (newEquipmentNames.size() < 2) {
      throw new InvalidInputException(
          "Equipment bundle must contain at least two distinct types of equipment");
    }
    // There are at least 2 equiments

    if (!BookableItem.hasWithName(oldName)
        || !(BookableItem.getWithName(oldName) instanceof EquipmentBundle)) {
      throw new InvalidInputException("Equipment bundle " + oldName + " does not exist");
    } // equipment bundle exists

    if (newDiscount < 0) {
      throw new InvalidInputException("Discount must be at least 0");
    } else if (newDiscount > 100) {
      throw new InvalidInputException("Discount must be no more than 100");
    }
    // discount is now valid



    for (String equipmentName : newEquipmentNames) {
      if (!Equipment.hasWithName(equipmentName)
          || !(Equipment.getWithName(equipmentName) instanceof Equipment)) {
        throw new InvalidInputException("Equipment " + equipmentName + " does not exist");
      }
    }
    // all names have a corresponding equipment


    if (newEquipmentQuantities.size() == newEquipmentNames.size()) {
      for (Integer equipmentQuantity : newEquipmentQuantities) {
        if (equipmentQuantity <= 0) {
          throw new InvalidInputException(
              "Each bundle item must have quantity greater than or equal to 1");
        }
      }
    } else {
      throw new InvalidInputException(
          "Each bundle item must have quantity greater than or equal to 1");
    }
    // Each item has a corresponding quantity


    boolean hasTwoDifferentEquipments = false;
    for (int i = 1; i < newEquipmentNames.size(); i++) {
      if (!newEquipmentNames.get(0).equals(newEquipmentNames.get(i))) {
        hasTwoDifferentEquipments = true;
      }
    }
    if (!hasTwoDifferentEquipments) {
      throw new InvalidInputException(
          "Equipment bundle must contain at least two distinct types of equipment");
    }
    // There are at least 2 distinct types of equipment

    if (!oldName.equals(newName)) {
      if (newName.trim().isEmpty()) {
        throw new InvalidInputException("Equipment bundle name cannot be empty");
      } else if (BookableItem.hasWithName(newName)) {
        throw new InvalidInputException("A bookable item called " + newName + " already exists");
      }
    } // new name is valid

    // The bundle we want to update
    EquipmentBundle bundle = (EquipmentBundle) BookableItem.getWithName(oldName);

    // update the name
    bundle.setName(newName);

    // update the discount
    bundle.setDiscount(newDiscount);

    ArrayList<BundleItem> oldItemsToRemove = new ArrayList<BundleItem>();

    for (BundleItem item : bundle.getBundleItems()) {
      oldItemsToRemove.add(item);
    }

    for (int i = 0; i < newEquipmentNames.size(); i++) {

      Equipment equipment = (Equipment) BookableItem.getWithName(newEquipmentNames.get(i));
      boolean found = false;

      for (BundleItem bundleItem : equipment.getBundleItems()) {
        if (bundleItem.getBundle() == bundle) {

          // when the item is the same for the old and new bundle
          bundleItem.setQuantity(newEquipmentQuantities.get(i));
          found = true;

          oldItemsToRemove.remove(bundleItem);
          break;

        }
      }

      if (!found) {

        // if new item was not in old bundle
        bundle.addBundleItem(newEquipmentQuantities.get(i), system, equipment);
      }

    }

    for (BundleItem bundleItem : oldItemsToRemove) {
      bundleItem.delete();
    }
    // Delete items that are not in the new bundle

    // Updated Bundle with name, discount, bundle items and quantities

    ClimbSafePersistence.save(system);

  }

}
