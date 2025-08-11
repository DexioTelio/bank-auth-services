package com.bank.auth.auth_services.repository;

import com.bank.auth.auth_services.model.entity.Role;
import com.bank.auth.auth_services.repository.interfaces.RoleRepository;
import com.bank.auth.auth_services.repository.rowMapper.RoleRowMapper;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final RoleRowMapper roleRowMapper;

  @Override
  public Try<List<Role>> findRoleByUserId(Long userId) {
    String sql = """
            SELECT r.role_id, r.name, r.description
            FROM auth.auth_user_roles usr
            JOIN catalogs.roles r ON r.role_id = usr.role_id
            WHERE ur.auth_user_id = :userId
            """;

    Map<String, Object> params = Map.of("userId", userId);
    return Try.of(() ->
            namedParameterJdbcTemplate.query(sql, params, roleRowMapper)
    );
  }
}
