var deletedInvoiceGrid = Ext.create("Ext.grid.Panel", {
  xtype: "grid",
  title: "Deleted Invoices",
  layout: "fit",
  store: store3,
  selModel: {
    checkOnly: false,
    injectCheckbox: "first",
    mode: "SIMPLE",
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
      store: store3,
      dock: "top",
      displayInfo: true,
    },
  ],
});
