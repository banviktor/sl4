package phoebe.gfx;

public class Render extends Thread {
	
	private MainWindow mw;
	private boolean stopSignal = false;
	
	public Render(MainWindow mw){
		this.mw = mw;
		this.setDaemon(true);
	}

	@Override
	public void run() {
		boolean RENDER_TIME = true;
		long initialTime = System.nanoTime();
	    final int TARGET_FPS = 60;
	    final double OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	    double deltaT = 0;
	    long timer = System.currentTimeMillis();
	    int frames = 0;
	    long currentTime;
	    ViewPanel vp = mw.getViewPanel();
	    
		while(!stopSignal){
			 currentTime = System.nanoTime();
			 deltaT += (currentTime - initialTime) / OPTIMAL_TIME;
			 initialTime = currentTime;
			 
			 if (deltaT >= 1) {
				 if (vp != null)
					 // blokkol a repaint alatt, így mérhető a szükséges idő
					vp.paintImmediately(0, 0, vp.getHeight(), vp.getWidth());
		         frames++;
		         deltaT--;
		    }
		    
			 if (System.currentTimeMillis() - timer > 1000) {
		            if (RENDER_TIME) {
		                System.out.println(String.format("FPS: %s", frames));
		            }
		            frames = 0;
		            timer += 1000;
		        }
		}
	}
	
	public void stopSignal(){
		stopSignal = true;
	}

}
