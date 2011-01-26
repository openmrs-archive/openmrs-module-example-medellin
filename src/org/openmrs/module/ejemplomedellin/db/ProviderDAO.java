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
package org.openmrs.module.ejemplomedellin.db;

import java.util.List;

import org.openmrs.Person;
import org.openmrs.User;
import org.openmrs.module.ejemplomedellin.Invitation;


public interface ProviderDAO {

	List<Person> getProvidersByUser(User user);

	/**
     * Saves an invitation to the database
     * 
     * @param invitation
     */
    void saveInvitation(Invitation invitation);

	/**
     * Return *all* invitations, for admin purposes
     * 
     * @return
     */
    List<Invitation> getAllInvitations();
	
}
