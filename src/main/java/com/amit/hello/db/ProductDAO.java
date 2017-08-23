package com.amit.hello.db;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.amit.hello.model.ProductObject;

import db.tables.Product;
import io.dropwizard.hibernate.AbstractDAO;

public class ProductDAO extends AbstractDAO<Product> {	
	public ProductDAO(SessionFactory sessionFactory)
	{
	  super(sessionFactory);
	}
	public Product getPriductById(String productId){
	    Session session = currentSession();
	    Criteria criteria = session.createCriteria(Product.class);
	    criteria.add(Restrictions.eq("productId", productId));
	    criteria.add(Restrictions.eq("isActive", Short.valueOf((short)1)));
	    criteria.setMaxResults(1);
	    return (Product)criteria.uniqueResult();
	    
//	    EntityManager em = new EntityManager ();
//	    CriteriaBuilder cb = em.getCriteriaBuilder();
//	    
//	    CriteriaQuery<Product> q = cb.createQuery(Product.class);
//	    Root<Product> c = q.from(Product.class);
//	    ParameterExpression<Integer> p = cb.parameter(Integer.class);
//	    q.select(c).where(cb.gt(c.get("population"), p));
	}
	public Product addProduct(Product jpaObject) {
		persist(jpaObject);
		return jpaObject;
	}
}