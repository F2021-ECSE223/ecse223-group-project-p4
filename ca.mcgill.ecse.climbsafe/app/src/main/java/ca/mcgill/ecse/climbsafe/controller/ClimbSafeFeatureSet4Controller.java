package ca.mcgill.ecse.climbsafe.controller;

public class ClimbSafeFeatureSet4Controller {

  public static void addEquipment(String name, int weight, int pricePerWeek)
      
		  throws InvalidInputException {
	  
     //Checking for Invalid Input in for name 
	  
	  if(name.equals("")){
		  throw new InvalidInputException("Equipment name cannot be empty");
	  }

    //User inputs special character in the name, checks each character in the string 
      int i;
       for (i=0 ; i < (name.length() - 1) ; i++) {
    	   char chTest = name.charAt(i);
    	   int test = chTest;
    	   
    	   if (test < 32) {
    		  
    		   throw new InvalidInputException("Equipment name cannot have special characters");
    	   
    	   }else if (test == 32 && i == 0) {
    		   
    		   throw new InvalidInputException("Equipment name cannot start with an empty space");
    
               }else if (test > 32 && test < 65) {
    		   
    		   throw new InvalidInputException("Equipment name cannot have special characters");
    	   
    	   }else if (test > 90 && test < 97) {
    		   
    		   throw new InvalidInputException("Equipment name cannot have special characters");
    	   
    	   }else if (test > 122) {
    		  
    		   throw new InvalidInputException("Equipment name cannot have special characters");
    	   }
       }
       
     //Checks for invalid weight inputs 
    
       if (weight <= 0) {
    	   
    	   throw new InvalidInputException("The weight must be greater than 0 ");    
       }
       //TODO the case that there is no weight inputed 
       
    //Checks for invalid price per week inputs 
       
       if (pricePerWeek < 0 ) {
    	   
    	   throw new InvalidInputException("The price per week must be greater than or equal to 0 ");    
       }
       //TODO the case that not price per week in inputed 
       
   
  }

  public static void updateEquipment(String oldName, String newName, int newWeight,
      int newPricePerWeek) throws InvalidInputException {}

}
