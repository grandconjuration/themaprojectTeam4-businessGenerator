package generate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import domain.Application;



public interface Writer {

	// alle strings(behalve de trigger) moeten vervangen worden door het application object.
	public void writeToDataBase(Application app, String tr);
}

