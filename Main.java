import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
        
        new GuiWindow();

    }

    // public static void clearScreen() {  
    //     System.out.print("\033[H\033[2J");  
    //     System.out.flush();  
    // }  

    public static void deleteFile(String path){

        // deletes previous calendar file if exixtsing
        File calendar = new File(path); 
        calendar.delete();

    }

    public static void storeArgoment(Argoment arg, String filepath){
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

    public static void readArgoments(String datafilepath, ArrayList<Argoment> args){
        try{
        FileReader reader = new FileReader(datafilepath);
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

    public static Argoment addArgoment(Scanner scanner){
        Argoment a = new Argoment();
        String argoment, finalDate;
        int f = 0, p = 0;

        System.out.println("Di che argomento si tratta?");
        argoment = scanner.nextLine();
        a.setArgoment(argoment);

        System.out.println("Priorità (1, 2, 3): ");
        p = scanner.nextInt();
        scanner.nextLine();
        while(p < 1 || p > 3){
            System.out.println("Scegli un valore da 1 a 3");
            p = scanner.nextInt();
            scanner.nextLine();
        }
        a.setPriority(p);

        System.out.println("Con che frequenza vuoi ripassarlo (in giorni)? ");
        f = scanner.nextInt();
        scanner.nextLine();
        a.setFrequency(f);

        System.out.println("Quando vuoi smettere di ripassarlo?");
        finalDate = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fd = LocalDate.parse(finalDate, formatter);
        a.setFinalDate(fd);

        return a;
    }

    public static ArrayList<Integer> getCalendarArgomentsIds(LocalDate date, String calendarfilepath){
        // vector of id
        ArrayList<Integer> ids = new ArrayList<Integer>();
        // set up datetime formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try{
                // inizialize the readers
                FileReader reader = new FileReader(calendarfilepath);
                BufferedReader buffreader = new BufferedReader(reader);
                String line;
                
                // reads each line of the calendar
                while((line = buffreader.readLine()) != null){
                    String[] row = line.split(",");
                    
                    // gets all ids of today's date
                    if(LocalDate.parse(row[1], formatter).compareTo(LocalDate.now()) == 0)
                        ids.add(Integer.parseInt(row[0]));
                }

                // closing the readers
                reader.close();
                buffreader.close();

            }catch(Exception E){
                JOptionPane.showMessageDialog(null, "Record not red");
                E.printStackTrace();
            }


        return ids;
    }

    public static void writeInCalendar(Argoment a, String filepath){
        try{
            //setting up the writer
            FileWriter writer = new FileWriter(filepath, true);
            BufferedWriter buffwriter = new BufferedWriter(writer);
            PrintWriter pwriter = new PrintWriter(buffwriter);
            // setting the today's date
            LocalDate currentDate = LocalDate.now();
            // inizializing the begin date
            LocalDate calendarDate = a.insertionDate;
            //settin up the date formatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // until date in the calendar is equal or major than the final date
            while(calendarDate.compareTo(a.finalDate) <= 0){
                // true if the date in the calendar is major than the today date
                if(calendarDate.compareTo(currentDate) >= 0){
                    // true if the date in calendar is less than the final date
                    if(calendarDate.compareTo(a.finalDate) <= 0){

                        //wrties the data in calendar
                        pwriter.println(a.id + "," + calendarDate.format(formatter) + "," + a.priority);
                        pwriter.flush();
                        // adds two days to the date in calendar
                        calendarDate = calendarDate.plusDays(a.frequency);
                    }else{
                        break;
                    }
                }else{
                    // adds two days to the date in calendar
                    calendarDate = calendarDate.plusDays(a.frequency);
                    continue;
                }
            }
            //closing writer
            pwriter.close();

        // handles the exceptions
        }catch(Exception E){
            JOptionPane.showMessageDialog(null, "Record not saved");
            E.printStackTrace();
        }
    }

    public static void createCalendar(String calendarFilepath, String dataFilepath){
        ArrayList<Argoment> aList = new ArrayList<Argoment>();
        readArgoments(dataFilepath, aList);

        deleteFile(calendarFilepath);

        for(Argoment a : aList){
            writeInCalendar(a, calendarFilepath);
        }
    }

    public static boolean isArgPresent(int id, ArrayList<Argoment> a){
        for(Argoment x : a){
            if(x.id == id)
                return true;
        }
        return false;
    }

    public static int findIdPos(int id, ArrayList<Argoment> a){
        if(a.isEmpty())
            return -1;
        
        int i = 0;
        for(Argoment x : a){
            if(x.id == id)
                break;
            i++;
        }
        return i;
    }

    public static int findArgPos(String s, ArrayList<Argoment> a){
        if(a.isEmpty())
            return -1;

        int i = 0;
        for(Argoment x : a){
            if(x.Argoment.toLowerCase().compareTo(s.toLowerCase()) == 0)
                return i;
            i++;
        }
        return -1;
    }

    public static ArrayList<String> idToArgumentName (ArrayList<Integer> ids, String dataFilepath){
        ArrayList<String> a = new ArrayList<String>();
        ArrayList<Argoment> args = new ArrayList<Argoment>();

        readArgoments(dataFilepath, args);

        for(Integer id : ids){
            if(isArgPresent((int)id, args)){
                int i = findIdPos((int)id, args);
                a.add(args.get(i).Argoment);
            }
        }

        return a;
    }

    public static String idToArgumentName (int id, String dataFilepath){
        String a = "";
        ArrayList<Argoment> args = new ArrayList<Argoment>();

        readArgoments(dataFilepath, args);

        if(isArgPresent((int)id, args)){
            int i = findIdPos((int)id, args);
            a = args.get(i).Argoment;
        }

        return a;
    }

    public static void deleteArgument(int id, String datafilepath){
        ArrayList<Argoment> args = new ArrayList<Argoment>();

        readArgoments(datafilepath, args);

        if(args.isEmpty())  
            System.out.println("Nessun elemento da cancellare");
        else{
            int i = findIdPos(id, args);
            args.remove(i);

            // deletes previous calendar file if exixtsing
            File data = new File(datafilepath); 
            data.delete();

            for(Argoment x : args){
                storeArgoment(x, datafilepath);
            }
        }
    }

    public static void deleteArgument(String s, String datafilepath){
        ArrayList<Argoment> args = new ArrayList<Argoment>();

        readArgoments(datafilepath, args);

        if(args.isEmpty())  
            System.out.println("Nessun elemento da cancellare");
        else{
            int i = findArgPos(s, args);
            args.remove(i);

            // deletes previous calendar file if exixtsing
            File data = new File(datafilepath); 
            data.delete();

            for(Argoment x : args){
                storeArgoment(x, datafilepath);
            }
        }

    }

    public static int modifyArgument(String datafilepath, String name, String finaldate, int p, int f){
        ArrayList<Argoment> args = new ArrayList<Argoment>();
        readArgoments(datafilepath, args);

        int i = findArgPos(name, args);
        if(i == -1)
            return 1;
        
        if(finaldate != "null"){
            try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fd = LocalDate.parse(finaldate, formatter);
            args.get(i).setFinalDate(fd);
            }catch(Exception E){
                return -1;
            }
        }
        if(p != -1)
            args.get(i).setPriority(p);
        if(f != -1)
            args.get(i).setFrequency(f);

        //delete previous datafile if existing
        deleteFile(datafilepath);

        //dtoring new data in new datafile
        for(Argoment a : args){
            storeArgoment(a, datafilepath);
        }

        return 0;
    }

    public static int modifyArgument(String datafilepath, int id, String finaldate, int p, int f){
        ArrayList<Argoment> args = new ArrayList<Argoment>();
        readArgoments(datafilepath, args);

        int i = findIdPos(id, args);
        if(i == -1)
            return 1;
        
        if(finaldate != "null"){
            try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fd = LocalDate.parse(finaldate, formatter);
            args.get(i).setFinalDate(fd);
            System.out.println("set 1");
            }catch(Exception E){
                return -1;
            }
        }
        if(p != -1){
            args.get(i).setPriority(p);
            System.out.println("set 2");
        }
        if(f != -1){    
            args.get(i).setFrequency(f);
            System.out.println("set 3");
        }
        //delete previous datafile if existing
        deleteFile(datafilepath);

        //dtoring new data in new datafile
        for(Argoment a : args){
            storeArgoment(a, datafilepath);
            a.toString();
        }

        return 0;
    }

    public static void printCalendarArgoments(LocalDate date, String calendarfilepath, String datafilepath, String savePath){
        // set up datetime formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try{
                // inizialize the readers
                FileReader reader = new FileReader(calendarfilepath);
                BufferedReader buffreader = new BufferedReader(reader);
                String line;
                
                // reads each line of the calendar
                while((line = buffreader.readLine()) != null){
                    String[] row = line.split(",");
                    
                    // gets all Argoments of today's date
                    if(LocalDate.parse(row[1], formatter).compareTo(date) == 0){
                        FileWriter writer = new FileWriter(savePath.concat("calendar.txt"), true);
                        BufferedWriter buffwriter = new BufferedWriter(writer);
                        PrintWriter pwriter = new PrintWriter(buffwriter);
                        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd MMMM yyyy");

                        pwriter.println(LocalDate.parse(row[1], formatter).format(formatter2) + " - " + idToArgumentName(Integer.parseInt(row[0]), datafilepath));
                        
                        //closing the writers
                        pwriter.close();
                        writer.close();
                    
                    }
                }

                // closing the readers
                reader.close();
                buffreader.close();

            }catch(Exception E){
                JOptionPane.showMessageDialog(null, "Record not red");
                E.printStackTrace();
            }
    }

    public static void printToFile(ArrayList<String> args){

        try{
            FileWriter writer = new FileWriter("calendar.txt", false);
            BufferedWriter buffwriter = new BufferedWriter(writer);
            PrintWriter pwriter = new PrintWriter(buffwriter);
            
            for(String a : args){
                pwriter.print(a + "\n");
            }
                
            //closing the writers
            pwriter.close();
            writer.close();

        }catch(Exception E){
            JOptionPane.showMessageDialog(null, "Record not red");
            E.printStackTrace();
        }

    }

    public static void printToFile(ArrayList<String> args, String opt){

        try{
            FileWriter writer = new FileWriter("calendar.txt", false);
            BufferedWriter buffwriter = new BufferedWriter(writer);
            PrintWriter pwriter = new PrintWriter(buffwriter);
            pwriter.println(opt + "\n");
            for(String a : args){
                pwriter.print(a + "\n");
            }
                
            //closing the writers
            pwriter.close();
            writer.close();

        }catch(Exception E){
            JOptionPane.showMessageDialog(null, "Record not red");
            E.printStackTrace();
        }

    }

    public static void printToFile(String path, ArrayList<String> args, String opt){

        try{
            FileWriter writer = new FileWriter(path, false);
            BufferedWriter buffwriter = new BufferedWriter(writer);
            PrintWriter pwriter = new PrintWriter(buffwriter);
            pwriter.println(opt + "\n");
            for(String a : args){
                pwriter.print(a + "\n");
            }
                
            //closing the writers
            pwriter.close();
            writer.close();

        }catch(Exception E){
            JOptionPane.showMessageDialog(null, "Record not red");
            E.printStackTrace();
        }

    }
}