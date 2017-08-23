package com.amit.hello.mapper;

import com.amit.hello.model.ProductObject;
import db.tables.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-08-22T00:01:38+0530"
)
public class ProductObjectMapperImpl implements ProductObjectMapper {

    @Override
    public ProductObject toObject(Product jpaObject)  {
        if ( jpaObject == null ) {
            return null;
        }

        ProductObject productObject = new ProductObject();
        productObject.setProductId( jpaObject.getProductId() );
        productObject.setCreatedDate( jpaObject.getCreatedDate() );
        productObject.setIsActive( jpaObject.getIsActive() );
        productObject.setLastUpdatedDate( jpaObject.getLastUpdatedDate() );
        productObject.setProductCategory( jpaObject.getProductCategory() );
        productObject.setProductDescription( jpaObject.getProductDescription() );
        productObject.setProductName( jpaObject.getProductName() );

        return productObject;
    }


    @Override
    public Product toJpa(ProductObject object)  {
        if ( object == null ) {
            return null;
        }

        Product product = new Product();
        product.setProductId( object.getProductId() );
        product.setCreatedDate( object.getCreatedDate() );
        product.setIsActive( object.getIsActive() );
        product.setLastUpdatedDate( object.getLastUpdatedDate() );
        product.setProductCategory( object.getProductCategory() );
        product.setProductDescription( object.getProductDescription() );
        product.setProductName( object.getProductName() );

        return product;
    }


    @Override
    public List<ProductObject> toObjectList(List<Product> paramList)  {
        if ( paramList == null ) {
            return null;
        }

        List<ProductObject> list = new ArrayList<ProductObject>();

        for ( Product product : paramList ) {
            list.add( toObject( product ) );
        }

        return list;
    }


    @Override
    public List<Product> toJpaList(List<ProductObject> paramList)  {
        if ( paramList == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>();

        for ( ProductObject productObject : paramList ) {
            list.add( toJpa( productObject ) );
        }

        return list;
    }

}
