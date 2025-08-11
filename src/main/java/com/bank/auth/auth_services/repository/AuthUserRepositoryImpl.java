package com.bank.auth.auth_services.repository;

import com.bank.auth.auth_services.model.entity.AuthUser;
import com.bank.auth.auth_services.repository.interfaces.AuthUserRepository;
import com.bank.auth.auth_services.repository.rowMapper.AuthUserRowMapper;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthUserRepositoryImpl implements AuthUserRepository {
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final AuthUserRowMapper authUserRowMapper;

  @Override
  public Try<Optional<AuthUser>> findByUserName(String username) {
    String sql = """
            SELECT * FROM auth.auth_users WHERE username = :username
            """;

    Map<String, Object> params = Map.of("username", username);
    return Try.of(() ->
            namedParameterJdbcTemplate.query(sql, params, authUserRowMapper).stream().findFirst()
    );
  }
}
