package zona_fit.dao;

import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO{
    @Override
    public List<Cliente> findAll() {
        ArrayList<Cliente> clientes = new ArrayList<>();

        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conexion.getConexion();
        String sql = "SELECT * FROM cliente ORDER BY id";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));

                clientes.add(cliente);
            }
        }catch (Exception e){
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
        finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return clientes;
    }

    @Override
    public boolean findById(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conexion.getConexion();
        String query = "SELECT * FROM cliente WHERE id = ?";

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();

            if(rs.next()){
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
                return true;
            }
            con.close();
        }catch (Exception e){
            System.out.println("Error al buscar un cliente: " + e.getMessage());
        }
       return false;
    }

    @Override
    public boolean add(Cliente cliente) {
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        String query = "INSERT INTO cliente (nombre, apellido, membresia) " + "VALUES (?, ?, ?)";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());

            ps.execute();

            con.close();
            return true;

        }catch (Exception e){
            System.out.println("Error al agregar un cliente: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Cliente cliente) {
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        String query = "UPDATE cliente SET nombre = ?, apellido = ?, membresia = ? WHERE id = ?";

        try {
            ps = con.prepareStatement(query);

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, cliente.getMembresia());
            ps.setInt(4, cliente.getId());

            ps.executeUpdate();
            con.close();

            return true;

        }catch (Exception e){
            System.out.println("Error al actualizar un cliente: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean delete(Cliente cliente) {
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        String query = "DELETE FROM cliente WHERE id = ?";

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, cliente.getId());
            ps.execute();

            con.close();

            return true;
        }catch (Exception e){
            System.out.println("Error al eliminar el cliente: " + e.getMessage());
        }
        return false;
    }

}





















