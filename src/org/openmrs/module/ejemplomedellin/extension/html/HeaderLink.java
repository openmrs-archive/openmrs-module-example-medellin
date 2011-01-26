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
package org.openmrs.module.ejemplomedellin.extension.html;

import org.openmrs.module.web.extension.LinkExt;


/**
 *
 */
public class HeaderLink extends LinkExt {
	
	/**
	 * @see org.openmrs.module.web.extension.LinkExt#getLabel()
	 */
	@Override
	public String getLabel() {
		return "My Providers";
	}
	
	/**
	 * @see org.openmrs.module.web.extension.LinkExt#getRequiredPrivilege()
	 */
	@Override
	public String getRequiredPrivilege() {
		return null;
	}
	
	/**
	 * @see org.openmrs.module.web.extension.LinkExt#getUrl()
	 */
	@Override
	public String getUrl() {
		return "/module/ejemplomedellin/inviteProvider.form";
	}
	
}
