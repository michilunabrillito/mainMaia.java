package com.example.vista;

import com.example.modelo.alumno;
import com.example.modelo.bibliografia;
import com.example.modelo.bibliotecario;
import com.example.modelo.libro;
import com.example.modelo.persona;
import com.example.modelo.prestamo;
import com.example.modelo.profesor;
import com.example.control.DatabaseConnection;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;

/*aca agregar imports nuevos*/

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static final Scanner teclado = new Scanner(System.in); /*static final es para que no puedas redefinir 
    la funcion, no sea alterada en el resto de la clase*/
    private static ArrayList<libro> listaLibros = new ArrayList<>();
    private static ArrayList<persona> listaPersonas = new ArrayList<>();
    private static ArrayList<alumno> listaAlumnos = new ArrayList<>();
    private static ArrayList<bibliotecario> listaBibliotecarios = new ArrayList<>();
    private static ArrayList<prestamo> listaPrestamos = new ArrayList<>();

    private static ArrayList<profesor> listaProfesores = new ArrayList<>();
    private static ArrayList<profesor> listaBibliografias = new ArrayList<>();
    /*declaro variables final static y private. archiv_libros*/

    private static final String ARCHIVO_RUTA = "C:\\Users\\estudiante\\Desktop\\clases 2025\\Jordaney - Estuctura de datos\\ejemploPOO\\data\\";
    private static final String ARCHIVO_LIBRO = ARCHIVO_RUTA + "libros.dat";
    private static final String ARCHIVO_PERSONAS = ARCHIVO_RUTA + "personas.dat";
    private static final String ARCHIVO_ALUMNOS = ARCHIVO_RUTA + "alumnos.dat";
    private static final String ARCHIVO_BIBLIOTECARIOS = ARCHIVO_RUTA + "bibliotecarios.dat";
    private static final String ARCHIVO_PRESTAMOS = ARCHIVO_RUTA + "prestamos.dat";
    private static final String ARCHIVO_PROFESORES = ARCHIVO_RUTA + "profesores.dat";
    private static final String ARCHIVO_BIBLIOGRAFIA = ARCHIVO_RUTA + "bibliografia.dat";

    private static int hash_listaLibros; //hacer hash de cda array.
    private static int hash_listaPersonas;
    private static int hash_listaAlumnos;
    private static int hash_listaBibliotecarios;
    private static int hash_listaPrestamos;
    private static int hash_listaProfesores;
    private static int hash_listaBibliografias;

    public static void main(String[] args) {
        cargarDatosDesdeLosArchivos(); //DeLOs archivos a memoria. 

        hash_listaLibros = listaLibros.hashCode();
        hash_listaPersonas = listaPersonas.hashCode();
        hash_listaAlumnos = listaAlumnos.hashCode();
        hash_listaBibliotecarios = listaBibliotecarios.hashCode();
        hash_listaPrestamos = listaPrestamos.hashCode();
        hash_listaProfesores = listaProfesores.hashCode();
        hash_listaBibliografias = listaBibliografias.hashCode();

        System.out.println("Hash ni bien lei el archivo libros: " + hash_listaLibros);
        System.out.println("Hash ni bien lei el archivo personas: " + hash_listaPersonas);
        System.out.println("Hash ni bien lei el archivo alumnos: " + hash_listaAlumnos);
        System.out.println("Hash ni bien lei el archivo bibliotecarios: " + hash_listaBibliotecarios);
        System.out.println("Hash ni bien lei el archivo prestamos: " + hash_listaPrestamos);
        System.out.println("Hash ni bien lei el archivo profesores: " + hash_listaProfesores);
        System.out.println("Hash ni bien lei el archivo bibliografias: " + hash_listaBibliografias);

        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 99);
        
        teclado.close();
        System.out.println("Salimos del programa");
    }

    private static void mostrarMenu() {
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1) Cargar Libros");
        System.out.println("2) Listar Libros");
        System.out.println("3) Cargar Personas");
        System.out.println("4) Listar Personas");
        System.out.println("5) Cargar Alumnos");
        System.out.println("6) Listar Alumnos");
        System.out.println("7) Cargar Bibliotecarios");
        System.out.println("8) Listar Bibliotecarios");
        System.out.println("9) Guardar datos de memoria a archivos");
        System.out.println("10) Registar prestamo de libro");
        System.out.println("11) Listar prestamos de libros");
        System.out.println("12) Buscar Libro por nombre");
        System.out.println("13) Buscar Libro por autor");
        System.out.println("14) Buscar Libro por ISBN");
        System.out.println("15) Registrar devolucion de libro");
        System.out.println("16) Listar Prestamos SIN devolucion.");
        System.out.println("Opciones nuevas del parcial:");
        System.out.println("17) Cargar Profesores");
        System.out.println("18) Listar Profesores");
        System.out.println("19) Busqueda de Profesores por Materia");
        System.out.println("20) Busqueda de Profesores por Nombre");
        System.out.println("21) ");
        System.out.println("22) ");
        System.out.println("23) ");
        System.out.println("24) Salir");
        System.out.print("Ingrese una opción: ");
    }
