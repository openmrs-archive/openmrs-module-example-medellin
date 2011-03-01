/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.ejemplomedellin.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * model object for the registerUser form
 */
public class UserDetails {
	
	private String username;
	private String firstName;
	private String secondName;
	private String lastName;
	private String lastName2;
	private String email;
	private String confirmEmail;
	private String password;
	private String confirmPassword;
	private String gender;
	private Date birthdate;
	private String citizenship;
	
	private String friendEmails;
	
	public UserDetails() {
	}
	
    /**
     * @return the username
     */
    public String getUsername() {
    	return username;
    }
	
    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
    	this.username = username;
    }
	
    /**
     * @return the firstName
     */
    public String getFirstName() {
    	return firstName;
    }
	
    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }
	
    /**
     * @return the lastName
     */
    public String getLastName() {
    	return lastName;
    }
	
    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }

	
    /**
     * @return the secondName
     */
    public String getSecondName() {
    	return secondName;
    }

	
    /**
     * @param secondName the secondName to set
     */
    public void setSecondName(String secondName) {
    	this.secondName = secondName;
    }

	
    /**
     * @return the lastName2
     */
    public String getLastName2() {
    	return lastName2;
    }

	
    /**
     * @param lastName2 the lastName2 to set
     */
    public void setLastName2(String lastName2) {
    	this.lastName2 = lastName2;
    }

	
    /**
     * @return the email
     */
    public String getEmail() {
    	return email;
    }

	
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
    	this.email = email;
    }

	
    /**
     * @return the confirmEmail
     */
    public String getConfirmEmail() {
    	return confirmEmail;
    }

	
    /**
     * @param confirmEmail the confirmEmail to set
     */
    public void setConfirmEmail(String confirmEmail) {
    	this.confirmEmail = confirmEmail;
    }

	
    /**
     * @return the password
     */
    public String getPassword() {
    	return password;
    }

	
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
    	this.password = password;
    }

	
    /**
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
    	return confirmPassword;
    }

	
    /**
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
    	this.confirmPassword = confirmPassword;
    }

	
    /**
     * @return the gender
     */
    public String getGender() {
    	return gender;
    }

	
    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
    	this.gender = gender;
    }

	
    /**
     * @return the birthdate
     */
    public Date getBirthdate() {
    	return birthdate;
    }

	
    /**
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(Date birthdate) {
    	this.birthdate = birthdate;
    }

	
    /**
     * @return the citizenship
     */
    public String getCitizenship() {
    	return citizenship;
    }

	
    /**
     * @param citizenship the citizenship to set
     */
    public void setCitizenship(String citizenship) {
    	this.citizenship = citizenship;
    }

	
    /**
     * @return the friendEmails
     */
    public String getFriendEmails() {
    	return friendEmails;
    }

	
    /**
     * @param friendEmails the friendEmails to set
     */
    public void setFriendEmails(String friendEmails) {
    	this.friendEmails = friendEmails;
    }
	
   
}