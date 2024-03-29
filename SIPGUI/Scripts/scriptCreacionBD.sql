USE [SIPbd]
GO

/*****************************************************/
/********************** TABLAS ***********************/
/*****************************************************/

/****** Object:  Table [dbo].[EstadoPedido]    Script Date: 07/17/2011 11:51:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[EstadoPedido](
	[IdEstado] [smallint] NOT NULL,
	[Descripcion] [varchar](50) NOT NULL,
 CONSTRAINT [PK_EstadoPedido] PRIMARY KEY CLUSTERED 
(
	[IdEstado] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO


/****** Object:  Table [dbo].[Proveedor]    Script Date: 07/17/2011 11:51:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Proveedor](
	[Cuit] [bigint] NOT NULL,
	[Nombre] [varchar](50) NULL,
	[Direccion] [varchar](50) NULL,
	[CodigoPostal] [varchar](50) NULL,
        [Telefono] [varchar](20) NOT NULL,
        [Fax] [varchar](20) NOT NULL,
 CONSTRAINT [PK_Proveedor] PRIMARY KEY CLUSTERED 
(
	[Cuit] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO


/****** Object:  Table [dbo].[TipoCompromiso]    Script Date: 07/17/2011 11:51:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TipoCompromiso](
	[IdTipoCompromiso] [smallint] NOT NULL,
	[Descripcion] [varchar](50) NOT NULL,
 CONSTRAINT [PK__EstadoPe__FBB0EDC13D5E1FD2] PRIMARY KEY CLUSTERED 
(
	[IdTipoCompromiso] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO


/****** Object:  Table [dbo].[Cliente]    Script Date: 07/17/2011 11:51:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Cliente](
	[Cuit] [bigint] NOT NULL,
	[Nombre] [varchar](50) NOT NULL,
	[Direccion] [varchar](50) NOT NULL,
	[CodigoPostal] [varchar](50) NOT NULL,
	[Telefono] [varchar](20) NOT NULL,
        [Fax] [varchar](20) NOT NULL,
        [Habilitado] [bit]NOT NULL,
 CONSTRAINT [PK_Cliente] PRIMARY KEY CLUSTERED 
(
	[Cuit] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO


/****** Object:  Table [dbo].[PedidoProveedor]    Script Date: 07/17/2011 11:51:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PedidoProveedor](
	[NroPedido] [int] IDENTITY(1,1) NOT NULL,
	[Cuit] [bigint] NULL,
	[FechaInicio] [date] NULL,
	[FechaEntrega] [date] NULL,
	[Estado] [smallint] NULL,
 CONSTRAINT [PK_PedidoProveedor] PRIMARY KEY CLUSTERED 
(
	[NroPedido] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


/****** Object:  Table [dbo].[PedidoCliente]    Script Date: 07/17/2011 11:51:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PedidoCliente](
	[NroPedido] [int] IDENTITY(1,1) NOT NULL,
	[Cuit] [bigint] NOT NULL,
	[FechaInicio] [date] NOT NULL,
	[FechaEntrega] [date] NOT NULL,
	[Estado] [smallint] NOT NULL,
	[Prioridad] [smallint] NOT NULL,
	[NroRemito] [int] NOT NULL,
 CONSTRAINT [PK_PedidoCliente] PRIMARY KEY CLUSTERED 
(
	[NroPedido] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


/****** Object:  Table [dbo].[Producto]    Script Date: 07/17/2011 11:51:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Producto](
	[IdProducto] [varchar](50) NOT NULL,
	[Nombre] [varchar](50) NOT NULL,
	[Descripcion] [varchar](50) NOT NULL,
	[IdProveedor] [bigint] NOT NULL,
	[StockMinimo] [int] NULL,
 CONSTRAINT [PK_Producto] PRIMARY KEY CLUSTERED 
(
	[IdProducto] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO


/****** Object:  View [dbo].[PedidosProveedorPendientes]    Script Date: 07/17/2011 11:51:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[PedidosProveedorPendientes] AS
SELECT * FROM PedidoProveedor WHERE Estado = 0	--Pendiente
GO


/****** Object:  View [dbo].[PedidosClientePendientes]    Script Date: 07/17/2011 11:51:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[PedidosClientePendientes] AS
SELECT * FROM PedidoCliente WHERE Estado = 0 --Pendiente
GO


/****** Object:  Table [dbo].[DetallePedidoProveedor]    Script Date: 07/17/2011 11:51:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[DetallePedidoProveedor](
	[NroPedido] [int] NOT NULL,
	[IdProducto] [varchar](50) NOT NULL,
	[Cantidad] [int] NULL,
 CONSTRAINT [PK_DetallePedidoProveedor] PRIMARY KEY CLUSTERED 
(
	[NroPedido] ASC,
	[IdProducto] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO


/****** Object:  Table [dbo].[DetallePedidoCliente]    Script Date: 07/17/2011 11:51:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[DetallePedidoCliente](
	[NroPedido] [int] NOT NULL,
	[IdProducto] [varchar](50) NOT NULL,
	[Cantidad] [int] NOT NULL,
 CONSTRAINT [PK_DetallePedidoCliente] PRIMARY KEY CLUSTERED 
(
	[NroPedido] ASC,
	[IdProducto] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO


/****** Object:  Table [dbo].[StockDeposito]    Script Date: 07/17/2011 11:51:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[StockDeposito](
	[IdProducto] [varchar](50) NOT NULL,
	[Cantidad] [int] NULL,
 CONSTRAINT [PK_Stock] PRIMARY KEY CLUSTERED 
(
	[IdProducto] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO


/****** Object:  Table [dbo].[StockComprometidoDetallePedidoCliente]    Script Date: 07/17/2011 11:51:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[StockComprometidoDetallePedidoCliente](
	[IdStockComprometido] [int] IDENTITY(1,1) NOT NULL,
	[NroPedido] [int] NOT NULL,
	[IdProducto] [varchar](50) NOT NULL,
	[NroPedidoProveedor] [int] NULL,
	[CantidadComprometida] [int] NOT NULL,
	[TipoCompromiso] [smallint] NULL,
 CONSTRAINT [PK__StockCom__D6286F075812160E] PRIMARY KEY CLUSTERED 
(
	[IdStockComprometido] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO








/*****************************************************/
/********************** VISTAS ***********************/
/*****************************************************/

