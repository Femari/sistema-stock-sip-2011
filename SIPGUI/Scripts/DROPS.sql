USE [SIPbd]
GO

/********************** DROPS ***********************/


/****** Object:  ForeignKey [FK_PedidoProveedor_EstadoPedido]    Script Date: 07/17/2011 11:50:05 ******/
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_PedidoProveedor_EstadoPedido]') AND parent_object_id = OBJECT_ID(N'[dbo].[PedidoProveedor]'))
ALTER TABLE [dbo].[PedidoProveedor] DROP CONSTRAINT [FK_PedidoProveedor_EstadoPedido]
GO
/****** Object:  ForeignKey [FK_PedidoProveedor_Proveedor]    Script Date: 07/17/2011 11:50:05 ******/
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_PedidoProveedor_Proveedor]') AND parent_object_id = OBJECT_ID(N'[dbo].[PedidoProveedor]'))
ALTER TABLE [dbo].[PedidoProveedor] DROP CONSTRAINT [FK_PedidoProveedor_Proveedor]
GO
/****** Object:  ForeignKey [FK_PedidoCliente_Cliente]    Script Date: 07/17/2011 11:50:05 ******/
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_PedidoCliente_Cliente]') AND parent_object_id = OBJECT_ID(N'[dbo].[PedidoCliente]'))
ALTER TABLE [dbo].[PedidoCliente] DROP CONSTRAINT [FK_PedidoCliente_Cliente]
GO
/****** Object:  ForeignKey [FK_PedidoCliente_EstadoPedido]    Script Date: 07/17/2011 11:50:05 ******/
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_PedidoCliente_EstadoPedido]') AND parent_object_id = OBJECT_ID(N'[dbo].[PedidoCliente]'))
ALTER TABLE [dbo].[PedidoCliente] DROP CONSTRAINT [FK_PedidoCliente_EstadoPedido]
GO
/****** Object:  ForeignKey [FK_Producto_Proveedor]    Script Date: 07/17/2011 11:50:06 ******/
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Producto_Proveedor]') AND parent_object_id = OBJECT_ID(N'[dbo].[Producto]'))
ALTER TABLE [dbo].[Producto] DROP CONSTRAINT [FK_Producto_Proveedor]
GO
/****** Object:  ForeignKey [FK_DetallePedidoProveedor_PedidoProveedor]    Script Date: 07/17/2011 11:50:06 ******/
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_DetallePedidoProveedor_PedidoProveedor]') AND parent_object_id = OBJECT_ID(N'[dbo].[DetallePedidoProveedor]'))
ALTER TABLE [dbo].[DetallePedidoProveedor] DROP CONSTRAINT [FK_DetallePedidoProveedor_PedidoProveedor]
GO
/****** Object:  ForeignKey [FK_DetallePedidoProveedor_Producto]    Script Date: 07/17/2011 11:50:06 ******/
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_DetallePedidoProveedor_Producto]') AND parent_object_id = OBJECT_ID(N'[dbo].[DetallePedidoProveedor]'))
ALTER TABLE [dbo].[DetallePedidoProveedor] DROP CONSTRAINT [FK_DetallePedidoProveedor_Producto]
GO
/****** Object:  ForeignKey [FK_DetallePedidoCliente_PedidoCliente]    Script Date: 07/17/2011 11:50:06 ******/
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_DetallePedidoCliente_PedidoCliente]') AND parent_object_id = OBJECT_ID(N'[dbo].[DetallePedidoCliente]'))
ALTER TABLE [dbo].[DetallePedidoCliente] DROP CONSTRAINT [FK_DetallePedidoCliente_PedidoCliente]
GO
/****** Object:  ForeignKey [FK_DetallePedidoCliente_Producto]    Script Date: 07/17/2011 11:50:06 ******/
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_DetallePedidoCliente_Producto]') AND parent_object_id = OBJECT_ID(N'[dbo].[DetallePedidoCliente]'))
ALTER TABLE [dbo].[DetallePedidoCliente] DROP CONSTRAINT [FK_DetallePedidoCliente_Producto]
GO
/****** Object:  ForeignKey [FK_StockDeposito_Producto]    Script Date: 07/17/2011 11:50:06 ******/
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_StockDeposito_Producto]') AND parent_object_id = OBJECT_ID(N'[dbo].[StockDeposito]'))
ALTER TABLE [dbo].[StockDeposito] DROP CONSTRAINT [FK_StockDeposito_Producto]
GO
/****** Object:  ForeignKey [FK_StockComprometidoDetallePedidoCliente_DetallePedidoCliente]    Script Date: 07/17/2011 11:50:06 ******/
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_StockComprometidoDetallePedidoCliente_DetallePedidoCliente]') AND parent_object_id = OBJECT_ID(N'[dbo].[StockComprometidoDetallePedidoCliente]'))
ALTER TABLE [dbo].[StockComprometidoDetallePedidoCliente] DROP CONSTRAINT [FK_StockComprometidoDetallePedidoCliente_DetallePedidoCliente]
GO
/****** Object:  ForeignKey [FK_StockComprometidoDetallePedidoCliente_DetallePedidoProveedor]    Script Date: 07/17/2011 11:50:06 ******/
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_StockComprometidoDetallePedidoCliente_DetallePedidoProveedor]') AND parent_object_id = OBJECT_ID(N'[dbo].[StockComprometidoDetallePedidoCliente]'))
ALTER TABLE [dbo].[StockComprometidoDetallePedidoCliente] DROP CONSTRAINT [FK_StockComprometidoDetallePedidoCliente_DetallePedidoProveedor]
GO
/****** Object:  ForeignKey [FK_StockComprometidoDetallePedidoCliente_TipoCompromiso]    Script Date: 07/17/2011 11:50:06 ******/
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_StockComprometidoDetallePedidoCliente_TipoCompromiso]') AND parent_object_id = OBJECT_ID(N'[dbo].[StockComprometidoDetallePedidoCliente]'))
ALTER TABLE [dbo].[StockComprometidoDetallePedidoCliente] DROP CONSTRAINT [FK_StockComprometidoDetallePedidoCliente_TipoCompromiso]
GO
/****** Object:  View [dbo].[DisponibilidadStockAFuturo]    Script Date: 07/17/2011 11:50:06 ******/
IF  EXISTS (SELECT * FROM sys.views WHERE object_id = OBJECT_ID(N'[dbo].[DisponibilidadStockAFuturo]'))
DROP VIEW [dbo].[DisponibilidadStockAFuturo]
GO
/****** Object:  View [dbo].[StockDepositoLibre]    Script Date: 07/17/2011 11:50:06 ******/
IF  EXISTS (SELECT * FROM sys.views WHERE object_id = OBJECT_ID(N'[dbo].[StockDepositoLibre]'))
DROP VIEW [dbo].[StockDepositoLibre]
GO
/****** Object:  View [dbo].[StockLibrePorPedido]    Script Date: 07/17/2011 11:50:06 ******/
IF  EXISTS (SELECT * FROM sys.views WHERE object_id = OBJECT_ID(N'[dbo].[StockLibrePorPedido]'))
DROP VIEW [dbo].[StockLibrePorPedido]
GO
/****** Object:  View [dbo].[StockComprometidoPorPedidoPendiente]    Script Date: 07/17/2011 11:50:06 ******/
IF  EXISTS (SELECT * FROM sys.views WHERE object_id = OBJECT_ID(N'[dbo].[StockComprometidoPorPedidoPendiente]'))
DROP VIEW [dbo].[StockComprometidoPorPedidoPendiente]
GO
/****** Object:  Table [dbo].[StockComprometidoDetallePedidoCliente]    Script Date: 07/17/2011 11:50:06 ******/
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_StockComprometidoDetallePedidoCliente_DetallePedidoCliente]') AND parent_object_id = OBJECT_ID(N'[dbo].[StockComprometidoDetallePedidoCliente]'))
ALTER TABLE [dbo].[StockComprometidoDetallePedidoCliente] DROP CONSTRAINT [FK_StockComprometidoDetallePedidoCliente_DetallePedidoCliente]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_StockComprometidoDetallePedidoCliente_DetallePedidoProveedor]') AND parent_object_id = OBJECT_ID(N'[dbo].[StockComprometidoDetallePedidoCliente]'))
ALTER TABLE [dbo].[StockComprometidoDetallePedidoCliente] DROP CONSTRAINT [FK_StockComprometidoDetallePedidoCliente_DetallePedidoProveedor]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_StockComprometidoDetallePedidoCliente_TipoCompromiso]') AND parent_object_id = OBJECT_ID(N'[dbo].[StockComprometidoDetallePedidoCliente]'))
ALTER TABLE [dbo].[StockComprometidoDetallePedidoCliente] DROP CONSTRAINT [FK_StockComprometidoDetallePedidoCliente_TipoCompromiso]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[StockComprometidoDetallePedidoCliente]') AND type in (N'U'))
DROP TABLE [dbo].[StockComprometidoDetallePedidoCliente]
GO
/****** Object:  Table [dbo].[StockDeposito]    Script Date: 07/17/2011 11:50:06 ******/
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_StockDeposito_Producto]') AND parent_object_id = OBJECT_ID(N'[dbo].[StockDeposito]'))
ALTER TABLE [dbo].[StockDeposito] DROP CONSTRAINT [FK_StockDeposito_Producto]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[StockDeposito]') AND type in (N'U'))
DROP TABLE [dbo].[StockDeposito]
GO
/****** Object:  Table [dbo].[DetallePedidoCliente]    Script Date: 07/17/2011 11:50:06 ******/
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_DetallePedidoCliente_PedidoCliente]') AND parent_object_id = OBJECT_ID(N'[dbo].[DetallePedidoCliente]'))
ALTER TABLE [dbo].[DetallePedidoCliente] DROP CONSTRAINT [FK_DetallePedidoCliente_PedidoCliente]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_DetallePedidoCliente_Producto]') AND parent_object_id = OBJECT_ID(N'[dbo].[DetallePedidoCliente]'))
ALTER TABLE [dbo].[DetallePedidoCliente] DROP CONSTRAINT [FK_DetallePedidoCliente_Producto]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[DetallePedidoCliente]') AND type in (N'U'))
DROP TABLE [dbo].[DetallePedidoCliente]
GO
/****** Object:  Table [dbo].[DetallePedidoProveedor]    Script Date: 07/17/2011 11:50:06 ******/
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_DetallePedidoProveedor_PedidoProveedor]') AND parent_object_id = OBJECT_ID(N'[dbo].[DetallePedidoProveedor]'))
ALTER TABLE [dbo].[DetallePedidoProveedor] DROP CONSTRAINT [FK_DetallePedidoProveedor_PedidoProveedor]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_DetallePedidoProveedor_Producto]') AND parent_object_id = OBJECT_ID(N'[dbo].[DetallePedidoProveedor]'))
ALTER TABLE [dbo].[DetallePedidoProveedor] DROP CONSTRAINT [FK_DetallePedidoProveedor_Producto]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[DetallePedidoProveedor]') AND type in (N'U'))
DROP TABLE [dbo].[DetallePedidoProveedor]
GO
/****** Object:  View [dbo].[PedidosClientePendientes]    Script Date: 07/17/2011 11:50:06 ******/
IF  EXISTS (SELECT * FROM sys.views WHERE object_id = OBJECT_ID(N'[dbo].[PedidosClientePendientes]'))
DROP VIEW [dbo].[PedidosClientePendientes]
GO
/****** Object:  View [dbo].[PedidosProveedorPendientes]    Script Date: 07/17/2011 11:50:06 ******/
IF  EXISTS (SELECT * FROM sys.views WHERE object_id = OBJECT_ID(N'[dbo].[PedidosProveedorPendientes]'))
DROP VIEW [dbo].[PedidosProveedorPendientes]
GO
/****** Object:  Table [dbo].[Producto]    Script Date: 07/17/2011 11:50:06 ******/
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_Producto_Proveedor]') AND parent_object_id = OBJECT_ID(N'[dbo].[Producto]'))
ALTER TABLE [dbo].[Producto] DROP CONSTRAINT [FK_Producto_Proveedor]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Producto]') AND type in (N'U'))
DROP TABLE [dbo].[Producto]
GO
/****** Object:  Table [dbo].[PedidoCliente]    Script Date: 07/17/2011 11:50:05 ******/
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_PedidoCliente_Cliente]') AND parent_object_id = OBJECT_ID(N'[dbo].[PedidoCliente]'))
ALTER TABLE [dbo].[PedidoCliente] DROP CONSTRAINT [FK_PedidoCliente_Cliente]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_PedidoCliente_EstadoPedido]') AND parent_object_id = OBJECT_ID(N'[dbo].[PedidoCliente]'))
ALTER TABLE [dbo].[PedidoCliente] DROP CONSTRAINT [FK_PedidoCliente_EstadoPedido]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[PedidoCliente]') AND type in (N'U'))
DROP TABLE [dbo].[PedidoCliente]
GO
/****** Object:  Table [dbo].[PedidoProveedor]    Script Date: 07/17/2011 11:50:05 ******/
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_PedidoProveedor_EstadoPedido]') AND parent_object_id = OBJECT_ID(N'[dbo].[PedidoProveedor]'))
ALTER TABLE [dbo].[PedidoProveedor] DROP CONSTRAINT [FK_PedidoProveedor_EstadoPedido]
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_PedidoProveedor_Proveedor]') AND parent_object_id = OBJECT_ID(N'[dbo].[PedidoProveedor]'))
ALTER TABLE [dbo].[PedidoProveedor] DROP CONSTRAINT [FK_PedidoProveedor_Proveedor]
GO
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[PedidoProveedor]') AND type in (N'U'))
DROP TABLE [dbo].[PedidoProveedor]
GO
/****** Object:  Table [dbo].[Cliente]    Script Date: 07/17/2011 11:50:05 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Cliente]') AND type in (N'U'))
DROP TABLE [dbo].[Cliente]
GO
/****** Object:  Table [dbo].[TipoCompromiso]    Script Date: 07/17/2011 11:50:05 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[TipoCompromiso]') AND type in (N'U'))
DROP TABLE [dbo].[TipoCompromiso]
GO
/****** Object:  Table [dbo].[Proveedor]    Script Date: 07/17/2011 11:50:05 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Proveedor]') AND type in (N'U'))
DROP TABLE [dbo].[Proveedor]
GO
/****** Object:  Table [dbo].[EstadoPedido]    Script Date: 07/17/2011 11:50:05 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[EstadoPedido]') AND type in (N'U'))
DROP TABLE [dbo].[EstadoPedido]
GO
