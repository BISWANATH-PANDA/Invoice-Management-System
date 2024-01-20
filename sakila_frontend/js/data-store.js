var model = Ext.define("InvoiceModel", {
  extend: "Ext.data.Model",
  fields: [
    { name: "slNo", type: "int" },
    { name: "invoiceNo", type: "string" },
    { name: "stockCode", type: "string" },
    { name: "description", type: "string" },
    { name: "quantity", type: "int" },
    { name: "invoiceDate", type: "date" },
    { name: "unitPrice", type: "float" },
    { name: "customerID", type: "int" },
    { name: "country", type: "string" },
    { name: "isDeleted", type: "string" },
    { name: "isOpen", type: "string" },
  ],
});

var store = Ext.create("Ext.data.Store", {
  model: "InvoiceModel",
  storeId: "invoiceStore",
  pageSize: 10,
  autoLoad: false,
  proxy: {
    type: "ajax",
    url: "http://localhost:8080/sakila_project/allinvoices", // Adjust the path to your JSON file
    reader: {
      type: "json",
      rootProperty: "invoices",
      // startProperty: "start",
      // limitProperty: "limit",
      // totalProperty: "total",
    },
  },
});
var store1 = Ext.create("Ext.data.Store", {
  model: "InvoiceModel",
  storeId: "openinvoiceStore",
  pageSize: 10,
  autoLoad: false,
  proxy: {
    type: "ajax",
    url: "http://localhost:8080/sakila_project/openinvoices", // Adjust the path to your JSON file
    reader: {
      type: "json",
      rootProperty: "invoices",
      // totalProperty: "total",
    },
  },
});
var store2 = Ext.create("Ext.data.Store", {
  model: "InvoiceModel",
  storeId: "invoiceStore",
  pageSize: 10,
  autoLoad: false,
  proxy: {
    type: "ajax",
    url: "http://localhost:8080/sakila_project/closedinvoices", // Adjust the path to your JSON file
    reader: {
      type: "json",
      rootProperty: "invoices",
      //totalProperty: "total",
    },
  },
});
var store3 = Ext.create("Ext.data.Store", {
  model: "InvoiceModel",
  storeId: "invoiceStore",
  pageSize: 10,
  autoLoad: false,
  proxy: {
    type: "ajax",
    url: "http://localhost:8080/sakila_project/deletedinvoices", // Adjust the path to your JSON file
    reader: {
      type: "json",
      rootProperty: "invoices",
      //totalProperty: "total",
    },
  },
});

store.load({});

function addAjax() {
  // Get the form values
  var form = addPopupPanel.down("form");
  var formValues = form.getForm().getValues();

  // Perform AJAX request to add a new record
  Ext.Ajax.request({
    url: "http://localhost:8080/sakila_project/addinvoice",
    method: "POST",
    jsonData: {
      invoice: {
        // Pass the data as parameters
        invoiceNo: formValues.invoiceNo,
        stockCode: formValues.stockCode,
        description: formValues.description,
        quantity: formValues.quantity,
        invoiceDate: formValues.invoiceDate,
        unitPrice: formValues.unitPrice,
        customerID: formValues.customerID,
        country: formValues.country,
        isOpen: formValues.isOpen,
      },
    },
    success: function (response) {
      // Handle success
      var responseData = Ext.decode(response.responseText);
      console.log(responseData);
      Ext.toast({
        html: '<div style="text-align: center;">Invoice Added Successfully!</div>',
        align: "t",
        slideInDuration: 400,
        minWidth: 400,
      });
      //  refresh the grid after adding a new record
      store.reload();
      addPopupPanel.close();
    },
    failure: function (response) {
      // Handle failure
      addPopupPanel.close();
      Ext.toast({
        html: '<div style="text-align: center;"><b>Failed to add invoice!</b></div>',
        align: "t",
        slideInDuration: 400,
        minWidth: 400,
      });
      console.error("AJAX request failed", response);
    },
  });
}

