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
import sv.com.rrhh.entidades.DescuentosConf;

/**
 *
 * @author ortg_
 */
@ManagedBean
@ViewScoped
public class DescuentosConfManejador {

    private DescuentosConf descuentosConf;
    private DescuentosController descuentosController = new DescuentosController();
    private List<DescuentosConf> descuentosList;
    /**
     * Creates a new instance of DescuentosConf
     */
    @PostConstruct
    public void cargarDatos(){
        descuentosList = descuentosController.encontrarEntidades();
    }
    
    @PreDestroy
    public void destruir(){
       descuentosList = null;
        
        for(DescuentosConf desc : descuentosList){
            desc.getDsNombre();
        }
    }
    
     public void nuevoDescuento(){
        descuentosConf = new DescuentosConf();
    }

      public void inicializar() {
        descuentosController = new DescuentosController();
       descuentosConf = new DescuentosConf();

    }
    
     public void guardarDescuento(){
        try{
           descuentosController.editar(descuentosConf);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Descuento "+descuentosConf.getDsNombre()+" editado exitosamente",""));
            cargarDatos();
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "UPS!! OCURRIO UN ERROR AL Editar EL Descuento"," ERROR"+ex.getMessage()));
            Logger.getLogger(DepartamentoManejador.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }
     
     
    public void eliminarDescuento(){
        try{
            descuentosController.destruir(descuentosConf);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamento "+descuentosConf.getDsNombre()+" eliminado exitosamente",""));
            cargarDatos();
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "UPS!! OCURRIO UN ERROR AL ELIMINAR EL DEPARTAMENTO"," ERROR"+ex.getMessage()));
            Logger.getLogger(DepartamentoManejador.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

  

    public List<DescuentosConf> getDescuentosList() {
        return descuentosList;
    }

    public void setDescuentosList(List<DescuentosConf> descuentosList) {
        this.descuentosList = descuentosList;
    }

    public DescuentosConf getDescuentosConf() {
        return descuentosConf;
    }

    public void setDescuentosConf(DescuentosConf descuentosConf) {
        this.descuentosConf = descuentosConf;
    }
    
    
    
}
