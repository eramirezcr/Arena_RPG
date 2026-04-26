import java.util.ArrayList;

public abstract class Personaje {
    private String nombre;
    private int vidaMaxima;
    private int vidaActual;
    private int ataque; //Ramdom de ataque le resta vida al oponente
    private int defensa; //Ramdom de defensa, la defensa le resta al ataque del oponente
    private ArrayList<Item> item; //El personaje puede tener un item que le da habilidades especiales
    private float oro;
    
    abstract String identidad();
    abstract String mostrarHabilidades();
    
    public int atacar(int... incrementarDannio) {
        if (incrementarDannio.length > 0) {
            return ((int) (Math.random() * 21) + 5) + incrementarDannio[0];
        }
        return (int) (Math.random() * 21) + 5;
    }

    public void recibirAtaque(int intensidad) {
        int vida = getVidaActual();
        vida = vida - intensidad;
        setVidaActual(vida);
    }

    public void recuperarVida(int extra) {
        int vida = getVidaActual();
        vida = vida + (int) (Math.random() * 7) + 1;
        vida = vida + extra;
        if(vida > getVidaMaxima()) //Se verifica que el jugador no tenga más vida de la permitida.
            vida = getVidaMaxima();
        setVidaActual(vida);
    }

    public float getOro() {
        return oro;
    }
    public void setOro(float oro) {
        this.oro = oro;
    }

    public ArrayList<Item> getItem() {
        return item;
    }
    
    public void setItem(ArrayList<Item> item) {
        this.item = item;
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
