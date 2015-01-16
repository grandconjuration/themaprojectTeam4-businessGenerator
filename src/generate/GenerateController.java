package generate;

import java.util.ArrayList;

import domain.Application;
import domain.Rule;

public class GenerateController {
	private ArrayList<Rule> allRules = new ArrayList<Rule>();
	private Parser parser;
	private Writer writer;

	public GenerateController() {
		parser = new Parser();
		
	}

	public ArrayList<String> generate() {
		ArrayList<String> list = new ArrayList<String>();
		for (Rule r : allRules) {
			if (r.isToBeGenerated()) {
				list.add(parser.generateCode(r));
			}
		}
		return list;
	}

	public void addRule(Rule rule) {
		allRules.add(rule);
	}

	public ArrayList<Rule> getAllRules() {
		return allRules;
	}

	public void setAllRules(ArrayList<Rule> allRules) {
		this.allRules = allRules;
	}

	public Parser getParser() {
		return parser;
	}

	public void setParser(Parser parser) {
		this.parser = parser;
	}

	public Writer getWriter(String a) {
		if ( a.equals("mysql")){
			writer = new MySQLWriter();
		}
		else if ( a.equals("oracle")){
			writer = new OracleWriter();
		}
		return writer;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}
	

}