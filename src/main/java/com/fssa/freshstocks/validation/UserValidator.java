package com.fssa.freshstocks.validation;

import com.fssa.freshstocks.model.User;
import java.util.regex.Pattern;

import com.fssa.freshstocks.validation.exception.InvalidUserException;

public class UserValidator {

	/**
	 * Validates a User object for its username, email, and password.
	 *
	 * @param user The User object to be validated.
	 * @return true if the user is valid, false otherwise.
	 * @throws InvalidUserException If the user is found to be invalid.
	 */
	public static boolean validateUser(User user) throws InvalidUserException {
	    if (user == null || !validateName(user.getUsername()) || !validateEmail(user.getEmail()) || !validatePassword(user.getPassword())) {
	    	throw new InvalidUserException("Invalid user details. Please ensure that all user information is valid.");
	    }
	    return true;
	}
	
	public static boolean validateUpdateUser(User user) throws InvalidUserException {
	    if (user == null || !validateGender(user.getGender()) || !validateMobileNumber(user.getMobileNumber()) || !validateDateOfBirth(user.getDateOfBirth())) {
	    	throw new InvalidUserException("Invalid Updating user details. Please ensure that all user information is valid.");
	    }
	    return true;
	}

	/**
	 * Validates a username based on specific pattern criteria.
	 *
	 * @param name The username to be validated.
	 * @return true if the username is valid, false otherwise.
	 * @throws InvalidUserException If the username is found to be invalid.
	 */
	public static boolean validateName(String name) throws InvalidUserException {
	    String regex = "^[\\w]{3,30}$";
	    boolean match = Pattern.matches(regex, name);
	    if (!match) {
	    	throw new InvalidUserException("Invalid user name. User names must be 3 to 30 characters long and may include letters, numbers, and underscores.");
	    }
	    return match;
	}

	/**
	 * Validates a mobile number using a specific pattern.
	 *
	 * @param mobileNumber The mobile number to be validated.
	 * @return true if the mobile number is valid, false otherwise.
	 * @throws InvalidUserException If the mobile number is found to be invalid.
	 */
	public static boolean validateMobileNumber(String mobileNumber) throws InvalidUserException {
	    String regex = "^\\+?\\d{1,4}-?\\d{5,14}$";
	    boolean match = Pattern.matches(regex, mobileNumber);
	    if (!match) {
	    	throw new InvalidUserException("Invalid mobile number. Please provide a valid phone number, including the country code and area code if applicable.");
	    }
	    return match;
	}

	/**
	 * Validates a date of birth using a specific pattern.
	 *
	 * @param dateOfBirth The date of birth to be validated.
	 * @return true if the date of birth is valid, false otherwise.
	 * @throws InvalidUserException If the date of birth is found to be invalid.
	 */
	public static boolean validateDateOfBirth(String dateOfBirth) throws InvalidUserException {
	    String regex = "^(?:19|20)\\d{2}-(?:0[1-9]|1[0-2])-(?:0[1-9]|[1-2]\\d|3[0-1])$";
	    boolean match = Pattern.matches(regex, dateOfBirth);
	    if (!match) {
	    	 throw new InvalidUserException("Invalid date of birth. Please provide a valid date in the format YYYY-MM-DD.");
	    }
	    return match;
	}

	/**
	 * Validates a gender using a specific pattern.
	 *
	 * @param gender The gender to be validated.
	 * @return true if the gender is valid, false otherwise.
	 * @throws InvalidUserException If the gender is found to be invalid.
	 */
	public static boolean validateGender(String gender) throws InvalidUserException {
	    String regex = "^(male|female|others)$";
	    boolean match = Pattern.matches(regex, gender.toLowerCase());
	    if (!match) {
	        throw new InvalidUserException("Given Gender is not valid. Expected Input: male|female|others");
	    }
	    return match;
	}

	/**
	 * Validates a password using a specific pattern.
	 *
	 * @param password The password to be validated.
	 * @return true if the password is valid, false otherwise.
	 * @throws InvalidUserException If the password is found to be invalid.
	 */
	public static boolean validatePassword(String password) throws InvalidUserException {
	    String patternString = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=.*[^\\s]).{8,}";
	    if (!Pattern.matches(patternString, password)) {
	    	throw new InvalidUserException("Invalid password. Passwords must meet specific complexity requirements.");
	    }
	    return true;
	}

	/**
	 * Validates an email address using a specific pattern.
	 *
	 * @param email The email address to be validated.
	 * @return true if the email address is valid, false otherwise.
	 * @throws InvalidUserException If the email address is found to be invalid.
	 */
	public static boolean validateEmail(String email) throws InvalidUserException {
	    if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
	    	throw new InvalidUserException("Invalid email address. Please provide a valid email address.");
	    }
	    return true;
	}
	
}
