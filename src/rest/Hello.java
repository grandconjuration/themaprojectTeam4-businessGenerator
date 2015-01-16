package rest;

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
			Application app = new Application();
			hibernateSession.load(app, 1);
			System.out.println(app.getName());
			AppTable table = new AppTable();
			hibernateSession.load(table, 1);
			System.out.println(table.getApplication().getName());
			AppColumn column = new AppColumn();
			hibernateSession.load(column, 1);
			System.out.println("COLNAME: " + column.getName());
			System.out.println("TABNAME: " + column.getTable().getName());
			System.out.println("APPNAME: "
					+ column.getTable().getApplication().getName());

			Operator op = new Operator();
			hibernateSession.load(op, 1);

			System.out.println(op.getName());

			Value val = new Value();
			hibernateSession.load(val, 11);

			System.out.println(val.getValue());

			RuleType rt = new RuleType();
			hibernateSession.load(rt, 1);

			List<Template> list = rt.getAllTemplates();
			for (Template temp : list) {
				System.out.println(temp.getName());
			}

			Rule rule = new Rule();
			hibernateSession.load(rule, 1);
			List<AppColumn> clist = rule.getAllColumns();
			int i = 0;
			for (AppColumn col : clist) {
				System.out.println("COL num " + i + ": "+ col.getTable().getName());
				i++;
			}
			
			List<Value> vlist = rule.getAllValues();
			int j = 0;
			for(Value v : vlist){
				System.out.println("Value " + j + ": " + v.getValue());
				j++;
			}

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			hibernateSession.close();
			factory.close();
		}
    return "<html> " + "<title>" + "Hello Jersey" + "</title>"
        + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
  }

} 