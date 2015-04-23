package phoebe.gfx;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import phoebe.basic.Line;
import phoebe.basic.Vector;
import phoebe.game.GameController;
import phoebe.game.Map;

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
	
	private int transform(double d) {
		return (int)( d / 10.0 * getWidth() );
	}
}
