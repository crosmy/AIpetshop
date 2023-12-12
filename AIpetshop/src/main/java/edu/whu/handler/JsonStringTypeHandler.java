package edu.whu.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JsonStringTypeHandler extends BaseTypeHandler<List<String>> {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, toJsonFromString(parameter));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return fromJsonToString(rs.getString(columnName), new TypeReference<List<String>>() {});
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return fromJsonToString(rs.getString(columnIndex), new TypeReference<List<String>>() {});
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return fromJsonToString(cs.getString(columnIndex), new TypeReference<List<String>>() {});
    }

    private String toJsonFromString(List<String> param) {
        try {
            return mapper.writeValueAsString(param);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> fromJsonToString(String json, TypeReference<List<String>> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