function editAjax() {
  // Get the form values
  var form = editPopupPanel.down("form");
  var formValues = form.getForm().getValues();
  // Perform AJAX request to update the record on the server
  Ext.Ajax.request({
    url: "http://localhost:8080/sakila_project/editinvoice",
    method: "POST",
    jsonData: {
      invoice: {
        // Pass the data as parameters
        invoiceNo: formValues.invoiceNo,
        description: formValues.description,
        quantity: formValues.quantity,
      },
    },
    success: function (response) {
      // Handle success
      var responseData = Ext.decode(response.responseText);
      console.log(responseData);
      Ext.toast({
        html: '<div style="text-align: center;">Invoice Edited Successfully!</div>',
        align: "t",
        slideInDuration: 400,
        minWidth: 400,
      });
      editPopupPanel.close();
    },
    failure: function (response) {
      // Handle failure
      Ext.toast({
        html: '<div style="text-align: center;"><b>Failed to edit!</b></div>',
        align: "t",
        slideInDuration: 400,
        minWidth: 400,
      });
      editPopupPanel.close();
      console.error("AJAX request failed", response);
    },
  });
}

function deleteAjax() {
  var selectedRecords = allInvoiceGrid.getSelection();
  var invoiceNo = selectedRecords.map((record) => record.get("invoiceNo"));
  console.log(invoiceNo);
  if (selectedRecords.length > 0) {
    Ext.Msg.show({
      title: "Delete Record",
      message: "Do you want to delete the selected invoice(s)?",
      buttons: Ext.Msg.YESNO,
      fn: function (buttonId) {
        if (buttonId === "yes") {
          // AJAX request to delete records
          Ext.Ajax.request({
            url: "http://localhost:8080/sakila_project/deleteinvoices",
            method: "POST",
            jsonData: { invoiceno: invoiceNo }, // Send SL Nos to the server
            success: function (response) {
              // Handle success
              var responseData = Ext.decode(response.responseText);
              console.log(responseData);
              Ext.Msg.alert(
                "Success",
                `${selectedRecords.length} row(s) deleted Successfully`
              );
            },
            failure: function (response) {
              // Handle failure
              var responseData = Ext.decode(response.responseText);
              console.log(responseData);
              Ext.Msg.alert("Error", "Failed to delete records");
            },
          });
        }
      },
    });
  } else {
    Ext.Msg.alert("Warning", "No records selected for deletion");
  }
}

function ApproveAjax() {
  var selectedRecords = allInvoiceGrid.getSelection();
  var invoiceNo = selectedRecords.map((record) => record.get("invoiceNo"));
  console.log(invoiceNo);
  if (selectedRecords.length > 0) {
    Ext.Msg.show({
      title: "Approve Record",
      message:
        " Are you sure the status of the invoice would be changed from open to close?",
      buttons: Ext.Msg.YESNO,
      fn: function (buttonId) {
        if (buttonId === "yes") {
          // AJAX request to delete records
          Ext.Ajax.request({
            url: "http://localhost:8080/sakila_project/deleteinvoices",
            method: "POST",
            jsonData: { invoiceno: invoiceNo }, // Send SL Nos to the server
            success: function (response) {
              // Handle success
              var responseData = Ext.decode(response.responseText);
              console.log(responseData);
              Ext.Msg.alert(
                "Success",
                `${selectedRecords.length} row(s) Status Successfully`
              );
            },
            failure: function (response) {
              // Handle failure
              var responseData = Ext.decode(response.responseText);
              console.log(responseData);
              Ext.Msg.alert(
                "Error",
                "Failed to update  the status of the records"
              );
            },
          });
        }
      },
    });
  } else {
    Ext.Msg.alert("Warning", "No records selected to update the status");
  }
}

function searchAjax() {
  var formValues = PopUpPanel.down("form").getValues();
  var cust = parseInt(formValues.customerID);
  Ext.Ajax.request({
    url: "http://localhost:8080/sakila_project/advancesearch",
    method: "POST",
    jsonData: {
      invoice: {
        invoiceNo: formValues.invoiceNo,
        customerID: cust,
      },
    },

    success: function (response) {
      // Handle success response
      console.log(response.responseText);
      // Handle success response
      var responseData = Ext.decode(response.responseText);
      console.log(responseData);

      // Assuming responseData contains the search results in JSON format
      // You may need to adjust this based on the actual structure of your data
      var searchResults = responseData.invoices;
      console.log(searchResults);

      // Update the store with new data
      store.loadData(searchResults);

      // Close the search popup
      PopUpPanel.close();
    },
    failure: function (response) {
      // Handle failure response
      console.error(response.responseText);
      PopUpPanel.close();
    },
  });
}
