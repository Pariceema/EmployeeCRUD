package com.employee.roles;

import static com.employee.roles.Permission.ADMIN_CREATE;
import static com.employee.roles.Permission.ADMIN_DELETE;
import static com.employee.roles.Permission.ADMIN_READ;
import static com.employee.roles.Permission.ADMIN_UPDATE;
import static com.employee.roles.Permission.USER_CREATE;
import static com.employee.roles.Permission.USER_DELETE;
import static com.employee.roles.Permission.USER_READ;
import static com.employee.roles.Permission.USER_UPDATE;

import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public enum Role {
 USER(Set.of(USER_READ,USER_CREATE,USER_UPDATE,USER_DELETE)),
 ADMIN(Set.of(ADMIN_READ,ADMIN_CREATE,ADMIN_UPDATE,ADMIN_DELETE,USER_READ,USER_CREATE,USER_UPDATE,USER_DELETE));

	private Set<Permission> permission;
	

	public List<SimpleGrantedAuthority> getAuthority(){
		var authorities=new ArrayList<>(getPermission().stream().map(permission->new SimpleGrantedAuthority(permission.name())).toList());
		authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
		return authorities;
	}
	
	public Set<Permission> getPermission() {
		return permission;
	}

	private Role(Set<Permission> permission) {
		this.permission = permission;
	}

	
	
	
	
}
