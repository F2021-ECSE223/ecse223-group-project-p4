package ca.mcgill.ecse.climbsafe.features;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.model.Assignment.AssignmentState;
import ca.mcgill.ecse.climbsafe.model.Member.MemberState;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignmentFeatureStepDefinitions {

  ClimbSafe climbSafe;
  String error;

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

  @When("the administrator attempts to initiate the assignment process")
  public void the_administrator_attempts_to_initiate_the_assignment_process() {
    try {
      AssignmentController.initiateAssignment();
    } catch (InvalidInputException e) {
      error = e.getMessage();
    }
  }

  @Then("the following assignments shall exist in the system:")
  public void the_following_assignments_shall_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();

    for (var row : rows) {

      String memberEmail = row.get("memberEmail");
      String guideEmail = row.get("guideEmail");
      int startWeek = Integer.parseInt(row.get("startWeek"));
      int endWeek = Integer.parseInt(row.get("endWeek"));

      User user = User.getWithEmail(memberEmail); // Return the User with the associated email
      assertNotNull(user); // Checking it is not null
      assertTrue(user instanceof Member); // Checking that it is a member

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

  @Then("the assignment for {string} shall be marked as {string}")
  public void the_assignment_for_shall_be_marked_as(String memberEmail, String assignmentState) {
    Member member = (Member) User.getWithEmail(memberEmail);
    assertEquals(assignmentState, member.getAssignment().getAssignmentState().name());
  }

  @Then("the number of assignments in the system shall be {string}")
  public void the_number_of_assignments_in_the_system_shall_be(String expectedNumberOfAssignments) {
    assertEquals(Integer.parseInt(expectedNumberOfAssignments), climbSafe.getAssignments().size());
  }

  @Then("the system shall raise the error {string}")
  public void the_system_shall_raise_the_error(String expectedError) {
    assertEquals(expectedError, error);
  }

  @Given("the following assignments exist in the system:")
  public void the_following_assignments_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> rows = dataTable.asMaps();

    // Extracting the components of the table and adding the corresponding pieces of equipment
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

  @When("the administrator attempts to confirm payment for {string} using authorization code {string}")
  public void the_administrator_attempts_to_confirm_payment_for_using_authorization_code(
      String memberEmail, String authorizationCode) {
    try {
      AssignmentController.payForTrip(memberEmail, authorizationCode);
    } catch (InvalidInputException e) {
      error = e.getMessage();
    }
  }

  @Then("the assignment for {string} shall record the authorization code {string}")
  public void the_assignment_for_shall_record_the_authorization_code(String memberEmail,
      String authorizationCode) {
    Member member = (Member) User.getWithEmail(memberEmail);
    assertEquals(authorizationCode, member.getAssignment().getAuthorizationCode());
  }

  @Then("the member account with the email {string} does not exist")
  public void the_member_account_with_the_email_does_not_exist(String memberEmail) {
    User user = User.getWithEmail(memberEmail);
    assertTrue(user == null || user instanceof Guide);
  }

  @Then("there are {string} members in the system")
  public void there_are_members_in_the_system(String numberOfMembers) {
    assertEquals(Integer.parseInt(numberOfMembers), climbSafe.getMembers().size());
  }

  @Then("the error {string} shall be raised")
  public void the_error_shall_be_raised(String expectedError) {
    assertEquals(expectedError, error);
  }

  @When("the administrator attempts to cancel the trip for {string}")
  public void the_administrator_attempts_to_cancel_the_trip_for(String memberEmail) {
    try {
      AssignmentController.cancelTrip(memberEmail);
    } catch (InvalidInputException e) {
      error = e.getMessage();
    }
  }

  @Given("the member with {string} has paid for their trip")
  public void the_member_with_has_paid_for_their_trip(String memberEmail) {
    Member member = (Member) User.getWithEmail(memberEmail);
    member.getAssignment().setState(AssignmentState.Paid);
  }

  @Then("the member with email address {string} shall receive a refund of {string} percent")
  public void the_member_with_email_address_shall_receive_a_refund_of_percent(String memberEmail,
      String refund) {

    User user = User.getWithEmail(memberEmail); // Return the User with the associated email
    assertNotNull(user); // Checking it is not null
    assertTrue(user instanceof Member); // Checking that it is a member
    assertEquals(Integer.parseInt(refund), ((Member) user).getAssignment().getRefundPercentage());
  }

  @Given("the member with {string} has started their trip")
  public void the_member_with_has_started_their_trip(String memberEmail) {
    Member member = (Member) User.getWithEmail(memberEmail);
    member.getAssignment().setState(AssignmentState.Started);
  }

  @When("the administrator attempts to finish the trip for the member with email {string}")
  public void the_administrator_attempts_to_finish_the_trip_for_the_member_with_email(
      String memberEmail) {
    try {
      AssignmentController.finishTrip(memberEmail);
    } catch (InvalidInputException e) {
      error = e.getMessage();
    }
  }

  @Given("the member with {string} is banned")
  public void the_member_with_is_banned(String memberEmail) {
    Member member = (Member) User.getWithEmail(memberEmail);
    member.setState(MemberState.Banned);
  }

  @Then("the member with email {string} shall be {string}")
  public void the_member_with_email_shall_be(String memberEmail, String memberState) {

    User user = User.getWithEmail(memberEmail); // Return the User with the associated email
    assertNotNull(user); // Checking it is not null
    assertTrue(user instanceof Member); // Checking that it is a member

    assertEquals(memberState, ((Member) user).getMemberState().name());
  }

  @When("the administrator attempts to start the trips for week {string}")
  public void the_administrator_attempts_to_start_the_trips_for_week(String weekNr) {
    try {
      AssignmentController.startTrips(Integer.parseInt(weekNr));
    } catch (InvalidInputException e) {
      error = e.getMessage();
    }
  }

  @Given("the member with {string} has cancelled their trip")
  public void the_member_with_has_cancelled_their_trip(String memberEmail) {
    Member member = (Member) User.getWithEmail(memberEmail);
    member.getAssignment().setState(AssignmentState.Cancelled);
  }

  @Given("the member with {string} has finished their trip")
  public void the_member_with_has_finished_their_trip(String memberEmail) {
    Member member = (Member) User.getWithEmail(memberEmail);
    member.getAssignment().setState(AssignmentState.Finished);
  }

}
