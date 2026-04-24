public class Item {
    private String nombre;
    private float precio;
    private int porcDannioCura;

    public Item(String nombre, float precio, int porcDannioCura) {
        this.nombre = nombre;
        this.precio = precio;
        this.porcDannioCura = porcDannioCura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getPorcDannioCura() {
        return porcDannioCura;
    }

    public void setPorcDannioCura(int porcDannioCura) {
        this.porcDannioCura = porcDannioCura;
    }

    @Override
    public String toString() {
        return "Item{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", porcDannioCura=" + porcDannioCura +
                '}';
    }
}