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
import sv.com.rrhh.controladores.EmpleadoController;
import sv.com.rrhh.entidades.Empleado;

/**
 *
 * @author ortg_
 */
@ManagedBean
@ViewScoped
public class EmpleadoManejador {

    /**
     * Creates a new instance of EmpleadoManejador
     */
    private Empleado empleado;
    private EmpleadoController empleadoController = new EmpleadoController();
    private List<Empleado> empleadoList;
    
    public EmpleadoManejador() {
    }

    @PostConstruct
    public void cargarDatos(){
        empleadoList = empleadoController.encontrarEntidades();
    }
    
    @PreDestroy
    public void destruir(){
        empleadoList = null;
        
        for(Empleado emp : empleadoList){
            emp.getEmNombre();
        }
    }
    
    public void nuevoEmpleado(){
       empleado = new Empleado();
    }

    public void guardarEmpleado(){
        try{
            empleadoController.editar(empleado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Empleado "+empleado.getEmNombre()+" editado exitosamente",""));
            cargarDatos();
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "UPS!! OCURRIO UN ERROR AL Editar"," ERROR"+ex.getMessage()));
            Logger.getLogger(DepartamentoManejador.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }
    
    public void eliminarEmpleado(){
        try{
             empleadoController.destruir(empleado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Empleado "+empleado.getEmNombre()+" eliminado exitosamente",""));
            cargarDatos();
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "UPS!! OCURRIO UN ERROR AL ELIMINAR"," ERROR"+ex.getMessage()));
            Logger.getLogger(DepartamentoManejador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }
 
    
}
