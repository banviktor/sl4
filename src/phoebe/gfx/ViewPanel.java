package phoebe.gfx;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import phoebe.basic.Line;
import phoebe.basic.Vector;
import phoebe.game.CleanerRobot;
import phoebe.game.GameController;
import phoebe.game.Map;
import phoebe.game.PlayerRobot;
import phoebe.game.Robot;
import phoebe.game.Smudge;

public class ViewPanel extends JPanel{
	private static final long serialVersionUID = -138224603482222475L;
	private final GameController gc;
	private final HashMap<String, BufferedImage> sprites;
	private final HashMap<Integer, BufferedImage> cachedSprites;
	
	public ViewPanel(GameController gc) {
		this.gc = gc;
		sprites = new HashMap<String, BufferedImage>();
		cachedSprites = new HashMap<Integer, BufferedImage>();
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
		
		if(gc.isRunning()){
			drawMap(g2d);
			drawSmudges(g2d);
			drawRobots(g2d);
		}else{
			PlayerRobot winnerCopy = new PlayerRobot(gc.getWinner().getColor(), new Vector(Map.size / 2, Map.size / 2));
			drawRobot(g2d, winnerCopy, new Image[] {sprites.get("robot" + winnerCopy.getColor().toInt()), sprites.get("flares")}, new double[] {0, 0});
			g2d.setColor(Color.BLACK);
			g2d.setFont(g2d.getFont().deriveFont(30.0f).deriveFont(Font.BOLD));			
			g2d.drawString("A nyertes", 225, 250);
		}
		
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
		//Játékosrobotok kirajzolása
		for(PlayerRobot r : gc.getGame().getPlayerRobots()) {
			Image image = sprites.get("robot" + r.getColor().toInt());
			if (image != null) {				
				//A forgatás szöge
				double angle = Math.atan2(-r.getSpeedVector().getX(), r.getSpeedVector().getY());
				
				//Rajzolás
				drawRobot(g, r, new Image[] {image, sprites.get("flares")}, new double[] {angle, 0});
			}
		}
		
		//Takarítórobotok kirajzolása
		for(CleanerRobot r : gc.getGame().getCleanerRobots()){
			drawRobot(g, r, new Image[] {sprites.get("cleaner")}, new double[] {0});
		}
	}
	
	/**
	 * Felrajzol egy robotot a felületre
	 * @param g felület
	 * @param r robot
	 * @param sprites sprite-ok
	 * @param angles az egyes spriteok forgatásának szögei
	 */
	private void drawRobot(Graphics2D g, Robot r, Image[] sprites, double[] angles){
		//Átméretezés
		List<BufferedImage> resized = new ArrayList<BufferedImage>();
		for(Image sprite : sprites){
			resized.add(resize(sprite, transform(r.getRadius()*2)));
		}
		
		//Bal felső sarok
		int x = transform(r.getPosition().getX()-r.getRadius());
		int y = transform(r.getPosition().getY()-r.getRadius());
		
		//Valós középpont
		int rotateX = transform(r.getPosition().getX());
		int rotateY = transform(r.getPosition().getY());
		
		//Rajzolás		
		for(int i = 0; i < resized.size(); ++i){
			g.rotate(angles[i], rotateX, rotateY);
			g.drawImage(resized.get(i), x, y, this);
			g.rotate(-angles[i], rotateX, rotateY);
		}	
	}
	
	/**
	 * Felrajzolja a foltokat a felületre
	 * @param g felület
	 */
	private void drawSmudges(Graphics2D g) {
		for(Smudge s : gc.getMap().getSmudges()){
			Image image;
			int diameter = transform(s.getRadius()*2);
			if(s.getClass().getName() == "phoebe.game.Oil"){
				image = sprites.get("oil");
			}else{
				image = sprites.get("glue");
			}
			if (image != null) {
				//Átméretezés
				BufferedImage resized = resize( image, diameter );
				
				//Bal felső sarok
				int x = transform(s.getPosition().getX()-s.getRadius());
				int y = transform(s.getPosition().getY()-s.getRadius());
				
				//Rajzolás
				g.drawImage(resized, x, y, this);
			}
		}
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
	 * A játék elején betölti a memóriába a játékban használt képeket
	 */
	private void loadSprites() {
		String[] filenames = new String[5];
		filenames[0] = new String("sprites/robot_piros.png");
		filenames[1] = new String("sprites/robot_sarga.png");
		filenames[2] = new String("sprites/robot_zold.png");
		filenames[3] = new String("sprites/robot_kek.png");
		filenames[4] = new String("sprites/robot_lila.png");
		for(int i=0;i<5;++i) {
			try {
                sprites.put( "robot" + i, ImageIO.read(new File(filenames[i])) );
            } catch (IOException ex) {
                ex.printStackTrace();
            }
		}
		try {
			sprites.put("oil", ImageIO.read(new File("sprites/oil.png")) );
			sprites.put("glue", ImageIO.read(new File("sprites/glue.png")) );
			sprites.put("cleaner", ImageIO.read(new File("sprites/kisrobot.png")) );
			sprites.put("flares", ImageIO.read(new File("sprites/flares.png")) );
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
		BufferedImage cachedImage = cachedSprites.get(img.hashCode());
		if (cachedImage == null ) {
			cachedImage = resize(img, side, side); // ha még nincs cachelve tároljuk
			cachedSprites.put(img.hashCode(), cachedImage);
		} else {
			if (cachedImage.getWidth() != side) {
				return resize(img, side, side); // ha másik méretű van cachelve, nem tároljuk
			}
		}
		return cachedImage; // ha cachelve van, visszaadjuk
	}
}
