resource "azurerm_resource_group" "rg" {
  name     = "tfclienttestrg"
  location = "westus2"
}

resource "azurerm_storage_account" "testsa" {
  name                      = "tfclienttestsa"
  resource_group_name       = "${azurerm_resource_group.rg.name}"
  location                  = "${azurerm_resource_group.rg.location}"
  account_tier              = "Standard"
  account_replication_type  = "GRS"
  enable_https_traffic_only = true
  network_rules {
    default_action = "Deny"
  }
  bypass                    = ["AzureServices"]
  min_tls_version           = "TLS1_2"
}
