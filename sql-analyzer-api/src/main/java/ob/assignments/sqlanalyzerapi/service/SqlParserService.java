package ob.assignments.sqlanalyzerapi.service;

import java.util.HashMap;

/**
 * SqlExpression service interface
 */
public interface SqlParserService {
    HashMap<String, String> parseSqlExpression(String sqlExpression);
}
