

var model = Ext.define('City', {
        extend: 'Ext.data.Model',
        fields: [
            { name: 'city_id', type: 'number' },
            { name: 'city', type: 'string' },
            { name: 'country_id', type: 'string' },
            { name: 'last_update', type: 'string' },
        ]
    });

    var storeWithProxy = Ext.create('Ext.data.Store', {
        model: 'City',
        storeId: 'citydata',
        autoLoad: true,
        proxy: {
            type: 'ajax',
            url: 'http://localhost:3000/city',
            reader: {
                type: 'json',
            }
        }
    });

 var grid112=Ext.create('Ext.grid.Panel', {
    title: 'City Table',
    store: Ext.data.StoreManager.lookup('citydata'),
    columns: [
        { text: 'City_id', dataIndex: 'city_id' },
        { text: 'City', dataIndex: 'city', flex: 1 },
        { text: 'Country_id', dataIndex: 'country_id'},
        {text : 'Last_update',dataIndex:'last_update'}
    ],
    height: 300,
    width: 650,
    renderTo: Ext.getBody()
});