/****** Object:  View [dbo].[StockComprometidoPorPedidoPendiente]    Script Date: 07/17/2011 11:51:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
/*Calcula el stock comprometido de todos los pedidos de clientes pendientes
	DetalleProveedor es nulo cuando compromete stock de deposito (se cargó asi el pedido o el pedido de proveedor ya fue entregado)*/
CREATE VIEW [dbo].[StockComprometidoPorPedidoPendiente]
AS
SELECT     StockComprometido.IdStockComprometido, PedidoCliente.NroPedido, DetalleCliente.IdProducto, StockComprometido.CantidadComprometida, 
                      CASE PedidoProveedor.Estado WHEN 0 THEN StockComprometido.NroPedidoProveedor ELSE NULL END AS PedidoProveedor, 
                      StockComprometido.TipoCompromiso
FROM         dbo.PedidosClientePendientes AS PedidoCliente INNER JOIN
                      dbo.DetallePedidoCliente AS DetalleCliente ON DetalleCliente.NroPedido = PedidoCliente.NroPedido INNER JOIN
                      dbo.StockComprometidoDetallePedidoCliente AS StockComprometido ON StockComprometido.NroPedido = PedidoCliente.NroPedido AND 
                      StockComprometido.IdProducto = DetalleCliente.IdProducto LEFT OUTER JOIN
                      dbo.PedidoProveedor ON dbo.PedidoProveedor.NroPedido = StockComprometido.NroPedidoProveedor
GO


/****** Object:  View [dbo].[StockLibrePorPedido]    Script Date: 07/17/2011 11:51:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Devuelve el stock libre por cada pedido y fecha de disponibilidad
CREATE VIEW [dbo].[StockLibrePorPedido] AS	
SELECT 	Pedido.NroPedido, 
		MAX(Pedido.FechaEntrega) FechaDisponibilidad,
		Detalle.IdProducto, 
		MIN(Detalle.Cantidad) - ISNULL(SUM(Comprometido.CantidadComprometida), 0) StockLibre 

FROM PedidosProveedorPendientes Pedido
	INNER JOIN DetallePedidoProveedor Detalle ON (Detalle.NroPedido = Pedido.NroPedido)
	LEFT JOIN StockComprometidoDetallePedidoCliente Comprometido ON (Comprometido.NroPedidoProveedor = Detalle.NroPedido AND Comprometido.IdProducto = Detalle.IdProducto)

GROUP BY Pedido.NroPedido, Detalle.IdProducto
HAVING SUM(Detalle.Cantidad) - ISNULL(SUM(Comprometido.CantidadComprometida), 0) > 0
GO


/****** Object:  View [dbo].[StockDepositoLibre]    Script Date: 07/17/2011 11:51:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[StockDepositoLibre]
AS
SELECT     Stock.IdProducto, MIN(Stock.Cantidad) - ISNULL(SUM(Comprometido.CantidadComprometida), 0) AS StockLibre
FROM         dbo.StockDeposito AS Stock LEFT OUTER JOIN
                      dbo.StockComprometidoPorPedidoPendiente AS Comprometido ON Comprometido.IdProducto = Stock.IdProducto AND Comprometido.TipoCompromiso = 0
GROUP BY Stock.IdProducto
GO


/****** Object:  View [dbo].[DisponibilidadStockAFuturo]    Script Date: 07/17/2011 11:51:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[DisponibilidadStockAFuturo] AS
SELECT Stock.IdProducto, SUM(Acumulado.StockLibre) StockLibre, Stock.FechaDisponibilidad FechaDisponibilidad
	FROM StockLibrePorPedido Stock
		INNER JOIN StockLibrePorPedido Acumulado ON (Stock.IdProducto = Acumulado.IdProducto AND Acumulado.FechaDisponibilidad <= Stock.FechaDisponibilidad)
		
GROUP BY Stock.IdProducto, Stock.FechaDisponibilidad
GO







/*****************************************************/
/********************** RELACIONES *******************/
/*****************************************************/


