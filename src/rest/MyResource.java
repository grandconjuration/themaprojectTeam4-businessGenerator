package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/resources")
public class MyResource {

   @GET
   @Path("{subResources: [a-zA-Z0-9_/]+}")
   public String get(@PathParam("subResources") String subResources) {
	   return subResources; 
   }
}