package generate;

import hibernate.HibernateConnector;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import domain.AppColumn;
import domain.AppTable;
import domain.Application;
import domain.Rule;
import domain.RuleColumn;
import domain.RuleColumnPK;

public class GenerateController {
	private Parser parser;
	private Session hibernateSession;

	public GenerateController() {
		parser = new Parser();
		SessionFactory factory = new HibernateConnector().getSessionFactory();
		hibernateSession = factory.openSession();

	}

	public ArrayList<String> generate(int idApp) {
		Transaction tx = null;
		List<Rule> rules = new ArrayList<Rule>();
		ArrayList<String> list = new ArrayList<String>();

		try {
			tx = hibernateSession.beginTransaction();

			Application application = new Application();
			hibernateSession.load(application, idApp);
			@SuppressWarnings(value = { "unchecked" })
			List<AppTable> tables = hibernateSession
					.createQuery("FROM AppTable WHERE application = :appid")
					.setParameter("appid", application).list();
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
							.createSQLQuery("SELECT DISTINCT RULE_ID FROM RULECOLUMNS WHERE COLUMN_ID = :colid");
					List ruleIdList = query.setParameter("colid", col.getId())
							.list();
					hibernateSession.clear();
					for (Object id : ruleIdList) {
						Rule foundRule = new Rule();
						hibernateSession.load(foundRule,
								Integer.parseInt(id.toString()));
						System.out.println("Rule id: " + foundRule.getId());
						// System.out.println("id num: " + id.toString());
						System.out.println("insert trigger: "
								+ foundRule.getInsertTrigger());
						System.out.println("update trigger: "
								+ foundRule.getUpdateTrigger());
						;
						System.out.println("delete trigger: "
								+ foundRule.getDeleteTrigger());
						System.out.println("ruletypename: "
								+ foundRule.getRuleType().getName());
						if (foundRule.isToBeGenerated() == true) {
							if (!rules.contains(foundRule)) {
								rules.add(foundRule);
							}
						}
					}
				}

			}

			System.out.println(rules.size());
			for (Rule rule : rules) {
				System.out.println(rule.getErrorMessage());
			}

			for (Rule r : rules) {
				if (r.isToBeGenerated()) {
					String generatedCode = parser.generateCode(r);
					r.setGeneratedCode(generatedCode);
					hibernateSession.merge(r);
					list.add(generatedCode);
				}
			}

			tx.commit();
			// hibernateSession.flush();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			hibernateSession.close();
		}

		return list;
	}

	public Parser getParser() {
		return parser;
	}

	public void setParser(Parser parser) {
		this.parser = parser;
	}

}