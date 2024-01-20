var headerPanel = Ext.create("Ext.panel.Panel", {
  bodyPadding: 10,
  height: 100,
  layout: {
    type: "hbox",
    align: "middle", // Center the content vertically
  },
  items: [
    {
      xtype: "container", // Use a container for the first logo
      html: "<img src='/Images/High_Radius_logo.jpg' class='imglogo1'>",
    },
    {
      xtype: "container", // Use a container for the second logo
      items: [
        {
          xtype: "box",
          autoEl: {
            tag: "img",
            src: "/Images/Logo.png",
            cls: "imglogo",
          },
        },
      ],
      flex: 4, // This will make the second container take up remaining space
      layout: {
        type: "hbox",
        pack: "center", // Center the content horizontally
        align: "middle", // Center the content vertically
      },
    },
    {
      xtype: "container", // Use a container for the third item
      html: "<p>Welcome <b>Highway to IR</b>&nbsp&nbsp</p>", // Replace with your other content
      margin: "0 0 0 auto", // Align the container to the right
    },
  ],
});
