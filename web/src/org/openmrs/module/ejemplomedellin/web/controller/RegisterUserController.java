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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.openmrs.Person;
import org.openmrs.PersonAttribute;
import org.openmrs.PersonName;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.ejemplomedellin.VitalboxConstants;
import org.openmrs.web.WebConstants;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


/**
 * Controller for the page where a user requests their account
 */
@Controller
@SessionAttributes("userDetails")
public class RegisterUserController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
			new CustomDateEditor(Context.getDateFormat(), true, 10));
	}

	@RequestMapping(value="/module/ejemplomedellin/registerUser", method=RequestMethod.GET)
	public void showForm(Model model) {
		model.addAttribute("userDetails", new UserDetails());
	}
	
	@RequestMapping(value="/module/ejemplomedellin/registerUser", method=RequestMethod.POST)
	public String submitForm(@ModelAttribute("userDetails") UserDetails userDetails, Errors errors,
	                         SessionStatus status, HttpSession session) {
		new UserDetailsValidator().validate(userDetails, errors);
		if (errors.hasErrors()) {
			return "/module/ejemplomedellin/registerUser";
		} else {
			createUser(userDetails);
			status.setComplete();
			session.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Saved user: " + userDetails.getUsername());
			return "redirect:registerUser.form";
		}
	}
	
	@RequestMapping("/module/ejemplomedellin/q/checkUsername")
	@ResponseBody
	public Map<String, Boolean> isUsernameTaken(@RequestParam("username") String username) {
		Map<String, Boolean> ret = new HashMap<String, Boolean>();
		ret.put("result", Context.getUserService().getUserByUsername(username) != null);
		return ret;
	}

	/**
     * Save the user to the database
     * 
     * @param userDetails
     */
    private void createUser(UserDetails details) {
    	Person person = new Person();
    	person.setGender(details.getGender());
    	person.setBirthdate(details.getBirthdate());
    
	    PersonName name = new PersonName();
	    name.setGivenName(details.getFirstName());
	    name.setMiddleName(details.getSecondName());
	    name.setFamilyName(details.getLastName());
	    name.setFamilyName2(details.getLastName2());
	    person.addName(name);
	    
	    User user = new User(person);
	    user.setUsername(details.getUsername());
	    user.setUserProperty(VitalboxConstants.USER_PROPERTY_EMAIL, details.getEmail());
	    user.setUserProperty(VitalboxConstants.USER_PROPERTY_EMAIL_CONFIRMATION, "false");
	    
	    PersonAttribute citizenship = new PersonAttribute(VitalboxConstants.PERSON_ATTRIBUTE_TYPE_CITIZENSHIP(),
	    												  details.getCitizenship());
	    person.addAttribute(citizenship);
	    
	    //TODO add role "Paciente" to user
	    
	    Context.getUserService().saveUser(user, details.getPassword());
    }
	
}
