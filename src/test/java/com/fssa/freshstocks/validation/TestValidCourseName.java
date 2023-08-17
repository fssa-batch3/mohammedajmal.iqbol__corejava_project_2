package com.fssa.freshstocks.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.fssa.freshstocks.validation.exception.InvalidCourseException;

class TestValidCourseName {

	@Test
	void testValidName() {
		try {
			assertTrue(CourseValidator.validateName("forex course"));
		} catch (InvalidCourseException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testInvalidName() {
		try {
			CourseValidator.validateName("candles5_course");
		} catch (InvalidCourseException e) {
			assertEquals("Course Name is Invalid",e.getMessage());
		}
	}
}