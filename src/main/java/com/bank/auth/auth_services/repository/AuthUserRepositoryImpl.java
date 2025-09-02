package com.bank.auth.auth_services.repository;

import com.bank.auth.auth_services.model.entity.AuthUser;
import com.bank.auth.auth_services.repository.interfaces.AuthUserRepository;
import com.bank.auth.auth_services.repository.rowMapper.AuthUserRowMapper;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
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
    ).onFailure(err -> System.out.println("Error: " + err));
  }

  @Override
  public Try<AuthUser> save(String username, String email, String hashedPassword) {
    String sql = """
            INSET INTO auth.auth_users (username, email password)
            VALUES (:username, :email, :password)
            RETURNING auth_user_id, username, email, password, status, account_non_locked,
                      credentials_non_expired, email_verified, two_factor_enable,
                      created_at, update_at;
            """;
    Map<String, Object> params = Map.of(
            "username", username,
            "email", email,
            "password", hashedPassword
    );

    return Try.of(() ->
            namedParameterJdbcTemplate.queryForObject(sql, params, authUserRowMapper));
  }

  public boolean existByUsername(String username) {
    String sql = """
            SELECT EXISTS(SELECT 1 FROM auth.auth_users WHERE username = :username);
            """;

    Map<String, Object> params = Map.of("username", username);
    return Boolean.TRUE.equals(
            namedParameterJdbcTemplate.queryForObject(sql, params, Boolean.class));
  }
}
