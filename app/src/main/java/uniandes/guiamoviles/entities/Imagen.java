package uniandes.guiamoviles.entities;

import java.io.Serializable;

/**
 * Created by ASUS on 19/02/2017.
 */

public class Imagen implements Serializable{
    private int id;
    private String nombre;
    private int precio;
    private String imagen;
    private boolean selected;

    public Imagen(int id, String nombre, int precio, String imagen){
        this.id=id;
        this.nombre=nombre;
        this.precio=precio;
        this.imagen=imagen;
    }
    public int getId(){return id; }
    public String getNombre(){ return nombre; }
    public int getPrecio(){return precio; }
    public String getImagen() { return imagen; }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
