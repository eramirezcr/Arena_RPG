public abstract class Personaje {
    private String nombre;
    private int vidaMaxima;
    private int vidaActual;
    private int ataque; //Ramdom de ataque le resta vida al oponente
    private int defensa; //Ramdom de defensa, la defensa le resta al ataque del oponente

    abstract String identidad();
    abstract String mostrarHabilidades();
        
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getVidaMaxima() {
        return vidaMaxima;
    }
    public void setVidaMaxima(int vidaMaxima) {
        this.vidaMaxima = vidaMaxima;
    }
    public int getVidaActual() {
        return vidaActual;
    }
    public void setVidaActual(int vidaActual) {
        this.vidaActual = vidaActual;
    }
    public int getAtaque() {
        return ataque;
    }
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }
    public int getDefensa() {
        return defensa;
    }
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }


}
