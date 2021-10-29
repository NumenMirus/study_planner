import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Font;

public class Label extends JLabel{

    Label(){
        Border border1 = BorderFactory.createLineBorder(Color.BLACK, 3, true);
        this.setText("Lorem Ipsum");
        this.setBorder(border1);
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.CENTER);
        this.setForeground(Color.BLACK);
        this.setVerticalAlignment(JLabel.NORTH);
        this.setHorizontalAlignment(JLabel.CENTER);
        setVisible(false);
    }

    Label(String s){
        Border border1 = BorderFactory.createLineBorder(Color.BLACK, 3, true);
        this.setText(s);
        this.setBorder(border1);
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.CENTER);
        this.setForeground(Color.BLACK);
        this.setVerticalAlignment(JLabel.NORTH);
        this.setHorizontalAlignment(JLabel.CENTER);
        setVisible(false);
    }

    Label(String s, int x, int y, int width, int height){
        this.setText(s);
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.CENTER);
        this.setForeground(Color.BLACK);
        this.setVerticalAlignment(JLabel.NORTH);
        this.setHorizontalAlignment(JLabel.CENTER);
        setVisible(false);
    }

    Label(String s, int x, int y, int width, int height, boolean title){
        if(title){
            Border border1 = BorderFactory.createLineBorder(Color.BLACK, 3, true);
            this.setBorder(border1);
        }
        this.setText(s);
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.CENTER);
        this.setForeground(Color.BLACK);
        this.setVerticalAlignment(JLabel.NORTH);
        this.setHorizontalAlignment(JLabel.CENTER);
        setVisible(false);
    }

    Label(String s, int x, int y, int width, int height, boolean title, boolean visible){
        if(title){
            Border border1 = BorderFactory.createLineBorder(Color.BLACK, 3, true);
            this.setBorder(border1);
        }
        setVisible(false);
        this.setText(s);
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.CENTER);
        this.setForeground(Color.BLACK);
        this.setVerticalAlignment(JLabel.NORTH);
        this.setHorizontalAlignment(JLabel.CENTER);
        if(visible){
            setVisible(true);
        }
    }
}
