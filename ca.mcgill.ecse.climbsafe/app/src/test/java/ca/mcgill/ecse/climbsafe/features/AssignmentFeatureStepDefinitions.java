package ca.mcgill.ecse.climbsafe.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.model.Assignment;
import ca.mcgill.ecse.climbsafe.model.Assignment.AssignmentState;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.BookedItem;
import ca.mcgill.ecse.climbsafe.model.BundleItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignmentFeatureStepDefinitions {

  ClimbSafe climbSafe;
  String error;

  /**
   * Creating the climbsafe system with the desired number of weeks, date and price of guide per
   * week
   * @author  Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph Nassar
   * @param dataTable The table that contains the inputs specified in the feature file
   */
  @Given("the following ClimbSafe system exists:")
  public void the_following_climb_safe_system_exists(io.cucumber.datatable.DataTable dataTable) {
    error = "";

    List<Map<String, String>> rows = dataTable.asMaps();

    for (var row : rows) {

      // Extracting the components of the table
      String startDate = row.get("startDate");
      Date date = Date.valueOf(startDate);
      int nrWeeks = Integer.parseInt(row.get("nrWeeks"));
      int priceOfGuidePerWeek = Integer.parseInt(row.get("priceOfGuidePerWeek"));

      // Creating the new system
      climbSafe = ClimbSafeApplication.getClimbSafe();
      climbSafe.setNrWeeks(nrWeeks);
      climbSafe.setStartDate(date);
      climbSafe.setPriceOfGuidePerWeek(priceOfGuidePerWeek);

    }
  }

  /**
   * Adding the pieces of equipment that should already be in the system before going into the when
   * clause
   * 
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param dataTable The table that contains the inputs specified in the feature file
   */
  @Given("the following pieces of equipment exist in the system:")
  public void the_following_pieces_of_equipment_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();

    // Extracting the components of the table and adding the corresponding pieces of equipment
    for (var row : rows) {

      String name = row.get("name");
      int weight = Integer.parseInt(row.get("weight"));
      int pricePerWeek = Integer.parseInt(row.get("pricePerWeek"));

      climbSafe.addEquipment(name, weight, pricePerWeek);
    }

  }
  
  /**
   * Adding the bundles that should already be in the system before going into the when clause
   * 
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param dataTable The table that contains the inputs specified in the feature file
   */
  @Given("the following equipment bundles exist in the system:")
  public void the_following_equipment_bundles_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();

    for (var row : rows) {

      // Extracting the components of the table
      String name = row.get("name");
      int discount = Integer.parseInt(row.get("discount"));
      String[] items = row.get("items").split(",");
      String[] quantity = row.get("quantity").split(",");

      // Creating the bundle
      EquipmentBundle bundle = new EquipmentBundle(name, discount, climbSafe);

      // Creating the corresponding bundleItems associated to the previously create equipment items
      for (int j = 0; j < quantity.length; j++) {
        var bundleItem = new BundleItem(Integer.parseInt(quantity[j]), climbSafe, bundle,
            (Equipment) BookableItem.getWithName(items[j]));
        bundle.addBundleItem(bundleItem);
      }

      // Adding the bundle to the system
      climbSafe.addBundle(bundle);

    }

  }

  /**
   * Adding the guides that should already be in the system before going into the when clause
   * 
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param dataTable The table that contains the inputs specified in the feature file
   * 
   */
  @Given("the following guides exist in the system:")
  public void the_following_guides_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();

    for (var row : rows) {

      // Extracting the components of the table
      String name = row.get("name");
      String email = row.get("email");
      String password = row.get("password");
      String emergencyContact = row.get("emergencyContact");

      climbSafe.addGuide(new Guide(email, password, name, emergencyContact, climbSafe));

    }
  }

  /**
   * Adding the members that should already be in the system before going into the when clause
   * 
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param dataTable The table that contains the inputs specified in the feature file
   * 
   */
  
  @Given("the following members exist in the system:")
  public void the_following_members_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();

    for (var row : rows) {

      // Extracting the components of the table
      String name = row.get("name");
      String email = row.get("email");
      String password = row.get("password");
      String emergencyContact = row.get("emergencyContact");
      int nrWeeks = Integer.parseInt(row.get("nrWeeks"));
      boolean guideRequired = row.get("guideRequired").equals("true");
      boolean hotelRequired = row.get("hotelRequired").equals("true");

      Member member = new Member(email, password, name, emergencyContact, nrWeeks, guideRequired,
          hotelRequired, climbSafe);
      climbSafe.addMember(member);

      String[] equipmentNames = row.get("bookedItems").split(",");
      String[] equipmentQuantities = row.get("bookedItemQuantities").split(",");

      for (int i = 0; i < equipmentNames.length; i++) {
        member.addBookedItem(new BookedItem(Integer.parseInt(equipmentQuantities[i]), climbSafe,
            member, BookableItem.getWithName(equipmentNames[i])));
      }
    }
  }

  
  
  /**
   * Calls the controller to try and initiate the assignment
   * 
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   */
  
  @When("the administrator attempts to initiate the assignment process")
  public void the_administrator_attempts_to_initiate_the_assignment_process() {
    try {
      AssignmentController.initiateAssignment();
    } catch (InvalidInputException e) {
      error = e.getMessage();
    }
  }

  /**
   * Checks that the assignments in the system are as expected
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   *         
   * @param dataTable The table that contains the inputs specified in the feature file
   */
  
  @Then("the following assignments shall exist in the system:")
  public void the_following_assignments_shall_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();

    for (var row : rows) {

   // Extracting the components of the table
      String memberEmail = row.get("memberEmail");
      String guideEmail = row.get("guideEmail");
      int startWeek = Integer.parseInt(row.get("startWeek"));
      int endWeek = Integer.parseInt(row.get("endWeek"));

      User user = User.getWithEmail(memberEmail); // Return the User with the associated email
      assertNotNull(user); // Checking it is not null
      assertTrue(user instanceof Member); // Checking that it is a member

      //Get assignment from user
      Member member = (Member) user;    
      Assignment assignment = member.getAssignment();   

      if (guideEmail == null) {
        assertNull(assignment.getGuide());  
      } else {
        assertEquals(guideEmail, assignment.getGuide().getEmail());
      }

      assertEquals(startWeek, assignment.getStartWeek());
      assertEquals(endWeek, assignment.getEndWeek());

    }

  }

  /**
   * Checks that the state of the member in the system are as expected
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param memberEmail The member email
   * @param assignmentState The state of the member
   */
  
  
  @Then("the assignment for {string} shall be marked as {string}")
  public void the_assignment_for_shall_be_marked_as(String memberEmail, String assignmentState) {
    Member member = (Member) User.getWithEmail(memberEmail);  //Getting the member
    assertEquals(assignmentState, member.getAssignment().getAssignmentStateFullName()); //Making sure the state is correct
  }

  /**
   * Checks that the number of assignment in the system are as expected
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param expectedNumberOfAssignments The expected number of assignments
   */
  @Then("the number of assignments in the system shall be {string}")
  public void the_number_of_assignments_in_the_system_shall_be(String expectedNumberOfAssignments) {
    assertEquals(Integer.parseInt(expectedNumberOfAssignments), climbSafe.getAssignments().size()); //Making sure the number of assignment is correct
  }

  /**
   * Checks that the error raised in the system is as expected
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param expectedError The expected error
   */
  
  @Then("the system shall raise the error {string}")
  public void the_system_shall_raise_the_error(String expectedError) {
    assertEquals(expectedError, error);  //Making sure that the error raised is correct
  }

  /**
   * Adding the assignments that should already be in the system
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param dataTable The table that contains the inputs specified in the feature file
   */
  
  @Given("the following assignments exist in the system:")
  public void the_following_assignments_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();

    // Extracting the components of the table
    for (var row : rows) {

      String memberEmail = row.get("memberEmail");
      String guideEmail = row.get("guideEmail");
      int startWeek = Integer.parseInt(row.get("startWeek"));
      int endWeek = Integer.parseInt(row.get("endWeek"));

      Member member = (Member) User.getWithEmail(memberEmail);
      Assignment assignment = new Assignment(startWeek, endWeek, member, climbSafe);

      member.setAssignment(assignment);

      if (guideEmail != null) {
        assignment.setGuide((Guide) User.getWithEmail(guideEmail));
      }
    }


  }

  /**
   * Try to pay for a trip for a select member
   * 
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param memberEmail The member email
   * @param authorizationCode The authorization code
   */
  
  @When("the administrator attempts to confirm payment for {string} using authorization code {string}")
  public void the_administrator_attempts_to_confirm_payment_for_using_authorization_code(
      String memberEmail, String authorizationCode) {
    try {
      AssignmentController.payForTrip(memberEmail, authorizationCode);
    } catch (InvalidInputException e) {
      error = e.getMessage();
    }
  }

  /**
   * Checks that the authorization code in the system are as expected
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param memberEmail The member email
   * @param authorizationCode The authorization code
   */  
  
  @Then("the assignment for {string} shall record the authorization code {string}")
  public void the_assignment_for_shall_record_the_authorization_code(String memberEmail,
      String authorizationCode) {
    Member member = (Member) User.getWithEmail(memberEmail);
    assertEquals(authorizationCode, member.getAssignment().getAuthorizationCode());
  }

  /**
   * Checks that the member is not in the system, as expected
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param memberEmail The member email
   */
  @Then("the member account with the email {string} does not exist")
  public void the_member_account_with_the_email_does_not_exist(String memberEmail) {
    User user = User.getWithEmail(memberEmail);
    assertTrue(user == null || user instanceof Guide);
  }

  
  /**
   * Checks that the number of members in the system are as expected
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param numberOfMembers The number of members
   */
  @Then("there are {string} members in the system")
  public void there_are_members_in_the_system(String numberOfMembers) {
    assertEquals(Integer.parseInt(numberOfMembers), climbSafe.getMembers().size());
  }

  
  /**
   * Checks that the error raised in the system is as expected
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param expectedError The expected error
   */
  @Then("the error {string} shall be raised")
  public void the_error_shall_be_raised(String expectedError) {
    assertEquals(expectedError, error);
  }

  
  /**
   * Try to cancel a trip for a select member
   * 
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param memberEmail The member email
   */
  @When("the administrator attempts to cancel the trip for {string}")
  public void the_administrator_attempts_to_cancel_the_trip_for(String memberEmail) {
    try {
      AssignmentController.cancelTrip(memberEmail);
    } catch (InvalidInputException e) {
      error = e.getMessage();
    }
  }

 /**
  * Setting the state of a member as paid
  * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
  *         Nassar
  * @param memberEmail The member email
  */
  @Given("the member with {string} has paid for their trip")
  public void the_member_with_has_paid_for_their_trip(String memberEmail) {
    Member member = (Member) User.getWithEmail(memberEmail);
    member.getAssignment().setState(AssignmentState.Paid);
  }

  /**
   * Checks that the refund in the system for the member is as expected
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param memberEmail The member email
   * @param refund The refund 
   */
  @Then("the member with email address {string} shall receive a refund of {string} percent")
  public void the_member_with_email_address_shall_receive_a_refund_of_percent(String memberEmail,
      String refund) {

    User user = User.getWithEmail(memberEmail); // Return the User with the associated email
    assertNotNull(user); // Checking it is not null
    assertTrue(user instanceof Member); // Checking that it is a member
    assertEquals(Integer.parseInt(refund), ((Member) user).getAssignment().getRefundPercentage());
  }

  /**
   * Checks that the member in the system has started their trip
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param memberEmail The member email
   */
  @Given("the member with {string} has started their trip")
  public void the_member_with_has_started_their_trip(String memberEmail) {
    Member member = (Member) User.getWithEmail(memberEmail);
    member.getAssignment().setState(AssignmentState.Started);
  }

  /**
   * Try and cancel the trip for select member
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param memberEmail The member email
   */
  @When("the administrator attempts to finish the trip for the member with email {string}")
  public void the_administrator_attempts_to_finish_the_trip_for_the_member_with_email(
      String memberEmail) {
    try {
      AssignmentController.finishTrip(memberEmail);
    } catch (InvalidInputException e) {
      error = e.getMessage();
    }
  }
  /**
   * Checks that the member in the system is banned
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param memberEmail The member email
   */

  @Given("the member with {string} is banned")
  public void the_member_with_is_banned(String memberEmail) {
    Member member = (Member) User.getWithEmail(memberEmail);
    member.getAssignment().setState(AssignmentState.Banned);
  }

  /**
   * Checks that the state of the member in the system is as expected
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param memberEmail The member email
   * @param assignmentState The state of the member
   */
  @Then("the member with email {string} shall be {string}")
  public void the_member_with_email_shall_be(String memberEmail, String assignmentState) {

    User user = User.getWithEmail(memberEmail); // Return the User with the associated email
    assertNotNull(user); // Checking it is not null
    assertTrue(user instanceof Member); // Checking that it is a member

    assertEquals(assignmentState, ((Member) user).getAssignment().getAssignmentStateFullName());
  }

  /**
   * Try and start the trip with select number of week
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param weekNr The number of week
   */
  @When("the administrator attempts to start the trips for week {string}")
  public void the_administrator_attempts_to_start_the_trips_for_week(String weekNr) {
    try {
      AssignmentController.startTrips(Integer.parseInt(weekNr));
    } catch (InvalidInputException e) {
      error = e.getMessage();
    }
  }

  /**
   * Checks that the member in the system has cancelled their trip
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param memberEmail The member email
   */
  @Given("the member with {string} has cancelled their trip")
  public void the_member_with_has_cancelled_their_trip(String memberEmail) {
    Member member = (Member) User.getWithEmail(memberEmail);
    member.getAssignment().setState(AssignmentState.Cancelled);
  }

  /**
   * Checks that the member in the system has finished their trip
   * @author Wassim Jabbour, Matthieu Hakim, Karl Rouhana, Tinetendo Makata, Adam Kazma, Ralph
   *         Nassar
   * @param memberEmail The member email
   */
  @Given("the member with {string} has finished their trip")
  public void the_member_with_has_finished_their_trip(String memberEmail) {
    Member member = (Member) User.getWithEmail(memberEmail);
    member.getAssignment().setState(AssignmentState.Finished);
  }

}
