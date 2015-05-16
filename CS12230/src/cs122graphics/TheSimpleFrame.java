package cs122graphics;
import java.awt.*;
import javax.swing.*;
import java.io.*;
//DO NOT CHANGE THIS ODE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class TheSimpleFrame extends JFrame {
 
	private static final long serialVersionUID = -5289524063713141421L;
	ThePanel canvas;

    /**
     * The constructor creates a Frame ready to display images
     */
    public TheSimpleFrame ()     {
        super("Art Show");
        setLayout(new BorderLayout());
        
        canvas = new ThePanel();
        setSize(700,650);
        this.add(canvas, BorderLayout.CENTER);

    //    setVisible(true);   // Display the window
    }  

    /**
     * displays current picture 
     * @param   pix   a filename to be displayed 
    */
    public void display(String pix) {        
        setVisible(true);
        canvas.display(pix);
    }

    ///////////////////////////////////////////////////
    //internal class to display
    class ThePanel extends JPanel{
 
		private static final long serialVersionUID = 6862317917898856696L;
		private String pix;
        private Image image;

        ThePanel() {
            super();
            setBackground(Color.pink);
        }

        void display(String c) {
            pix=c;
            repaint();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int x=20;
            int y=50;
            String filename="images/"+pix;
            File file = new File(".", filename);
            if (file.isFile() )               {
                image = Toolkit.getDefaultToolkit().getImage(filename);  
                g.drawImage(image,x,y,500,500,this);
            }
            else{
                filename="images/default.jpg";
                image = Toolkit.getDefaultToolkit().getImage(filename);  
                g.drawImage(image,x,y,500,500,this);
            }

        }

    }

}          