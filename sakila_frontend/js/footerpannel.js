var footerPanel = Ext.create("Ext.panel.Panel", {
  height: 50,
  html: "<p style='text-align:center'>Copyright 2023 Highradius. All Rights Reserved</p>",
  layout: {
    type: "hbox",
    align: "middle", // Vertical centering
    pack: "center", // Horizontal centering
  },
  style: {
    border: "none", // Set border to none
  },
});