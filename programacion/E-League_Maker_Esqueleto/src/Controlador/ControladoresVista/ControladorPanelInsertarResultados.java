/*package Controlador.ControladoresVista;

import Vista.VentanaCompeticion;

public class ControladorPanelInsertarResultados {

    private ControladorVentanaCompeticion cvCompeti;
    private VentanaCompeticion vCompeti;



    public ControladorPanelInsertarResultados(ControladorVentanaCompeticion cvCompeti, VentanaCompeticion vCompeti)
    {
        this.cvCompeti = cvCompeti;
        this.vCompeti = vCompeti;
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

}
*/