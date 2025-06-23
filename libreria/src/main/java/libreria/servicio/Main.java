package libreria.servicio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Libro {
    private String id;
    private String titulo;
    private String autor;
    private boolean prestado;

    public Libro(String id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.prestado = false;
    }

    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public boolean isPrestado() { return prestado; }
    public void setPrestado(boolean prestado) { this.prestado = prestado; }
}

class Usuario {
    private String id;
    private String nombre;

    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
}

class Prestamo {
    private String libroId;
    private String usuarioId;
    private LocalDate fecha;

    public Prestamo(String libroId, String usuarioId, LocalDate fecha) {
        this.libroId = libroId;
        this.usuarioId = usuarioId;
        this.fecha = fecha;
    }

    public String getLibroId() { return libroId; }
    public String getUsuarioId() { return usuarioId; }
    public LocalDate getFecha() { return fecha; }
}

class LibreriaServicio {
    private List<Libro> libros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Prestamo> prestamos = new ArrayList<>();

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Libro buscarLibroPorId(String id) {
        return libros.stream().filter(l -> l.getId().equals(id)).findFirst().orElse(null);
    }

    public Usuario buscarUsuarioPorId(String id) {
        return usuarios.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
    }

    public void prestarLibro(String libroId, String usuarioId) {
        Libro libro = buscarLibroPorId(libroId);
        if (libro == null || libro.isPrestado()) {
            System.out.println("❌ El libro no existe o ya está prestado.");
            return;
        }

        Usuario usuario = buscarUsuarioPorId(usuarioId);
        if (usuario == null) {
            System.out.println("❌ Usuario no encontrado.");
            return;
        }

        libro.setPrestado(true);
        prestamos.add(new Prestamo(libroId, usuarioId, LocalDate.now()));
        System.out.println("✅ Libro prestado exitosamente.");
    }

    public void devolverLibro(String libroId) {
        Libro libro = buscarLibroPorId(libroId);
        if (libro != null && libro.isPrestado()) {
            libro.setPrestado(false);
            System.out.println("📚 Libro devuelto correctamente.");
        } else {
            System.out.println("❌ El libro no está prestado o no existe.");
        }
    }

    public void mostrarLibrosDisponibles() {
        for (Libro libro : libros) {
            if (!libro.isPrestado()) {
                System.out.println("- " + libro.getId() + ": " + libro.getTitulo() + " de " + libro.getAutor());
            }
        }
    }

