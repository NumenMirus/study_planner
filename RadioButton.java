import java.awt.Font;

import javax.swing.JRadioButton;

public class RadioButton extends JRadioButton{
    RadioButton(){
        this.setFont(new Font("Arial", Font.ITALIC, 18));
        this.setText("Lorem Ipsum");
        this.setBounds(0, 0, 20, 20);
        this.setFocusable(false);
        this.setVisible(true);
    }

    RadioButton(String s, int x, int y, int width, int height){
        this.setFont(new Font("Arial", Font.ITALIC, 18));
        this.setText(s);
        this.setBounds(x, y, width, height);
        this.setFocusable(false);
        this.setVisible(true);
    }

    RadioButton(String s, int x, int y, int width, int height, boolean visible){
        this.setFont(new Font("Arial", Font.ITALIC, 18));
        this.setText(s);
        this.setBounds(x, y, width, height);
        this.setFocusable(false);
        this.setVisible(visible);
    }

}
