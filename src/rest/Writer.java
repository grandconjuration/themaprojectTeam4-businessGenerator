package rest;

import generate.WriteController;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/writer/app/{appid}")
public class Writer {

	   @GET
	   public String get(@PathParam("appid") String subResources) {
		   WriteController wc = new WriteController();
		   wc.write(Integer.parseInt(subResources));
		   return "Generated code written to target database.";
	   }
}