    public void mostrarPrestamosPorUsuario(String usuarioId) {
        for (Prestamo p : prestamos) {
            if (p.getUsuarioId().equals(usuarioId)) {
                Libro libro = buscarLibroPorId(p.getLibroId());
                System.out.println("📕 " + libro.getTitulo() + " - Fecha: " + p.getFecha());
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibreriaServicio servicio = new LibreriaServicio();

        // 15 Usuarios predefinidos
        servicio.registrarUsuario(new Usuario("1", "Laura Gómez"));
        servicio.registrarUsuario(new Usuario("2", "Carlos Ramírez"));
        servicio.registrarUsuario(new Usuario("3", "Andrea Torres"));
        servicio.registrarUsuario(new Usuario("4", "Santiago Pérez"));
        servicio.registrarUsuario(new Usuario("5", "Valentina Rodríguez"));
        servicio.registrarUsuario(new Usuario("6", "Miguel Herrera"));
        servicio.registrarUsuario(new Usuario("7", "Camila Martínez"));
        servicio.registrarUsuario(new Usuario("8", "Juan José López"));
        servicio.registrarUsuario(new Usuario("9", "Mariana Ríos"));
        servicio.registrarUsuario(new Usuario("10", "Esteban Moreno"));
        servicio.registrarUsuario(new Usuario("11", "Sofía Castro"));
        servicio.registrarUsuario(new Usuario("12", "Daniel Mejía"));
        servicio.registrarUsuario(new Usuario("13", "Isabella Vargas"));
        servicio.registrarUsuario(new Usuario("14", "Sebastián Luna"));
        servicio.registrarUsuario(new Usuario("15", "Natalia Sánchez"));

        // 15 Libros predefinidos
        servicio.agregarLibro(new Libro("1", "Cien Años de Soledad", "Gabriel García Márquez"));
        servicio.agregarLibro(new Libro("2", "La Ciudad y los Perros", "Mario Vargas Llosa"));
        servicio.agregarLibro(new Libro("3", "Don Quijote de la Mancha", "Miguel de Cervantes"));
        servicio.agregarLibro(new Libro("4", "El amor en los tiempos del cólera", "Gabriel García Márquez"));
        servicio.agregarLibro(new Libro("5", "Rayuela", "Julio Cortázar"));
        servicio.agregarLibro(new Libro("6", "Pedro Páramo", "Juan Rulfo"));
        servicio.agregarLibro(new Libro("7", "Ficciones", "Jorge Luis Borges"));
        servicio.agregarLibro(new Libro("8", "La sombra del viento", "Carlos Ruiz Zafón"));
        servicio.agregarLibro(new Libro("9", "Los detectives salvajes", "Roberto Bolaño"));
        servicio.agregarLibro(new Libro("10", "1984", "George Orwell"));
        servicio.agregarLibro(new Libro("11", "Crónica de una muerte anunciada", "Gabriel García Márquez"));
        servicio.agregarLibro(new Libro("12", "Ensayo sobre la ceguera", "José Saramago"));
        servicio.agregarLibro(new Libro("13", "El túnel", "Ernesto Sabato"));
        servicio.agregarLibro(new Libro("14", "La tregua", "Mario Benedetti"));
        servicio.agregarLibro(new Libro("15", "El Principito", "Antoine de Saint-Exupéry"));

        int opcion;
        do {
            System.out.println("\n=== SISTEMA DE GESTIÓN DE LIBRERÍA ===");
            System.out.println("1. Agregar Libro");
            System.out.println("2. Buscar Libro");
            System.out.println("3. Crear Usuario");
            System.out.println("4. Prestar Libro");
            System.out.println("5. Devolver Libro");
            System.out.println("6. Ver Libros Disponibles");
            System.out.println("7. Ver Préstamos de un Usuario");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1: {
                    System.out.print("ID del libro: ");
                    String id = sc.nextLine();
                    System.out.print("Título: ");
                    String titulo = sc.nextLine();
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();
                    servicio.agregarLibro(new Libro(id, titulo, autor));
                    System.out.println("✅ Libro agregado.");
                    break;
                }
                case 2: {
                    System.out.print("ID del libro: ");
                    String id = sc.nextLine();
                    Libro libro = servicio.buscarLibroPorId(id);
                    if (libro != null) {
                        System.out.println("📖 " + libro.getTitulo() + " de " + libro.getAutor() +
                                           (libro.isPrestado() ? " (Prestado)" : " (Disponible)"));
                    } else {
                        System.out.println("❌ Libro no encontrado.");
                    }
                    break;
                }
                case 3: {
                    System.out.print("ID del usuario: ");
                    String userId = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    servicio.registrarUsuario(new Usuario(userId, nombre));
                    System.out.println("✅ Usuario registrado.");
                    break;
                }
                case 4: {
                    System.out.print("ID del libro: ");
                    String libroId = sc.nextLine();
                    System.out.print("ID del usuario: ");
                    String usuarioId = sc.nextLine();
                    servicio.prestarLibro(libroId, usuarioId);
                    break;
                }
                case 5: {
                    System.out.print("ID del libro: ");
                    String libroId = sc.nextLine();
                    servicio.devolverLibro(libroId);
                    break;
                }
                case 6: {
                    System.out.println("📚 Libros disponibles:");
                    servicio.mostrarLibrosDisponibles();
                    break;
                }
                case 7: {
                    System.out.print("ID del usuario: ");
                    String usuarioId = sc.nextLine();
                    System.out.println("📕 Libros prestados por el usuario:");
                    servicio.mostrarPrestamosPorUsuario(usuarioId);
                    break;
                }
                case 8: {
                    System.out.println("👋 Saliendo del sistema...");
                    break;
                }
                default: {
                    System.out.println("❌ Opción inválida.");
                }
            }
        } while (opcion != 8);
    }
}
