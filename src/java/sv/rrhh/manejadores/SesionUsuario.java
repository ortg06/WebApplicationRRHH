/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.rrhh.manejadores;


import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import sv.com.rrhh.controladores.UsuarioController;
import sv.com.rrhh.entidades.Usuario;

/**
 *
 * @author ortg_
 */
@ManagedBean
@SessionScoped
public class SesionUsuario implements Serializable {

    private Usuario usuario;
    private UsuarioController usuarioController;
    /**
     * Creates a new instance of SesionUsuario
     */
    public SesionUsuario() {
    }
    
}
