import java.awt.Font;
import java.awt.event.ActionEvent;
//import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GuiWindow extends JFrame implements Action{

    final String dataFilepath = "./data.csv";
    final String calendarFilepath = "./calendar.csv";


    //GLOBAL DECLARATIONS FOR BUTTONS
    Button button1;
    Button button2;
    Button button3;

    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio3;
    RadioButton radio4;
    RadioButton radio5;
    RadioButton radioFreq1;
    RadioButton radioFreq2;
    RadioButton radioFreq3;
    RadioButton radioFreq4;
    RadioButton radioFreq5;
    RadioButton radioFreq6;


    //GLOBAL DECLARATIONS FOR TEXTFIELDS
    JTextField text1;
    JTextField text2;
    JTextField text3;
    JTextField text4;
    JTextField text5;

    //GLOBAL DECLARATIONS FOR LABELS
    Label label2;
    Label label3;
    Label label4;
    Label label5;
    Label label6;
    Label label7;

    //GLOBAL DECLARATIONS FOR COMBOBOX
    JComboBox<String> combo1;

    GuiWindow(){
        this.setTitle("Study Planner");
        this.setSize(500, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        
        
        //IMAGES AND ICONS

        ImageIcon icon = new ImageIcon("./icons/studying.png");
        this.setIconImage(icon.getImage());


        //LABELS

        Label label1 = new Label("Pianifica il tuo studio:", 5, 10, 490, 30, true, true);
        this.add(label1);

        label2 = new Label("PATH:", 50, 260, 400, 30);
        this.add(label2);

        label3 = new Label("Nome argomento:", 50, 260, 400, 30);
        this.add(label3);

        label4 = new Label("Priorità:", 50, 375, 400, 30);
        this.add(label4);

        label5 = new Label("Frequenza (giorni):", 50, 470, 400, 30);
        this.add(label5);

        label6 = new Label("Data di fine (gg/mm/yyyy):", 50, 585, 400, 30);
        this.add(label6);

        label7 = new Label("Cerca argomento per: ", 50, 260, 280, 30);
        this.add(label7);


        //BUTTONS AND RADIOBUTTONS

        button1 = new Button("Stampa", 70, 350, 360, 50, false);
        button1.addActionListener(this);
        this.add(button1);

        button2 = new Button("Aggiungi", 50, 700, 400, 50, false);
        button2.addActionListener(this);
        this.add(button2);

        button3 = new Button("Elimina", 70, 350, 360, 50, false);
        button3.addActionListener(this);
        this.add(button3);


        radio1 = new RadioButton("Stampa ripassi di oggi", 20, 50, 450, 20);
        radio1.addActionListener(this);
        this.add(radio1);

        radio2 = new RadioButton("Aggiungi argomento", 20, 80, 450, 20);
        radio2.addActionListener(this);
        this.add(radio2);

        radio3 = new RadioButton("Elimina Argomento", 20, 110, 450, 20);
        radio3.addActionListener(this);
        this.add(radio3);

        radio4 = new RadioButton("Modifica impostazioni di un argomento", 20, 140, 450, 20);
        radio4.addActionListener(this);
        this.add(radio4);

        radio5 = new RadioButton("Stampa calendario 7 giorni", 20, 170, 400, 20);
        radio5.addActionListener(this);
        this.add(radio5);

        radioFreq1 = new RadioButton("1", 175, 415, 50, 20, false);
        radioFreq1.addActionListener(this);
        this.add(radioFreq1);

        radioFreq2 = new RadioButton("2", 225, 415, 50, 20, false);
        radioFreq2.addActionListener(this);
        this.add(radioFreq2);

        radioFreq3 = new RadioButton("3", 275, 415, 50, 20, false);
        radioFreq3.addActionListener(this);
        this.add(radioFreq3);

        //radiobutton group
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(radio1);
        radioGroup.add(radio2);
        radioGroup.add(radio3);
        radioGroup.add(radio4);
        radioGroup.add(radio5);

        ButtonGroup radioGroupFreq1 = new ButtonGroup();
        radioGroupFreq1.add(radioFreq1);
        radioGroupFreq1.add(radioFreq2);
        radioGroupFreq1.add(radioFreq3);

        //TEXTFIELDS

        text1 = new JTextField();
        text1.setBounds(50, 300, 400, 30);
        text1.setHorizontalAlignment(JTextField.CENTER);
        text1.setVisible(false);
        this.add(text1);

        text2 = new JTextField();
        text2.setBounds(50, 300, 400, 30);
        text2.setHorizontalAlignment(JTextField.CENTER);
        text2.setVisible(false);
        this.add(text2);

        text3 = new JTextField();
        text3.setBounds(200, 510, 100, 30);
        text3.setHorizontalAlignment(JTextField.CENTER);
        text3.setVisible(false);
        this.add(text3);

        text4 = new JTextField();
        text4.setBounds(50, 625, 400, 30);
        text4.setHorizontalAlignment(JTextField.CENTER);
        text4.setVisible(false);
        this.add(text4);

        text5 = new JTextField();
        text5.setBounds(50, 300, 400, 30);
        text5.setHorizontalAlignment(JTextField.CENTER);
        text5.setVisible(false);
        this.add(text5);

        //COMBOBOX

        String[] choice = {" Nome", " ID"};
        combo1 = new JComboBox<>(choice);
        combo1.setFont(new Font("Arial", Font.ITALIC, 18));
        combo1.setBounds(330, 260, 100, 30);
        combo1.addActionListener(this);
        combo1.setVisible(false);
        this.add(combo1);




        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        ///// RADIO BUTTON NUMBER 1 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if(e.getSource()==radio1){
            //setting other elements not visible
            text2.setVisible(false);
            label3.setVisible(false);
            radioFreq1.setVisible(false);
            radioFreq2.setVisible(false);
            radioFreq3.setVisible(false);
            label4.setVisible(false);
            label5.setVisible(false);
            text3.setVisible(false);
            label6.setVisible(false);
            text4.setVisible(false);
            button2.setVisible(false);
            text5.setVisible(false);
            combo1.setVisible(false);
            label7.setVisible(false);
            button3.setVisible(false);
            

            //setting elements visible
            text1.setVisible(true);
            label2.setVisible(true);
            button1.setVisible(true);
        }
        
        /// LOGIC ///
        if(e.getSource() == button1){

            //stampa ripassi di oggi
            if(text1.getText() == "")
                JOptionPane.showConfirmDialog(null, "Path mancante!", "Errore", JOptionPane.ERROR_MESSAGE);
            else{   
                // create calendar
                Main.createCalendar(calendarFilepath, dataFilepath);

                // find today's date ids from calendar and converts them in Argoment names to display
                ArrayList<Integer> ids = Main.getCalendarArgomentsIds(LocalDate.now(), calendarFilepath);

                // converts ids in Argoment name to display
                ArrayList<String> argNames = Main.idToArgumentName(ids, dataFilepath);

                Main.printToFile(text1.getText()+"calendar.txt", argNames, "Oggi hai da fare:"); 

                text1.setText("");
            }
        }

        ///// RADIO BUTTON NUMBER 2 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if(e.getSource()==radio2){
            //setting other elements not visible
            text1.setVisible(false);
            label2.setVisible(false);
            button1.setVisible(false);
            label3.setVisible(false);
            text5.setVisible(false);
            combo1.setVisible(false);
            label7.setVisible(false);
            button3.setVisible(false);

            //setting elements visible
            text2.setVisible(true);
            label3.setVisible(true);
            radioFreq1.setVisible(true);
            radioFreq2.setVisible(true);
            radioFreq3.setVisible(true);
            label4.setVisible(true);
            label5.setVisible(true);
            text3.setVisible(true);
            label6.setVisible(true);
            text4.setVisible(true);
            button2.setVisible(true);
        }
            /// LOGIC ///
        if(e.getSource() == button2){
            if(text1.getText() == "" || text2.getText() == "" || text3.getText() == ""){
                JOptionPane.showMessageDialog(null, "Dati mancanti", "Errore", JOptionPane.ERROR_MESSAGE);
            }else{
                int p = 10;
                if(radioFreq1.isSelected())
                    p = 1;
                else if(radioFreq2.isSelected())
                    p = 2;
                else if(radioFreq3.isSelected())
                    p = 3;
                Main.storeArgoment(addArgoment(text2.getText(),  text4.getText(), Integer.parseInt(text3.getText()), p), dataFilepath);

                text2.setText("");
                text3.setText("");
                text4.setText("");
            }
        }
        
        ///// RADIO BUTTON NUMBER 3 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if(e.getSource()==radio3){

            //setting other elements not visible
            text1.setVisible(false);
            label2.setVisible(false);
            button1.setVisible(false);
            label3.setVisible(false);
            label5.setVisible(false);
            text3.setVisible(false);
            label6.setVisible(false);
            text4.setVisible(false);
            button2.setVisible(false);
            radioFreq1.setVisible(false);
            radioFreq2.setVisible(false);
            radioFreq3.setVisible(false);
            label4.setVisible(false);
            text5.setVisible(false);

            //setting elements visible
            text2.setVisible(true);
            combo1.setVisible(true);
            label7.setVisible(true);
            button3.setVisible(true);            
        }

        /// LOGIC ///
        if(e.getSource() == button3){
            String s = text2.getText();
            System.out.println(s);
            if(s != ""){

                if(combo1.getSelectedIndex() == 0){

                    Main.deleteArgument(s, dataFilepath);
                    System.out.println("fatto 1");

                }else if(combo1.getSelectedIndex() == 1){

                    Main.deleteArgument(Integer.parseInt(s), dataFilepath);
                    System.out.println("fatto 2");
                }

                text2.setText("");
            }
        }
        
        ///// RADIO BUTTON NUMBER 4 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if(e.getSource()==radio4){

            //setting other elements not visible
            text1.setVisible(false);
            label2.setVisible(false);
            button1.setVisible(false);
            label3.setVisible(false);
            text2.setVisible(false);
            label4.setVisible(false);
            label5.setVisible(false);
            text3.setVisible(false);
            label6.setVisible(false);
            text4.setVisible(false);
            button2.setVisible(false);
            button3.setVisible(false);

            //setting elements visible
            text5.setVisible(true);
            combo1.setVisible(true);
            label7.setVisible(true);
            radioFreq1.setVisible(true);
            radioFreq2.setVisible(true);
            radioFreq3.setVisible(true);
            label4.setVisible(true);



        }

        ///// RADIO BUTTON NUMBER 5 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if(e.getSource()==radio5){

            //setting other elements not visible
            text1.setVisible(false);
            label2.setVisible(false);
            button1.setVisible(false);
            label3.setVisible(false);
            text2.setVisible(false);
            radioFreq1.setVisible(false);
            radioFreq2.setVisible(false);
            radioFreq3.setVisible(false);
            label4.setVisible(false);
            label5.setVisible(false);
            text3.setVisible(false);
            label6.setVisible(false);
            text4.setVisible(false);
            button2.setVisible(false);
            text5.setVisible(false);
            combo1.setVisible(false);
            label7.setVisible(false);
            radioFreq4.setVisible(false);
            radioFreq5.setVisible(false);
            radioFreq6.setVisible(false);
            button3.setVisible(false);



        }
    }

    @Override
    public Object getValue(String arg0) {
        return null;
    }

    @Override
    public void putValue(String arg0, Object arg1) {
        
    }

    public static void main(String[] args) {
        new GuiWindow();
    }


    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  

    public static void deleteFile(String path){

        // deletes previous calendar file if exixtsing
        File calendar = new File(path); 
        calendar.delete();

    }

    public static Argoment addArgoment(String argoment, String finalDate, int p, int f){
        Argoment a = new Argoment();

        a.setArgoment(argoment);

        a.setPriority(p);

        a.setFrequency(f);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fd = LocalDate.parse(finalDate, formatter);
        a.setFinalDate(fd);

        return a;
    }
}