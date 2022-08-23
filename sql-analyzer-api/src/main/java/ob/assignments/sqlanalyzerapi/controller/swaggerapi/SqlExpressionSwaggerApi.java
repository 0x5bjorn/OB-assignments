package ob.assignments.sqlanalyzerapi.controller.swaggerapi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
public interface SqlExpressionSwaggerApi {

    @ApiOperation(value = "Create sql expression columns map ", response = String.class)
    public String createSqlColumnsMap(String sqlExpression);
}
