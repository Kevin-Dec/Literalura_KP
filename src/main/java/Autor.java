public class Autor {
    private String nombre;
    private Integer anioNacimiento;
    private Integer anioMuerte;

    public Autor(String nombre, Integer anioNacimiento, Integer anioMuerte) {
        this.nombre = nombre;
        this.anioNacimiento = anioNacimiento;
        this.anioMuerte = anioMuerte;
    }

    public String getNombre() { return nombre; }
    public Integer getAnioNacimiento() { return anioNacimiento; }
    public Integer getAnioMuerte() { return anioMuerte; }

    public boolean estaVivoEn(int anio) {
        return (anioNacimiento != null && anioNacimiento <= anio) &&
               (anioMuerte == null || anioMuerte >= anio);
    }

    @Override
    public String toString() {
        return nombre + " (" +
                (anioNacimiento != null ? anioNacimiento : "¿?") +
                " - " +
                (anioMuerte != null ? anioMuerte : "¿?") + ")";
    }
}
