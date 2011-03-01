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

import org.openmrs.api.PasswordException;
import org.openmrs.api.context.Context;
import org.openmrs.util.OpenmrsUtil;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * Error-checking for registerUser form
 */
public class UserDetailsValidator implements Validator {
	
	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(UserDetails.class);
	}
	
	/**
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.null");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.null");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.null");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.null");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmEmail", "error.null");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "citizenship", "error.null");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.null");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "error.null");
		ValidationUtils.rejectIfEmpty(errors, "birthdate", "error.null");
		
		UserDetails details = (UserDetails) target;
		if (details.getEmail() != null && details.getConfirmEmail() != null) {
			if (!details.getEmail().equals(details.getConfirmEmail()))
				errors.rejectValue("confirmEmail", "ejemplomedellin.error.not.same");
		}
		if (details.getUsername() != null && details.getPassword() != null) {
			try {
				OpenmrsUtil.validatePassword(details.getUsername(), details.getPassword(), null);
			} catch (PasswordException ex) {
				errors.rejectValue("password", ex.getMessage());
			}
		}
		if (details.getPassword() != null && details.getConfirmPassword() != null) {
			if (!details.getPassword().equals(details.getConfirmPassword()))
				errors.rejectValue("confirmPassword", "ejemplomedellin.error.not.same");
		}
	}
	
}
