package com.microsoft.terraform.spring.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("terraform")
public class TerraformProperties {
    private String armSubscriptionId, armClientId, armClientSecret, armTenantId;

	public String getArmSubscriptionId() {
		return this.armSubscriptionId;
	}

	public void setArmSubscriptionId(String armSubscriptionId) {
		this.armSubscriptionId = armSubscriptionId;
	}

	public String getArmClientId() {
		return this.armClientId;
	}

	public void setArmClientId(String armClientId) {
		this.armClientId = armClientId;
	}

	public String getArmClientSecret() {
		return this.armClientSecret;
	}

	public void setArmClientSecret(String armClientSecret) {
		this.armClientSecret = armClientSecret;
	}

	public String getArmTenantId() {
		return this.armTenantId;
	}

	public void setArmTenantId(String armTenantId) {
		this.armTenantId = armTenantId;
	}
}
