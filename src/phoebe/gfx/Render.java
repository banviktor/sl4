package phoebe.gfx;

public class Render extends Thread {
	
	private MainWindow mw;
	
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
	    
		while(true){
			 currentTime = System.nanoTime();
			 deltaT += (currentTime - initialTime) / OPTIMAL_TIME;
			 initialTime = currentTime;
			 
			 if (deltaT >= 1) {
				 if (mw.vp != null)
					 // blokkol a repaint alatt, így mérhető a szükséges idő
					mw.vp.paintImmediately(0, 0, mw.vp.getHeight(), mw.vp.getWidth());
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

}
