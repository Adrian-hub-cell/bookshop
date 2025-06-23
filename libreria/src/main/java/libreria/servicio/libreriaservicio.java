package libreria.servicio;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import libreria.modelo.Libro;
import libreria.modelo.prestamo;
import libreria.modelo.usuario;
import libreria.repositorio.librorepositorio;
import libreria.repositorio.prestamorepositorio;
import libreria.repositorio.usuariorepositorio;

public class libreriaservicio {
    private librorepositorio libroRepo;
    private usuariorepositorio usuarioRepo = new usuariorepositorio();
    private prestamorepositorio prestamoRepo = new prestamorepositorio();
    @SuppressWarnings("unused")
    private prestamorepositorio prestamorepo;

    public libreriaservicio(librorepositorio libroRepo, usuariorepositorio usuarioRepo, prestamorepositorio prestamoRepo) {
        this.libroRepo = new librorepositorio();
        
        this.libroRepo = libroRepo;
        
        this.usuarioRepo = usuarioRepo;
        
        this.prestamoRepo = prestamoRepo;
    }

    public void agregarLibro(Libro libro) {
        libroRepo.agregarLibro(libro);
    }

    public Libro obtenerLibroPorId(String id) {
        return libroRepo.obtenerPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Libro no encontrado: " + id));
    }

    public void registrarUsuario(usuario usuario) {
        usuarioRepo.agregarusuario(usuario);
    }

    @SuppressWarnings("unchecked")
    public void prestarLibro(String libroId, String usuarioId) throws SQLException {
        Libro libro = obtenerLibroPorId(libroId);
        @SuppressWarnings("unused")
        Libro usuario = ((Optional<Libro>) usuarioRepo.obtenerPorId(usuarioId))
                .orElseThrow (() -> new IllegalArgumentException("Usuario no encontrado: " + usuarioId));

        if (libro.isPrestado()) {
            throw new IllegalStateException("El libro ya está prestado.");
        }

        libro.setPrestado(true);
        prestamoRepo.agregarPrestamo(new prestamo(libroId, usuarioId, LocalDate.now()));
    }

    public List<prestamo> obtenerPrestamosPorUsuario(String usuarioId) {
        return prestamoRepo.obtenerPorusuario(usuarioId);
    }

    public void devolverLibro(String devolverId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'devolverLibro'");
    }
}
