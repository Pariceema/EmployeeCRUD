package com.employee.roles;

public enum Permission {

	USER_READ("user:read"),
	USER_CREATE("user:create"),
	USER_UPDATE("user:update"),
	USER_DELETE("user:delete"),
	
	ADMIN_READ("admin:read"),
	ADMIN_CREATE("admin:create"),
	ADMIN_UPDATE("admin:update"),
	ADMIN_DELETE("admin:delete");
	
	private String permission;

	public String getPermission() {
		return permission;
	}

	private Permission(String permission) {
		this.permission = permission;
	}

	
}
