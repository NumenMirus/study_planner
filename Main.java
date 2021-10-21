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

        storeArgoments(addArgoment(), filepath);
        storeArgoments(addArgoment(), filepath);

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

}