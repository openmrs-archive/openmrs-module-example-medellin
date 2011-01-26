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
package org.openmrs.module.ejemplomedellin;

import java.util.Date;

import org.openmrs.Patient;
import org.openmrs.User;


/**
 * Represents an invitation from a patient to a provider (or vice versa)
 */
public class Invitation {

	private Integer id;
	private Date dateCreated;
	private Patient patient;
	private User provider;
	private Boolean acceptedByPatient;
	private Boolean acceptedByProvider;
	
	public boolean isAccepted() {
		return acceptedByPatient != null && acceptedByProvider != null && acceptedByPatient && acceptedByProvider;
	}
	
    /**
     * @return the id
     */
    public Integer getId() {
    	return id;
    }
	
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
    	this.id = id;
    }
	
    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
    	return dateCreated;
    }
	
    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
    	this.dateCreated = dateCreated;
    }
	
    /**
     * @return the patient
     */
    public Patient getPatient() {
    	return patient;
    }
	
    /**
     * @param patient the patient to set
     */
    public void setPatient(Patient patient) {
    	this.patient = patient;
    }
	
    /**
     * @return the provider
     */
    public User getProvider() {
    	return provider;
    }
	
    /**
     * @param provider the provider to set
     */
    public void setProvider(User provider) {
    	this.provider = provider;
    }
	
    /**
     * @return the acceptedByPatient
     */
    public Boolean getAcceptedByPatient() {
    	return acceptedByPatient;
    }
	
    /**
     * @param acceptedByPatient the acceptedByPatient to set
     */
    public void setAcceptedByPatient(Boolean acceptedByPatient) {
    	this.acceptedByPatient = acceptedByPatient;
    }
	
    /**
     * @return the acceptedByProvider
     */
    public Boolean getAcceptedByProvider() {
    	return acceptedByProvider;
    }
	
    /**
     * @param acceptedByProvider the acceptedByProvider to set
     */
    public void setAcceptedByProvider(Boolean acceptedByProvider) {
    	this.acceptedByProvider = acceptedByProvider;
    }
	
}
