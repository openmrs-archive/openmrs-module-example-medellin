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
package org.openmrs.module.ejemplomedellin.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.Relationship;
import org.openmrs.RelationshipType;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.ejemplomedellin.Invitation;
import org.openmrs.module.ejemplomedellin.ProviderService;
import org.openmrs.module.ejemplomedellin.db.ProviderDAO;


/**
 *
 */
public class ProviderServiceImpl extends BaseOpenmrsService implements ProviderService {

	private ProviderDAO dao;
	
	public void setDao(ProviderDAO dao) {
		this.dao = dao;
	}
	
	/**
	 * @see org.openmrs.module.ejemplomedellin.ProviderService#getProvidersForCurrentUser()
	 */
	@Override
	public List<Person> getProvidersForCurrentUser() {
		return dao.getProvidersByUser(Context.getAuthenticatedUser());
	}
	
	
	/**
	 * @see org.openmrs.module.ejemplomedellin.ProviderService#isAllowedToSee(org.openmrs.User, java.lang.Integer)
	 */
	@Override
	public boolean isAllowedToSee(User whichUser, Integer patientId) {
		// TODO do this more efficiently directly in the DAO
		RelationshipType providerRelType = Context.getPersonService().getRelationshipType(5);
		List<Integer> myPatientIds = new ArrayList<Integer>();
		
		for (Relationship rel : Context.getPersonService().getRelationshipsByPerson(whichUser.getPerson())) {
			if (rel.getRelationshipType().equals(providerRelType)) {
				myPatientIds.add(rel.getPersonB().getPersonId());
			}
		}
		
		return myPatientIds.contains(patientId);
	}
	
	/**
	 * @return 
	 * @see org.openmrs.module.ejemplomedellin.ProviderService#getAllInvitations()
	 */
	@Override
	public List<Invitation> getAllInvitations() {
	    return dao.getAllInvitations();
	}
	
	
	/**
	 * @see org.openmrs.module.ejemplomedellin.ProviderService#inviteProvider(org.openmrs.Patient, org.openmrs.User)
	 */
	@Override
	public void inviteProvider(Patient patient, User provider) {
	    Invitation invitation = new Invitation();
	    invitation.setPatient(patient);
	    invitation.setProvider(provider);
	    invitation.setDateCreated(new Date());
	    invitation.setAcceptedByPatient(true);
	    invitation.setAcceptedByProvider(false);
	    dao.saveInvitation(invitation);
	}
}
