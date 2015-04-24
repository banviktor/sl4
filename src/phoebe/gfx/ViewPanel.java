package phoebe.gfx;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import phoebe.basic.Line;
import phoebe.basic.Vector;
import phoebe.game.Game;
import phoebe.game.GameController;
import phoebe.game.Map;
import phoebe.game.PlayerRobot;
import phoebe.game.Robot;

public class ViewPanel extends JPanel{
	private static final long serialVersionUID = -138224603482222475L;
	private GameController gc;
	
	public ViewPanel(GameController gc) {
		this.gc = gc;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawAll(g);
	}
	
	private void drawAll(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		drawMap(g2d);
		drawRobots(g2d);
	}
	
	private void drawMap(Graphics2D g) {
		Map map = gc.getMap();
		int lineWidth = transform(map.getLineWidth());
		List<Line> lines = map.getLines();
		g.setColor(Color.GRAY);
		for(Line l : lines) {
			Vector v1 = l.getVector1();
			Vector v2 = l.getVector2();
			int x1 = transform(v1.getX());
			int y1 = transform(v1.getY());
			int x2 = transform(v2.getX());
			int y2 = transform(v2.getY());
			g.fillOval(x1-lineWidth/2, y1-lineWidth/2, lineWidth, lineWidth);
			g.setStroke(new BasicStroke(lineWidth));
			g.drawLine(x1, y1, x2, y2);
			g.fillOval(x2-lineWidth/2, y2-lineWidth/2, lineWidth, lineWidth);
		}
	}
	
	private void drawRobots(Graphics2D g) {
		Game game = gc.getGame();
		List<PlayerRobot> robots = game.getPlayerRobots();
		for(PlayerRobot r : robots) {
			BufferedImage img = null;
			try {
                img = ImageIO.read(new File("sprites/robot_kek.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
			Image image = img.getScaledInstance(transform(0.45*2), transform(0.45*2), Image.SCALE_SMOOTH);
			if (image != null) {
	            int x = transform(r.getPosition().getX()-0.45);
	            int y = transform(r.getPosition().getY()-0.45);
	            g.drawImage(image, x, y, this);
	        }
		}
		
	}
	
	private int transform(double d) {
		return (int)( d / 10.0 * getWidth() );
	}
}
