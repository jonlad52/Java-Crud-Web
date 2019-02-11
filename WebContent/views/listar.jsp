
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listar Producto</title>
</head>
<body>
<h1>Listar Producto</h1>
<br>
<table border="1">
	<tr>
	 <td>IdProducto</td>
	 <td>Nombre</td>
	 <td>Cantidad</td>
	 <td>Precios</td>
	 <td>Fecha Crea</td>
	 <td>Fecha Actualiza</td>
	 <td>Accion</td>
	</tr>
	<c:forEach var="producto" items="${productos}">
		<tr>
		 <td><c:out value="${producto.id}" /></td>
		 <td><c:out value="${producto.nombre}" /></td>
		 <td><c:out value="${producto.cantidad}" /></td>
		 <td><c:out value="${producto.precio}" /></td>
		 <td><c:out value="${producto.fechaCrear}" /></td>
		 <td><c:out value="${producto.fechaActualizar}" /></td>
		 <td><a href="ProductoController?opcion=meditar&id=${producto.id}">Editar</a>&nbsp;
		 	<a href="ProductoController?opcion=eliminar&id=${producto.id}">Eliminar</a>
		 </td>
		</tr>
	</c:forEach>
</table>
</body>
</html>