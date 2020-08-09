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
import sv.com.rrhh.controladores.EstatusEmpleadoController;
import sv.com.rrhh.entidades.EstatusEmpleado;

/**
 *
 * @author ortg_
 */
@ManagedBean
@ViewScoped
public class EstatusEmpleadoManejador {
    
    private EstatusEmpleado estatusEmpleado;
    private EstatusEmpleadoController estatusEmpleadoController = 
            new EstatusEmpleadoController();
    private List<EstatusEmpleado> estatusEmpleadoList;

    /**
     * Creates a new instance of EstatusEmpleado
     */
    @PostConstruct
    public void cargarDatos(){
        estatusEmpleadoList = estatusEmpleadoController.encontrarEntidades();
    }
    
    @PreDestroy
    public void destruir(){
        estatusEmpleadoList = null;
        
        for(EstatusEmpleado est: estatusEmpleadoList){
            est.getStNombre();
        }
    }
    
     public void nuevoEstatus(){
        estatusEmpleado = new EstatusEmpleado();
    }

       
     public void guardarEstatus(){
        try{
          estatusEmpleadoController.editar(estatusEmpleado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Estado de empleado: "+estatusEmpleado.getStNombre()+" editado exitosamente",""));
            cargarDatos();
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "UPS!! OCURRIO UN ERROR AL Editar EL Estado"," ERROR"+ex.getMessage()));
            Logger.getLogger(DepartamentoManejador.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }
     
     
    public void eliminarEstatus(){
        try{
            estatusEmpleadoController.destruir(estatusEmpleado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Estado de empleado:"+estatusEmpleado.getStNombre()+" eliminado exitosamente",""));
            cargarDatos();
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "UPS!! OCURRIO UN ERROR AL ELIMINAR EL ESTADO"," ERROR"+ex.getMessage()));
            Logger.getLogger(DepartamentoManejador.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    public EstatusEmpleado getEstatusEmpleado() {
        return estatusEmpleado;
    }

    public void setEstatusEmpleado(EstatusEmpleado estatusEmpleado) {
        this.estatusEmpleado = estatusEmpleado;
    }

    public List<EstatusEmpleado> getEstatusEmpleadoList() {
        return estatusEmpleadoList;
    }

    public void setEstatusEmpleadoList(List<EstatusEmpleado> estatusEmpleadoList) {
        this.estatusEmpleadoList = estatusEmpleadoList;
    }
    
    
    
    
}
