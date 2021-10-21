import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main{
    public static void main(String[] args){
        final String filepath = "data.csv";
        ArrayList<Argoment> argoments = new ArrayList<Argoment>();
        
        for(int i = 0; i < 20; i++){
            Argoment a = new Argoment("ciao");
            storeArg(a, filepath);
        }
        
        readArgs(filepath, argoments);

        for(Argoment a : argoments){
            System.out.println(a);
        }
    }

    public static void storeArg(Argoment arg, String filepath){
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

    public static void readArgs(String filepath, ArrayList<Argoment> args){
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
}