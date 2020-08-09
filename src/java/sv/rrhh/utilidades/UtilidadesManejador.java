/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.rrhh.utilidades;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author ortg_
 */
public class UtilidadesManejador {
 
    
    public static void lanzarMensaje(FacesMessage.Severity severidad,String encabezado, String detalle){
        FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(severidad,encabezado, detalle));
    }
    
    public static void lanzarMensajeInfo(String encabezado, String detalle){
       lanzarMensaje(FacesMessage.SEVERITY_INFO, encabezado,detalle);
    }
    
    public static void lanzarMensajeError(String encabezado, String detalle){
       lanzarMensaje(FacesMessage.SEVERITY_ERROR, encabezado,detalle);
    }
    
     public static void lanzarMensajeAdvertencia(String encabezado, String detalle){
       lanzarMensaje(FacesMessage.SEVERITY_WARN, encabezado,detalle);
    }
    
     public static void redireccion(String pagina){
          ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect(context.getRequestContextPath() + "/"+ pagina + ".ortg");
        } catch (IOException ex) {
            Logger.getLogger(UtilidadesManejador.class.getName()).log(Level.SEVERE, null, ex);
        }
     } 
}
