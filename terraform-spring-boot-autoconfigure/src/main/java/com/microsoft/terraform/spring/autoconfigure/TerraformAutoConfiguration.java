package com.microsoft.terraform.spring.autoconfigure;

import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

import com.microsoft.terraform.*;

@Configuration
@EnableConfigurationProperties(TerraformProperties.class)
@ConditionalOnMissingBean(TerraformClient.class)
public class TerraformAutoConfiguration {
    private TerraformProperties tfProperties;

    public TerraformAutoConfiguration(TerraformProperties properties) {
        assert properties != null;
        this.tfProperties = properties;
    }

    @Bean
    public TerraformClient terraformClient() {
        TerraformOptions tfOptions = new TerraformOptions();
        tfOptions.setArmSubscriptionId(this.tfProperties.getArmSubscriptionId());
        tfOptions.setArmClientId(this.tfProperties.getArmClientId());
        tfOptions.setArmClientSecret(this.tfProperties.getArmClientSecret());
        tfOptions.setArmTenantId(this.tfProperties.getArmTenantId());
        return new TerraformClient(tfOptions);
    }
}
