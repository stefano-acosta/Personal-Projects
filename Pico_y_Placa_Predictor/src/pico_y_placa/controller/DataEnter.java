package pico_y_placa.controller;

import acosta.ingreso.controlador.Ingreso;
import pico_y_placa.model.Information;
import java.util.Calendar;

/**
 *
 * @author Stefano Acosta
 */
public class DataEnter {
    public static Information dataEnter() throws Exception {
        //Initialize variables
        Information information = new Information();
        String aux_plate;
        String aux_plateLastDigit = " ";
        String aux_date;
        String aux_time;
        int aux_year;
        int aux_month;
        int aux_day;
        float aux_hour;
        float aux_minute;
        int aux_dayOfWeek;
        boolean leap_year;
        boolean monthEndsIn31;
        boolean flagPicoyPlaca;
        Calendar cal = Calendar.getInstance();
        cal.clear();
        
        //Get the plate
        aux_plate = new Ingreso().leerString("\nPor favor ingresa tu placa: (ejemplo: ABC1234)");
        //Validate the plate
        try {
            Integer.parseInt(aux_plate.substring(6));
        }catch (NumberFormatException ex) {
            aux_plateLastDigit = "X";
        }
        while ((aux_plate.length() != 7) || (aux_plateLastDigit == "X")){
            aux_plate = new Ingreso().leerString("La placa que ingresaste no es válida, por favor ingrésela de nuevo, "
                    + "debe tener 3 letras y 4 numeros: (ejemplo: PAQ1204)"); 
            aux_plateLastDigit = aux_plate.substring(6);
            try {
                Integer.parseInt(aux_plate.substring(6));
            }catch (NumberFormatException ex) {
                aux_plateLastDigit = "X";
            }   
        }
        
        //Set the plate
        information.setPlate(aux_plate);
        
        //Get the date
        aux_date = new Ingreso().leerString("Ahora ingresa la fecha en formato día-mes-año: (ejemplo: 28-03-2017)");
        //First validation of the date
        while (aux_date.length() != 10){
            aux_date = new Ingreso().leerString("La fecha que ingresaste no es válida, por favor ingrésala de nuevo, "
                    + "debe ser dia-mes-año en números así: (ejemplo: 03-01-2017)"); 
        }
        //Get subset of the string for day-month-year
        try {
        aux_day = Integer.parseInt(aux_date.substring(0, 2));
        }catch (NumberFormatException ex) {
            //System.out.println("ERROR:....."+ex.getMessage()); //just in case the program needs error messages
            aux_day = 0; //So it becomes validated later
        }
        try {
        aux_month = Integer.parseInt(aux_date.substring(3, 5));
        }catch (NumberFormatException ex) {
            aux_month = 0;
        }
        try {
            aux_year = Integer.parseInt(aux_date.substring(6, 10));
        }catch (NumberFormatException ex) {
            aux_year = 0;
        }
        //Seconth validation of the date
        
        //Validation of the month
        while (aux_month > 12 || aux_month < 1){
            try {
                aux_month = new Ingreso().leerInt("El mes que ingresaste no es válido o no está en el formato adecuado"
                    + ", por favor ingrésalo de nuevo, debe ser de 01 al 12 (Enero a Diciembre): (ejemplo: 03)");
                }catch (NumberFormatException ex) {
                    aux_month = 0;
                }
        }
        
        //Validation of the year
        while (aux_year < 0){
            try {
                aux_year = new Ingreso().leerInt("El año que ingresaste no es válido o no está en el formato adecuado"
                    + ", por favor ingrésalo de nuevo, debe ser mayor a 0, -Información: El pico y placa se implementó"
                    + " desde el 2010-: (ejemplo: 2017)");
                }catch (NumberFormatException ex) {
                    aux_year = 0;
                }
        }
        
        //Validation of the day
        //We have to know if it is a leap year, if the case is a leap year
        //February would have 29 days
        monthEndsIn31= aux_month == 1 || aux_month == 3 || aux_month == 5 || aux_month == 7 || aux_month == 8
                  || aux_month == 10 || aux_month == 12;
        
        leap_year = aux_year%4 == 0;
        if (aux_month == 2 & leap_year){
            while (aux_day > 29 || aux_day < 1){
                try {
                    aux_day = new Ingreso().leerInt("El dia que ingresaste no es válido o no está en el formato adecuado"
                        + ", por favor ingrésalo de nuevo, debe ser de 01 al 29 (Año Bisiesto, Ingresaste Febrero): (ejemplo: 03)");
                }catch (NumberFormatException ex) {
                    aux_day = 0;
                }
            }
        } else if (aux_month == 2 & (leap_year == false)) {
            while (aux_day > 31 || aux_day < 1){
                try {
                    aux_day = new Ingreso().leerInt("El dia que ingresaste no es válido o no está en el formato adecuado"
                        + ", por favor ingrésalo de nuevo, debe ser de 01 al 28 (Ingresaste Febrero): (ejemplo: 03)");
                }catch (NumberFormatException ex) {
                    aux_day = 0;
                }
            }
        } else if (monthEndsIn31==true & aux_month!=2) {
            while (aux_day > 31 || aux_day < 1){
                try {
                    aux_day = new Ingreso().leerInt("El dia que ingresaste no es válido o no está en el formato adecuado"
                        + ", por favor ingrésalo de nuevo, debe ser de 01 al 31, debido al mes que ingresaste: (ejemplo: 22)");
                }catch (NumberFormatException ex) {
                    aux_day = 0;
                }
            }
        } else if (monthEndsIn31==false & aux_month!=2) {
            while (aux_day > 30 || aux_day < 1){
                try {
                    aux_day = new Ingreso().leerInt("El dia que ingresaste no es válido o no está en el formato adecuado"
                        + ", por favor ingrésalo de nuevo, debe ser de 01 al 30, debido al mes que ingresaste: (ejemplo: 15)");
                }catch (NumberFormatException ex) {
                    aux_day = 0;
                }
            }
        }
        //End of the date's validation
        //Set Date
        information.setSpecifiedDate(aux_day+"-"+aux_month+"-"+aux_year);
        
        //Get the time
        aux_time = new Ingreso().leerString("Por último, ingresa el tiempo en formato hora:minuto : (ejemplo: 16:30)");
        
        while (aux_time.length() != 5){
            aux_time = new Ingreso().leerString("El tiempo que ingresaste no es válido, por favor ingrésalo de nuevo, "
                    + "debe ser hora:mes en números así: (ejemplo: 09:27)"); 
        }
        
        //Get subset of the string for hour:minutes
        //First validation of time
        try {
        aux_hour = Float.parseFloat(aux_time.substring(0, 2));
        }catch (NumberFormatException ex) {
            aux_hour = 25; //So it becomes validated later
        }
        try {
        aux_minute = Float.parseFloat(aux_time.substring(3, 5));
        }catch (NumberFormatException ex) {
            aux_minute = 61; //So it becomes validated later
        }
        //Seconth validation of the time
        while (aux_hour > 24 || aux_hour < 0){
            try {
                aux_hour = new Ingreso().leerFloat("La hora que ingresaste no es válida"
                    + ", por favor ingrésala de nuevo, debe ser de 0 al 24: (ejemplo: 18)");
                }catch (NumberFormatException ex) {
                    aux_hour = 25;
                }
        }
        
        while (aux_minute > 59 || aux_minute < 0){
            try {
                aux_minute = new Ingreso().leerFloat("Los minutos que ingresaste no son válidos"
                    + ", por favor ingrésalos de nuevo, deben ser de 0 al 59: (ejemplo: 47)");
                }catch (NumberFormatException ex) {
                    aux_minute = 61;
                }
        }
        //End of time's validation
        information.setTime(Integer.parseInt(aux_time.substring(0,2))
                +":"+Integer.parseInt(aux_time.substring(3,5)));
        
        //Calculate
        //set calendar
        cal.set(aux_year,aux_month-1,aux_day,Integer.parseInt(aux_time.substring(0,2)),
                Integer.parseInt(aux_time.substring(3,5))); //month is minus 1 because January equals 0 for Calendar lib

        aux_dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); //Sunday takes day=1
        aux_hour += ((aux_minute)/60);
        
