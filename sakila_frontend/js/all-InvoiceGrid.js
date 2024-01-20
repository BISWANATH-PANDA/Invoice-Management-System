var noOfDeletedRows = 0;
var editPopupPanel;
var addPopupPanel;

var allInvoiceGrid = Ext.create("Ext.grid.Panel", {
  xtype: "grid",
  title: "All Invoices",
  layout: "fit",
  store: store,
  selModel: {
    checkOnly: false,
    injectCheckbox: "first",
    mode: "SIMPLE",
    listeners: {
      selectionchange: function (selModel, selectedRecords) {
        // Get a reference to the "Edit" button
        var editButton = Ext.ComponentQuery.query('button[text="Edit"]')[0];
        var deleteBtn = Ext.ComponentQuery.query('button[text="Delete"]')[0];
        var approveBtn = Ext.ComponentQuery.query('button[text="Approve"]')[0];
        // Enable the "Edit" button only if a record is selected
        editButton.setDisabled(selectedRecords.length !== 1);
        deleteBtn.setDisabled(selectedRecords.length === 0);

        if (
          selectedRecords.length > 0 &&
          selectedRecords[0].get("isOpen") === "1"
        ) {
          approveBtn.setDisabled(false);
          // console.log("Button disabled: ", approveBtn.disabled);
        } else {
          approveBtn.setDisabled(true);
          // console.log("Button disabled: ", approveBtn.disabled);
        }

        //}
      },
    },
  },
  selType: "checkboxmodel",
  columns: [
    {
      text: "SL No",
      dataIndex: "slNo",
      type: "int",
      flex: 1,
      editor: "textfield",
    },
    {
      text: "Invoice No",
      dataIndex: "invoiceNo",
      flex: 2,
      editor: "textfield",
    },
    {
      text: "Stock Code",
      dataIndex: "stockCode",
      flex: 2,
      editor: "textfield",
    },
    {
      text: "Description",
      dataIndex: "description",
      flex: 2,
      editor: "textfield",
    },
    {
      text: "Quantity",
      dataIndex: "quantity",
      flex: 1,
      editor: "numberfield",
    },
    {
      text: "Invoice Date",
      dataIndex: "invoiceDate",
      xtype: "datecolumn",
      format: "Y-m-d H:i:s",
      flex: 2,
      editor: "datefield",
    },
    {
      text: "Unit Price",
      dataIndex: "unitPrice",
      flex: 1,
      editor: "numberfield",
    },
    {
      text: "Customer ID",
      dataIndex: "customerID",
      flex: 1,
      editor: "numberfield",
    },
    {
      text: "Country",
      dataIndex: "country",
      flex: 2,
      editor: "textfield",
    },
    {
      text: "Is Deleted",
      dataIndex: "isDeleted",
      flex: 1,
      editor: "textfield",
    },
    {
      text: "Is Open",
      dataIndex: "isOpen",
      flex: 1,
      editor: "textfield",
    },
  ],
  dockedItems: [
    {
      xtype: "pagingtoolbar",
      store: store,
      dock: "top",
      displayInfo: true,
      items: [
        {
          xtype: "button",
          iconCls: "fa fa-plus-circle button-icon",
          text: "Add",
          handler: function () {
            addPopupPanel = Ext.create("Ext.window.Window", {
              title: "Add",
              width: "30%",
              height: "85%",
              layout: "fit",
              items: [
                {
                  xtype: "form",
                  layout: {
                    type: "vbox",
                    pack: "center",
                    align: "middle", // Center the content horizontally
                  },
                  defaults: {
                    margin: "10 0", // Add some horizontal spacing between components
                    labelPad: 10, // Add padding between label and input field
                    width: "90%", // Set the width of the input field
                  },
                  items: [
                    {
                      xtype: "textfield",
                      fieldLabel: "Invoice No",
                      name: "invoiceNo",
                      allowBlank: false,
                      emptyText: "Invoice no",
                    },
                    {
                      xtype: "textfield",
                      fieldLabel: "Stock Code",
                      name: "stockCode",
                      allowBlank: false,
                      emptyText: "Stock code",
                    },
                    {
                      xtype: "textfield",
                      fieldLabel: "Description",
                      name: "description",
                      allowBlank: false,
                      emptyText: "Description",
                    },
                    {
                      xtype: "numberfield",
                      fieldLabel: "Quantity",
                      name: "quantity",
                      allowBlank: false,
                      emptyText: "Quantity",
                    },
                    {
                      xtype: "datefield",
                      fieldLabel: "Invoice Date",
                      name: "invoiceDate",
                      format: "Y-m-d H:i:s",
                      allowBlank: false,
                      emptyText: "Invoice date",
                    },
                    {
                      xtype: "numberfield",
                      fieldLabel: "Unit Price",
                      name: "unitPrice",
                      allowBlank: false,
                      emptyText: "Unit price",
                    },
                    {
                      xtype: "numberfield",
                      fieldLabel: "Customer ID",
                      name: "customerID",
                      allowBlank: false,
                      emptyText: "Customer id",
                    },
                    {
                      xtype: "textfield",
                      fieldLabel: "Country",
                      name: "country",
                      allowBlank: false,
                      emptyText: "Country",
                    },
                    {
                      xtype: "numberfield",
                      fieldLabel: "Is Open",
                      name: "isOpen",
                      allowBlank: false,
                      emptyText: "Is Open",
                    },

                    {
                      xtype: "container",
                      layout: {
                        type: "hbox",
                        pack: "center", // Center the buttons horizontally
                      },
                      items: [
                        {
                          xtype: "button",
                          text: "Save",
                          width: 100,
                          handler: addAjax,
                          margin: "10 0 0 0",
                        },

                        {
                          xtype: "button",
                          text: "Cancel",
                          width: 100,
                          handler: function () {
                            addPopupPanel.close();
                          },
                          margin: "10 0 0 15", // Add margin to separate the buttons
                        },
                      ],
                    },
                  ],
                },
              ],
            });
            // Show the pop-up panel when the application is launched
            addPopupPanel.show();
          },
        },
        {
          xtype: "button",
          iconCls: "fa fa-pen-to-square button-icon",
          text: "Edit",
          disabled: true,
          handler: editHandler,
        },

        {
          xtype: "button",
          text: "Delete",
          disabled: true,
          handler: deleteAjax,
        },
        {
          xtype: "button",
          text: "Approve",
          disabled: true,
          handler: ApproveAjax,
        },
      ],
    },
  ],
});

