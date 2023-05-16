package com.spro.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

@Entity
@Table(name="users",
	   uniqueConstraints = {
		@UniqueConstraint(name = "unique_username",
						  columnNames = "username"	)	   
	   })

public class AppUser implements UserDetails{

	/**
	 * 
	 */
	private transient static final long serialVersionUID = -5851722595836822074L;
	
	
	@Id
    @SequenceGenerator(
            name = "user_id_seq",
            sequenceName = "user_id_seq",
            allocationSize = 1
    )
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "user_id_seq")
	private Long id;
	private String username;
	private String nickname;
	private String password;
	private boolean isCredentialsExpired;
	private boolean isAccountLocked;
	private boolean isAccountExpired;
	private boolean isEnabled;
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !isAccountExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !isAccountLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !isCredentialsExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	public AppUser(String username, String nickname, String password) {
		this.username = username;
		this.nickname = nickname;
		this.password = password;
		 isCredentialsExpired = false;
		 isAccountLocked = false;
		 isAccountExpired = false;
		 isEnabled = true;
	}

}
