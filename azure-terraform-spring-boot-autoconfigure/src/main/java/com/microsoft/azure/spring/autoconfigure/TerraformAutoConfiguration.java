package com.microsoft.azure.spring.autoconfigure;

import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

import com.microsoft.azure.*;

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
        tfOptions.setSubscriptionId(this.tfProperties.getArmSubscriptionId());
        tfOptions.setClientId(this.tfProperties.getArmClientId());
        tfOptions.setClientSecret(this.tfProperties.getArmClientSecret());
        tfOptions.setTenantId(this.tfProperties.getArmTenantId());
        return new TerraformClient(tfOptions);
    }
}
