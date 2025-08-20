import java.util.List;

public class Libro {
    private String titulo;
    private String idioma;
    private List<Autor> autores;

    public Libro(String titulo, String idioma, List<Autor> autores) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.autores = autores;
    }

    public String getTitulo() { return titulo; }
    public String getIdioma() { return idioma; }
    public List<Autor> getAutores() { return autores; }

    @Override
    public String toString() {
        return "Libro: " + titulo + " | Idioma: " + idioma + " | Autores: " + autores;
    }
}
