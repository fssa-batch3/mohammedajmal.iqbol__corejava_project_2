package com.fssa.freshstocks.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import com.fssa.freshstocks.model.*;
import com.fssa.freshstocks.validation.exception.InvalidCommentException;

class TestValidateCommentBody {

	@Test
	void testValidCommentBody() {
	    try {
	        assertTrue(CommentValidator.validateComment("this is test comment"));
	    } catch (InvalidCommentException e) {
	        fail("Unexpected exception: " + e.getMessage());
	    }
	}

	@Test
	void testInvalidCommentBody() {
		Comment comment = new Comment(1,1," ");
	        try {
				CommentValidator.validateComment(comment);
			} catch (InvalidCommentException e) {
				assertEquals("Course comment is not valid",e.getMessage());
			}
	}
}
