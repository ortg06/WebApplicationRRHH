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

import sv.com.rrhh.controladores.EmpleadoController;
import sv.com.rrhh.controladores.EstatusEmpleadoController;
import sv.com.rrhh.controladores.TipoEmpleoController;
import sv.com.rrhh.entidades.Cargo;

import sv.com.rrhh.entidades.Empleado;
import sv.com.rrhh.entidades.EstatusEmpleado;
import sv.com.rrhh.entidades.TipoEmpleo;

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
    private final EmpleadoController empleadoController = new EmpleadoController();
    private List<Empleado> empleadoList;
    private CargoController cargoController = new CargoController();
    private TipoEmpleoController tipoEmpleoController = new TipoEmpleoController();
    private EstatusEmpleadoController estatusEmpleadoController = new EstatusEmpleadoController();
    private Cargo cargo;
    private EstatusEmpleado estatusEmpleado;
    private TipoEmpleo tipoEmpleo;

    

    @PostConstruct
    public void cargarDatos() {
        empleadoList = empleadoController.encontrarEntidades();
        cargoController = new CargoController();
        tipoEmpleoController = new TipoEmpleoController();
        estatusEmpleadoController =new EstatusEmpleadoController();
        this.cargo = new Cargo();
        this.estatusEmpleado = new EstatusEmpleado();
        this.tipoEmpleo = new TipoEmpleo();
    }

    @PreDestroy
    public void destruir() {
        empleadoList = null;

        for (Empleado emp : empleadoList) {
            emp.getEmNombre();
        }
    }
    
    

    public void nuevoEmpleado() {
        empleado = new Empleado();
    }

    public void guardarEmpleado() {
        try {
            
            empleadoController.editar(empleado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Empleado " + empleado.getEmNombre() + " guardado exitosamente", ""));
            cargarDatos();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "UPS!! OCURRIO UN ERROR AL Editar", " ERROR" + ex.getMessage()));
            Logger.getLogger(DepartamentoManejador.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void eliminarEmpleado() {
        try {
            empleadoController.destruir(empleado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Empleado " + empleado.getEmNombre() + " eliminado exitosamente", ""));
            cargarDatos();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "UPS!! OCURRIO UN ERROR AL ELIMINAR", " ERROR" + ex.getMessage()));
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

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public EstatusEmpleado getEstatusEmpleado() {
        return estatusEmpleado;
    }

    public void setEstatusEmpleado(EstatusEmpleado estatusEmpleado) {
        this.estatusEmpleado = estatusEmpleado;
    }

    public TipoEmpleo getTipoEmpleo() {
        return tipoEmpleo;
    }

    public void setTipoEmpleo(TipoEmpleo tipoEmpleo) {
        this.tipoEmpleo = tipoEmpleo;
    }

    public CargoController getCargoController() {
        return cargoController;
    }

    public void setCargoController(CargoController cargoController) {
        this.cargoController = cargoController;
    }

    public TipoEmpleoController getTipoEmpleoController() {
        return tipoEmpleoController;
    }

    public void setTipoEmpleoController(TipoEmpleoController tipoEmpleoController) {
        this.tipoEmpleoController = tipoEmpleoController;
    }

    public EstatusEmpleadoController getEstatusEmpleadoController() {
        return estatusEmpleadoController;
    }

    public void setEstatusEmpleadoController(EstatusEmpleadoController estatusEmpleadoController) {
        this.estatusEmpleadoController = estatusEmpleadoController;
    }

    
   

    
}
