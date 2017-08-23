package com.amit.hello.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ResourceNotFoundException
  extends WebApplicationException
{
  private static final long serialVersionUID = 1L;
  
  public ResourceNotFoundException(String message)
  {
    super(Response.status(Response.Status.NOT_FOUND).entity(message).build());
  }
  
  public ResourceNotFoundException(String message, String realm)
  {
    super(Response.status(Response.Status.NOT_FOUND).entity(message).build());
  }
}