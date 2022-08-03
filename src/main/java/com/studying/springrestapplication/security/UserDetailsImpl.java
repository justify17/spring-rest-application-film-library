package com.studying.springrestapplication.security;

import com.studying.springrestapplication.model.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
public class UserDetailsImpl implements UserDetails {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetails createFromUser(User user) {
        UserDetailsImpl userDetails = new UserDetailsImpl();

        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(user.getPassword());

        Collection<? extends GrantedAuthority> authorities =
                Collections.<GrantedAuthority>singleton(new SimpleGrantedAuthority(user.getRole().name()));

        userDetails.setAuthorities(authorities);

        return userDetails;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
