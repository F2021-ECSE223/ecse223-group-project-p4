package ca.mcgill.ecse.climbsafe.controller;

// Imports (remove if not used)
import java.util.List;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;
import java.util.ArrayList;

public class ClimbSafeFeatureSet4Controller {

  public static void addEquipment(String name, int weight, int pricePerWeek)
      
		  throws InvalidInputException {
    	   
	  checkCommonConditions(weight, name, pricePerWeek);
	  
	//Checks to see if equipment with same name already exists in the system 
      
      BookableItem existingItem = BookableItem.getWithName(name);
      
      if (existingItem != null) {
   	   if(existingItem instanceof Equipment) 
   		   throw new InvalidInputException("The piece of equipment already exists");    
   	   if(existingItem instanceof EquipmentBundle)
   		   throw new InvalidInputException("The equipment bundle already exists");    
   	   
	  ClimbSafe system = ClimbSafeApplication.getClimbSafe();
	  
	  system.addEquipment(new Equipment (name, weight, pricePerWeek, system));
      }
  }

  public static void updateEquipment(String oldName, String newName, int newWeight,
      int newPricePerWeek) throws InvalidInputException {
	  
	  checkCommonConditions(newWeight, newName, newPricePerWeek);
	  
	  BookableItem toChange = BookableItem.getWithName(oldName);
	  
	  if (toChange == null || toChange instanceof EquipmentBundle)
		 throw new InvalidInputException("That equipment does not yet exist in the system.");	 
	 
	  //Checks to see if equipment with same name already exists in the system 
      
      BookableItem existingItem = BookableItem.getWithName(newName);
      
      if (existingItem != null) {
   	   if(existingItem instanceof Equipment) 
   		   throw new InvalidInputException("The piece of equipment already exists");    
   	   if(existingItem instanceof EquipmentBundle)
   		   throw new InvalidInputException("An equipment bundle with the same name already exists");    
      }
	 
   	   //Updating the equipment
   	   toChange.setName(newName);
   	   ((Equipment) toChange).setWeight(newWeight);
   	   ((Equipment) toChange).setPricePerWeek(newPricePerWeek);
      
  }
  
  private static void checkCommonConditions(int weight, String name, int pricePerWeek) throws InvalidInputException{
	  
	  //Checking for Invalid Input in for name 
	  if(name.equals("")){
		  throw new InvalidInputException("Equipment name cannot be empty.");
	  }
       
	  //Checks for invalid weight inputs 
       if (weight <= 0) {
    	   
    	   throw new InvalidInputException("The weight must be greater than 0");    
       }

       
       //Checks for invalid price per week inputs 
       if (pricePerWeek < 0 ) {
    	   
    	   throw new InvalidInputException("The price per week must be greater than or equal to 0");    
       }
    		   
  }

}
