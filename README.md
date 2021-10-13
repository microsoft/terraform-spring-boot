
# Terraform Spring Boot

## Introduction

This repository is for Spring Boot Starters of Terraform client.

## How to use it

There are two ways you could use this library. One way is to directly use the `TerraformClient` class which wraps the `terraform` executable on your local machine; and the other way is to integrate it into a Spring boot application using annotations.

### Client library

Simply add the following dependency to your project's `pom.xml` will enable you to use the `TerraformClient` class.

```xml
<dependency>
    <groupId>com.microsoft.terraform</groupId>
    <artifactId>terraform-client</artifactId>
    <version>1.0.0</version>
</dependency>
```

And now you are able to provision terraform resources in your Java application. Make sure you have already put a terraform file `storage.tf` under `/some/local/path/` folder; and then use the Java code snippet below to invoke `terraform` executable operate on the resources defined in `storage.tf`. In this example, we also assume that you are provisioning Azure specific resource, which means you need to set some Azure related credentials.

```java
TerraformOptions options = new TerraformOptions();
options.setArmSubscriptionId("<Azure Subscription ID>");
options.setArmClientId("<Azure Client ID>");
options.setArmClientSecret("<Azure Client Secret>");
options.setArmTenantId("<Azure Tenant ID>");

try (TerraformClient client = new TerraformClient(options)) {
    client.setOutputListener(System.out::println);
    client.setErrorListener(System.err::println);

    client.setWorkingDirectory("/some/local/path/");
    client.plan().get();
    client.apply().get();
}
```

### Spring boot

Let's still use the terraform file `storage.tf` under `/some/local/path/` folder to provision Azure resources in this example. Rather than create the `TerraformClient` by ourselves, we let the spring boot framework to wire it for us. First add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.microsoft.terraform</groupId>
    <artifactId>terraform-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

And now let's also introduce the Azure credentials in `application.properties`:

```
terraform.armSubscriptionId=<Azure Subscription ID>
terraform.armClientId=<Azure Client ID>
terraform.armClientSecret=<Azure Client Secret>
terraform.armTenantId=<Azure Tenant ID>
```

The final step is to let the Spring framework wire up everything in your spring boot application:

```java
@SpringBootApplication
public class SpringStarterSampleApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringStarterSampleApp.class, args);
    }

    @Autowired
    private TerraformClient terraform;

    @Override
    public void run(String... args) throws Exception {
        try {
            this.terraform.setOutputListener(System.out::println);
            this.terraform.setErrorListener(System.err::println);

            this.terraform.setWorkingDirectory("/some/local/path/");
            this.terraform.plan().get();
            this.terraform.apply().get();
        } finally {
            this.terraform.close();
        }
    }
}
```


## Contributing

This project welcomes contributions and suggestions.  Most contributions require you to agree to a
Contributor License Agreement (CLA) declaring that you have the right to, and actually do, grant us
the rights to use your contribution. For details, visit https://cla.microsoft.com.

When you submit a pull request, a CLA-bot will automatically determine whether you need to provide
a CLA and decorate the PR appropriately (e.g., label, comment). Simply follow the instructions
provided by the bot. You will only need to do this once across all repos using our CLA.

This project has adopted the [Microsoft Open Source Code of Conduct](https://opensource.microsoft.com/codeofconduct/).
For more information see the [Code of Conduct FAQ](https://opensource.microsoft.com/codeofconduct/faq/) or
contact [opencode@microsoft.com](mailto:opencode@microsoft.com) with any additional questions or comments.
