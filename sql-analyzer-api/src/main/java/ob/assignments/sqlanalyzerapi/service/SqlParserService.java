package ob.assignments.sqlanalyzerapi.service;

import java.util.HashMap;

public interface SqlParserService {
    HashMap<String, String> parseSqlExpression(String sqlExpression);
}
