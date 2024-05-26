package Controlador.ControladoresVista;

import Modelo.Competicion;
import Modelo.Jornada;
import Vista.VentanaCompeticion;

import java.util.ArrayList;

public class ControladorPanelInsertarResultados {

    private ControladorVentanaCompeticion cvCompeti;
    private VentanaCompeticion vCompeti;


    private ArrayList<Competicion> listaCompetis;
    private ArrayList<Jornada> listaJornadas;


    public ControladorPanelInsertarResultados(ControladorVentanaCompeticion cvCompeti, VentanaCompeticion vCompeti)
    {
        this.cvCompeti = cvCompeti;
        this.vCompeti = vCompeti;
        listaCompetis = new ArrayList<>();
        listaJornadas = new ArrayList<>();

    }


    public void iniciarComponentes() {
        if (!vCompeti.getpBotones().isVisible()) {
            cargarDatosclasificacionPVisualizarAsinc();
            vCompeti.verPanelBotonesLateralIzq();
        } else {
            vCompeti.quitarPanelBotonesLateralIzq();
        }

        vCompeti.getbCalendario().setVisible(false);
        vCompeti.getCbJornadas().setVisible(false);
        vCompeti.getLabelJornada().setVisible(false);

        vCompeti.addBBuscarAL(new ControladorPanelVerTodosResultados.BBuscar());

       // pVisualizar = vCompeti.getpVisualizar();
        //vaciarPVisualizar();
    }


    public void cargarListas()
    {
        try
        {
            listaCompetis = cvCompeti.pedirCompeticionesCerradas();
            System.out.println("Carga de competiciones hecha");


            if (!listaCompetis.isEmpty()) {
                listaJornadas = cvCompeti.consultarTablaJornadas(listaCompetis.get(0));
                System.out.println("Carga de jornadas hecha");
            } else {
                listaJornadas.clear();
            }



        }
        catch (Exception ex)
        {
            System.out.println("\nHa salido un error en panelInserta cargarListas()\n"ex.getMessage());
        }

    }



}
