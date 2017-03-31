package pico_y_placa.model;
/**
 *
 * @author Stefano Acosta
 */
public class Information {
    private String plate;
    private String specifiedDate;
    private String time;
    private Boolean flagPicoyPlaca;

    public Information() {
        plate = " ";
        specifiedDate = "";
        time = " "; 
        flagPicoyPlaca = false;
    }

    public Information(String plate, String specifiedDate, String time, Boolean flagPicoyPlaca) {
        this.plate = plate;
        this.specifiedDate = specifiedDate;
        this.time = time;
        this.flagPicoyPlaca = flagPicoyPlaca;
    }
    
    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getSpecifiedDate() {
        return specifiedDate;
    }

    public void setSpecifiedDate(String specifiedDate) {
        this.specifiedDate = specifiedDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getFlagPicoyPlaca() {
        return flagPicoyPlaca;
    }

    public void setFlagPicoyPlaca(Boolean flagPicoyPlaca) {
        this.flagPicoyPlaca = flagPicoyPlaca;
    } 
    
    @Override
    public String toString() {
        return "Informacion:\n" + "Placa:" + plate + " , Fecha:" + specifiedDate + " , Hora=" + time + ' ';
    }
    
    
    
}
