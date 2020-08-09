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
import sv.com.rrhh.controladores.CargoController;
import sv.com.rrhh.entidades.Cargo;


/**
 *
 * @author ortg_
 */
@ManagedBean
@ViewScoped
public class CargoManejador {

    private Cargo cargo;
    private CargoController cargoController = new CargoController();
    private List<Cargo> cargoList;
    /**
     * Creates a new instance of CargoManejador
     */
     @PostConstruct
    public void cargarDatos(){
       cargoList = cargoController.encontrarEntidades();
    }
    
    @PreDestroy
    public void destruir(){
       cargoList = null;
        
        for(Cargo cargo : cargoList){
            cargo.getCaNombre();
        }
    }
    
     public void nuevoCargo(){
        cargo = new Cargo();
    }

      
     public void guardarCargo(){
        try{
           cargoController.editar(cargo);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargo "+cargo.getCaNombre()+" guardado exitosamente",""));
            cargarDatos();
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "UPS!! OCURRIO UN ERROR AL Editar EL CARGO"," ERROR"+ex.getMessage()));
            Logger.getLogger(DepartamentoManejador.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }
     
     
    public void eliminarCargo(){
        try{
            cargoController.destruir(cargo);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Cargo "+cargo.getCaNombre()+" eliminado exitosamente",""));
            cargarDatos();
        }catch(Exception ex){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "UPS!! OCURRIO UN ERROR AL ELIMINAR EL Cargo"," ERROR"+ex.getMessage()));
            Logger.getLogger(DepartamentoManejador.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<Cargo> getCargoList() {
        return cargoList;
    }

    public void setCargoList(List<Cargo> cargoList) {
        this.cargoList = cargoList;
    }
    
    
    
    
}
