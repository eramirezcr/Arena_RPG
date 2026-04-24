// Nueva linea
import java.util.HashMap;

public class Estadistica {
    private int juegosJugados;
    private int victorias;
    private int derrotas;
    private int danoTotalInfligido;
    private int danoTotalRecibido;
    // Nueva linea
    private HashMap<String, Integer> estadisticas;

    public Estadistica() {
        this.juegosJugados = 0;
        this.victorias = 0;
        this.derrotas = 0;
        this.danoTotalInfligido = 0;
        this.danoTotalRecibido = 0;
        // Nueva linea
        estadisticas = new HashMap<>();
        // Nueva linea
        estadisticas.put("danio_total", 0);
        // Nueva linea
        estadisticas.put("curacion_total", 0);
        // Nueva linea
        estadisticas.put("turnos_jugados", 0);
    }

    public void registrarVictoria() {
        juegosJugados++;
        victorias++;
    }

    public void registrarDerrota() {
        juegosJugados++;
        derrotas++;
    }

    public void agregarDanoInfligido(int dano) {
        danoTotalInfligido += dano;
        // Nueva linea
        estadisticas.put("danio_total", estadisticas.get("danio_total") + dano);
    }

    public void agregarDanoRecibido(int dano) {
        danoTotalRecibido += dano;
        // Nueva linea
        estadisticas.put("danio_total", estadisticas.get("danio_total") + dano);
    }

    // Nueva linea
    public void registrarCuracion(int cantidad) {
        // Nueva linea
        estadisticas.put("curacion_total", estadisticas.get("curacion_total") + cantidad);
    }

    // Nueva linea
    public void registrarTurnoJugado() {
        // Nueva linea
        estadisticas.put("turnos_jugados", estadisticas.get("turnos_jugados") + 1);
    }

    // Nueva linea
    public int obtenerEstadistica(String clave) {
        // Nueva linea
        return estadisticas.getOrDefault(clave, 0);
    }

    // Nueva linea
    public HashMap<String, Integer> getEstadisticas() {
        // Nueva linea
        return estadisticas;
    }

    public int getJuegosJugados() {
        return juegosJugados;
    }

    public int getVictorias() {
        return victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getDanoTotalInfligido() {
        return danoTotalInfligido;
    }

    public int getDanoTotalRecibido() {
        return danoTotalRecibido;
    }

    public double getPromedioDanoInfligido() {
        return juegosJugados > 0 ? (double) danoTotalInfligido / juegosJugados : 0;
    }

    public double getPromedioDanoRecibido() {
        return juegosJugados > 0 ? (double) danoTotalRecibido / juegosJugados : 0;
    }

    public double getPorcentajeVictorias() {
        return juegosJugados > 0 ? (double) victorias / juegosJugados * 100 : 0;
    }

    @Override
    public String toString() {
        return String.format(
            "Estadísticas del Juego:\n" +
            "Juegos jugados: %d\n" +
            "Victorias: %d\n" +
            "Derrotas: %d\n" +
            "Porcentaje de victorias: %.2f%%\n" +
            "Daño total infligido: %d\n" +
            "Daño total recibido: %d\n" +
            "Daño total: %d\n" +
            "Curación total: %d\n" +
            "Turnos jugados: %d\n" +
            "Promedio daño infligido por juego: %.2f\n" +
            "Promedio daño recibido por juego: %.2f",
            juegosJugados, victorias, derrotas, getPorcentajeVictorias(),
            danoTotalInfligido, danoTotalRecibido, estadisticas.get("danio_total"), estadisticas.get("curacion_total"), estadisticas.get("turnos_jugados"), getPromedioDanoInfligido(), getPromedioDanoRecibido()
        );
    }
}