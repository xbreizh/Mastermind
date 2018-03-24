package application;

import org.apache.log4j.Logger;

public class Log4J {

	private static final Logger log = Logger.getLogger(Log4J.class);

	public static void main(String[] args) {

		try {
			throw new Exception("New exception");
		} catch (Exception e) {
			log.warn(e);
		}

	}

}
