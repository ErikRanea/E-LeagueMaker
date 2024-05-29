
package Modelo;

public class Clasificacion {

    private Competicion competicion;
    private Equipo equipo;

    private int posicion;
    private int puntos;

    public Clasificacion(Competicion competicion , Equipo equipo, int posicion, int puntos) {
        this.competicion = competicion;
        this.equipo = equipo;
        this.posicion = posicion;
        this.puntos = puntos;
    }

    public Clasificacion() {
    }

    public Competicion getCompeticion() {
        return competicion;
    }

    public void setCompeticion(Competicion competicion) {
        this.competicion = competicion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

}
