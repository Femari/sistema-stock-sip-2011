DELETE StockComprometidoDetallePedidoCliente
DELETE DetallePedidoCliente
DELETE PedidoCliente

DELETE DetallePedidoProveedor
DELETE PedidoProveedor

DELETE StockDeposito
DELETE Producto

DELETE Cliente
DELETE Proveedor

GO

INSERT INTO Cliente(Cuit, Nombre, Direccion, CodigoPostal, Telefono, Fax, Habilitado) VALUES (1, 'QuieroMats S.A.', 'Amenabar 2045', '1429','5551234','5551234',1)
INSERT INTO Proveedor(Cuit, Nombre, Direccion, CodigoPostal, Telefono, Fax) VALUES (1, 'DoyMats SRL', 'Juana Azurduy 1366', '1420','5556789','5556789')

INSERT INTO Producto(IdProducto, Nombre, Descripcion, IdProveedor, StockMinimo) VALUES ('PROD1', 'Canios', 'Canios... largos', 1,20)
INSERT INTO Producto(IdProducto, Nombre, Descripcion, IdProveedor, StockMinimo) VALUES ('PROD2', 'Tuercas', 'Tuercas de acero cromado', 1,100)
INSERT INTO Producto(IdProducto, Nombre, Descripcion, IdProveedor, StockMinimo) VALUES ('PROD3', 'Tornillos', 'Tornillos de 2" m�trico', 1,150)

INSERT INTO StockDeposito(IdProducto, Cantidad) VALUES ('PROD1', 500)
INSERT INTO StockDeposito(IdProducto, Cantidad) VALUES ('PROD2', 500)
INSERT INTO StockDeposito(IdProducto, Cantidad) VALUES ('PROD3', 500)