package ca.mcgill.ecse.climbsafe.features;

//Default imports
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

//Our imports
import io.cucumber.java.Before;
import java.sql.Date;
import java.util.List;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.*;
import static org.junit.jupiter.api.Assertions.*;


//Step method definitions
public class P4StepDefinitions {
	
	private ClimbSafe system; // The system instance variable
	private String error; // The current error
	
   /**
	 * Creating the climbsafe system with the desired number of weeks, date and price of guide per week
	 * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph Nassar
	 * @param dataTable The table that contains the inputs specified in the feature file
	 */
  @Given("the following ClimbSafe system exists: \\(p4)")
  public void the_following_climb_safe_system_exists_p4(io.cucumber.datatable.DataTable dataTable) {
	  
	  List<String> list = dataTable.asList();
	  
	  //Extracting the components of the table
	  String startDate = list.get(3);
	  Date date = Date.valueOf(startDate);  
	  int nrWeeks = Integer.parseInt(list.get(4));
	  int priceOfGuidePerWeek = Integer.parseInt(list.get(5));
	  
	  //Creating the new system
	  system = ClimbSafeApplication.getClimbSafe();
	  system.setNrWeeks(nrWeeks);
	  system.setStartDate(date);
	  system.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
  }

   /**
	 * Adding the pieces of equipment that should already be in the system before going into the when clause
	 * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph Nassar
	 * @param dataTable The table that contains the inputs specified in the feature file
	 */
  @Given("the following pieces of equipment exist in the system: \\(p4)")
  public void the_following_pieces_of_equipment_exist_in_the_system_p4(
      io.cucumber.datatable.DataTable dataTable) {
    
	  List<List<String>> list = dataTable.cells();
	  
	  //Extracting the components of the table and adding the corresponding pieces of equipment
	  for(int i = 1; i < list.size(); i++) {
		  
		  List<String> row = list.get(i);
		  String name = row.get(0);
		  int weight = Integer.parseInt(row.get(1));
		  int pricePerWeek = Integer.parseInt(row.get(2));
		  
		  system.addEquipment(new Equipment(name, weight, pricePerWeek, system));
	  }
  }
  
  /**
 	* Adding the bundles that should already be in the system before going into the when clause
    * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph Nassar
    * @param dataTable The table that contains the inputs specified in the feature file
    */
  @Given("the following equipment bundles exist in the system: \\(p4)")
  public void the_following_equipment_bundles_exist_in_the_system_p4(
      io.cucumber.datatable.DataTable dataTable) {
	  
	  List<List<String>> list = dataTable.cells();
	  
	  for(int i = 1; i < list.size(); i++) {
		  
		//Extracting the components of the table
		  List<String> row = list.get(i);
		  String name = row.get(0);
		  int discount = Integer.parseInt(row.get(1));
		  String[] items = row.get(2).split("[,]");
		  String[] quantity = row.get(3).split("[,]");
		  Integer[] quantityIntegers = new Integer[quantity.length];
		  
		  for(int j = 0; j < quantity.length; j++) {
			  quantityIntegers[j] = Integer.parseInt(quantity[j]);
		  }
		  
		  //Creating the bundle
		  EquipmentBundle bundle = new EquipmentBundle(name, discount, system);
		  
		  //Creating the corresponding bundleItems associated to the previously create equipment items
		  for(int j = 0; j < quantity.length; j++) {
			  BundleItem bundleItem = new BundleItem(quantityIntegers[j], system, bundle, (Equipment) BookableItem.getWithName(items[j]));
			  bundle.addBundleItem(bundleItem);
		  }
		
		  //Adding the bundle to the system
		  system.addBundle(bundle);
		  
	  }
  }
  
