$(document)
	.ready(
		function() {
			$('table.tb1')
				.DataTable(
					{
						"dom": "<'row'<'col-sm-12 col-md-4'><'col-sm-12 col-md-4'><'col-sm-12 col-md-4'p>>"
							+ "<'row'<'col-sm-12'tr>>",
						"pageLength": 5,

						"language": {
							"emptyTable": "No existen productos en el pedido",
							"paginate": {
								"previous": "Anterior",
								"next": "Siguiente"
							}
						}
					});

			$("div.toolbar").html('<b>Factura</b>');
		});
