package com.bank.auth.auth_services.services;

import com.bank.auth.auth_services.model.entity.AuthUser;
import com.bank.auth.auth_services.model.entity.Role;
import com.bank.auth.auth_services.repository.AuthUserRepositoryImpl;
import com.bank.auth.auth_services.repository.RoleRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  private final AuthUserRepositoryImpl authUserRepository;
  private final RoleRepositoryImpl roleRepositoryImpl;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AuthUser authUser = authUserRepository.findByUserName(username)
            .get()
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    List<Role> roles = roleRepositoryImpl.findRoleByUserId(authUser.getId()).get();
    List<SimpleGrantedAuthority> authorities = roles.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()))
            .toList();

    // Collection<? extends GrantedAuthority> authorities
  }
}
