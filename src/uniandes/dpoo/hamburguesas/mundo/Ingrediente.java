package uniandes.dpoo.hamburguesas.mundo;

/**
 * Esta clase se utiliza para mantener la información de un ingrediente que se puede agregar o eliminar de un producto
 */
public class Ingrediente
{
    /**
     * El nombre del ingrediente
     */
    private String nombre;

    /**
     * El costo de agregar el ingrediente a un producto
     */
    private int costoAdicional;

    /**
     * Construye un nuevo ingrediente con un nombre y un costo adicional
     * @param nombre El nombre del ingrediente
     * @param costoAdicional El costo adicional de agregar el ingrediente
     */
    public Ingrediente(String nombre, int costoAdicional)
    {
        this.nombre = nombre;
        this.costoAdicional = costoAdicional;
    }

    /**
     * Retorna el nombre del ingrediente
     * @return Nombre del ingrediente
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Retorna el costo adicional del ingrediente
     * @return Costo adicional del ingrediente
     */
    public int getCostoAdicional()
    {
        return costoAdicional;
    }

    /**
     * Sobrescribe el método toString() para mejorar la visualización del ingrediente
     * @return Representación en String del ingrediente
     */
    @Override
    public String toString()
    {
        return nombre + " ($" + costoAdicional + ")";
    }
}