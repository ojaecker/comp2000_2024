import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;

//PART 2

public class Main extends JFrame{
	public static void main(String[] args) throws Exception {
		Main window = new Main();
		window.run();
	}

	class Canvas extends JPanel  implements MouseMotionListener {
		Grid grid = new Grid();
		List<Point> mousePos = new ArrayList<>();

		public Canvas() {
			setPreferredSize(new Dimension(720, 720));
			addMouseMotionListener(this);
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g); // clear the previous drawings
			grid.paint(g, getMousePosition()); 
			
			/*Point currentMousePos = getMousePosition();
            if (currentMousePos != null) {
                if (mousePos.size() > 99) {
                    mousePos.remove(0); // remove the oldest position
                }
                mousePos.add(new Point(currentMousePos));
            }*/
      //if(!mousePos.isEmpty()){
			//	mousePos.remove(0);
			//}
			
			for (int i = 0; i < mousePos.size(); i++) {
				Point p = mousePos.get(i);
				g.setColor(new Color(128, 128, 128, 100));
				g.fillOval(p.x - 10, p.y - 10, 20, 20);
			}
            
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if(e.getPoint() != null){
				mousePos.add(e.getPoint());
        if (mousePos.size() > 99) {
                    mousePos.remove(0); // remove the oldest position
        }
			}
			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			mouseMoved(e); //dragging is not movement?
		}
	}

	private Main() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Canvas canvas = new Canvas();
		this.setContentPane(canvas);
		this.pack();
		this.setVisible(true);
	}

	public void run() {
		while (true) {
			repaint();
		}
	}
}
