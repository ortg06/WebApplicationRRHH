/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.rrhh.utilidades;

/**
 *
 * @author ortg_
 */
public class FuncionCodAleatorio {
    
    public static int getNumeroAleatorio(){// Metodo para generar numero entero aleatorio
        
        return (int)(Math.random()* 100000 + 1);//retorna un numero entero de 6 cifras
    } 
    
}
