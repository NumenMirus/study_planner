import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;

public class Argomento {
    int id;
    String Argomento;
    LocalDate dataInserimento;
    LocalDate dataFinale;
    int frequenza;
    int priorità;

    Argomento(){
        this.id = (int)(Math.random()*10000);
        this.Argomento = "-";
        this.dataInserimento = LocalDate.now();
        this.dataFinale = dataInserimento.plusYears(1);
    }

    Argomento(String arg){
        this.id = (int)(Math.random()*10000);
        this.Argomento = arg;
        this.dataInserimento = LocalDate.now();
        this.dataFinale = dataInserimento.plusYears(1);
    }

    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyy");
        return "ID:" + this.id + " Arg:" + this.Argomento + " Inserimento:" + dataInserimento.format(formatter) + " Finale:" + dataFinale.format(formatter) + " Frequenza:" + frequenza + "Priorità:" + priorità;
    }

    public boolean checkId(int id){
        if(this.id == id)
            return true;

        return false;
    }


}
 