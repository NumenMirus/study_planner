import java.awt.Font;
import java.awt.event.ActionEvent;
//import java.awt.GridLayout;
import java.util.ArrayList;

import javax.lang.model.util.ElementScanner14;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GuiWindow extends JFrame implements Action{

    final String dataFilepath = "data.csv";
    final String calendarFilepath = "calendar.csv";


    //GLOBAL DECLARATIONS FOR BUTTONS
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;

    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio3;
    RadioButton radio4;
    RadioButton radio5;
    RadioButton radioFreq1;
    RadioButton radioFreq2;
    RadioButton radioFreq3;

    //GLOBAL DECLARATIONS FOR BOTTONGROUPS
    ButtonGroup radioGroupFreq1;


    //GLOBAL DECLARATIONS FOR TEXTFIELDS
    JTextField text1;
    JTextField text2;
    JTextField text3;
    JTextField text4;
    JTextField text5;
    JTextField text6;

    //GLOBAL DECLARATIONS FOR LABELS
    Label label2;
    Label label3;
    Label label4;
    Label label5;
    Label label6;
    Label label7;
    Label label8;
    Label label9;
    Label label10;
    Label labelDone;
    Label labelError;

    //GLOBAL DECLARATIONS FOR COMBOBOX
    JComboBox<String> combo1;

    //GLOBAL DECLARATIONS FOR CHECKBOXES
    JCheckBox checkbox1;

    GuiWindow(){
        this.setTitle("Study Planner");
        this.setSize(500, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        
        
        //IMAGES AND ICONS

        ImageIcon icon = new ImageIcon("./icons/studying.png");
        this.setIconImage(icon.getImage());

        ImageIcon done = new ImageIcon("./icons/done.png");

        ImageIcon error = new ImageIcon("./icons/x.png");


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

        label8 = new Label("Stampa calendario dei prossimi ", 50, 380, 400, 30);
        this.add(label8);

        label9 = new Label("Attiva notifiche", 150, 470, 200, 30);
        this.add(label9);

        labelDone = new Label(" DONE!", 200, 665, 100, 31);
        labelDone.setFont(new Font("Arial", Font.PLAIN, 18));
        labelDone.setIcon(done);
        labelDone.setVerticalTextPosition(JLabel.CENTER);
        labelDone.setHorizontalTextPosition(JLabel.RIGHT);
        this.add(labelDone);

        labelError = new Label("error", 150, 405, 200, 30);
        this.add(labelError);

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

        button4 = new Button("Modifica", 50, 700, 400, 50, false);
        button4.addActionListener(this);
        this.add(button4);

        button5 = new Button("Componi", 50, 550, 400, 50, false);
        button5.addActionListener(this);
        this.add(button5);


        radio1 = new RadioButton("Stampa ripassi di oggi", 20, 60, 450, 20);
        radio1.addActionListener(this);
        this.add(radio1);

        radio2 = new RadioButton("Aggiungi argomento", 20, 90, 450, 20);
        radio2.addActionListener(this);
        this.add(radio2);

        radio3 = new RadioButton("Elimina Argomento", 20, 120, 450, 20);
        radio3.addActionListener(this);
        this.add(radio3);

        radio4 = new RadioButton("Modifica impostazioni di un argomento", 20, 150, 450, 20);
        radio4.addActionListener(this);
        this.add(radio4);

        radio5 = new RadioButton("Stampa calendario", 20, 180, 400, 20);
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

        radioGroupFreq1 = new ButtonGroup();
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

        text6 = new JTextField();
        text6.setBounds(225, 420, 50, 30);
        text6.setHorizontalAlignment(JTextField.CENTER);
        text6.setVisible(false);
        this.add(text6);

        //COMBOBOX

        String[] choice = {" Nome", " ID"};
        combo1 = new JComboBox<>(choice);
        combo1.setFont(new Font("Arial", Font.ITALIC, 18));
        combo1.setBounds(330, 260, 100, 30);
        combo1.addActionListener(this);
        combo1.setVisible(false);
        this.add(combo1);

        //CHECKBOX

        checkbox1 = new JCheckBox(error, false);
        checkbox1.setDisabledIcon(done);
        checkbox1.setText("Notifiche");
        checkbox1.setBounds(175, 220, 150, 25);
        checkbox1.setFont(new Font("Arial", Font.ITALIC, 18));
        checkbox1.addActionListener(this);
        checkbox1.setFocusable(false);
        checkbox1.setVisible(true);
        this.add(checkbox1);


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
            button4.setVisible(false);
            button3.setVisible(false);
            label8.setVisible(false);
            label9.setVisible(false);
            text6.setVisible(false);
            button5.setVisible(false);
            labelDone.setVisible(false);

            

            //setting elements visible
            text1.setVisible(true);
            label2.setVisible(true);
            button1.setVisible(true);
        }
        
        /// LOGIC ///
        if(e.getSource() == button1){
            labelDone.setVisible(false);

            //stampa ripassi di oggi
            if(text1.getText().trim().isEmpty())
                JOptionPane.showMessageDialog(null, "Path mancante!", "Error", JOptionPane.ERROR_MESSAGE);
            else{   
                // create calendar
                Main.createCalendar(calendarFilepath, dataFilepath);

                // find today's date ids from calendar and converts them in Argoment names to display
                ArrayList<Integer> ids = Main.getCalendarArgomentsIds(LocalDate.now(), calendarFilepath);

                // converts ids in Argoment name to display
                ArrayList<String> argNames = Main.idToArgumentName(ids, dataFilepath);

                Main.printToFile(text1.getText()+"calendar.txt", argNames, "Oggi hai da fare:"); 

                text1.setText("");
                labelDone.setVisible(true);
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
            button4.setVisible(false);
            label8.setVisible(false);
            label9.setVisible(false);
            text6.setVisible(false);
            button5.setVisible(false);
            labelDone.setVisible(false);


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
            labelDone.setVisible(false);
            if(text2.getText().trim().isEmpty() || text3.getText().trim().isEmpty() || text4.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "Dati mancanti", "Errore", JOptionPane.ERROR_MESSAGE);
            }else{
                int p = -1;
                if(radioFreq1.isSelected())
                    p = 1;
                else if(radioFreq2.isSelected())
                    p = 2;
                else if(radioFreq3.isSelected())
                    p = 3;
                Main.storeArgoment(addArgoment(text2.getText(),  text4.getText(), p, Integer.parseInt(text3.getText())), dataFilepath);
                Main.createCalendar(calendarFilepath, dataFilepath);
                text2.setText("");
                text3.setText("");
                text4.setText("");

                radioGroupFreq1.clearSelection();
                labelDone.setVisible(true);
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
            button4.setVisible(false);
            label8.setVisible(false);
            label9.setVisible(false);
            text6.setVisible(false);
            button5.setVisible(false);
            labelDone.setVisible(false);


            //setting elements visible
            text2.setVisible(true);
            combo1.setVisible(true);
            label7.setVisible(true);
            button3.setVisible(true);            
        }

        /// LOGIC ///
        if(e.getSource() == button3){
            labelDone.setVisible(false);
            
            if(!text2.getText().trim().isEmpty()){

                String s = text2.getText();
                if(combo1.getSelectedIndex() == 0){
                    
                    Main.deleteArgument(s, dataFilepath);

                }else if(combo1.getSelectedIndex() == 1){

                    Main.deleteArgument(Integer.parseInt(s), dataFilepath);
                }

                text2.setText("");
                labelDone.setVisible(true);
            }
        }
        
        ///// RADIO BUTTON NUMBER 4 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if(e.getSource()==radio4){

            //setting other elements not visible
            text1.setVisible(false);
            label2.setVisible(false);
            button1.setVisible(false);
            label3.setVisible(false);
            button3.setVisible(false);
            button2.setVisible(false);
            label8.setVisible(false);
            label9.setVisible(false);
            text6.setVisible(false);
            button5.setVisible(false);
            labelDone.setVisible(false);


            //setting elements visible
            text5.setVisible(true);
            combo1.setVisible(true);
            label7.setVisible(true);
            radioFreq1.setVisible(true);
            radioFreq2.setVisible(true);
            radioFreq3.setVisible(true);
            label4.setVisible(true);
            label5.setVisible(true);
            text3.setVisible(true);
            label6.setVisible(true);
            text4.setVisible(true);
            button4.setVisible(true);
        }

        ///// LOGIC /////
        if(e.getSource() == button4){
            labelDone.setVisible(false);
            if(combo1.getSelectedIndex() == 0){
                labelDone.setVisible(false);
                //set priority
                int p = -1;
                if(radioFreq1.isSelected())
                    p = 1;
                else if(radioFreq2.isSelected())
                    p = 2;
                else if(radioFreq3.isSelected())
                    p = 3;

                //set date
                String date = "";
                if(text4.getText().trim().isEmpty())
                    date = "null";
                else
                    date = text4.getText();

                //set frequency
                String f = "";
                int freq;
                if(text3.getText().trim().isEmpty())
                    freq = -1;
                else{    
                    f = text3.getText();
                    freq = Integer.parseInt(f);
                }

                Main.modifyArgument(dataFilepath, text2.getText(), date, p, freq);

                radioGroupFreq1.clearSelection();
                
                text3.setText("");
                text4.setText("");
                labelDone.setVisible(true);
                
                
            }else if(combo1.getSelectedIndex() == 1){
                labelDone.setVisible(false);
                int p = -1;
                if(radioFreq1.isSelected())
                    p = 1;
                else if(radioFreq2.isSelected())
                    p = 2;
                else if(radioFreq3.isSelected())
                    p = 3;
                
                //set date
                String date = "";
                if(text4.getText().trim().isEmpty())
                    date = "null";
                else
                    date = text4.getText();

                //set frequency
                String f = "";
                int freq;
                if(text3.getText().trim().isEmpty())
                    freq = -1;
                else{    
                    f = text3.getText();
                    freq = Integer.parseInt(f);
                }
                Main.modifyArgument(dataFilepath, Integer.parseInt(text2.getText()), date, p, freq);

                radioGroupFreq1.clearSelection();
                
                text3.setText("");
                text4.setText("");
                labelDone.setVisible(true);
                
            }

            
        }

        ///// RADIO BUTTON NUMBER 5 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if(e.getSource()==radio5){

            //setting other elements not visible
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
            button3.setVisible(false);
            button4.setVisible(false);
            labelDone.setVisible(false);

            //setting elements visible
            text1.setVisible(true);
            label2.setVisible(true);
            label8.setVisible(true);
            label9.setVisible(true);
            text6.setVisible(true);
            button5.setVisible(true);
        }

        //// LOGIC /////
        if(e.getSource() == button5){
            labelDone.setVisible(false);

            if(text1.getText().trim().isEmpty())
                JOptionPane.showMessageDialog(null, "Path mancante!", "Errore", JOptionPane.ERROR_MESSAGE);
            else if(text6.getText().trim().isEmpty())
                JOptionPane.showMessageDialog(null, "Lasso temporale mancante!", "Errore", JOptionPane.ERROR_MESSAGE);
            else{
                String savePath = text1.getText();
                
                //crea calendario
                Main.createCalendar(calendarFilepath, dataFilepath);
                //genera .txt file di tutti gli argomenti per una settimana
                LocalDate now = LocalDate.now();

                //elimina il file calendario.txt precedente 
                Main.deleteFile("calendar.txt");

                int dd = Integer.parseInt(text6.getText());

                //stampa nel nuovo calendario
                for(int i = 0; i < dd; i++){
                    Main.printCalendarArgoments(now.plusDays(i), calendarFilepath, dataFilepath, savePath);
                }

                labelDone.setVisible(true);
            }
        }

        if(e.getSource() == checkbox1){
            String command;
            String requirements = "pip install -r requirements";
            if(System.getProperty("os.name").compareTo("Windows") == 0)
                command = "pythonw notify.py";
            else
                command = "python3 notify.py &";

            // String requirements = "pip install -r requirements.txt > log.txt";
            try {
                Process proc = Runtime.getRuntime().exec(command);
                checkbox1.setEnabled(false);
                
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "Errore attivazione notifiche", "Errore", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        }
    }

    @Override
    public Object getValue(String arg0) {
        return null;
    }

    @Override
    public void putValue(String arg0, Object arg1) {
        
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