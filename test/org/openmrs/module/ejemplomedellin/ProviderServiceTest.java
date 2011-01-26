package org.openmrs.module.ejemplomedellin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.test.BaseModuleContextSensitiveTest;

public class ProviderServiceTest extends BaseModuleContextSensitiveTest {
	
	ProviderService service;
	
	@Before
	public void runBefore() {
		service = Context.getService(ProviderService.class);
	}
	
	/**
	 * @see ProviderService#inviteProvider(Patient,User)
	 * @verifies create an invitation
	 */
	@Test
	public void inviteProvider_shouldCreateAnInvitation() throws Exception {
		int numBefore = service.getAllInvitations().size();

		Patient patient = Context.getPatientService().getPatient(2);
		User provider = Context.getUserService().getUser(501);
		service.inviteProvider(patient, provider);
		
		int numAfter = service.getAllInvitations().size();
		
		Assert.assertEquals(numBefore + 1, numAfter);
	}
}