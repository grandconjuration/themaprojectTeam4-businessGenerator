package generate;

import java.util.Scanner;

import domain.Rule;
import domain.RuleColumn;
import domain.Value;

public class Parser {
	private int numberOfValues, numberOfColumns;

	public Parser() {

	}

	@SuppressWarnings("resource")
	public String generateCode(Rule rule) {
		numberOfValues = 0;
		numberOfColumns = 0;
		String r = getTemplate(rule);
		rule.setCode(getRuleCode(rule));
		String newRule = "";

		Scanner s = new Scanner(r).useDelimiter("\\<<\\s*");
		while (s.hasNext()) {
			String x = s.next();
			if (x.charAt(0) == '0') {
				x = x.replace("0 ", "");
				newRule += x + " ";
				// System.out.println("TEKST " + x);
			} else if (x.charAt(0) == '1') {
				x = x.replace("1 ", "");
				x = x.trim();
				String temp = getVariable(x, rule);
				// System.out.println("temp "+ x);
				newRule += temp + " ";
				// System.out.println("VARIABELE " + x);
			}
		}
		s.close();
		System.out.println(newRule);
		return newRule;
	}

	public String getTemplate(Rule rule) {
		String type = rule.getColumnByNumber(0).getTable().getApplication()
				.getDatabaseType();
		return rule.getRuleType().searchTemplate(type).getCode();
	}

	public String getVariable(String request, Rule rule) {
		String s = "";

		switch (request) {
			case "Table_name":
				s = rule.getColumnByNumber(0).getTable().getName();
				break;
			case "Column_name":
				s = getColumn(rule);
				break;
			case "Value_value":
				s = getValue(rule);
				break;
			case "Value_all":
				s = getAllValues(rule);
				break;
			case "Operator_code":
				s = rule.getOperator().getCode();
				break;
			case "Rule_code":
				s = rule.getCode();
				break;
			case "Rule_errorMessage":
				s = rule.getErrorMessage();
				break;
			case "Rule_trigger":
				s = getAllTriggers(rule);
				break;
		}

		return s;
	}

	// This method gathers all the pieces code from the rule to create the
	// complete ruleCode
	public String getRuleCode(Rule rule) {
		String ruleCode = "BRG_";
		ruleCode += rule.getColumnByNumber(0).getTable().getApplication()
				.getCode();
		ruleCode += "_" + rule.getColumnByNumber(0).getTable().getCode();
		ruleCode += "_CNS_" + rule.getRuleType().getCode() + "_";
		ruleCode += rule.getId();
		return ruleCode;
	}

	public String getValue(Rule rule) {
		String s = "";
		s = rule.returnValue(numberOfValues);
		numberOfValues++;
		return s;
	}

	public String getColumn(Rule rule) {
		String s = "";
		s = rule.returnColumn(numberOfColumns);
		numberOfColumns++;
		return s;
	}

	public String getAllValues(Rule rule) {
		String s = "";
		for (Value v : rule.getAllValues()) {
			s += v.getValue() + ", ";
		}
		s = s.substring(0, s.length() - 2);
		return s;
	}

	public String getAllTriggers(Rule rule) {
		String s = "";
		if (rule.getUpdateTrigger() != false) {
			s += " update or ";
		}
		if (rule.getInsertTrigger() != false) {
			s += " insert or ";
		}
		if (rule.getDeleteTrigger() != false) {
			s += " delete or ";
		}
		s = s.substring(0, s.length() - 3);
		return s;
	}
}