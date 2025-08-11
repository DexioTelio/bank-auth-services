package com.bank.auth.auth_services.repository.rowMapper;

import com.bank.auth.auth_services.model.entity.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {
  @Override
  public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new Role(
            rs.getLong("role_id"),
            rs.getString("name"),
            rs.getString("description")
    );
  }
}
