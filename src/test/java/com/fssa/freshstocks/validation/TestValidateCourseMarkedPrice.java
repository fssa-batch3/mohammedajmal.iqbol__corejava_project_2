package com.fssa.freshstocks.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.fssa.freshstocks.validation.exception.InvalidCourseException;

class TestValidateCourseMarkedPrice {

	@Test 
	void testValidMarkedPrice()  {
		
		try {
			assertTrue(CourseValidator.validateMarkedPrice("1000"));
		} catch (InvalidCourseException e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	void testInvalidMarkedPrice()  {
		
		try {
			CourseValidator.validateMarkedPrice("Rs.1000");
		} catch (InvalidCourseException e) {
			assertEquals("Invalid course marked price. Please provide a valid price (e.g., 100 or 99.99).",e.getMessage());
		}
	}
}
