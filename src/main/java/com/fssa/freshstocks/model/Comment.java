package com.fssa.freshstocks.model;

public class Comment {

	// Fields
	private int commentId;
	private int userId;
	private int courseId;
	private String commentBody;
	private String username;
	private String coursename;
	private String userProfile;
	private String createdTime;

	// Constructors

	/**
	 * Constructor for creating a comment with course ID, user ID, and comment body.
	 *
	 * @param courseId The ID of the course the comment is related to.
	 * @param userId   The ID of the user who posted the comment.
	 * @param comment  The content of the comment.
	 */
	public Comment(int courseId, int userId, String comment) {
		this.courseId = courseId;
		this.userId = userId;
		this.commentBody = comment;
	}

	/**
	 * Constructor for creating a comment with comment ID, username, coursename, and
	 * comment body.
	 *
	 * @param commentId   The ID of the comment.
	 * @param username    The username of the user who posted the comment.
	 * @param coursename  The name of the course the comment is related to.
	 * @param commentBody The content of the comment.
	 */

	public Comment(int commentId, int userId, String username, String userProfile, String coursename,
			String commentBody, String createdTime) {
		this.commentId = commentId;
		this.userId = userId;
		this.username = username;
		this.coursename = coursename;
		this.commentBody = commentBody;
		this.userProfile = userProfile;
		this.createdTime = createdTime;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * Constructor for creating a comment with comment ID, course ID, user ID, and
	 * comment body.
	 *
	 * @param commentId   The ID of the comment.
	 * @param courseId    The ID of the course the comment is related to.
	 * @param userId      The ID of the user who posted the comment.
	 * @param commentBody The content of the comment.
	 */

	public Comment(int commentId, int courseId, int userId, String comment) {
		this.commentId = commentId;
		this.courseId = courseId;
		this.userId = userId;
		this.commentBody = comment;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

	public Comment(int commentID, String commentBody) {
		this.commentId = commentID;
		this.commentBody = commentBody;
	}

	/**
	 * Constructor for creating a comment with just the comment body.
	 *
	 * @param comment The content of the comment.
	 */
	public Comment(String comment) {
		this.commentBody = comment;
	}

	/**
	 * Constructor for creating a comment with only the course ID.
	 *
	 * @param courseId The ID of the course the comment is related to.
	 */
	public Comment(int courseId) {
		this.courseId = courseId;
	}

	// Getters and Setters
	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getComment() {
		return commentBody;
	}

	// toString method to provide a string representation of the Comment object
	@Override
	public String toString() {
		return "[commentId=" + commentId + ", username=" + username + ", coursename=" + coursename + ", commentBody="
				+ commentBody + "]";
	}

}
