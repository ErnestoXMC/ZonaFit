package zona_fit.app;

import zona_fit.dao.ClienteDAO;
import zona_fit.dao.IClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.List;
import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }
    public static void zonaFitApp(){
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        IClienteDAO clienteDAO = new ClienteDAO();

        while (!salir){
            try {
                int opcion = mostrarMenu(scanner);
                salir = ejecutarOpciones(scanner, opcion, clienteDAO);
            }catch (Exception e){
                System.out.println("Error al ejecutar ZonaFit " + e.getMessage());
            }
            System.out.println();
        }


    }
    public static int mostrarMenu(Scanner scanner){
        System.out.print("""
               -----ZONA-FIT-GYM---
               1. Listar Clientes
               2. Buscar Cliente
               3. Agregar Cliente
               4. Actualizar Cliente
               5. Eliminar Cliente
               6. Salir
               Elije una opcion: \s""");

        return Integer.parseInt(scanner.nextLine());
    }
    public static boolean ejecutarOpciones(Scanner scanner, int opcion ,IClienteDAO clienteDAO){
        var salir = false;
        switch (opcion){
            case 1 -> {//Listado de clientes
                System.out.println("---Listado de Clientes---");
                List<Cliente> clientes = clienteDAO.findAll();
                clientes.forEach(System.out::println);
            }
            case 2 -> {//Listado de clientes por id
                System.out.print("Ingresa el id del cliente: ");
                int idCliente = Integer.parseInt(scanner.nextLine());

                Cliente cliente = new Cliente(idCliente);
                boolean encontrado = clienteDAO.findById(cliente);

                if(encontrado)
                    System.out.println("El cliente con el id: '" + idCliente + "' ha sido encontrado\n" + cliente);
                else
                    System.out.println("El cliente con el id: '" + idCliente + "' NO ha sido encontrado");
            }
            case 3 -> {
                System.out.println("---Agregar-Cliente---");

                System.out.print("Ingresa el nombre: ");
                String nombreCliente = scanner.nextLine();

                System.out.print("Ingresa el apellido: ");
                String apellidoCliente = scanner.nextLine();

                System.out.print("Ingresa la membresia: ");
                int membresia = Integer.parseInt(scanner.nextLine());

                Cliente cliente = new Cliente(nombreCliente, apellidoCliente, membresia);
                boolean agregado = clienteDAO.add(cliente);

                if(agregado)
                    System.out.println("Cliente agregado correctamente\n" + cliente);
                else
                    System.out.println("No se pudo agregar al cliente");
            }
            case 4 -> {
                System.out.println("---Actualizar-Cliente---");

                System.out.print("Ingresa el Codigo del Cliente: ");
                int idCliente = Integer.parseInt(scanner.nextLine());

                System.out.print("Ingresa el nombre: ");
                String nombreCliente = scanner.nextLine();

                System.out.print("Ingresa el apellido: ");
                String apellidoCliente = scanner.nextLine();

                System.out.print("Ingresa la membresia: ");
                int membresia = Integer.parseInt(scanner.nextLine());

                Cliente cliente = new Cliente(idCliente, nombreCliente, apellidoCliente, membresia);
                boolean actualizado = clienteDAO.update(cliente);

                if(actualizado)
                    System.out.println("Cliente actualizado correctamente\n" + cliente);
                else
                    System.out.println("No se pudo actualizar al cliente");
            }
            case 5 -> {
                System.out.println("---Eliminar-Cliente---");

                System.out.print("Ingrese el codigo del cliente: ");
                int idCliente = Integer.parseInt(scanner.nextLine());

                Cliente cliente = new Cliente(idCliente);
                boolean eliminado = clienteDAO.delete(cliente);

                if(eliminado)
                    System.out.println("Cliente eliminado correctamente");
                else
                    System.out.println("No se pudo eliminar al cliente");
            }
            case 6 -> {
                salir = true;
                System.out.println("Gracias, esperamos volver a verte \nHasta pronto!");
            }
            default -> System.out.println("Opcion ingresada no valida");
        }

        return salir;
    }
}
