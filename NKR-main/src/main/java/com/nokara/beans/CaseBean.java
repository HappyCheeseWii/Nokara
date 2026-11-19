
package com.nokara.beans;

import com.nokara.ejb.CaseService;
import com.nokara.models.Case;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("caseBean")
@ViewScoped
public class CaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private CaseService caseService;

    private Case caso = new Case();

    private Long casoSeleccionado;

    private int totalProcesamientos;

    public void registrar() {

        totalProcesamientos++;

        if (caseService.registrar(caso)) {
            caso = new Case();

            mostrarMensaje("Caso registrado correctamente.",
                    FacesMessage.SEVERITY_INFO);
        } else {
            mostrarMensaje("No fue posible registrar el caso.",
                    FacesMessage.SEVERITY_ERROR);
        }
    }

    public void seleccionar(Long id) {

        caso = caseService.buscarPorId(id);

        if (caso == null) {
            caso = new Case();
            casoSeleccionado = null;

            mostrarMensaje("No se encontró el caso.",
                    FacesMessage.SEVERITY_ERROR);
            return;
        }

        casoSeleccionado = id;
    }

    public void actualizar() {

        if (caseService.actualizar(caso)) {
            caso = new Case();
            casoSeleccionado = null;

            mostrarMensaje("Caso actualizado correctamente.",
                    FacesMessage.SEVERITY_INFO);
        } else {
            mostrarMensaje("No fue posible actualizar el caso.",
                    FacesMessage.SEVERITY_ERROR);
        }
    }

    public void eliminar(Long id) {

        if (caseService.eliminar(id)) {
            mostrarMensaje("Caso eliminado correctamente.",
                    FacesMessage.SEVERITY_INFO);
        } else {
            mostrarMensaje("No fue posible eliminar el caso.",
                    FacesMessage.SEVERITY_ERROR);
        }

        if (id != null && id.equals(casoSeleccionado)) {
            caso = new Case();
            casoSeleccionado = null;
        }
    }

    public List<Case> getCasos() {
        return caseService.listarTodos();
    }

    public long getTotalCasos() {
        return caseService.contarCasos();
    }

    public int getTotalProcesamientos() {
        return totalProcesamientos;
    }

    public Case getCaso() {
        return caso;
    }

    public void setCaso(Case caso) {
        this.caso = caso;
    }

    public Long getCasoSeleccionado() {
        return casoSeleccionado;
    }

    public void setCasoSeleccionado(Long casoSeleccionado) {
        this.casoSeleccionado = casoSeleccionado;
    }

    private void mostrarMensaje(String mensaje,
                                FacesMessage.Severity severidad) {

        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(severidad, mensaje, null)
        );
    }
}
