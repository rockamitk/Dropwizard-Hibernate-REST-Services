package com.amit.hello.mapper;

import java.util.List;

import com.amit.hello.model.ProductObject;

import db.tables.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public abstract interface ProductObjectMapper
{
  public static final ProductObjectMapper INSTANCE = (ProductObjectMapper)Mappers.getMapper(ProductObjectMapper.class);
  
  public abstract ProductObject toObject(Product jpaObject);
  
  public abstract Product toJpa(ProductObject object);
  
  public abstract List<ProductObject> toObjectList(List<Product> paramList);
  
  public abstract List<Product> toJpaList(List<ProductObject> paramList);
}