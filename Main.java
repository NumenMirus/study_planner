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

        final String dataFilepath = "data.csv";
        final String calendarFilepath = "calendar.csv";

        Scanner scanner = new Scanner(System.in);
        int choice = 1;
        


        while(choice != 0){
            clearScreen();
            System.out.println("Bentornato, seleziona l'opzione:\n\n1) Visualizza ripassi di oggi\n2) Aggiungi nuovo argomento\n3) Elimina argomento\n4) Modifica impostazioni argomento\n5) Stampa calendario\n6) Attiva notifiche Desktop\n\n0) ESCI\n\n");
            choice = scanner.nextInt();
            scanner.nextLine();            

            switch(choice){
                case 1:
                    clearScreen();
                    // create calendario
                    createCalendar(calendarFilepath, dataFilepath);

                    // find today's date ids from calendar and converts them in Argoment names to display
                    ArrayList<Integer> ids = getCalendarArgomentsIds(LocalDate.now(), calendarFilepath);

                    // converts ids in Argoment name to display
                    ArrayList<String> argNames = idToArgumentName(ids, dataFilepath);

                    //visualizza ripassi di oggi
                    for(String s : argNames){
                        System.out.println(s);
                    }
                    break;
                case 2:
                    clearScreen();
                    storeArgoments(addArgoment(scanner), dataFilepath);
                    break;
                case 3:
                    clearScreen();
                    //elimina argomento
                    break;
                case 4:
                    clearScreen();
                    //modifica impostazioni argomento
                    break;
                case 5:
                    clearScreen();
                    //crea calendario
                    createCalendar(calendarFilepath, dataFilepath);
                    //generates pdf
                    break;
                case 6:
                    clearScreen();
                    //attiva notifiche
                    break;  
                default:
                    break;
            }
        }
        
        scanner.close();
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
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

        // deletes previous calendar file if exixtsing
        File calendar = new File("calendar.csv"); 
        calendar.delete();

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

    public static int findId(int id, ArrayList<Argoment> a){
        int i = 0;
        for(Argoment x : a){
            if(x.id == id)
                break;
            i++;
        }
        return i;
    }

    public static ArrayList<String> idToArgumentName (ArrayList<Integer> ids, String dataFilepath){
        ArrayList<String> a = new ArrayList<String>();
        ArrayList<Argoment> args = new ArrayList<Argoment>();

        readArgoments(dataFilepath, args);

        for(Integer id : ids){
            if(isArgPresent((int)id, args)){
                int i = findId((int)id, args);
                a.add(args.get(i).Argoment);
            }
        }

        return a;
    }
}