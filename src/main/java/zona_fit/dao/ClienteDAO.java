package zona_fit.dao;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IClienteDAO{
    @Override
    public List<Cliente> listarClientes() {
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
    public boolean findClienteById(Cliente cliente) {
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
    public boolean addCliente(Cliente cliente) {
        return false;
    }

    @Override
    public boolean updateCliente(Cliente cliente) {
        return false;
    }

    @Override
    public boolean deleteCliente(Cliente cliente) {
        return false;
    }

    @Override
    public Cliente findByIdNumber(int id) {
        Cliente cliente = null;
        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conexion.getConexion();
        String query = "SELECT * FROM cliente WHERE id = ?";

        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if(rs.next()){
                cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresia"));
            }

            con.close();
        }catch (Exception e){
            cliente = null;
            System.out.println("Error al buscar al cliente por su id: " + e.getMessage());
        }
        return cliente;
    }

}





