/****** Object:  ForeignKey [FK_PedidoProveedor_EstadoPedido]    Script Date: 07/17/2011 11:51:49 ******/
ALTER TABLE [dbo].[PedidoProveedor]  WITH CHECK ADD  CONSTRAINT [FK_PedidoProveedor_EstadoPedido] FOREIGN KEY([Estado])
REFERENCES [dbo].[EstadoPedido] ([IdEstado])
GO
ALTER TABLE [dbo].[PedidoProveedor] CHECK CONSTRAINT [FK_PedidoProveedor_EstadoPedido]
GO


/****** Object:  ForeignKey [FK_PedidoProveedor_Proveedor]    Script Date: 07/17/2011 11:51:49 ******/
ALTER TABLE [dbo].[PedidoProveedor]  WITH CHECK ADD  CONSTRAINT [FK_PedidoProveedor_Proveedor] FOREIGN KEY([Cuit])
REFERENCES [dbo].[Proveedor] ([Cuit])
GO
ALTER TABLE [dbo].[PedidoProveedor] CHECK CONSTRAINT [FK_PedidoProveedor_Proveedor]
GO


/****** Object:  ForeignKey [FK_PedidoCliente_Cliente]    Script Date: 07/17/2011 11:51:49 ******/
ALTER TABLE [dbo].[PedidoCliente]  WITH CHECK ADD  CONSTRAINT [FK_PedidoCliente_Cliente] FOREIGN KEY([Cuit])
REFERENCES [dbo].[Cliente] ([Cuit])
GO
ALTER TABLE [dbo].[PedidoCliente] CHECK CONSTRAINT [FK_PedidoCliente_Cliente]
GO


/****** Object:  ForeignKey [FK_PedidoCliente_EstadoPedido]    Script Date: 07/17/2011 11:51:49 ******/
ALTER TABLE [dbo].[PedidoCliente]  WITH CHECK ADD  CONSTRAINT [FK_PedidoCliente_EstadoPedido] FOREIGN KEY([Estado])
REFERENCES [dbo].[EstadoPedido] ([IdEstado])
GO
ALTER TABLE [dbo].[PedidoCliente] CHECK CONSTRAINT [FK_PedidoCliente_EstadoPedido]
GO


/****** Object:  ForeignKey [FK_Producto_Proveedor]    Script Date: 07/17/2011 11:51:49 ******/
ALTER TABLE [dbo].[Producto]  WITH CHECK ADD  CONSTRAINT [FK_Producto_Proveedor] FOREIGN KEY([IdProveedor])
REFERENCES [dbo].[Proveedor] ([Cuit])
GO
ALTER TABLE [dbo].[Producto] CHECK CONSTRAINT [FK_Producto_Proveedor]
GO


/****** Object:  ForeignKey [FK_DetallePedidoProveedor_PedidoProveedor]    Script Date: 07/17/2011 11:51:49 ******/
ALTER TABLE [dbo].[DetallePedidoProveedor]  WITH CHECK ADD  CONSTRAINT [FK_DetallePedidoProveedor_PedidoProveedor] FOREIGN KEY([NroPedido])
REFERENCES [dbo].[PedidoProveedor] ([NroPedido])
GO
ALTER TABLE [dbo].[DetallePedidoProveedor] CHECK CONSTRAINT [FK_DetallePedidoProveedor_PedidoProveedor]
GO


