package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

import ca.mcgill.ecse.climbsafe.application.*;
import ca.mcgill.ecse.climbsafe.model.*;

public class ClimbSafeFeatureSet5Controller {
	
	
	
	private static ClimbSafe system = ClimbSafeApplication.getClimbSafe(); // The system instance

	
	public static void addEquipmentBundle(String name, int discount, List<String> equipmentNames,
      List<Integer> equipmentQuantities) throws InvalidInputException {
	  
		
	  if(name.trim().isEmpty()) {
		  //TODO: make string full of spaces invalid too
		  throw new InvalidInputException("Equipment bundle name cannot be empty");
		  
		  
	  }else if(BookableItem.hasWithName(name)) {
		  
		  throw new InvalidInputException("A bookable item called " + name + " already exists");
		  
	  }
	  //name is now valid
	  
	  
	  
	  
	  if(discount < 0) {
		  throw new InvalidInputException("Discount must be at least 0");
		  
	  }else if(discount > 100) {
		  throw new InvalidInputException("Discount must be no more than 100");
	  }
	  //discount is now valid
	  
	  
	  
	  
	  for(String equipmentName : equipmentNames) {
		  if(!Equipment.hasWithName(equipmentName) || (Equipment.getWithName(equipmentName) instanceof Equipment)) {
			  
			  throw new InvalidInputException("Equipment " + equipmentName + " does not exist");
		  }
	  }
	  //all names have a corresponding equipment
	  
	  
	  if(equipmentQuantities.size() == equipmentNames.size()) {
		  
		  
		  for(Integer equipmentQuantity : equipmentQuantities) {
			  if(equipmentQuantity <= 0) {
				  throw new InvalidInputException("Each bundle item must have quantity greater than or equal to 1");
			  }
		  }
		  
	  }else {
		  
		  throw new InvalidInputException("Each bundle item must have quantity greater than or equal to 1");
	  }
	  
	  	  
	  if(equipmentNames.size() < 2) {
		  
		  throw new InvalidInputException("Equipment bundle must contain at least two distinct types of equipment");
	  }
	  
	  boolean hasTwoDifferentEquipments = false;
	  for(int i = 0; i < equipmentNames.size(); i++) {
		  
		  if(equipmentNames.get(i) != equipmentNames.get(0)) {
			  
			  hasTwoDifferentEquipments = true;
		  }
	  }
	  if(!hasTwoDifferentEquipments) {
		  throw new InvalidInputException("Equipment bundle must contain at least two distinct types of equipment");

	  }
	 //There are at least 2 distinct types of equipment
	  
	  
	  	  

	  EquipmentBundle newBundle = new EquipmentBundle(name, discount, system);
	  for(int i = 0; i < equipmentNames.size(); i++) {
		  
		  BundleItem item = new BundleItem(equipmentQuantities.get(i), system, newBundle, (Equipment)(BookableItem.getWithName(equipmentNames.get(i))));
		  newBundle.addBundleItem(item);
	  }
	  system.addBundle(newBundle);
	  //Added Bundle with name, discount, bundle items and quantities
	  
  }

	public static void updateEquipmentBundle(String oldName, String newName, int newDiscount,
      List<String> newEquipmentNames, List<Integer> newEquipmentQuantities)
      throws InvalidInputException {
		
		
		
		
		
		
		
		
	}

}
