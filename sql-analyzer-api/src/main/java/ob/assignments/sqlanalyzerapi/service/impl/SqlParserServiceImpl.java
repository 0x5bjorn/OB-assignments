package ob.assignments.sqlanalyzerapi.service.impl;

import ob.assignments.sqlanalyzerapi.service.SqlParserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

/**
 * SqlExpression service implementation
 */
@Service
public class SqlParserServiceImpl implements SqlParserService {

    final List<String> aggregateFuncs = Arrays.asList("avg(", "min(", "max(", "sum(", "count(");

    /**
     * Parse sql expression to extract columns
     * @param sqlExpression SQL expression
     * @return Map of columns
     */
    @Override
    public HashMap<String, String> parseSqlExpression(String sqlExpression) {
        HashMap<String, String> result = new HashMap<>();

        String sqlExprInOneLine = StringUtils.replace(sqlExpression,"\n"," ");
        String[] sqlExprTokens = sqlExprInOneLine.split(" ", 2);

        System.out.println(sqlExprInOneLine);

        switch (sqlExprTokens[0].toLowerCase()) {
            case "select":
                parseSelectExpression(sqlExprTokens[1], result);
                break;
            case "update":
                parseUpdateExpression(sqlExprTokens[1], result);
                break;
            case "insert":
                parseInsertExpression(sqlExprTokens[1], result);
                break;
            default:
                return null;
        }

        return result;
    }

    /**
     * Parse simple SELECT expression
     * @param sqlExpression SELECT sql expression
     * @param result Result map of columns
     */
    private void parseSelectExpression(String sqlExpression, HashMap<String, String> result) {
        if (sqlExpression.contains("*")) return;

        int fromIndex = sqlExpression.toLowerCase().indexOf("from");
        String columnString = sqlExpression.substring(0, fromIndex);
        String[] columnsTokens = columnString.split(", ");

        for (String columnToken : columnsTokens) {
            columnToken = columnToken.replace("distinct", "");
            int topStartIndex = columnToken.toLowerCase().indexOf("top");
            int topEndIndex = columnToken.toLowerCase().lastIndexOf(" ");
            if (topStartIndex > -1) {
                columnToken = columnToken.replace(columnToken.substring(topStartIndex, topEndIndex), "");
            }

            int asIndex = columnToken.toLowerCase().indexOf("as");
            if (asIndex > -1) {
                columnToken = columnToken.replace(columnToken.substring(0, asIndex), "");
                columnToken = columnToken.replace("as", "");
            }
            for (String aggrFunc : aggregateFuncs) {
                int aggrIndex = columnToken.toLowerCase().indexOf(aggrFunc);
                if (aggrIndex > -1) {
                    int aggrStartIndex = columnToken.toLowerCase().indexOf("(", aggrIndex);
                    int aggrEndIndex = columnToken.toLowerCase().indexOf(")", aggrIndex);
                    columnToken = columnToken.substring(aggrStartIndex+1, aggrEndIndex);
                    break;
                }
            }
            System.out.println(columnToken);
            result.put(columnToken.trim(), "[\"int\", \"string\", \"double\"]");
        }
    }

    /**
     * Parse simple UPDATE expression
     * @param sqlExpression UPDATE sql expression
     * @param result Result map of columns
     */
    private void parseUpdateExpression(String sqlExpression, HashMap<String, String> result) {
        int setIndex = sqlExpression.toLowerCase().indexOf("set");
        int whereIndex = sqlExpression.toLowerCase().contains("where") ? sqlExpression.toLowerCase().indexOf("where") : sqlExpression.length();
        String columnString = sqlExpression.substring(setIndex+3, whereIndex);
        String[] columnsTokens = columnString.split(", ");

        for (String columnToken : columnsTokens) {
            int equalSignIndex = columnToken.toLowerCase().indexOf("=");
            columnToken = columnToken.substring(0, equalSignIndex);

            result.put(columnToken.trim(), "[\"int\", \"string\", \"double\"]");
        }
    }

    /**
     * Parse simple INSERT expression
     * @param sqlExpression INSERT sql expression
     * @param result Result map of columns
     */
    private void parseInsertExpression(String sqlExpression, HashMap<String, String> result) {
        int valuesIndex = sqlExpression.toLowerCase().indexOf("values");
        String columnString = sqlExpression.substring(0, valuesIndex);

        if (!columnString.contains("(") && !columnString.contains(")")) return;

        int parenthesesStartIndex = columnString.toLowerCase().indexOf("(");
        int parenthesesEndIndex = columnString.toLowerCase().indexOf(")");
        columnString = columnString.substring(parenthesesStartIndex+1, parenthesesEndIndex);

        String[] columnsTokens = columnString.split(", ");
        for (String columnToken : columnsTokens) {
            result.put(columnToken.trim(), "[\"int\", \"string\", \"double\"]");
        }
    }
}
