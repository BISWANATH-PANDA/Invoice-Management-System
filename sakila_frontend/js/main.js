Ext.onReady(function () {
  Ext.create("Ext.container.Viewport", {
    renderTo: Ext.getBody(),
    layout: {
      type: "vbox",
      align: "stretch",
    },
    items: [headerPanel,mainPanel,footerPanel],
  });
});