  /**
    * Calls the controller method that adds a piece of equipment to the system
    * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph Nassar
    * @param name Name of the equipment
    * @param weight Weight of the equipment
    * @param pricePerWeek Price per week of the equipment
    */
  @When("the administator attempts to add a new piece of equipment to the system with name {string}, weight {string}, and price per week {string} \\(p4)")
  public void the_administator_attempts_to_add_a_new_piece_of_equipment_to_the_system_with_name_weight_and_price_per_week_p4(
      String name, String weight, String pricePerWeek) {
	  
	  //Adding the piece of equipment to the system using the controller method
	  try {
		  ClimbSafeFeatureSet4Controller.addEquipment(name, Integer.parseInt(weight), Integer.parseInt(pricePerWeek));
	  }
	  catch(InvalidInputException e) {
		  error = e.getMessage(); //Catching the error message if it gets thrown
	  }
  }

  /**
    * Checks that the number of pieces of equipment in the system is as expected
    * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph Nassar
    * @param number The expected number of pieces of equipment in the system
    */
  @Then("the number of pieces of equipment in the system shall be {string} \\(p4)")
  public void the_number_of_pieces_of_equipment_in_the_system_shall_be_p4(String number) {
	 
	  //Making sure the number of pieces of equipment in the system is as expected
	  assertEquals(Integer.parseInt(number), system.numberOfEquipment());
  
  }
  
  /**
    * Checks that the newly added piece of equipment exists in the system
    * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph Nassar
    * @param name Expected name of the piece of equipment
    * @param weight Expected weight of the piece of equipment
    * @param pricePerWeek Expected price per week of the piece of equipment
    */
  @Then("the piece of equipment with name {string}, weight {string}, and price per week {string} shall exist in the system \\(p4)")
  public void the_piece_of_equipment_with_name_weight_and_price_per_week_shall_exist_in_the_system_p4(
      String name, String weight, String pricePerWeek) {
	  
	  //Extracting the bookable item with the required name
	  BookableItem item = BookableItem.getWithName(name);
	  
	  //Checking that it's not null, that it's a piece of equipment (not a bundle) and that all its attributes are as required
	  assertNotNull(item);
	  assertTrue(item instanceof Equipment);
	  assertEquals(Integer.parseInt(weight), ((Equipment) item).getWeight());
	  assertEquals(Integer.parseInt(pricePerWeek), ((Equipment) item).getPricePerWeek());
	  
  }

  /**
    * Checks that the following piece of equipment does not exist in the system
    * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph Nassar
    * @param name Name of the equipment that should not exist
    * @param weight Weight of the equipment that should not exist
    * @param pricePerWeek Price per week of the equipment that should not exist
    */
  @Then("the piece of equipment with name {string}, weight {string}, and price per week {string} shall not exist in the system \\(p4)")
  public void the_piece_of_equipment_with_name_weight_and_price_per_week_shall_not_exist_in_the_system_p4(
      String name, String weight, String pricePerWeek) {
    
	  //Extracting the bookable item with the required name
	  BookableItem item = BookableItem.getWithName(name);
	  
  	  //Checking that it's either null, that it's a bundle, or that one of the attributes do not match
	  assertTrue(item == null || !(item instanceof Equipment) || Integer.parseInt(weight) != ((Equipment) item).getWeight() ||
			  Integer.parseInt(pricePerWeek) != ((Equipment) item).getPricePerWeek());
	  
  }

  /**
    * Checks that the error thrown is as expected
    * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph Nassar
    * @param expectedError The expected error
    */
  @Then("the system shall raise the error {string} \\(p4)")
  public void the_system_shall_raise_the_error_p4(String expectedError) {
	  
	  // Checking that the error is as expected
	  assertEquals(expectedError, error);
	  
  }
  
  /**
    * Resets the system and the error instance variables before each new scenario (or each example of the same scenario)
    * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph Nassar
    */
  @Before
  public void tearDown() {
	  
	  //Deleting the system
	  ClimbSafeApplication.getClimbSafe().delete();
	  
	  //Re-initializing the error instance variable
	  error = "";
  }
}
