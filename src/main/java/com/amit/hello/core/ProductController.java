package com.amit.hello.core;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import org.hibernate.SessionFactory;

import com.amit.hello.helloWorldConfiguration;
import com.amit.hello.db.ProductDAO;
import com.amit.hello.mapper.ProductObjectMapper;
import com.amit.hello.model.ProductObject;

import db.tables.Product;

public class ProductController {
	SessionFactory sessionFactory;
	helloWorldConfiguration configuration;
	ProductDAO dao;
	
	public ProductController(SessionFactory sessionFactory,helloWorldConfiguration configuration) {
		this.sessionFactory = sessionFactory;
		this.configuration = configuration;
		this.dao = new ProductDAO(sessionFactory);
	}
	
	public ProductObject getProductById(String productId) throws Exception {
		Product productdao = null;
		productdao = this.dao.getPriductById(productId);
		if (productdao == null) {
			throw new Exception("Not found");
	    }
		ProductObject studentObject = ProductObjectMapper.INSTANCE.toObject(productdao);
	    return studentObject;
  }

	public ProductObject addProduct(ProductObject object) throws Exception {
		Timestamp currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
		Product productDao = ProductObjectMapper.INSTANCE.toJpa(object);
		productDao.setCreatedDate(currentTimestamp);
		productDao.setLastUpdatedDate(currentTimestamp);
		productDao.setIsActive((short)1);
		productDao.setProductId(UUID.randomUUID().toString().replaceAll("-", "").trim());
		productDao = this.dao.addProduct(productDao);
		if (productDao == null) {
			throw new Exception("Product does not inserted");
	    }
		ProductObject productObject = ProductObjectMapper.INSTANCE.toObject(productDao);
	    return productObject;
	}
}