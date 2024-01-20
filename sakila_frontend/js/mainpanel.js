var PopUpPanel;
var mainPanel = Ext.create("Ext.tab.Panel", {
  title: "Invoice Management",
  items: [
    {
      title: "All Invoice",
      items: [allInvoiceGrid],
    },
    {
      title: "Open Invoice",
      items: [openInvoiceGrid],
    },
    {
      title: "Closed Invoice",
      items: [closedInvoiceGrid],
    },
    {
      title: "Deleted Invoice",
      items: [deletedInvoiceGrid],
    },
    {
      title: "Analytics",
      html: "ANALYTICS TAB",
    },
  ],
  tbar: [
    "->", // This will push the button to the right
    {
      xtype: "button",
      cls: "custom-split-button",
      text: "Advanced Search",
      split: true,
      handler: function () {
        PopUpPanel = Ext.create("Ext.window.Window", {
          title: "Advanced Search",
          width: "50%",
          height: "50%",
          layout: {
            type: "vbox",
            align: "center", // Center the items vertically
            pack: "center",
          },
          items: [
            {
              xtype: "form",
              layout: {
                type: "vbox",
                align: "stretch", // Stretch the items to full width
              },
              defaults: {
                margin: "50 10", // Add some spacing
                labelWidth: 120, // Set the width of the field label
                // labelPad:10,
                flex: 1,
              },
              items: [
                {
                  xtype: "container",
                  layout: "hbox",
                  items: [
                    {
                      xtype: "textfield",
                      fieldLabel: "Invoice Number",
                      name: "invoiceNo",
                      allowBlank: false,
                      margin: "0 10", // Add margin to center content
                    },
                    {
                      xtype: "textfield",
                      fieldLabel: "Customer Id",
                      name: "customerID",
                      margin: "0 10", // Add margin to center content
                    },
                  ],
                },
              ],
            },
          ],
          dockedItems: [
            {
              xtype: "toolbar",
              dock: "bottom",
              ui: "footer",
              layout: {
                pack: "center",
              },
              items: [
                {
                  text: "Search",
                  handler: searchAjax,
                },
                {
                  text: "Close",
                  handler: function () {
                    PopUpPanel.close();
                  },
                },
              ],
            },
          ],
        });

        // Show the pop-up panel when the application is launched
        PopUpPanel.show();
      },
    },
  ],
});
