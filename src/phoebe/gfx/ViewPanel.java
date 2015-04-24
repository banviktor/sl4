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
import phoebe.game.CleanerRobot;
import phoebe.game.GameController;
import phoebe.game.Map;
import phoebe.game.PlayerRobot;

public class ViewPanel extends JPanel{
	private static final long serialVersionUID = -138224603482222475L;
	private GameController gc;
	private Image[] robotSprites;
	private Image cleanerRobotSprite;
	private Image[] smudgesSprites;
	private Image flaresSprite;
	
	public ViewPanel(GameController gc) {
		this.gc = gc;
		loadSprites();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawAll(g);
	}
	
	/**
	 * Felrajzol mindent a felületre
	 * @param g felület
	 */
	private void drawAll(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		drawMap(g2d);
		drawSmudges(g2d);
		drawRobots(g2d);
	}
	
	/**
	 * Felrajzolja a pályát a felületre
	 * @param g felület
	 */
	private void drawMap(Graphics2D g) {
		Map map = gc.getMap();
		int lineWidth = transform(map.getLineWidth());
		List<Line> lines = map.getLines();
		g.setColor(Color.GRAY);
		g.setStroke(new BasicStroke(lineWidth));
		for(Line l : lines) {
			Vector v1 = l.getVector1();
			Vector v2 = l.getVector2();
			int x1 = transform(v1.getX());
			int y1 = transform(v1.getY());
			int x2 = transform(v2.getX());
			int y2 = transform(v2.getY());
			g.fillOval(x1-lineWidth/2, y1-lineWidth/2, lineWidth, lineWidth);
			g.drawLine(x1, y1, x2, y2);
			g.fillOval(x2-lineWidth/2, y2-lineWidth/2, lineWidth, lineWidth);
		}
	}
	
	/**
	 * Felrajzolja a robotokat a felületre
	 * @param g felület
	 */
	private void drawRobots(Graphics2D g) {
		// TODO: flares
		for(PlayerRobot r : gc.getGame().getPlayerRobots()) {
			Image image = robotSprites[r.getColor().toInt()];
			int diameter = transform(r.getRadius()*2);
			if (image != null) {
				//Átméretezés
				BufferedImage resized = resize( image, diameter );
				
				//Bal felső sarok
				int x = transform(r.getPosition().getX()-r.getRadius());
				int y = transform(r.getPosition().getY()-r.getRadius());
				
				//Valós középpont
				int rotateX = transform(r.getPosition().getX());
				int rotateY = transform(r.getPosition().getY());
				
				//A forgatás szöge
				double angle = Math.atan2(-r.getSpeedVector().getX(), r.getSpeedVector().getY());
				
				//Rajzolás
				g.rotate(angle, rotateX, rotateY);
				g.drawImage(resized, x, y, this);
				g.rotate(-angle, rotateX, rotateY);
			}
		}
		for(CleanerRobot r : gc.getGame().getCleanerRobots()){
			Image image = cleanerRobotSprite;
			int diameter = transform(r.getRadius()*2);
			if (image != null) {
				//Átméretezés
				BufferedImage resized = resize( image, diameter );
				
				//Bal felső sarok
				int x = transform(r.getPosition().getX()-r.getRadius());
				int y = transform(r.getPosition().getY()-r.getRadius());
				
				//Rajzolás
				g.drawImage(resized, x, y, this);
			}
		}
		g.drawString(Double.toString(Math.random()*30), 30, 30); // DEBUG
	}
	
	/**
	 * Felrajzolja a foltokat a felületre
	 * @param g felület
	 */
	private void drawSmudges(Graphics2D g) {
		// TODO
	}
	
	/**
	 * A pályakoordinátákat(0.0-10.0) képernyőkoordinátákká (0-600) alakítja
	 * @param d pályakoordináta
	 * @return képernyőkoordináta
	 */
	private int transform(double d) {
		return (int)( d / Map.size * 600 );
	}
	
	/**
	 * A játék elején betölti a memóriába a robot-képeket
	 */
	private void loadSprites() {
		robotSprites = new BufferedImage[5];
		smudgesSprites = new BufferedImage[2];
		String[] filenames = new String[5];
		filenames[0] = new String("sprites/robot_piros.png");
		filenames[1] = new String("sprites/robot_sarga.png");
		filenames[2] = new String("sprites/robot_zold.png");
		filenames[3] = new String("sprites/robot_kek.png");
		filenames[4] = new String("sprites/robot_lila.png");
		for(int i=0;i<5;++i) {
			try {
                robotSprites[i] = ImageIO.read(new File(filenames[i]));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
		}
		try {
			smudgesSprites[0] = ImageIO.read(new File("sprites/oil.png"));
			smudgesSprites[1] = ImageIO.read(new File("sprites/glue.png"));
			cleanerRobotSprite = ImageIO.read(new File("sprites/kisrobot.png"));
			flaresSprite = ImageIO.read(new File("sprites/flares.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Átméretez egy képet
	 * @param img bementi kép
	 * @param newW új szélesség
	 * @param newH új magasság
	 * @return átméretezett kép
	 */
	private BufferedImage resize(Image img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
	
	/**
	 * Átméretez egy négyzet alakú képet
	 * @param img
	 * @param diameter
	 * @return
	 */
	private BufferedImage resize(Image img, int side) { 
		return resize(img, side, side);
	}
}
