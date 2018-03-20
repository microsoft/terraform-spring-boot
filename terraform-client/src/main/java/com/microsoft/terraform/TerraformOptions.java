package com.microsoft.terraform;

public class TerraformOptions {
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
