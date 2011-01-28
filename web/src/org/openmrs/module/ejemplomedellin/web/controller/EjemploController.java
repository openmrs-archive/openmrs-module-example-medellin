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

import java.util.List;

import org.openmrs.Person;
import org.openmrs.Relationship;
import org.openmrs.RelationshipType;
import org.openmrs.Role;
import org.openmrs.User;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.module.ejemplomedellin.ProviderService;
import org.openmrs.notification.Message;
import org.openmrs.notification.MessageException;
import org.openmrs.util.OpenmrsConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 */
@Controller
public class EjemploController {

	@RequestMapping(value="/module/ejemplomedellin/inviteProvider", method=RequestMethod.GET)
	public void patientInvitesProvider(Model model) {
		UserService userService = Context.getUserService();
		Role provider = userService.getRole(OpenmrsConstants.PROVIDER_ROLE);
		List<User> providers = userService.getUsersByRole(provider);
		
		List<Person> myProviders = Context.getService(ProviderService.class).getProvidersForCurrentUser();
				
		model.addAttribute("myProviders", myProviders);
		model.addAttribute("allProviders", providers);
	}
	
	@RequestMapping(value="/module/ejemplomedellin/inviteProvider", method=RequestMethod.POST)
	public String handleInvitation(@RequestParam("userIdToInvite") Integer userId) {
		Person currentUser = Context.getAuthenticatedUser().getPerson();
		Person provider = Context.getUserService().getUser(userId).getPerson();
		RelationshipType relType = Context.getPersonService().getRelationshipType(5);
		
		Relationship rel = new Relationship();
		rel.setPersonA(provider);
		rel.setPersonB(currentUser);
		rel.setRelationshipType(relType);
		Context.getPersonService().saveRelationship(rel);
		
		Message email = new Message();
		email.addRecipient("djazayeri@gmail.com");
		email.setSubject("Invitiation");
		email.setContent("You have been invited to share a medical record with " + currentUser.getPersonName().getFullName());
		try {
			Context.getMessageService().sendMessage(email);
		} catch (MessageException ex) {
			throw new RuntimeException("Failed to send message", ex);
		}
		
		return "redirect:inviteProvider.form";
	}
	
	@RequestMapping("/module/ejemplomedellin/checkIfUserExists")
	public String checkIfUserExists(@RequestParam("username") String username,
	                                Model model) {
		User u = Context.getUserService().getUserByUsername(username);
		String jsonReturnValue = u == null ? "{ \"exists\" : \"false\" }" : "{ \"exists\" : \"true\" }";
		model.addAttribute("json", jsonReturnValue);
		return "/module/ejemplomedellin/json";
	}
	
}
