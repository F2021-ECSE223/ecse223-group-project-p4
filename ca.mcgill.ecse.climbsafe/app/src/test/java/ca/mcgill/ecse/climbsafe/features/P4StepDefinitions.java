package ca.mcgill.ecse.climbsafe.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class P4StepDefinitions {
  @Given("the following ClimbSafe system exists: \\(p4)")
  public void the_following_climb_safe_system_exists_p4(io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @Given("the following pieces of equipment exist in the system: \\(p4)")
  public void the_following_pieces_of_equipment_exist_in_the_system_p4(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @Given("the following equipment bundles exist in the system: \\(p4)")
  public void the_following_equipment_bundles_exist_in_the_system_p4(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @When("the administator attempts to add a new piece of equipment to the system with name {string}, weight {string}, and price per week {string} \\(p4)")
  public void the_administator_attempts_to_add_a_new_piece_of_equipment_to_the_system_with_name_weight_and_price_per_week_p4(
      String string, String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the number of pieces of equipment in the system shall be {string} \\(p4)")
  public void the_number_of_pieces_of_equipment_in_the_system_shall_be_p4(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the piece of equipment with name {string}, weight {string}, and price per week {string} shall exist in the system \\(p4)")
  public void the_piece_of_equipment_with_name_weight_and_price_per_week_shall_exist_in_the_system_p4(
      String string, String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the piece of equipment with name {string}, weight {string}, and price per week {string} shall not exist in the system \\(p4)")
  public void the_piece_of_equipment_with_name_weight_and_price_per_week_shall_not_exist_in_the_system_p4(
      String string, String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the system shall raise the error {string} \\(p4)")
  public void the_system_shall_raise_the_error_p4(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
}