function editHandler() {
  var selectedRecord = allInvoiceGrid.getSelection()[0];
  if (selectedRecord) {
    editPopupPanel = Ext.create("Ext.window.Window", {
      title: "Edit",
      width: "30%",
      height: "40%",
      layout: "fit",
      items: [
        {
          xtype: "form",
          layout: {
            type: "vbox",
            pack: "center",
            align: "middle",
          },
          defaults: {
            margin: "10 0",
            labelPad: 10,
            width: "90%",
          },
          items: [
            {
              xtype: "textfield",
              fieldLabel: "Invoice No",
              name: "invoiceNo",
              allowBlank: false,
              emptyText: "Invoice no",
              value: selectedRecord.get("invoiceNo"),
            },
            {
              xtype: "numberfield",
              fieldLabel: "Quantity",
              name: "quantity",
              allowBlank: false,
              emptyText: "Quantity",
              value: selectedRecord.get("quantity"),
            },
            {
              xtype: "textfield", // Assuming this should be a textfield
              fieldLabel: "Description",
              name: "description",
              allowBlank: false,
              emptyText: "Description",
              value: selectedRecord.get("description"),
            },
            {
              xtype: "container",
              layout: {
                type: "hbox",
                pack: "center",
              },
              items: [
                {
                  xtype: "button",
                  text: "Save",
                  width: 100,
                  handler: editAjax, // Call the saveHandler function for handling the Save button click
                  margin: "10 0 0 0",
                },
                {
                  xtype: "button",
                  text: "Cancel",
                  width: 100,
                  handler: function () {
                    editPopupPanel.close();
                  },
                  margin: "10 0 0 15",
                },
              ],
            },
          ],
        },
      ],
    });
    // Show the pop-up panel when the application is launched
    editPopupPanel.show();
  }
}
