package rest;

import java.util.ArrayList;

import generate.GenerateController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/generator/app/{appid}")
public class Generator {

	   @GET
	   public String get(@PathParam("appid") String subResources) {
		   GenerateController gc = new GenerateController();
		   ArrayList<String> list = gc.generate(Integer.parseInt(subResources));
		   StringBuilder sb = new StringBuilder();
		   int i = 1;
		   for(String code : list) {
			   sb.append("Generated code " + i + "<br/>");
			   i++;
			   sb.append(code);
			   System.out.println(code);
			   sb.append("<br/><br/>");
		   }
		   return sb.toString(); 
	   }
}