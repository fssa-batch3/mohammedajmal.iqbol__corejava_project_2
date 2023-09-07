package com.fssa.freshstocks.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.fssa.freshstocks.constants.*;
import com.fssa.freshstocks.dao.exception.DAOException;
import com.fssa.freshstocks.model.User;
import com.fssa.freshstocks.utils.ConnectionUtil;
import com.fssa.freshstocks.utils.exception.DatabaseException;

public class UserDAO {

	/**
	 * Attempts to log in a user by verifying their email and password in the
	 * database.
	 *
	 * @param user The User object representing the user's login information.
	 * @return {@code true} if the login was successful, {@code false} otherwise.
	 * @throws DAOException If there's an error while interacting with the database.
	 */
	public boolean login(User user) throws DAOException {
		boolean match = false;

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(UserModuleConstants.USER_SELECT_QUERY)) {

			pst.setString(1, user.getEmail());

			try (ResultSet resultSet = pst.executeQuery()) {
				while (resultSet.next()) {
					String emailId = resultSet.getString(UserModuleConstants.EMAIL_COLUMN_NAME);
					String password = resultSet.getString(UserModuleConstants.PASSWORD_COLUMN_NAME);

					if (user.getEmail().equals(emailId) && user.getPassword().equals(password)) {
						match = true;
					}
				}
			}
		} catch (SQLException | DatabaseException e) {
			throw new DAOException(UserModuleConstants.EMAIL_ERROR_MESSAGE + e);
		}
		return match;
	}

	/**
	 * Checks whether a given email address already exists in the database.
	 *
	 * @param The user object contains email to check for existence.
	 * @return {@code true} if the email address already exists, {@code false}
	 *         otherwise.
	 * @throws DAOException If there's an error while interacting with the database.
	 */
	public boolean emailExist(User user) throws DAOException {
		boolean emailExists = false;

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(UserModuleConstants.USER_SELECT_QUERY)) {
			pst.setString(1, user.getEmail());
			try (ResultSet resultSet = pst.executeQuery()) {
				while (resultSet.next()) {
					String emailId = resultSet.getString(UserModuleConstants.EMAIL_COLUMN_NAME);
					if (user.getEmail().equals(emailId)) {
						emailExists = true;
					}
				}
			}
		} catch (SQLException | DatabaseException e) {
			throw new DAOException(UserModuleConstants.EMAIL_ERROR_MESSAGE + e);
		}

		return emailExists;
	}

	/**
	 * Checks whether a given email address already exists in the database.
	 *
	 * @param email The email address to check for existence.
	 * @return {@code true} if the email address already exists, {@code false}
	 *         otherwise.
	 * @throws DAOException If there's an error while interacting with the database.
	 */
	public boolean emailAlreadyExist(String email) throws DAOException {
		boolean emailAlreadyExist = false;

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(UserModuleConstants.USER_SELECT_QUERY)) {
			pst.setString(1, email);
			try (ResultSet resultSet = pst.executeQuery()) {
				while (resultSet.next()) {
					String emailId = resultSet.getString(UserModuleConstants.EMAIL_COLUMN_NAME);
					if (email.equals(emailId)) {
						emailAlreadyExist = true;
						break;
					}
				}
			}
		} catch (SQLException | DatabaseException e) {
			throw new DAOException(UserModuleConstants.EMAIL_ERROR_MESSAGE + e);
		}

		return emailAlreadyExist;
	}

	/**
	 * Registers a new user in the database.
	 *
	 * @param user The User object representing the user to be registered.
	 * @return {@code true} if the registration was successful, {@code false}
	 *         otherwise.
	 * @throws DAOException If there's an error while interacting with the database.
	 */
	public boolean register(User user) throws DAOException {
		int rows = 0;

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(
						"INSERT INTO freshstocks (user_id, username, gender, mobile_number, date_of_birth, email, password, is_seller, avatar_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

			pst.setInt(1, user.getUserId());
			pst.setString(2, user.getUsername());
			pst.setString(3, user.getGender());
			pst.setString(4, user.getMobileNumber());
			pst.setString(5, user.getDateOfBirth());
			pst.setString(6, user.getEmail());
			pst.setString(7, user.getPassword());
			pst.setInt(8, user.getIsSeller());
			pst.setString(9, "https://ui-avatars.com/api/?name=" + user.getUsername() + "&background=random");

			rows = pst.executeUpdate();
		} catch (SQLException | DatabaseException e) {
			throw new DAOException(UserModuleConstants.REGISTER_ERROR_MESSAGE + e);
		}

		return (rows == 1);
	}

	/**
	 * Updates a user's information in the database.
	 *
	 * @param The User object containing the updated user information.
	 * @param userEmail The email address of the user to be updated.
	 * @return {@code true} if the update was successful, {@code false} otherwise.
	 * @throws DAOException If there's an error while interacting with the database.
	 */
	public boolean update(User user, String userEmail) throws DAOException {
		int rows = 0;

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement(
						"UPDATE freshstocks SET gender = ?, mobile_number = ?, date_of_birth = ? WHERE email = ?")) {

			pst.setString(1, user.getGender());
			pst.setString(2, user.getMobileNumber());
			pst.setString(3, user.getDateOfBirth());
			pst.setString(4, userEmail);

			// Execute query
			rows = pst.executeUpdate();
		} catch (SQLException | DatabaseException e) {
			throw new DAOException(UserModuleConstants.UPDATE_ERROR_MESSAGE + e);
		}

		return (rows == 1);
	}

	/**
	 * Marks a user as deleted or undeleted in the database.
	 *
	 * @param userEmail The email address of the user to be marked.
	 * @param isDeleted The value indicating whether the user should be marked as
	 *                  deleted (1) or not deleted (0).
	 * @return {@code true} if the marking was successful, {@code false} otherwise.
	 * @throws DAOException If there's an error while interacting with the database.
	 */
	public boolean delete(String userEmail, int isDeleted) throws DAOException {
		int rows = 0;

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection
						.prepareStatement("UPDATE freshstocks SET is_deleted = ? WHERE email = ?")) {

			String isDelete = Integer.toString(isDeleted);
			pst.setString(1, isDelete);
			pst.setString(2, userEmail);

			// Execute query
			rows = pst.executeUpdate();

		} catch (SQLException | DatabaseException e) {
			throw new DAOException(UserModuleConstants.DELETE_ERROR_MESSAGE + e);
		}

		return (rows == 1);
	}
	
	
	
	/**
	 * retrieve the user profile using getUserProfileFromUserID method from the database.
	 *
	 * @param The parameter contains userID to check for existence.
	 * @return {@code true} if the userID is valid, {@code false}
	 *         otherwise.
	 * @throws DAOException If there's an error while interacting with the database.
	 */
	public String getUserProfileFromUserID(int userID) throws DAOException {
		String userProfile = null;

		try (Connection connection = ConnectionUtil.getConnection();
				PreparedStatement pst = connection.prepareStatement("SELECT avatar_url FROM freshstocks WHERE user_id=?")) {
			pst.setInt(1, userID);
			try (ResultSet resultSet = pst.executeQuery()) {
				while (resultSet.next()) {
					userProfile = resultSet.getString("avatar_url");
				}
			}
		} catch (SQLException | DatabaseException e) {
			throw new DAOException(UserModuleConstants.EMAIL_ERROR_MESSAGE + e);
		}

		return userProfile;
	}
}