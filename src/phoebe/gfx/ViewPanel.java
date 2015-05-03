package phoebe.gfx;

import java.awt.AlphaComposite;
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
	private final MainWindow mw;
	private boolean ended = false;
	
	public ViewPanel(MainWindow mw) {
		this.mw = mw;
		this.gc = mw.getGameController();
		sprites = new HashMap<String, BufferedImage>();
		cachedSprites = new HashMap<Integer, BufferedImage>();
		loadSprites();
	}
	
	public void init(){
		ended = false;
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
			drawArrows(g2d);
			drawRobots(g2d);
		}else{			
			if(!ended){
				ended = true;
				mw.endGame();
			}
			
			PlayerRobot winnerCopy = new PlayerRobot(gc.getWinner().getColor(), new Vector(Map.size / 2, Map.size / 2));
			drawRobot(g2d, winnerCopy, new Image[] {sprites.get("robot" + winnerCopy.getColor().toInt()), sprites.get("flares")}, new double[] {0, 0});
			g2d.setColor(Color.BLACK);
			g2d.setFont(g2d.getFont().deriveFont(30.0f).deriveFont(Font.BOLD));			
			g2d.drawString("The winner is:", 200, 250);
		}
		
	}
	
	/**
	 * Felrajzolja a robotok inputvektorát, és sebességvektorát TODO Beta (colors, scheme don't match)
	 * @param g2d felület
	 */
	private void drawArrows(Graphics2D g2d){
		PlayerRobot r = gc.getRobotController().getActualRobot();
		g2d.setColor(new Color(47, 47, 47));
		g2d.setStroke(new BasicStroke(5));
		
		int x1 = transform(r.getPosition().getX());
		int y1 = transform(r.getPosition().getY());
		int y2 = y1 + transform(gc.getRobotController().getJumpDestination().length());
		
		double angle = Math.atan2(-gc.getRobotController().getJumpDestination().getX(),
				gc.getRobotController().getJumpDestination().getY());
		g2d.rotate(angle, x1, y1);
		int[] xPoints = {x1, x1-10, x1+10};
		int[] yPoints = {y2, y2-17, y2-17};
		g2d.drawLine(x1, y1, x1, y2 - 15);
		g2d.fillPolygon(xPoints, yPoints , 3);
		g2d.rotate(-angle, x1, y1);
		
		g2d.setColor(new Color(194, 194, 194));
		
		y2 = y1 + transform(1);
		
		angle = Math.atan2(-gc.getRobotController().getInputSpeedVector().getX(),
				gc.getRobotController().getInputSpeedVector().getY());
		g2d.rotate(angle, x1, y1);
		int[] xPoints2 = {x1, x1-10, x1+10};
		int[] yPoints2 = {y2, y2-17, y2-17};
		g2d.drawLine(x1, y1, x1, y2 - 15);
		g2d.fillPolygon(xPoints2, yPoints2 , 3);
		g2d.rotate(-angle, x1, y1);
	}
	
	/**
	 * Felrajzolja a pályát a felületre
	 * @param g felület
	 */
	private void drawMap(Graphics2D g) {
		g.drawImage(sprites.get("map"), 0, 0, this);
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
		// robotok betöltése
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
		// többi kép betöltése
		try {
			sprites.put("oil", ImageIO.read(new File("sprites/oil.png")) );
			sprites.put("glue", ImageIO.read(new File("sprites/glue.png")) );
			sprites.put("cleaner", ImageIO.read(new File("sprites/kisrobot.png")) );
			sprites.put("flares", ImageIO.read(new File("sprites/flares.png")) );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// map betöltése
		int lineWidth = transform(gc.getMap().getLineWidth());
		List<Line> lines = gc.getMap().getLines();
		
		// map képek betöltése
		BufferedImage mapImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = mapImage.createGraphics();
		BufferedImage mapRoad = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
		BufferedImage mapSky = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
		BufferedImage tmpImg;
		try {
			tmpImg = ImageIO.read(new File("sprites/map_road.png"));
			mapRoad.getGraphics().drawImage(tmpImg, 0, 0, this);
			tmpImg = ImageIO.read(new File("sprites/map_sky.png"));
			mapSky.getGraphics().drawImage(tmpImg, 0, 0, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// map képek összevágása
		g.drawImage(mapRoad, 0, 0, this);
		Graphics2D gSky = mapSky.createGraphics();
		gSky.setStroke(new BasicStroke(lineWidth));
		gSky.setComposite(AlphaComposite.Src);
		gSky.setColor( new Color(0, 0, 0, 0) ); // transparent ink
		for(Line l : lines) {
			Vector v1 = l.getVector1();
			Vector v2 = l.getVector2();
			int x1 = transform(v1.getX());
			int y1 = transform(v1.getY());
			int x2 = transform(v2.getX());
			int y2 = transform(v2.getY());
			gSky.fillOval(x1-lineWidth/2, y1-lineWidth/2, lineWidth, lineWidth);
			gSky.drawLine(x1, y1, x2, y2);
			gSky.fillOval(x2-lineWidth/2, y2-lineWidth/2, lineWidth, lineWidth);
		}
		g.drawImage(mapSky, 0, 0, this);
		sprites.put("map", mapImage);
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
