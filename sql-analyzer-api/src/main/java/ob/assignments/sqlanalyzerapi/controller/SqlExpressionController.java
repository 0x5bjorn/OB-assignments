package ob.assignments.sqlanalyzerapi.controller;

import ob.assignments.sqlanalyzerapi.controller.swaggerapi.SqlExpressionSwaggerApi;
import ob.assignments.sqlanalyzerapi.service.SqlParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * SqlExpression controller
 */
@RestController
public class SqlExpressionController implements SqlExpressionSwaggerApi {

    private SqlParserService sqlParserService;
    @Autowired
    public SqlExpressionController(SqlParserService sqlParserService) {
        this.sqlParserService = sqlParserService;
    }

    @Override
    @PostMapping(value = "/sqlcolumns", consumes = "text/plain")
    public String createSqlColumnsMap(@RequestBody String sqlExpression) {

        return sqlParserService.parseSqlExpression(sqlExpression) != null ?
                sqlParserService.parseSqlExpression(sqlExpression).toString() : "Unknown query\n";
    }
}
