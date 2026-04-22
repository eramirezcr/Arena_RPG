public abstract class Personaje {
    private String nombre;
    private int vidaMaxima;
    private int vidaActual;
    private int ataque; //Ramdom de ataque le resta vida al oponente
    private int defensa; //Ramdom de defensa, la defensa le resta al ataque del oponente

    abstract String identidad();
    abstract String mostrarHabilidades();
    
    public int atacar() {
        return (int) (Math.random() * 21) + 5;
    }

    public void recibirAtaque(int intensidad) {
        int vida = getVidaActual();
        vida = vida - intensidad;
        setVidaActual(vida);
    }

    public void recuperarVida() {
        int vida = getVidaActual();
        vida = vida + (int) (Math.random() * 7) + 1;
        if(vida > getVidaMaxima()) //Se verifica que el jugador no tenga más vida de la permitida.
            vida = getVidaMaxima();
        setVidaActual(vida);
    }
        
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
