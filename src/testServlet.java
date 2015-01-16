import hibernate.HibernateConnector;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

/**
 * Servlet implementation class testServlet
 */
public class testServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public testServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
