package com.jonathann.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jonathann.conexion.Conexion;
import com.jonathann.model.Producto;

public class ProductoDAO {
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;
	
	//OBTENER POOL DE CONEXIONES
	private Connection obtenerConexion() throws SQLException{
		return Conexion.getConexion();
	}
	
	public boolean guardar(Producto producto) throws SQLException{
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();
		
		try {
			connection.setAutoCommit(false);
			
			sql = "INSERT INTO productos(nombre,cantidad,precio,fecha_crear,fecha_actualizar)VALUES(?,?,?,?,?);";
			
			statement = connection.prepareStatement(sql);
			statement.setString(1,producto.getNombre());
			statement.setDouble(2,producto.getCantidad());
			statement.setDouble(3, producto.getPrecio());
			statement.setDate(4, producto.getFechaCrear());
			statement.setDate(5, producto.getFechaActualizar());
			estadoOperacion = (statement.executeUpdate()>0);
			
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			statement.close();
			connection.close();
		}
		return estadoOperacion;
	}
	
	public boolean editar(Producto producto) throws SQLException{
		String sql= null;
		estadoOperacion = false;
		connection = obtenerConexion();
		try{
			connection.setAutoCommit(false);
			sql = "UPDATE productos SET nombre = ?,cantidad = ?,precio = ?,fecha_actualizar = ? WHERE id = ?;";
			statement = connection.prepareStatement(sql);
			statement.setString(1, producto.getNombre());
			statement.setDouble(2, producto.getCantidad());
			statement.setDouble(3, producto.getPrecio());
			statement.setDate(4, producto.getFechaActualizar());
			statement.setInt(5,producto.getId());
			
			estadoOperacion = statement.executeUpdate()>0;
			connection.commit();
		}catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}finally {
			statement.close();
			connection.close();
		}
		return estadoOperacion;
	}
	
	public boolean eliminar(int idProducto) throws SQLException{
		String sql= null;
		estadoOperacion = false;
		connection = obtenerConexion();
		
		try{
			connection.setAutoCommit(false);
			sql = "DELETE FROM productos WHERE id = ?;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idProducto);
			estadoOperacion = statement.executeUpdate()>0;
			connection.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			connection.rollback();
		}finally {
			statement.close();
			connection.close();
			
		}
		
		return estadoOperacion;
	}
	
	public Producto obtenerProducto(int idProducto) throws SQLException{
		ResultSet rs = null;
		Producto p = new Producto();
		String sql= null;
		estadoOperacion = false;
		connection = obtenerConexion();
		try {
			sql = "SELECT id, nombre, cantidad, precio, fecha_crear, fecha_actualizar FROM productos WHERE id=?;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1,idProducto);
			rs = statement.executeQuery();
			if(rs.next()){
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setCantidad(rs.getDouble("cantidad"));
				p.setPrecio(rs.getDouble("precio"));
				p.setFechaCrear(rs.getDate("fecha_crear"));
				p.setFechaActualizar(rs.getDate("fecha_actualizar"));
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			statement.close();
			rs.close();
			connection.close();
			
		}
		
		return p;
	}
	
	public List<Producto> obtenerProductos() throws SQLException{
		ResultSet rs = null;
		List<Producto>listaProductos = new ArrayList<>();
		String sql= null;
		estadoOperacion = false;
		connection = obtenerConexion();
		try {
			sql = "SELECT id, nombre, cantidad, precio, fecha_crear, fecha_actualizar FROM productos;";
			statement=connection.prepareStatement(sql);
			rs = statement.executeQuery();
			while (rs.next()) {
				Producto p = new Producto();
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
				p.setCantidad(rs.getDouble("cantidad"));
				p.setPrecio(rs.getDouble("precio"));
				p.setFechaCrear(rs.getDate("fecha_crear"));
				p.setFechaActualizar(rs.getDate("fecha_actualizar"));
				listaProductos.add(p);
			}
		 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			statement.close();
			rs.close();
			connection.close();
		}
		
		return listaProductos;
	}
}
