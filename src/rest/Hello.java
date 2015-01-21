package rest;

import generate.GenerateController;
import hibernate.HibernateConnector;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import domain.AppColumn;
import domain.AppTable;
import domain.Application;
import domain.Operator;
import domain.Rule;
import domain.RuleColumn;
import domain.RuleType;
import domain.Template;
import domain.Value;

// Plain old Java Object it does not extend as class or implements 
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/hello")
public class Hello {

  // This method is called if TEXT_PLAIN is request
/* 
	@GET
  @Produces(MediaType.TEXT_PLAIN)
  public String sayPlainTextHello() {
    return "Hello Jersey";
  }
*/
  // This method is called if XML is request
 /* @GET
  @Produces(MediaType.TEXT_XML)
  public String sayXMLHello() {
    return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
  }
*/
  // This method is called if HTML is request
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String sayHtmlHello() {
		SessionFactory factory = new HibernateConnector().getSessionFactory();
		Session hibernateSession = factory.openSession();
		Transaction tx = null;

		try {
			tx = hibernateSession.beginTransaction();

			
			RuleColumn rc = new RuleColumn();
			Rule r = new Rule();
			hibernateSession.load(r, 1);
			AppColumn c = new AppColumn();
			hibernateSession.clear();
			hibernateSession.load(c, 1);
			rc.setRule(r);
			rc.setColumn(c);
			hibernateSession.clear();
		//	hibernateSession.load(rc, rc.getPk());
			for(RuleColumn rc2 : r.getAllRuleColumns()) {
		//		System.out.println(r.getAllRuleColumns().size());
				System.out.println("RuleColumn num: " + rc2.getNumber() + ", col num: " + rc2.getColumn().getId());
			}
			//System.out.println(rc.getNumber());
			
		//	System.out.println(rc.getPk().getRule().getCode());	
			
		//	tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			hibernateSession.close();
		}
    return "<html> " + "<title>" + "Hello Jersey" + "</title>"
        + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
  }

} 