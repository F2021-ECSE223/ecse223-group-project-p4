package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.*;
import ca.mcgill.ecse.climbsafe.model.Hotel.HotelRating;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

// We did not implement this feature set since we have 6 members on our team
public class ClimbSafeFeatureSet7Controller {

  public static void addHotel(String name, String address, int nrStars)
      throws InvalidInputException {

    // Checking for invalid name input
    if (name.trim().equals("")) {
      throw new InvalidInputException("Name cannot be empty");
    }

    // Checks for invalid address input
    if (address.trim().equals("")) {
      throw new InvalidInputException("Address cannot be empty");
    }

    // Checks to see if hotel with same name already exists in the system and outputs an error
    // if that is the case
    Hotel existingHotel = Hotel.getWithName(name);

    if (existingHotel != null) {

      throw new InvalidInputException("Hotel already exists in the system");

    }
    // Putting the system we're working on in a local variable
    ClimbSafe system = ClimbSafeApplication.getClimbSafe();

    // Add the hotel to the system
    system.addHotel(new Hotel(name, address, intToRating(nrStars), system));

    ClimbSafePersistence.save(system);

  }

  public static void updateHotel(String oldName, String newName, String newAddress, int newNrStars)
      throws InvalidInputException {

    Hotel toBeUpdated = Hotel.getWithName(oldName);

    if (toBeUpdated == null) {
      throw new InvalidInputException("Hotel does not exist in the system");
    } else if (newName.isEmpty()) {
      throw new InvalidInputException("Name cannot be empty");
    } else if (newAddress.isEmpty()) {
      throw new InvalidInputException("Address cannot be empty");
    } else if (newNrStars < 1 || newNrStars > 5) {
      throw new InvalidInputException("Number of stars must be between 1 and 5");
    } else if (Hotel.getWithName(newName) != null) {
      throw new InvalidInputException("New name already linked to another hotel");
    }

    toBeUpdated.setName(newName);
    toBeUpdated.setAddress(newAddress);
    toBeUpdated.setRating(intToRating(newNrStars));

    ClimbSafePersistence.save(ClimbSafeApplication.getClimbSafe());
  }

  private static HotelRating intToRating(int rating) throws InvalidInputException {

    switch (rating) {

      case 1:
        return HotelRating.OneStar;
      case 2:
        return HotelRating.TwoStars;
      case 3:
        return HotelRating.ThreeStars;
      case 4:
        return HotelRating.FourStars;
      case 5:
        return HotelRating.FiveStars;
      default:
        throw new InvalidInputException("Number of stars must be between 1 and 5");
    }
  }
}
