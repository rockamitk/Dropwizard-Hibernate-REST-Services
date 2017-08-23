package com.amit.hello.resources;

import io.dropwizard.hibernate.UnitOfWork;






import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.hibernate.SessionFactory;

import com.amit.hello.helloWorldConfiguration;
import com.amit.hello.core.ProductController;
import com.amit.hello.exception.ResourceNotFoundException;
import com.amit.hello.model.ProductObject;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponses;

import db.tables.Product;


@Path("/api/v1/user_db/product")
@Api(value="/api/v1/user_db/product", description="Operations about product")
@Produces({"application/json"})
public class ProductResource {
	private SessionFactory sessionFactory;
	private helloWorldConfiguration configuration;
	
    public ProductResource(SessionFactory sessionFactory, helloWorldConfiguration configuration) {
    	this.sessionFactory = sessionFactory;
    	this.configuration = configuration;
    }

    @GET
    @Path("/{productId}")
    @Timed
    @UnitOfWork
    @Produces({"application/json"})
    @ApiOperation(value="Find product by ID", notes="Returns a product object", response=ProductObject.class)
    @ApiResponses({@com.wordnik.swagger.annotations.ApiResponse(code=404, message="Product Resource Not Found")})
    public Response getProductById(@ApiParam(value="productId", required=true) @PathParam("productId") String productId) throws ResourceNotFoundException{
    	ProductObject productObject = null;
      try
      {
    	  productObject = new ProductController(this.sessionFactory, this.configuration).getProductById(productId);
      }
      catch (Exception e)
      {
        throw new ResourceNotFoundException(e.getMessage());
      }
      return Response.ok().entity(productObject).build();
    }

    @POST
    @Timed
    @UnitOfWork
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @ApiOperation(value="Add a new student to the specified tenant, class and section", notes="Returns the added student", response=ProductObject.class)
    @ApiResponses({@com.wordnik.swagger.annotations.ApiResponse(code=404, message="Tenant Resource Not Found"), @com.wordnik.swagger.annotations.ApiResponse(code=404, message="Class Resource Not Found"), @com.wordnik.swagger.annotations.ApiResponse(code=404, message="Section Resource Not Found"), @com.wordnik.swagger.annotations.ApiResponse(code=404, message="Student Resource Not Found"), @com.wordnik.swagger.annotations.ApiResponse(code=405, message="Invalid input")})
    public Response addProduct(@ApiParam(value="Student object that needs to be added", required=true) ProductObject object)
      throws ResourceNotFoundException
    {
      ProductObject newProductObject = null;
      try
      {
    	  newProductObject = new ProductController(this.sessionFactory, configuration).addProduct(object);
      }
      catch (Exception e)
      {
        throw new ResourceNotFoundException(e.getMessage());
      }
      return Response.ok().entity(newProductObject).build();
    }    
}