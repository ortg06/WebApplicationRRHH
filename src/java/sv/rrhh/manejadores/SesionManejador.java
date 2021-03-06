/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.rrhh.manejadores;




import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sv.com.rrhh.controladores.UsuarioController;
import sv.com.rrhh.entidades.Usuario;
import sv.rrhh.utilidades.UtilidadesManejador;

/**
 *
 * @author ortg_
 */
@ManagedBean
@SessionScoped
public class SesionManejador{

    /**
     * Creates a new instance of SesionManejador
     */
    private Usuario usuario;
    private UsuarioController usuarioController = new UsuarioController();

    @PostConstruct
    public void inicializar(){
        this.usuario = new Usuario();
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    public void validarUsuario(){
        try {
            Usuario user = usuarioController.encontrar(usuario.getUsId());
            int codigo = user.getUsId();
            System.out.println("usuario"+user.getUsNombre());
            if( usuario.getUsId() == codigo
                    && user.getUsContraseña().equals(usuario.getUsContraseña())){
                usuario.setRlId(user.getRlId());
               UtilidadesManejador.redireccion("index");
            }else{
                UtilidadesManejador.lanzarMensajeAdvertencia("Usuario Invalido", "El usuario o contraseña son incorrectos");
            }
        } catch (Exception ex) {
            Logger.getLogger(SesionManejador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void validarSesion(){
        if(usuario.getRlId()==null){
            UtilidadesManejador.redireccion("inicioSesion");
        }
    }
   
}
