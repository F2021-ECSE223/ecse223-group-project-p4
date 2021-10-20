package ca.mcgill.ecse.climbsafe.features;

//Default imports
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

//Our imports
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.*;
import static org.junit.jupiter.api.Assertions.*;
import io.cucumber.java.Before;


//Step method definitions
public class P4StepDefinitions {
	
	private ClimbSafe system; // The system instance variable
	private String error; // The current error
	
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

  @Given("the following pieces of equipment exist in the system: \\(p4)")
  public void the_following_pieces_of_equipment_exist_in_the_system_p4(
      io.cucumber.datatable.DataTable dataTable) {
    
	  List<List<String>> list = dataTable.cells();
	  
	  for(int i = 1; i < list.size(); i++) {
		  
		  List<String> row = list.get(i);
		  String name = row.get(0);
		  int weight = Integer.parseInt(row.get(1));
		  int pricePerWeek = Integer.parseInt(row.get(2));
		  
		  System.out.println("test");
		  
		  try {
			ClimbSafeFeatureSet4Controller.addEquipment(name, weight, pricePerWeek);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
	  }
	  
  }

  @Given("the following equipment bundles exist in the system: \\(p4)")
  public void the_following_equipment_bundles_exist_in_the_system_p4(
      io.cucumber.datatable.DataTable dataTable) {
	  
	  List<List<String>> list = dataTable.cells();
	  
	  for(int i = 1; i < list.size(); i++) {
		  
		  List<String> row = list.get(i);
		  String name = row.get(0);
		  int discount = Integer.parseInt(row.get(1));
		  String[] items = row.get(2).split("[,]");
		  String[] quantity = row.get(3).split("[,]");
		  Integer[] quantityIntegers = new Integer[quantity.length];
		  
		  for(int j = 0; j < quantity.length; j++) {
			  quantityIntegers[j] = Integer.parseInt(quantity[j]);
		  }
		  
		  try {
			ClimbSafeFeatureSet5Controller.addEquipmentBundle(name, discount, Arrays.asList(items), Arrays.asList(quantityIntegers));
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
	  }
  }

  @When("the administator attempts to add a new piece of equipment to the system with name {string}, weight {string}, and price per week {string} \\(p4)")
  public void the_administator_attempts_to_add_a_new_piece_of_equipment_to_the_system_with_name_weight_and_price_per_week_p4(
      String name, String weight, String pricePerWeek) {
	  
	  try {
		  ClimbSafeFeatureSet4Controller.addEquipment(name, Integer.parseInt(weight), Integer.parseInt(pricePerWeek));
	  }
	  catch(InvalidInputException e) {
		  error = e.getMessage();
	  }
  }

  @Then("the number of pieces of equipment in the system shall be {string} \\(p4)")
  public void the_number_of_pieces_of_equipment_in_the_system_shall_be_p4(String number) {
	 
	  assertEquals(Integer.parseInt(number), system.numberOfEquipment());
  
  }

  @Then("the piece of equipment with name {string}, weight {string}, and price per week {string} shall exist in the system \\(p4)")
  public void the_piece_of_equipment_with_name_weight_and_price_per_week_shall_exist_in_the_system_p4(
      String name, String weight, String pricePerWeek) {
	  
	  BookableItem item = BookableItem.getWithName(name);
	  assertNotNull(item);
	  assertTrue(item instanceof Equipment);
	  assertEquals(Integer.parseInt(weight), ((Equipment) item).getWeight());
	  assertEquals(Integer.parseInt(pricePerWeek), ((Equipment) item).getPricePerWeek());
	  
  }

  @Then("the piece of equipment with name {string}, weight {string}, and price per week {string} shall not exist in the system \\(p4)")
  public void the_piece_of_equipment_with_name_weight_and_price_per_week_shall_not_exist_in_the_system_p4(
      String name, String weight, String pricePerWeek) {
    
	  BookableItem item = BookableItem.getWithName(name);
	  
	  assertTrue(item == null || !(item instanceof Equipment) || Integer.parseInt(weight) != ((Equipment) item).getWeight() ||
			  Integer.parseInt(pricePerWeek) != ((Equipment) item).getPricePerWeek());
	  
  }

  @Then("the system shall raise the error {string} \\(p4)")
  public void the_system_shall_raise_the_error_p4(String expectedError) {
	  assertEquals(expectedError, error);
  }
  
  @BeforeEach
  public void beforeEachScenario() {
	  ClimbSafeApplication.getClimbSafe().delete();
	  error = "";
  }
}
