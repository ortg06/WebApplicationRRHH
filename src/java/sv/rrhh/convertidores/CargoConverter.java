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
import sv.com.rrhh.controladores.CargoController;
import sv.com.rrhh.entidades.Cargo;

/**
 *
 * @author ortg_
 */
@FacesConverter(forClass = Cargo.class)
public class CargoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        CargoController controller = new CargoController();
        try {
            return controller.encontrar(Integer.parseInt(value));
        } catch (Exception ex) {
            Logger.getLogger(CargoConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Cargo)value).getCaId().toString();
    }
    
}
