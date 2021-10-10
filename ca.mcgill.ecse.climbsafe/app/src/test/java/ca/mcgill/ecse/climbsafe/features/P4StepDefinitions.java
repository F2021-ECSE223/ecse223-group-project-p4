package ca.mcgill.ecse.climbsafe.features;

//Default imports
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

//Our imports
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.controller.*;
import static org.junit.jupiter.api.Assertions.*;


//Step method definitions
public class P4StepDefinitions {
	
	ClimbSafe system; // The system we are working on
	String error; // The current error
	
  @Given("the following ClimbSafe system exists: \\(p4)")
  public void the_following_climb_safe_system_exists_p4(io.cucumber.datatable.DataTable dataTable) {
    
	  List<String> list = dataTable.asList();
	  
	  //Extracting the date components
	  String startDate = list.get(0);
	  int year = Integer.parseInt(startDate.substring(0,4));
	  int month = Integer.parseInt(startDate.substring(5,7));
	  int day = Integer.parseInt(startDate.substring(8,10)); 
	  Date date = new Date(year, month, day);
			  
	  int nrWeeks = Integer.parseInt(list.get(1));
	  int priceOfGuidePerWeek = Integer.parseInt(list.get(2));
	  
	  try {
		ClimbSafeFeatureSet1Controller.setup(date, nrWeeks, priceOfGuidePerWeek);
	} catch (InvalidInputException e) {
		e.printStackTrace();
	}
	  //TODO Ask if we need to call this from the ClimbSafeApplication class
	  
  }

  @Given("the following pieces of equipment exist in the system: \\(p4)")
  public void the_following_pieces_of_equipment_exist_in_the_system_p4(
      io.cucumber.datatable.DataTable dataTable) {
    
	  List<List<String>> list = dataTable.cells();
	  
	  for(List<String> row : list) {
		  
		  String name = row.get(0);
		  int weight = Integer.parseInt(row.get(1));
		  int pricePerWeek = Integer.parseInt(row.get(2));
		  
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
	  
	  for(List<String> row : list) {
		  
		  String name = row.get(0);
		  int discount = Integer.parseInt(row.get(1));
		  String[] items = row.get(2).split("[,]");
		  String[] quantity = row.get(3).split("[,]");
		  Integer[] quantityIntegers = new Integer[quantity.length];
		  
		  for(int i = 0; i < quantity.length; i++) {
			  quantityIntegers[i] = Integer.parseInt(quantity[i]);
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
      String string, String string2, String string3) {
    
	  String name = string; //TODO Just for better naming - Make sure we can change header name
	  int weight = Integer.parseInt(string2); //TODO Make sure that we can parse int
	  int pricePerWeek = Integer.parseInt(string3);
	  
	  try {
		  ClimbSafeFeatureSet4Controller.addEquipment(name, weight, pricePerWeek);
	  }
	  catch(InvalidInputException e) {
		  error = e.getMessage();
	  }
  }

  @Then("the number of pieces of equipment in the system shall be {string} \\(p4)")
  public void the_number_of_pieces_of_equipment_in_the_system_shall_be_p4(String string) {
	 
	  assertEquals(Integer.parseInt(string), system.numberOfEquipment());
  
  }

  @Then("the piece of equipment with name {string}, weight {string}, and price per week {string} shall exist in the system \\(p4)")
  public void the_piece_of_equipment_with_name_weight_and_price_per_week_shall_exist_in_the_system_p4(
      String string, String string2, String string3) {
	  
	  BookableItem item = BookableItem.getWithName(string);
	  assertNotNull(item);
	  assertTrue(item instanceof Equipment);
	  assertEquals(Integer.parseInt(string2), ((Equipment) item).getWeight());
	  assertEquals(Integer.parseInt(string3), ((Equipment) item).getPricePerWeek());
	  
  }

  @Then("the piece of equipment with name {string}, weight {string}, and price per week {string} shall not exist in the system \\(p4)")
  public void the_piece_of_equipment_with_name_weight_and_price_per_week_shall_not_exist_in_the_system_p4(
      String string, String string2, String string3) {
    
	  BookableItem item = BookableItem.getWithName(string);
	  
	  assertTrue(item == null || !(item instanceof Equipment) || Integer.parseInt(string2) != ((Equipment) item).getWeight() ||
			  Integer.parseInt(string3) != ((Equipment) item).getPricePerWeek());
	  
  }

  @Then("the system shall raise the error {string} \\(p4)")
  public void the_system_shall_raise_the_error_p4(String string) {
	  assertEquals(string, error);
  }
  
  @AfterEach
  public void resetSystem() {
	  //TODO After each test is done (Make sure)
  }
}
