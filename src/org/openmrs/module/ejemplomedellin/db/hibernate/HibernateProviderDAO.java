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
package org.openmrs.module.ejemplomedellin.db.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Person;
import org.openmrs.Relationship;
import org.openmrs.RelationshipType;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.ejemplomedellin.Invitation;
import org.openmrs.module.ejemplomedellin.db.ProviderDAO;


/**
 *
 */
public class HibernateProviderDAO implements ProviderDAO {
	
	SessionFactory sessionFactory;
		
		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}
		
		/**
		 * @see org.openmrs.module.ejemplomedellin.db.ProviderDAO#getProvidersByUser(org.openmrs.User)
		 */
		@Override
		public List<Person> getProvidersByUser(User user) {
			RelationshipType providerRelType = Context.getPersonService().getRelationshipType(1);
			
			// relationship of Provider/Patient has personA=provider and personB=patient
			/*
			String hql = "select personA from Relationship where personB = :thisPerson and relationshipType = :relType";
			Query q = sessionFactory.getCurrentSession().createQuery(hql);
			q.setParameter("thisPerson", user.getPerson());
			q.setParameter("relType", providerRelType);
			return (List<Person>) q.list();
			*/
			
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(Relationship.class);
			crit.add( Restrictions.eq("personB", user.getPerson()) );
			crit.add( Restrictions.eq("relationshipType", providerRelType) );
			crit.setProjection( Projections.property("personA") );
			return (List<Person>) crit.list();
			
		}
		
		/**
		 * @see org.openmrs.module.ejemplomedellin.db.ProviderDAO#getAllInvitations()
		 */
		@Override
		public List<Invitation> getAllInvitations() {
		    Criteria crit = sessionFactory.getCurrentSession().createCriteria(Invitation.class);
		    return crit.list();
		    
		    //Query query = sessionFactory.getCurrentSession().createQuery("from Invitation");
		    //return query.list();
		}
		
		/**
		 * @see org.openmrs.module.ejemplomedellin.db.ProviderDAO#saveInvitation(org.openmrs.module.ejemplomedellin.Invitation)
		 */
		@Override
		public void saveInvitation(Invitation invitation) {
		    sessionFactory.getCurrentSession().saveOrUpdate(invitation);
		}
	}
