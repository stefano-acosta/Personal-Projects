package pico_y_placa.view;

import acosta.ingreso.controlador.Ingreso;
import pico_y_placa.controller.DataEnter;
import pico_y_placa.model.Information;
/**
 *
 * @author Stefano Acosta
 */
public class Principal {
    
    public static void main(String[] args) throws Exception { 
        char keepUsing = 'S';
        try{
        while (keepUsing == 'S' || keepUsing == 's'){
            //Greetings
            System.out.println("¡Hola! Este programa te ayudará a saber si tu carro puede circular o no por Pico y Placa,");
            //Data Enter
            Information information = DataEnter.dataEnter();
            //Result
            if (information.getFlagPicoyPlaca()){
                System.out.println("Su carro con placas: "+information.getPlate()+"\nNo puede circular a las: "+information.getTime()
                        +"\nde la fecha: "+information.getSpecifiedDate());
            } else{
                System.out.println("Su carro con placas: "+information.getPlate()+"\nSi puede circular a las: "+information.getTime()
                        +"\nde la fecha: "+information.getSpecifiedDate());
            }
        
            keepUsing = new Ingreso().leerChar("\nDesea continuar usando el programa (S/N): ");
        }
        }catch(Exception e){
            keepUsing = 'n';           
        }
    }
}
