/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.rrhh.propiedades;

import java.io.InputStream;

/**
 *
 * @author ortg_
 */
public class ConfigProperties {
    
    //metodo para retornar un String
    public static InputStream getResourceAsInputStream(String name){
        return ConfigProperties.class.getResourceAsStream(name);
    }
    
}
