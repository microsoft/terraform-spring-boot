package com.microsoft.azure.samples;

import java.io.*;
import java.util.*;

import com.microsoft.azure.*;

public final class RawClientLibSampleApp {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Working folder path argument missing");
            System.exit(1);
        }
        TerraformOptions options = new TerraformOptions();
        options.setSubscriptionId("<Azure Subscription ID>");
        options.setClientId("<Azure Client ID>");
        options.setClientSecret("<Azure Client Secret>");
        options.setTenantId("<Azure Tenant ID>");
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
