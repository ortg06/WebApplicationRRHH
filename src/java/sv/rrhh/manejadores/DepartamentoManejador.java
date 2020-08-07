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
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.com.rrhh.controladores.DepartamentoController;
import sv.com.rrhh.entidades.Departamento;

/**
 *
 * @author ortg_
 */
@ManagedBean
@ViewScoped
public class DepartamentoManejador {

    private Departamento departamento;
    private DepartamentoController departamentoController = new DepartamentoController();
    private List<Departamento> departamentoList;

  
    @PostConstruct
    public void cargarDatos(){
        departamentoList = departamentoController.encontrarEntidades();
    }
    
    @PreDestroy
    public void destruir(){
        departamentoList = null;
        
        for(Departamento dep : departamentoList){
            dep.getDpNombre();
        }
    }
    
    public void guardarDepartamento(){
        try{
           departamentoController.editar(departamento);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamento "+departamento.getDpNombre()+" editado exitosamente",""));
            cargarDatos();
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "UPS!! OCURRIO UN ERROR AL Editar EL DEPARTAMENTO"," ERROR"+ex.getMessage()));
            Logger.getLogger(DepartamentoManejador.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }
    
    public void eliminarDepartamento(){
        try{
            departamentoController.destruir(departamento);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamento "+departamento.getDpNombre()+" eliminado exitosamente",""));
            cargarDatos();
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "UPS!! OCURRIO UN ERROR AL ELIMINAR EL DEPARTAMENTO"," ERROR"+ex.getMessage()));
            Logger.getLogger(DepartamentoManejador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void inicializar() {
        departamentoController = new DepartamentoController();
        departamento = new Departamento();

    }
    
    public void nuevoDepartamento(){
        departamento = new Departamento();
    }

    public void crearDepartamento(){
        try {
            departamentoController.crear(departamento);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Departamento "+departamento.getDpNombre()+" creado exitosamente",""));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "UPS!! OCURRIO UN ERROR AL CREAR EL Departamento "," ERROR"+ex.getMessage()));
            Logger.getLogger(DepartamentoManejador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Departamento> getDepartamentoList() {
        return departamentoList;
    }

    public void setDepartamentoList(List<Departamento> departamentoList) {
        this.departamentoList = departamentoList;
    }

    
    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

}
