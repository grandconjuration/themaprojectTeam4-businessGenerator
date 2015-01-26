package generate;

import java.util.ArrayList;
import java.util.List;

import hibernate.HibernateConnector;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import domain.AppColumn;
import domain.AppTable;
import domain.Application;
import domain.Rule;

public class WriteController {

	private Session hibernateSession;
	private Writer writer;

	public WriteController() {
		SessionFactory factory = new HibernateConnector().getSessionFactory();
		hibernateSession = factory.openSession();
	}
	
	public void write(int idApp) {
		Transaction tx = null;
		List<Rule> rules = new ArrayList<Rule>();

		try {
			tx = hibernateSession.beginTransaction();
			
			Application application = new Application();
			hibernateSession.load(application, idApp);
			@SuppressWarnings(value = { "unchecked" })
			List<AppTable> tables = hibernateSession.createQuery(
					"FROM AppTable WHERE application = :appid")
					.setParameter("appid", application)
					.list();
			for (AppTable table : tables) {
				System.out.println("Table: " + table.getName());
				@SuppressWarnings(value = { "unchecked" })
				List<AppColumn> cols = hibernateSession
						.createQuery("FROM AppColumn WHERE table = :tableid")
						.setParameter("tableid", table).list();
				for (AppColumn col : cols) {
					System.out.println("Column: " + col.getName() + "(id:"
							+ col.getId() + ")");
					Query query = hibernateSession
							.createSQLQuery("SELECT RULE_ID FROM RULECOLUMNS WHERE COLUMN_ID = :colid");
					List ruleIdList = query.setParameter("colid", col.getId())
							.list();
					hibernateSession.clear();
					for (Object id : ruleIdList) {
						Rule foundRule = new Rule();
						hibernateSession.load(foundRule,
								Integer.parseInt(id.toString()));
						System.out.println("Rule id: " + foundRule.getId());
					//	System.out.println("id num: " + id.toString());
						System.out.println("insert trigger: " + foundRule.getInsertTrigger());
						System.out.println("update trigger: " + foundRule.getUpdateTrigger());;
						System.out.println("delete trigger: " + foundRule.getDeleteTrigger());
						System.out.println("ruletypename: " + foundRule.getRuleType().getName());
						if (foundRule.getWriteToDb() == true) {
							rules.add(foundRule);
						}
					}
				}

			}

			
			Writer writer = this.getWriter(application.getDatabaseType());
			
			for(Rule rule : rules) {
				writer.writeToDataBase(application, rule.getGeneratedCode());
				rule.setWriteToDb(false);
				hibernateSession.merge(rule);
			}
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			hibernateSession.close();
		}		
	}
	
	public Writer getWriter(String a) {
		if (a.equals("mysql")) {
			writer = new MySQLWriter();
		} else if (a.equals("oracle")) {
			writer = new OracleWriter();
		}
		return writer;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}
	

}
