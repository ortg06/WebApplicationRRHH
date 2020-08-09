/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.rrhh.convertidores;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sv.com.rrhh.controladores.EstatusEmpleadoController;
import sv.com.rrhh.entidades.EstatusEmpleado;

/**
 *
 * @author ortg_
 */
@FacesConverter(forClass = EstatusEmpleado.class)
public class EstadoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        EstatusEmpleadoController controller = new EstatusEmpleadoController();
        try {
            return controller.encontrar(Integer.parseInt(value));
        } catch (Exception ex) {
            Logger.getLogger(EstadoConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((EstatusEmpleado)value).getStId().toString();
    }
    
}