/****** Object:  ForeignKey [FK_DetallePedidoProveedor_Producto]    Script Date: 07/17/2011 11:51:49 ******/
ALTER TABLE [dbo].[DetallePedidoProveedor]  WITH CHECK ADD  CONSTRAINT [FK_DetallePedidoProveedor_Producto] FOREIGN KEY([IdProducto])
REFERENCES [dbo].[Producto] ([IdProducto])
GO
ALTER TABLE [dbo].[DetallePedidoProveedor] CHECK CONSTRAINT [FK_DetallePedidoProveedor_Producto]
GO


/****** Object:  ForeignKey [FK_DetallePedidoCliente_PedidoCliente]    Script Date: 07/17/2011 11:51:49 ******/
ALTER TABLE [dbo].[DetallePedidoCliente]  WITH CHECK ADD  CONSTRAINT [FK_DetallePedidoCliente_PedidoCliente] FOREIGN KEY([NroPedido])
REFERENCES [dbo].[PedidoCliente] ([NroPedido])
GO
ALTER TABLE [dbo].[DetallePedidoCliente] CHECK CONSTRAINT [FK_DetallePedidoCliente_PedidoCliente]
GO


/****** Object:  ForeignKey [FK_DetallePedidoCliente_Producto]    Script Date: 07/17/2011 11:51:49 ******/
ALTER TABLE [dbo].[DetallePedidoCliente]  WITH CHECK ADD  CONSTRAINT [FK_DetallePedidoCliente_Producto] FOREIGN KEY([IdProducto])
REFERENCES [dbo].[Producto] ([IdProducto])
GO
ALTER TABLE [dbo].[DetallePedidoCliente] CHECK CONSTRAINT [FK_DetallePedidoCliente_Producto]
GO


/****** Object:  ForeignKey [FK_StockDeposito_Producto]    Script Date: 07/17/2011 11:51:49 ******/
ALTER TABLE [dbo].[StockDeposito]  WITH CHECK ADD  CONSTRAINT [FK_StockDeposito_Producto] FOREIGN KEY([IdProducto])
REFERENCES [dbo].[Producto] ([IdProducto])
GO
ALTER TABLE [dbo].[StockDeposito] CHECK CONSTRAINT [FK_StockDeposito_Producto]
GO


/****** Object:  ForeignKey [FK_StockComprometidoDetallePedidoCliente_DetallePedidoCliente]    Script Date: 07/17/2011 11:51:49 ******/
ALTER TABLE [dbo].[StockComprometidoDetallePedidoCliente]  WITH CHECK ADD  CONSTRAINT [FK_StockComprometidoDetallePedidoCliente_DetallePedidoCliente] FOREIGN KEY([NroPedido], [IdProducto])
REFERENCES [dbo].[DetallePedidoCliente] ([NroPedido], [IdProducto])
GO
ALTER TABLE [dbo].[StockComprometidoDetallePedidoCliente] CHECK CONSTRAINT [FK_StockComprometidoDetallePedidoCliente_DetallePedidoCliente]
GO


/****** Object:  ForeignKey [FK_StockComprometidoDetallePedidoCliente_DetallePedidoProveedor]    Script Date: 07/17/2011 11:51:49 ******/
ALTER TABLE [dbo].[StockComprometidoDetallePedidoCliente]  WITH CHECK ADD  CONSTRAINT [FK_StockComprometidoDetallePedidoCliente_DetallePedidoProveedor] FOREIGN KEY([NroPedidoProveedor], [IdProducto])
REFERENCES [dbo].[DetallePedidoProveedor] ([NroPedido], [IdProducto])
GO
ALTER TABLE [dbo].[StockComprometidoDetallePedidoCliente] CHECK CONSTRAINT [FK_StockComprometidoDetallePedidoCliente_DetallePedidoProveedor]
GO


/****** Object:  ForeignKey [FK_StockComprometidoDetallePedidoCliente_TipoCompromiso]    Script Date: 07/17/2011 11:51:49 ******/
ALTER TABLE [dbo].[StockComprometidoDetallePedidoCliente]  WITH CHECK ADD  CONSTRAINT [FK_StockComprometidoDetallePedidoCliente_TipoCompromiso] FOREIGN KEY([TipoCompromiso])
REFERENCES [dbo].[TipoCompromiso] ([IdTipoCompromiso])
GO
ALTER TABLE [dbo].[StockComprometidoDetallePedidoCliente] CHECK CONSTRAINT [FK_StockComprometidoDetallePedidoCliente_TipoCompromiso]
GO
