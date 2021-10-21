import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main{
    public static void main(String[] args){
        final String filepath = "data.csv";
        Scanner scanner = new Scanner(System.in);
        int choice = 1;

        while(choice != 0){
            System.out.println("Bentornato, seleziona l'opzione:\n\n1) Visualizza ripassi di oggi\n2) Aggiungi nuovo argomento\n3) Elimina argomento\n4) Modifica impostazioni argomento\n5) Stampa calendario\n6) Attiva notifiche Desktop\n\n0) ESCI");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    //visualizza ripassi di oggi
                    break;
                case 2:
                    storeArgoments(addArgoment(), filepath);
                    break;
                case 3:
                    //elimina argomento
                    break;
                case 4:
                    //modifica impostazioni argomento
                    break;
                case 5:
                    //stampa calendario
                    break;
                case 6:
                    //attiva notifiche
                    break;  
                default:
                    break;
            }
        }
        
        scanner.close();
    }

    public static void storeArgoments(Argoment arg, String filepath){
        try{
            FileWriter writer = new FileWriter(filepath, true);
            BufferedWriter buffwriter = new BufferedWriter(writer);
            PrintWriter pwriter = new PrintWriter(buffwriter);

            pwriter.println(arg.id+","+arg.insertionDate+","+arg.finalDate+","+arg.frequency+","+arg.priority+","+arg.Argoment);
            pwriter.flush();
            pwriter.close();

        }catch(Exception E){
            JOptionPane.showMessageDialog(null, "Record not saved");
            E.printStackTrace();
        }
    }

    public static void readArgoments(String filepath, ArrayList<Argoment> args){
        try{
        FileReader reader = new FileReader(filepath);
        BufferedReader buffreader = new BufferedReader(reader);
        String line;

        while((line = buffreader.readLine()) != null){
            String[] row = line.split(",");

            Argoment temp = new Argoment(row[0], row[1], row[2], row[3], row[4], row[5]);
            args.add(temp);
        }

        reader.close();
        buffreader.close();

        }catch(Exception E){
            JOptionPane.showMessageDialog(null, "Record not red");
            E.printStackTrace();
        }
    }

    public static Argoment addArgoment(){
        Argoment a = new Argoment();
        String argoment, finalDate;
        int f = 0, p = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Di che argomento si tratta?");
        argoment = scanner.nextLine();
        a.setArgoment(argoment);

        System.out.println("Priorità (1, 2, 3): ");
        p = scanner.nextInt();
        scanner.nextLine();
        a.setPriority(p);

        System.out.println("Con che frequenza vuoi ripassarlo (in giorni)? ");
        f = scanner.nextInt();
        scanner.nextLine();
        a.setFrequency(f);

        System.out.println("Quando vuoi smettere di ripasarlo?");
        finalDate = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fd = LocalDate.parse(finalDate, formatter);
        a.setFinalDate(fd);

        scanner.close();
        return a;
    }

    public static ArrayList<Argoment> visualizeArguments(LocalDate date){
        ArrayList<Argoment> a = new ArrayList<Argoment>();

        // code here

        return a;
    }

}