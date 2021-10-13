package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

public class ClimbSafeFeatureSet5Controller {
	
  public static void addEquipmentBundle(String name, int discount, List<String> equipmentNames,
      List<Integer> equipmentQuantities) throws InvalidInputException {
	  
	  
	  if(name ==  "") {
		  //TODO: make string full of spaces invalid too
		  throw new InvalidInputException("Equipment bundle name cannot be empty");
	  }else if(name == "an already existing item") {
		  
		  //TODO: for_loop the system to find name == bookableItems.getName(); (something like that)
		  
		  throw new InvalidInputException("A bookable item called " + name + " already exists");
	  }
	  else {
		  //TODO: name is valid, assign to bundle
	  }
	  
	  
	  
	  
	  if(discount < 0) {
		  
		  throw new InvalidInputException("Discount must be at least 0");
	  }else if(discount > 100) {
		  throw new InvalidInputException("Discount must be no more than 100");
		  
	  }else {
		  
		  //TODO: discount is valid, assign to bundle
	  }
	  
	  
	  
	  //TODO: Do not count duplicates in the size of list
	  //TODO: Ask what we should do if there is a duplicate equipment but size constraint is satisfied (do we add another quantity?)
	  
	  if(equipmentNames.size() < 2) {
		  
		  throw new InvalidInputException("Equipment bundle must contain at least two distinct types of equipment");
	  }
	  //TODO: Check that all names in equipmentNames have a corresponding equipment
	  //throw new InvalidInputException("Equipment " + equipmentName + " does not exist");
	  
	  
	  
	  if(equipmentQuantities.size() == equipmentNames.size()) {
		  
		  
		  for(Integer equipmentQuantity : equipmentQuantities) {
			  if(equipmentQuantity <= 0) {
				  throw new InvalidInputException("Each bundle item must have quantity greater than or equal to 1");
			  }
		  }
		  
	  }else {
		  
		  //TODO: Ask what happens if names and quantities do not have the same size
		  //throw new InvalidInputException("Each bundle item must have quantity greater than or equal to 1");
	  }
		  
		  
	  
	  //TODO: Add Bundle with name, discount, equipments and quantities
	  
  }

  public static void updateEquipmentBundle(String oldName, String newName, int newDiscount,
      List<String> newEquipmentNames, List<Integer> newEquipmentQuantities)
      throws InvalidInputException {}

}
