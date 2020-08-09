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
import sv.com.rrhh.controladores.DescuentosController;
import sv.com.rrhh.controladores.TipoEmpleoController;
import sv.com.rrhh.entidades.DescuentosConf;
import sv.com.rrhh.entidades.TipoEmpleo;

/**
 *
 * @author ortg_
 */
@ManagedBean
@ViewScoped
public class TipoEmpleoManejador {

    private TipoEmpleo tipoEmpleo;
    private TipoEmpleoController tipoEmpleoController;
    private List<TipoEmpleo> tipoEmpleoList;
    /**
     * Creates a new instance of TipoEmpleoManejador
     */
    @PostConstruct
    public void cargarDatos(){
        tipoEmpleoList = tipoEmpleoController.encontrarEntidades();
    }
    
    @PreDestroy
    public void destruir(){
       tipoEmpleoList = null;
        
        for(TipoEmpleo tp : tipoEmpleoList){
            tp.getTpNombre();
        }
    }
    
     public void nuevoTipoEmpleo(){
        tipoEmpleo = new TipoEmpleo();
    }

    
     public void guardarTipoEmpleo(){
        try{
           tipoEmpleoController.editar(tipoEmpleo);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo Empleo "+tipoEmpleo.getTpNombre()+" guardado exitosamente",""));
            cargarDatos();
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "UPS!! OCURRIO UN ERROR AL Editar EL Tipo empleo"," ERROR"+ex.getMessage()));
            Logger.getLogger(DepartamentoManejador.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }
     
     
    public void eliminarTipoEmpleo(){
        try{
            tipoEmpleoController.destruir(tipoEmpleo);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo Empleo "+tipoEmpleo.getTpNombre()+" eliminado exitosamente",""));
            cargarDatos();
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "UPS!! OCURRIO UN ERROR AL ELIMINAR EL Tipo empleo"," ERROR"+ex.getMessage()));
            Logger.getLogger(DepartamentoManejador.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    public TipoEmpleo getTipoEmpleo() {
        return tipoEmpleo;
    }

    public void setTipoEmpleo(TipoEmpleo tipoEmpleo) {
        this.tipoEmpleo = tipoEmpleo;
    }

    public List<TipoEmpleo> getTipoEmpleoList() {
        return tipoEmpleoList;
    }

    public void setTipoEmpleoList(List<TipoEmpleo> tipoEmpleoList) {
        this.tipoEmpleoList = tipoEmpleoList;
    }
    
    
    
    
    
}
