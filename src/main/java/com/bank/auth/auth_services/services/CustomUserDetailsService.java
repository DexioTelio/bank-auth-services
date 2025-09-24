package com.bank.auth.auth_services.services;

import com.bank.auth.auth_services.enums.UserStatus;
import com.bank.auth.auth_services.model.entity.AuthUser;
import com.bank.auth.auth_services.model.entity.Role;
import com.bank.auth.auth_services.repository.AuthUserRepositoryImpl;
import com.bank.auth.auth_services.repository.RoleRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    return User.builder()
            .username(authUser.getUsername())
            .password(authUser.getPassword())
            .authorities(authorities)
            .accountLocked(!authUser.isAccountNonLocked())
            .accountExpired(!authUser.isAccountNonExpired())
            .credentialsExpired(!authUser.isCredentialsNonExpired())
            .disabled(authUser.getStatus().equals(UserStatus.ACTIVE))
            .build();
  }
}
