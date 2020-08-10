/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.rrhh.utilidades;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;



import sv.com.rrhh.entidades.Usuario;
import sv.rrhh.propiedades.ConfigProperties;

/**
 *
 * @author ortg_
 */
public class EnviarCorreo {  

    
    
    private Usuario user;
    private Properties propiedades;
    private final String NOMBRE_ARCHIVO_PROPS="config.properties";

    public void enviarCodigo(Usuario user, int num) { //Metodo que permite enviar correos

        propiedades = new Properties(); //Instancia de Properties
        try {
            propiedades.load(ConfigProperties.getResourceAsInputStream(NOMBRE_ARCHIVO_PROPS));
        } catch (IOException ex) {
            Logger.getLogger(EnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
                         
            Email email = new SimpleEmail(); //Instancia de SimpleEmail
            EncriptacionTexto encriptacionTexto = new EncriptacionTexto();//Instancia de EncriptacionTexto
        try {
            //Se configuran los parametros segun el config.properties
            email.setHostName(encriptacionTexto.getTextoDesencriptado(propiedades.getProperty("srEmN")));
            email.setSmtpPort(
                    Integer.parseInt(encriptacionTexto.getTextoDesencriptado(propiedades.getProperty("srEmPt"))));
            email.setAuthentication(encriptacionTexto.getTextoDesencriptado(propiedades.getProperty("srEmU")),
                    encriptacionTexto.getTextoDesencriptado(propiedades.getProperty("srEmP")));
            email.setSSLOnConnect(true);
            email.setFrom(encriptacionTexto.getTextoDesencriptado(propiedades.getProperty("srEmF")));
            email.setSubject("Codigo de verificación");
            email.setMsg("Este es tu codigo :"+num);
            email.addTo(user.getUsCorreo());
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(EnviarCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
