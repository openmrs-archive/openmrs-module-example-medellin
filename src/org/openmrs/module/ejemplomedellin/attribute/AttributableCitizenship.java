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
package org.openmrs.module.ejemplomedellin.attribute;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openmrs.Attributable;


/**
 *
 */
public class AttributableCitizenship implements Attributable<AttributableCitizenship> {

	static Map<String, String> codeToCountry = new LinkedHashMap<String, String>();
	static {
		codeToCountry.put("co", "Colombia");
		codeToCountry.put("us", "Estados Unidos");
	}
	
	private String value;
	
	public AttributableCitizenship(String value) {
		this.value = value;
	}
	
	/**
     * @see org.openmrs.Attributable#findPossibleValues(java.lang.String)
     */
    @Override
    public List<AttributableCitizenship> findPossibleValues(String arg0) {
	    // TODO Auto-generated method stub
	    return null;
    }

	/**
     * @see org.openmrs.Attributable#getDisplayString()
     */
    @Override
    public String getDisplayString() {
	    return codeToCountry.get(value);
    }

	/**
     * @see org.openmrs.Attributable#getPossibleValues()
     */
    @Override
    public List<AttributableCitizenship> getPossibleValues() {
	    List<AttributableCitizenship> ret = new ArrayList<AttributableCitizenship>();
	    for (String code : codeToCountry.keySet()) {
	    	ret.add(new AttributableCitizenship(code));
	    }
	    return ret;
    }

	/**
     * @see org.openmrs.Attributable#hydrate(java.lang.String)
     */
    @Override
    public AttributableCitizenship hydrate(String asString) {
	    return new AttributableCitizenship(asString);
    }

	/**
     * @see org.openmrs.Attributable#serialize()
     */
    @Override
    public String serialize() {
	    return value;
    }

}
