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
import java.util.Iterator;
import java.util.List;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.ejemplomedellin.ProviderService;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;


/**
 *
 */
public class LimitPatientSearchResultsAdvisor extends StaticMethodMatcherPointcutAdvisor implements Advisor {
	

	/**
	 * @see org.springframework.aop.MethodMatcher#matches(java.lang.reflect.Method, java.lang.Class)
	 */
	@Override
	public boolean matches(Method method, Class targetClass) {
		// only match getPatients(String, Integer, Integer)
		if (!method.getName().equals("getPatients"))
			return false;
		Class[] argTypes = method.getParameterTypes();
		return argTypes.length == 3 && argTypes[0].equals(String.class) && argTypes[1].equals(Integer.class) && argTypes[2].equals(Integer.class);
	}
	
	/**
	 * @see org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor#getAdvice()
	 */
	@Override
	public Advice getAdvice() {
	    return new LimitResultsAdvice();
	}

	
    /**
    *
    */
   public class LimitResultsAdvice implements MethodInterceptor {

		/**
         * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
         */
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
	        List<Patient> result = (List<Patient>) invocation.proceed();
	        for (Iterator<Patient> i = result.iterator(); i.hasNext(); ) {
	        	Patient candidate = i.next();
	        	if (!Context.getService(ProviderService.class).isAllowedToSee(Context.getAuthenticatedUser(), candidate.getPatientId())) {
	        		i.remove();
	        	}
	        }
	        return result;
        }

   }
}
