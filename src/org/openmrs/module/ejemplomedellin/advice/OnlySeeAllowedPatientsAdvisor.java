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
package org.openmrs.module.ejemplomedellin.advice;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.openmrs.Relationship;
import org.openmrs.RelationshipType;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.module.ejemplomedellin.ProviderService;
import org.springframework.aop.MethodBeforeAdvice;


/**
 *
 */
public class OnlySeeAllowedPatientsAdvisor implements MethodBeforeAdvice {

	/**
     * @see org.springframework.aop.MethodBeforeAdvice#before(java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
	    if (!method.getName().equals("getPatient"))
	    	return;

	    // this is PatientService.getPatient(Integer)
	    Integer patientId = (Integer) args[0];
	    
		RelationshipType providerRelType = Context.getPersonService().getRelationshipType(5);
	
		for (Relationship rel : Context.getPersonService().getRelationshipsByPerson(Context.getAuthenticatedUser().getPerson())) {
			if (rel.getRelationshipType().equals(providerRelType)) {
				if (rel.getPersonB().getPersonId().equals(patientId))
					return;
			}
		}
		
		throw new APIException("You are not allowed to see this patient");
    }
	
	

}
