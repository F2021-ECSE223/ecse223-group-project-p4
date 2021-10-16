package ca.mcgill.ecse.climbsafe.controller;

// Imports (remove if not used)
import java.util.List;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;
import java.util.ArrayList;

public class ClimbSafeFeatureSet4Controller {

  public static void addEquipment(String name, int weight, int pricePerWeek)
      
		  throws InvalidInputException {
	  
	 //System into local variables 
	  
	  ClimbSafe system = ClimbSafeApplication.getClimbSafe();
	  
     //Checking for Invalid Input in for name 
	  
	  if(name.equals("")){
		  throw new InvalidInputException("Equipment name cannot be empty.");
	  }

    //User inputs special character in the name, checks each character in the string 
      int i;
       for (i=0 ; i < (name.length() - 1) ; i++) {
    	   char chTest = name.charAt(i);
    	   int test = chTest;
    	   
    	   if (test < 32) {
    		  
    		   throw new InvalidInputException("Equipment name cannot have special characters.");
    	   
    	   }else if (test == 32 && i == 0) {
    		   
    		   throw new InvalidInputException("Equipment name cannot start with an empty space.");
    
               }else if (test > 32 && test < 65) {
    		   
    		   throw new InvalidInputException("Equipment name cannot have special characters.");
    	   
    	   }else if (test > 90 && test < 97) {
    		   
    		   throw new InvalidInputException("Equipment name cannot have special characters.");
    	   
    	   }else if (test > 122) {
    		  
    		   throw new InvalidInputException("Equipment name cannot have special characters.");
    	   }
       }
       
     //Checks for invalid weight inputs 
    
       if (weight <= 0) {
    	   
    	   throw new InvalidInputException("The weight must be greater than 0.");    
       }
       //TODO the case that there is no weight inputed 
       
    //Checks for invalid price per week inputs 
       
       if (pricePerWeek < 0 ) {
    	   
    	   throw new InvalidInputException("The price per week must be greater than or equal to 0.");    
       }
       //TODO the case that no price per week in inputed 
       
   //Checks to see if equipment with same name already exists in the system 
      
       BookableItem existingItem = Equipment.getWithName(name);
       
       if (existingItem != null) {
    	   throw new InvalidInputException("A bookable item called" + name + "alreay exists in the system.");    
       }else {
    	   //TODO add equipment to BookableItem 
    	   
    	//   BookableItem newItem = Equipment.add());
       }  
       
  }

  public static void updateEquipment(String oldName, String newName, int newWeight,
      int newPricePerWeek) throws InvalidInputException {}

}