/*xd re lol.metodo try/except version java 
 * usa Integer = clase de java que admite valores nulos y tiene una banda de metodos
 * ojo hay metodos para saber el valor asqii de la letra
*/
    private static int leerOpcion() {
        try {
            return Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Opción inválida
        }
    }
/*switch = es para reemplazar multiples if elif, etc.
 * este es mas reducida, no esta evaluando cada pasito digamos.
 */
    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> cargarLibro();
            case 2 -> listarLibros(listaLibros);
            case 3 -> cargarPersonas();
            case 4 -> listarPersonas(listaPersonas);
            case 5 -> cargarAlumnos();
            case 6 -> listarAlumnos(listaAlumnos);
            case 7 -> cargarBibliotecarios();
            case 8 -> listarBibliotecarios(listaBibliotecarios);
            case 9 -> guardarDatosALosArchivos(); // Llevará todo lo que esté en los arrays a sus correspondientes archivos
            case 10 -> prestarLibro();
            case 11 -> listarPrestamos(listaPrestamos);
            case 12 -> buscarLibroPorNombre(); // si querés buscar por nombre del libro
            case 13 -> buscarLibroPorAutor();
            case 14 -> buscarLibroPorIsbn(listaLibros);
            case 15 -> recuperarLibro();
            case 16 -> listarPrestamosSinDevolucion(listaPrestamos);
            case 17 -> cargarProfesores();
            case 18 -> listarProfesores(listaProfesores);
            case 19 -> buscarProfesorPorMateria();
            case 17 -> cargarProfesores();
            case 17 -> cargarProfesores();
            case 50 -> revisarAntesDeSalir(hash_listaLibros, hash_listaPersonas, hash_listaAlumnos, hash_listaBibliotecarios, hash_listaPrestamos);
            case 20 -> conectarBase();
            default -> System.out.println("Opción no válida. Intente nuevamente.");
        }
    }

    // Implementa los métodos faltantes (ejemplo para cargarLibros):
    private static void cargarLibro() {
        String opcion;
        do {
            libro libro = new libro();
            System.out.println("Ingrese nombre del libro: ");
            libro.setNombre(teclado.nextLine());

            System.out.println("ingrese el autor: ");
            libro.setAutor(teclado.nextLine());
            
            System.out.println("ingrese la editorial: ");
            libro.setEditorial(teclado.nextLine());
                
            System.out.println("ingrese el ISBN (numero): ");
            libro.setIsbn(teclado.nextInt());
            teclado.nextLine(); // <- limpia el buffer
            //teclado.nextline
            System.out.println("ingrese el genero: ");
            libro.setGenero(teclado.nextLine());
                
            System.out.println("ingrese si esta discontinuo (s/n): ");
            if (teclado.nextLine().equalsIgnoreCase("s")){
                libro.setDiscontinuo(true);
            }
            listaLibros.add(libro);
            System.out.print("¿Desea ingresar otro libro? (s/n): ");
            opcion = teclado.nextLine();
        } while (opcion.equalsIgnoreCase("s"));
    }
    
    // Implementar los demás métodos (listarLibros, cargarPersonas, etc.)...
    private static void listarLibros(ArrayList<libro> listaLibros) {
        if (listaLibros.size() == 0) {
            System.out.println("No hay libros cargados en la lista!");
        } else {
            System.out.println("Cantidad de libros de la lista : " + listaLibros.size());
            // Mostrar todos los libros ingresados
            System.out.println("\n--- Lista de libros ingresados ---");
            for (libro lib : listaLibros) {
                System.out.println("Indice en la lista: " + listaLibros.indexOf(lib));
                System.out.println(lib);
            }
        }
    }

    private static void cargarPersonas() {
        String opcion;
        do {
            persona persona = new persona();
            System.out.println("Ingrese nombre de la persona: ");
            persona.setNombre(teclado.nextLine());

            System.out.println("ingrese el apellido: ");
            persona.setApellido(teclado.nextLine());
            
            System.out.println("ingrese el DNI: ");
            persona.setDni(teclado.nextInt());
            teclado.nextLine();

            System.out.println("ingrese el e-mail: ");
            persona.setEmail(teclado.nextLine());

            listaPersonas.add(persona);
            System.out.print("¿Desea ingresar otra persona al sistema? (s/n): ");
            opcion = teclado.nextLine();
        } while (opcion.equalsIgnoreCase("s"));
    }

    // Implementar los demás métodos (listarLibros, cargarPersonas, etc.)...
    private static void listarPersonas(ArrayList<persona> listaPersonas){
        System.out.println("cantidad de personas de la lista: " + listaPersonas.size());
        //mostrar por pantalla todos los libros ingresados
        System.out.println("----Lista de Personas ingresadas----");
        for (persona per :listaPersonas){
            System.out.println(per);
        }

    }

    private static void cargarAlumnos() { 
        char opcion;
         do {
            alumno alumno = new alumno();
            
            System.out.println("Ingrese nombre del alumno: ");
            alumno.setNombre(teclado.nextLine());
            
            System.out.println("Ingrese el apellido: ");
            alumno.setApellido(teclado.nextLine());
        
            System.out.println("Ingrese el DNI: ");
            alumno.setDni(teclado.nextInt());
            teclado.nextLine(); // Limpiar buffer

            System.out.println("Ingrese el email: ");
            alumno.setEmail(teclado.nextLine());

            System.out.println("Ingrese el número de legajo: ");
            alumno.setNroLegajo(teclado.nextInt());
            teclado.nextLine(); // Limpiar buffer

            System.out.println("Ingrese el turno (char): ");
            String entradaTurno = teclado.nextLine();
            char turno = entradaTurno.isEmpty() ? 'M' : entradaTurno.charAt(0); // Valor por defecto 'M'
            alumno.setTurno(turno);

            System.out.println("Ingrese la carrera: ");
            alumno.setCarrera(teclado.nextLine());

            listaAlumnos.add(alumno);

            System.out.print("¿Desea ingresar otro alumno? (s/n): ");
            String entrada = teclado.nextLine();
            opcion = entrada.isEmpty() ? 'n' : entrada.charAt(0);

        } while (opcion == 's' || opcion == 'S');
    }

    private static void listarAlumnos(ArrayList<alumno> listaAlumnos){
        System.out.println("Cantidad de alumnos en la lista: " + listaAlumnos.size());

        System.out.println("----Lista de alumnos ingresados----");
        for (alumno alu : listaAlumnos){
            System.out.println(alu);
        }
    }

    private static void cargarBibliotecarios() { 
        char opcion;
         do {
            bibliotecario bibliotecario = new bibliotecario();
            
            System.out.println("Ingrese nombre del Biblitecario: ");
            bibliotecario.setNombre(teclado.nextLine());
            
            System.out.println("Ingrese el apellido: ");
            bibliotecario.setApellido(teclado.nextLine());
        
            System.out.println("Ingrese el DNI: ");
            bibliotecario.setDni(teclado.nextInt());
            teclado.nextLine(); // Limpiar buffer

            System.out.println("Ingrese el email: ");
            bibliotecario.setEmail(teclado.nextLine());

            System.out.println("Ingrese el número de Empleado: ");
            bibliotecario.setNroEmpleado(teclado.nextInt());
            teclado.nextLine(); // Limpiar buffer

            System.out.println("Ingrese el turno (char): ");
            String entradaTurno = teclado.nextLine();
            char turno = entradaTurno.isEmpty() ? 'M' : entradaTurno.charAt(0); // Valor por defecto 'M'
            bibliotecario.setTurno(turno);

            listaBibliotecarios.add(bibliotecario);

            System.out.print("¿Desea ingresar otro bibliotecario? (s/n): ");
            String entrada = teclado.nextLine();
            opcion = entrada.isEmpty() ? 'n' : entrada.charAt(0);

        } while (opcion == 's' || opcion == 'S');
    }

    private static void listarBibliotecarios(ArrayList<bibliotecario> listaBibliotecarios){
        System.out.println("Cantidad de bibliotecarios en la lista: " + listaBibliotecarios.size());

        System.out.println("----Lista de bibliotecarios ingresados----");
        for (bibliotecario bib : listaBibliotecarios){
            System.out.println(bib);
        }
    }

    @SuppressWarnings("unchecked")
    private static void cargarDatosDesdeLosArchivos(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_LIBRO))){
            listaLibros = (ArrayList<libro>) ois.readObject(); //cargar por cada elemento a la lista.
            System.out.println("Datos del libro cargados desde "+ ARCHIVO_LIBRO);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar libros: " + e.getMessage());
        }

        //repetir para los otros arraylist.

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_ALUMNOS))){
            listaAlumnos = (ArrayList<alumno>) ois.readObject(); //cargar por cada elemento a la lista.
            System.out.println("Datos del libro cargados desde "+ ARCHIVO_ALUMNOS);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los alumnos: " + e.getMessage());
        }

        //carga archivo_bibliotecaios a listaBibliotecarios.

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_BIBLIOTECARIOS))){
            listaBibliotecarios = (ArrayList<bibliotecario>) ois.readObject(); //cargar por cada elemento a la lista.
            System.out.println("Datos del libro cargados desde "+ ARCHIVO_BIBLIOTECARIOS);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los bibliotecarios: " + e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_PERSONAS))){
            listaPersonas = (ArrayList<persona>) ois.readObject(); //cargar por cada elemento a la lista.
            System.out.println("Datos del libro cargados desde "+ ARCHIVO_PERSONAS);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar a las personas: " + e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_PRESTAMOS))){
            listaPrestamos = (ArrayList<prestamo>) ois.readObject(); //cargar por cada elemento a la lista.
            System.out.println("Datos de los prestamos cargados desde "+ ARCHIVO_PRESTAMOS);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los prestamos: " + e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_PROFESORES))){
            listaProfesores = (ArrayList<profesor>) ois.readObject(); //cargar por cada elemento a la lista.
            System.out.println("Datos de los profesores cargados desde "+ ARCHIVO_PROFESORES);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los profesores: " + e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_BIBLIOGRAFIA))){
            listaBibliografias = (ArrayList<bibliografia>) ois.readObject(); //cargar por cada elemento a la lista.
            System.out.println("Datos de las bibliografias cargadas desde "+ ARCHIVO_BIBLIOGRAFIA);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar las bibliorafias: " + e.getMessage());
        }

    }

    private static void guardarDatosALosArchivos(){
        try (ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(ARCHIVO_LIBRO))){
            oss.writeObject(listaLibros);
            System.out.println("Datos de libros guardados en " + ARCHIVO_LIBRO);
            //---- Antes de seguir actualizando el hash ya que puede diferir del leido originalmente
            hash_listaLibros = listaLibros.hashCode();
        } catch (IOException e) {
            System.err.println("Error al guardar libros: " + e.getMessage());
        } 

        try (ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(ARCHIVO_ALUMNOS))){
            oss.writeObject(listaAlumnos);
            System.out.println("Datos de los alumnos guardados en " + ARCHIVO_ALUMNOS);
            hash_listaAlumnos = listaAlumnos.hashCode();
        } catch (IOException e) {
            System.err.println("Error al guardar a los alumnos: " + e.getMessage());
        } 

        try (ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(ARCHIVO_BIBLIOTECARIOS))){
            oss.writeObject(listaBibliotecarios);
            System.out.println("Datos de los bibliotecarios guardados en " + ARCHIVO_BIBLIOTECARIOS);
            hash_listaBibliotecarios = listaBibliotecarios.hashCode();
        } catch (IOException e) {
            System.err.println("Error al guardar a los bibliotecarios: " + e.getMessage());
        } 

        try (ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(ARCHIVO_PERSONAS))){
            oss.writeObject(listaPersonas);
            System.out.println("Datos de las personas guardadas en " + ARCHIVO_PERSONAS);
            hash_listaPersonas = listaPersonas.hashCode();
        } catch (IOException e) {
            System.err.println("Error al guardar a las personas: " + e.getMessage());
        } 

        try (ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream(ARCHIVO_PRESTAMOS))){
            oss.writeObject(listaPrestamos);
            System.out.println("Datos de los prestamos guardados en " + ARCHIVO_PRESTAMOS);
            hash_listaPrestamos = listaPrestamos.hashCode();
        } catch (IOException e) {
            System.err.println("Error al guardar los prestamos: " + e.getMessage());
        } 
    }

    private static void revisarAntesDeSalir(int hash_anterior_lib, int hash_anterior_per, int hash_anterior_alu, int hash_anterior_bib, int hash_anterior_pres){

        int hash_listarLibros_actual = listaLibros.hashCode();
        int hash_listarPersonas_actual = listaPersonas.hashCode();
        int hash_listarAlumnos_actual = listaAlumnos.hashCode();
        int hash_listarBibliotecarios_actual = listaBibliotecarios.hashCode();
        int hash_listaPrestamos_actual = listaPrestamos.hashCode();

        if (hash_anterior_lib != hash_listarLibros_actual || hash_anterior_per != hash_listarPersonas_actual || hash_anterior_alu != hash_listarAlumnos_actual || hash_anterior_bib != hash_listarBibliotecarios_actual || hash_anterior_pres != hash_listaPrestamos_actual) {
            System.out.println("Seguro quiere salir? Las listas actuales no fueron guardadas a archivo");

            String opcion_si_no;
            System.out.print("¿Desea guardar los datos? (s/n): ");
            opcion_si_no = teclado.nextLine();
            if (opcion_si_no.equals("s")) {
                guardarDatosALosArchivos();
            }
        }

    }

    private static void prestarLibro() {

        listarBibliotecarios(listaBibliotecarios);
        System.out.println("Ingrese nro bibliotecario (indice): ");
        int indiceBiblio = Integer.parseInt(teclado.nextLine());

        listarLibros(listaLibros);
        System.out.println("Ingrese nro libro a prestar (indice)");
        int indiceLibro = Integer.parseInt(teclado.nextLine());
        
        listarAlumnos(listaAlumnos);
        System.out.println("Ingrese nro alumno que retira (indice)");
        int indiceAlumno = Integer.parseInt(teclado.nextLine());

        prestamo prestamoLibro = new prestamo(listaBibliotecarios.get(indiceBiblio), listaLibros.get(indiceLibro),
        listaAlumnos.get(indiceAlumno), new Date(), null);
        listaPrestamos.add(prestamoLibro);
        
    }

    private static void listarPrestamos(ArrayList<prestamo> listaPrestamos) {

        if (listaPrestamos.size() == 0) {

            System.out.println("No hay libros prestados en la lista!");

        } else {
            System.out.println("Cantidad de prestamos registrados en la lista : " + listaPrestamos.size());
            System.out.println("\n--- Lista de prestamos realizados ---");
            for (prestamo pre : listaPrestamos) {
                System.out.println("Índice en la lista: " + listaPrestamos.indexOf(pre));
                System.out.println("Bibliotecario: " + pre.getBibliotecario().getNombre() + " " + pre.getBibliotecario().getApellido() +
                " (Empleado N° " + pre.getBibliotecario().getNroEmpleado() + ")");
                System.out.println("Libro: " + pre.getLibro().getNombre() + ", autor: " + pre.getLibro().getAutor());
                System.out.println("Alumno que recibió: " + pre.getAlumno().getNombre() + " " + pre.getAlumno().getApellido());
                System.out.println();//linea en blanco para separar cada prestamo.
            }
        }
    }

    private static void listarPrestamosSinDevolucion(ArrayList<prestamo> listaPrestamos) {
        if (listaPrestamos.size() == 0) {
            System.out.println("No hay préstamos pendientes de devolución.");
        } else {
            System.out.println("Cantidad de prestamos registrados en la lista pendientes de devolver: " + listaPrestamos.size());
            // Mostrar todos los prestamos realizados
            System.out.println("\n--- Lista de prestamos realizados pendientes de devolucion ---");
            for (prestamo pre : listaPrestamos) {
                if (pre.getFechaDevolucion() == null) {
                System.out.println("Índice en la lista: " + listaPrestamos.indexOf(pre));
                System.out.println("Bibliotecario: " + pre.getBibliotecario().getNombre() + " " + pre.getBibliotecario().getApellido() +
                " (Empleado N° " + pre.getBibliotecario().getNroEmpleado() + ")");
                System.out.println("Libro: " + pre.getLibro().getNombre() + ", autor: " + pre.getLibro().getAutor());
                System.out.println("Alumno que recibió: " + pre.getAlumno().getNombre() + " " + pre.getAlumno().getApellido());
                System.out.println("Fecha de préstamo: " + pre.getFechaPrestamo());
            System.out.println();
                }
            }
        }
    }

    private static void recuperarLibro() {
        listarPrestamosSinDevolucion(listaPrestamos);
        System.out.println("Ingrese nro prestamo (indice) del prestamo que se esta devolviendo");
        int indicePrestamo = Integer.parseInt(teclado.nextLine());

        listaPrestamos.get(indicePrestamo).setFechaDevolucion(new Date());
        System.out.println("Listo! Quedó registrada la devolución");
    }
    private static void buscarLibroPorNombre(){
        
        System.out.println("Ingrese nombre del libro a buscar: ");

        String nombreBuscado = (teclado.nextLine());

        int resultado = buscarLibro(listaLibros, nombreBuscado);

        if (resultado < 0) {
            System.out.println("El libro: " + nombreBuscado + " no se ha encontrado!");
            }else{
                System.out.println("El libro: " + nombreBuscado + " se ha encontrado!");
                System.out.println(listaLibros.get(resultado));
            }
        }

    
    private static int buscarLibro(ArrayList<libro> listaLibros, String nombreBuscado) {

        int indiceLibro = -1;

        if (listaLibros.size() == 0) {

            System.out.println("No hay libros cargados en la lista!");

        } else {

            for (libro lib : listaLibros) {
                if (lib.getNombre().equals(nombreBuscado)) {
                    indiceLibro = listaLibros.indexOf(lib);
                    System.out.println("Encontrado! Indice en la lista: " + listaLibros.indexOf(lib));
                    System.out.println(lib);
                } else {
                    System.out.println("No Encontrado!");
                }
            }
        }

        return indiceLibro;
    }
    
    private static void buscarLibroPorAutor(){

        System.out.println("Ingrese nombre del Autor a buscar: ");

        String nombreBuscadoAutor = (teclado.nextLine());

        ArrayList<Integer> resultado = buscarPorAutor(listaLibros, nombreBuscadoAutor);

        if (resultado.size() == 0) {
            System.out.println("El Autor: " + nombreBuscadoAutor + " no se ha encontrado!");
            }else{
                System.out.println("El autor: " + nombreBuscadoAutor + " se ha encontrado!");
                for(int indiceLibro : resultado)
                System.out.println(listaLibros.get(indiceLibro));
                }
        }

    private static ArrayList<Integer> buscarPorAutor(ArrayList<libro> listaLibros, String nombreBuscado) {

        ArrayList<Integer> librosDeAutor = new ArrayList<>();

        if (listaLibros.size() == 0) {

            System.out.println("No hay libros cargados en la lista!");

        } else {

            for (libro lib : listaLibros) {
                if (lib.getAutor().equals(nombreBuscado)) {
                    librosDeAutor.add(listaLibros.indexOf(lib)) ;

                    System.out.println("Encontrado! Indice en la lista: " + listaLibros.indexOf(lib));
                    System.out.println(lib);
                } else {
                    System.out.println("No Encontrado!");
                }
            }
        }
        return librosDeAutor;
    }

    private static void conectarBase() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            System.out.println("Nos conectamos!");
            conn.close(); 
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No nos conectamos!");
        }

    }

    private static void buscarLibroPorIsbn(ArrayList<libro> listaLibros) {
        System.out.println("Ingrese el ISBN del libro a buscar: ");
        int isbnBuscado = Integer.parseInt(teclado.nextLine());
        boolean coincidencia = false;
        
        if (listaLibros.isEmpty()) {
            System.out.println("¡No hay libros cargados en la lista!");
        } else {
            for (libro lib : listaLibros) {
                if (lib.getIsbn() == isbnBuscado) {
                    System.out.println("Libro encontrado:");
                    System.out.println(lib);
                    coincidencia = true;
                    break; 
                    }
            }
            
            if (!coincidencia) {
                System.out.println("No se encontraron libros con ese ISBN.");
            }
        }
    }

    //nuevas funciones Parcial

    private static void cargarProfesores() {
        String opcion;
        do {
            profesor profe = new profesor();
            
            System.out.println("Ingrese nombre del profesor: ");
            profe.setNombre(teclado.nextLine());
            
            System.out.println("Ingrese apellido: ");
            profe.setApellido(teclado.nextLine());
            
            System.out.println("Ingrese DNI: ");
            profe.setDni(teclado.nextInt());
            teclado.nextLine(); // limpiar el buffer
            
            System.out.println("Ingrese email: ");
            profe.setEmail(teclado.nextLine());
            
            System.out.println("Ingrese número de legajo: ");
            profe.setNroLegajo(teclado.nextInt());
            teclado.nextLine();
            
            System.out.println("Ingrese materia: ");
            profe.setMateria(teclado.nextLine());
            
            System.out.println("Ingrese año: ");
            profe.setAño(teclado.nextInt());
            teclado.nextLine();
            
            System.out.println("Ingrese turno (M/T/N): ");
            profe.setTurno(teclado.nextLine().charAt(0));
            
            listaProfesores.add(profe);
            
            System.out.print("¿Desea ingresar otro profesor? (s/n): ");
            opcion = teclado.nextLine();
        } while (opcion.equalsIgnoreCase("s"));
    }

    private static void listarProfesores(ArrayList<profesor> listaProfesores) {
        System.out.println("Cantidad de profesores en la lista: " + listaProfesores.size());
        System.out.println("----Lista de Profesores ingresados----");
        for (profesor prof : listaProfesores) {
            System.out.println(prof);
        }
    }

    private static void buscarProfesorPorMateria() {
        System.out.println("Ingrese materia del profesor a buscar: ");
        String materiaBuscada = teclado.nextLine();
        
        ArrayList<profesor> profesoresPorMateria = buscarPorMateria(listaProfesores, materiaBuscada);
        
        if (profesoresPorMateria.isEmpty()) {
            System.out.println("No se encontraron profesores para la materia: " + materiaBuscada);
        } else {
            System.out.println("Profesores que enseñan " + materiaBuscada + ":");
            for (profesor prof : profesoresPorMateria) {
                System.out.println(prof);
            }
        }
    }

    private static ArrayList<profesor> buscarPorMateria(ArrayList<profesor> listaProfesores, String materiaBuscada) {
        ArrayList<profesor> profesoresEncontrados = new ArrayList<>();
        
        for (profesor prof : listaProfesores) {
            if (prof.getMateria().equalsIgnoreCase(materiaBuscada)) {
                profesoresEncontrados.add(prof);
            }
        }
        return profesoresEncontrados;}
}