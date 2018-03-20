package com.microsoft.samples;

import java.io.*;
import java.util.*;

import com.microsoft.terraform.*;

public final class RawClientLibSampleApp {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Working folder path argument missing");
            System.exit(1);
        }
        TerraformOptions options = new TerraformOptions();
        options.setArmSubscriptionId("<Azure Subscription ID>");
        options.setArmClientId("<Azure Client ID>");
        options.setArmClientSecret("<Azure Client Secret>");
        options.setArmTenantId("<Azure Tenant ID>");
        try (TerraformClient client = new TerraformClient(options)) {
            System.out.println(client.version().get());
            client.setOutputListener(System.out::println);
            client.setErrorListener(System.err::println);

            client.setWorkingDirectory(new File(args[0]));

            Scanner input = new Scanner(System.in);
            System.out.print("Enter 'Y' to plan: ");
            if (!input.next().equalsIgnoreCase("Y")) {
                return;
            }
            System.out.println(client.plan().get());
            System.out.print("Enter 'Y' to apply: ");
            if (!input.next().equalsIgnoreCase("Y")) {
                return;
            }
            System.out.println(client.apply().get());
            System.out.print("Enter 'Y' to destroy: ");
            if (!input.next().equalsIgnoreCase("Y")) {
                return;
            }
            System.out.println(client.destroy().get());
        }
    }
}
