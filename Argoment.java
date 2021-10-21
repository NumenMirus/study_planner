import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Argoment {
    int id;
    String Argoment;
    LocalDate insertionDate;
    LocalDate finalDate;
    int frequency;
    int priority;

    Argoment(){
        this.id = (int)(Math.random()*10000);
        this.Argoment = "-";
        this.insertionDate = LocalDate.now();
        this.finalDate = insertionDate.plusYears(1);
        this.priority = 1;
    }

    Argoment(String arg){
        this.id = (int)(Math.random()*10000);
        this.Argoment = arg;
        this.insertionDate = LocalDate.now();
        this.finalDate = insertionDate.plusYears(1);
    }

    Argoment(String id, String ins, String fin, String f, String p, String arg){
        this.id = Integer.parseInt(id);
        this.Argoment = arg;
        this.insertionDate = LocalDate.parse(ins);
        this.finalDate = LocalDate.parse(fin);
        this.priority = Integer.parseInt(p);
        this.frequency = Integer.parseInt(f);
    }

    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return "ID:" + this.id + "|| Arg:" + this.Argoment + "|| Inserimento:" + insertionDate.format(formatter) + "|| Finale:" 
        + finalDate.format(formatter) + "|| frequency:" + frequency + "|| Argomento" + Argoment + "||Priorità:" + priority;
    }

    public boolean checkId(int id){
        if(this.id == id)
            return true;

        return false;
    }

    public boolean checkId(String s){
        int id = Integer.parseInt(s);
        if(this.id == id)
            return true;

        return false;
    }

    public void setPriority(int p){
        if(p > 3 && p < 1)
            System.out.println("Wrong priority value: choose between 1, 2, 3");
        else
            this.priority = p;
    }

    public void  setFrequency(int f){
        this.frequency = f;
    }
}