        //Verify day versus plate & time
        if((aux_dayOfWeek  == 2) & ((Integer.parseInt(aux_plate.substring(6))==1)
                ||(Integer.parseInt(aux_plate.substring(6))==2))){
            flagPicoyPlaca = ((aux_hour >= 7) & (aux_hour <= 9.5))
                    || ((aux_hour >= 16) & (aux_hour <= 19.5));
        }
        else if((aux_dayOfWeek  == 3) & ((Integer.parseInt(aux_plate.substring(6))==3)
                ||(Integer.parseInt(aux_plate.substring(6))==4))){
            flagPicoyPlaca = ((aux_hour >= 7) & (aux_hour <= 9.5))
                    || ((aux_hour >= 16) & (aux_hour <= 19.5));
        }
        else if((aux_dayOfWeek  == 4) & ((Integer.parseInt(aux_plate.substring(6))==5)
                ||(Integer.parseInt(aux_plate.substring(6))==6))){
            flagPicoyPlaca = ((aux_hour >= 7) & (aux_hour <= 9.5))
                    || ((aux_hour >= 16) & (aux_hour <= 19.5));
        }
        else if((aux_dayOfWeek  == 5) & ((Integer.parseInt(aux_plate.substring(6))==7)
                ||(Integer.parseInt(aux_plate.substring(6))==8))){
            flagPicoyPlaca = ((aux_hour >= 7) & (aux_hour <= 9.5))
                    || ((aux_hour >= 16) & (aux_hour <= 19.5));
        }
        else if((aux_dayOfWeek  == 6) & ((Integer.parseInt(aux_plate.substring(6))==9)
                ||(Integer.parseInt(aux_plate.substring(6))==0))){
            flagPicoyPlaca = ((aux_hour >= 7) & (aux_hour <= 9.5))
                    || ((aux_hour >= 16) & (aux_hour <= 19.5));
        }
        else {
            flagPicoyPlaca = false;
        }
        //Set plate verification
        information.setFlagPicoyPlaca(flagPicoyPlaca);
        //Return values
        return information;
    }
            
}
