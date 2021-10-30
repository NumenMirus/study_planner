import javax.swing.JButton;

public class Button extends JButton{

	Button(){
        this.setText("Lorem Ipsum");
        this.setBounds(0, 0, 50, 50);
        this.setFocusable(false);
        this.setVerticalAlignment(JButton.CENTER);
        this.setHorizontalAlignment(JButton.CENTER);
        this.setVisible(false);
    }

    Button(String s, int x, int y, int width, int height){
        this.setText(s);
        this.setBounds(x, y, width, height);
        this.setFocusable(false);
        this.setVerticalAlignment(JButton.CENTER);
        this.setHorizontalAlignment(JButton.CENTER);
        this.setVisible(false);
    }

    Button(String s, int x, int y, int width, int height, boolean visible){
        this.setText(s);
        this.setBounds(x, y, width, height);
        this.setFocusable(false);
        this.setVerticalAlignment(JButton.CENTER);
        this.setHorizontalAlignment(JButton.CENTER);
        this.setVisible(visible);
    }
}
