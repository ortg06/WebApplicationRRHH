/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.rrhh.manejadores;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import sv.com.rrhh.controladores.RolController;
import sv.com.rrhh.entidades.Rol;

/**
 *
 * @author ortg_
 */
@ManagedBean
@ViewScoped
public class RolManejador {

    private Rol rol;
    private RolController rolController = new RolController();
    private List<Rol> rolList;

    /**
     * Creates a new instance of RolManejador
     */
    @PostConstruct
    public void cargarDatos() {
        rolList = rolController.encontrarEntidades();
    }

    @PreDestroy
    public void destruir() {
        rolList = null;

        for (Rol rol : rolList) {
            rol.getRlNombre();
        }
    }

    public void nuevoRol() {
        rol = new Rol();
    }

    public void inicializar() {
        rolController = new RolController();
        rol = new Rol();

    }

    public void guardarRol() {
        try {
            rolController.editar(rol);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Rol: " + rol.getRlNombre() + " editado exitosamente", ""));
            cargarDatos();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "UPS!! OCURRIO UN ERROR AL Editar EL ROL", " ERROR" + ex.getMessage()));
            Logger.getLogger(DepartamentoManejador.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void eliminarRol() {
        try {
            rolController.destruir(rol);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Rol " + rol.getRlNombre() + " eliminado exitosamente", ""));
            cargarDatos();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "UPS!! OCURRIO UN ERROR AL ELIMINAR EL ROL", " ERROR" + ex.getMessage()));
            Logger.getLogger(DepartamentoManejador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    
    
}
