<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editar Producto</title>
</head>
<body>
<h1>Editar Producto</h1>

	<form action="ProductoController" method="post">
	<input type="hidden" name="opcion" value="editar"> 
	<input type="hidden" name="id" value="${producto.id }"> 
		<table>
		<tr>
			<td>Nombre</td>
			<td><input type="text" name="nombre" value="${producto.nombre}" size="50" /></td>
		</tr>
		<tr>
			<td>Cantidad</td>
			<td><input type="text" name="cantidad" value="${producto.cantidad}" size="50" /></td>
		</tr>
		<tr>
			<td>Precio</td>
			<td><input type="text" name="precio" value="${producto.precio}" size="50" /></td>
		</tr>
		
		</table>
		<br>
		<input type="submit" value="Guardar">
	</form>
</body>
</html>