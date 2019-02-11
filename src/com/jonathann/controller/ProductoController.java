package com.jonathann.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jonathann.dao.ProductoDAO;
import com.jonathann.model.Producto;

/**
 * Servlet implementation class ProductoController
 */
@WebServlet(description = "administra preticiones para la tabla productos", urlPatterns = { "/ProductoController" })
public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcion = request.getParameter("opcion");
		
		switch (opcion) {
		case "crearP":
			request.getRequestDispatcher("/views/crear.jsp").forward(request, response);
			break;
		case "listarP":
			this.listarProductos(request, response);
			break;
		case "meditar":
			this.mostrarEditarProducto(request, response);
			break;
		case "eliminar":
			this.eliminarProducto(request, response);
			break;
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcion= request.getParameter("opcion");
		
		if(opcion.equals("guardar")){
			this.crearProducto(request, response);
		}else if(opcion.equals("editar")){
			this.editarProducto(request, response);
		}
		
	}
	
	private void eliminarProducto(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		int idProducto = Integer.parseInt(request.getParameter("id"));
		ProductoDAO productoDAO = new ProductoDAO();
		try {
			productoDAO.eliminar(idProducto);
			System.out.println("PRODUCTO ELIMINADO CORRECTAMENTE");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void mostrarEditarProducto(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		int idProducto = Integer.parseInt(request.getParameter("id"));
		ProductoDAO productoDAO=new ProductoDAO();
		Producto p = new Producto();
		try {
			p=productoDAO.obtenerProducto(idProducto);
			request.setAttribute("producto",p);
			request.getRequestDispatcher("/views/editar.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void listarProductos(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		ProductoDAO productoDao = new ProductoDAO();
		List<Producto>productos = new ArrayList<>();
		
		try {
			productos = productoDao.obtenerProductos();
			request.setAttribute("productos", productos);
			request.getRequestDispatcher("/views/listar.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void crearProducto(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		Date fechaActual = new Date();
		ProductoDAO productoDAO = new ProductoDAO();
		Producto p = new Producto();
		p.setNombre(request.getParameter("nombre"));
		p.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
		p.setPrecio(Double.parseDouble(request.getParameter("precio")));
		p.setFechaCrear(new java.sql.Date(fechaActual.getTime()));
		
		try {
			productoDAO.guardar(p);
			System.out.println("REGISTRO GUARDADO CORRECTAMENTE");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void editarProducto(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		Date fechaActual = new Date();
		ProductoDAO productoDAO = new ProductoDAO();
		Producto p = new Producto();
		p.setId(Integer.parseInt(request.getParameter("id")));
		p.setNombre(request.getParameter("nombre"));
		p.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
		p.setPrecio(Double.parseDouble(request.getParameter("precio")));
		p.setFechaActualizar(new java.sql.Date(fechaActual.getTime()));
		
		try {
			productoDAO.editar(p);
			System.out.println("REGISTRO EDITADO CORRECTAMENTE");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
