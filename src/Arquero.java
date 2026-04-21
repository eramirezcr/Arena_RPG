public class Arquero extends Personaje {
    
    public Arquero(String pNombre) {
        super.setNombre(pNombre);
        super.setVidaActual(100);
        super.setVidaMaxima(100);
        super.setDefensa(3);
    }

    @Override
    String identidad() {
        return """
           Jugador: Arquero
           Nombre: %s
           Salud: %s""".formatted(super.getNombre(), super.getVidaActual());
    }

@Override
    String mostrarHabilidades() {
        return """
           Habilidad a usar:
           1. Habilidad.%s
           2. Habilidad.%s
           3. Habilidad.%s
           4. Habilidad.%s
           """.formatted(Habilidad.FlechaPrecisa, Habilidad.Medicamento,
            Habilidad.GolpePoderoso, Habilidad.Codazo);
    }
    
}