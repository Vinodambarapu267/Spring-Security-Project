package com.secuirty.demo.utility;

import java.util.Set;

public enum Role {
	ADMIN(Set.of(Permission.WRITE,Permission.READ,Permission.DELETE)), USER(Set.of(Permission.READ));

	private final Set<Permission> permission;

	private Role(Set<Permission> permission) {
		this.permission = permission;
	}

	public Set<Permission> getPermission() {
		return permission;
	}
	
}
