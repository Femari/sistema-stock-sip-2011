INSERT INTO PedidoProveedor(Cuit, Estado, FechaInicio, FechaEntrega) VALUES (1, 0, GETDATE(), DATEADD(m,2,GETDATE()))
INSERT INTO DetallePedidoProveedor(NroPedido, IdProducto, Cantidad) VALUES (IDENT_CURRENT('PedidoProveedor'), 'PROD1', 200)