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
		final boolean RENDER_TIME = true;
	    final long TARGET_FPS = 60;
	    final double OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	    long initialTime = System.nanoTime();
	    double deltaT = 0;
	    long timer = System.currentTimeMillis();
	    long frames = 0;
	    long currentTime;
	    ViewPanel vp = mw.getViewPanel();
	    
		while(!stopSignal){
			 currentTime = System.nanoTime();
			 deltaT += (currentTime - initialTime) / OPTIMAL_TIME;
			 initialTime = currentTime;
			 
			 // ha sok idő van hátra a következő renderelésig,
			 // akkor vár 0.9*renderközittidőt
			 if (deltaT <= 0.1){
			    try {
					Thread.sleep((long) (900/TARGET_FPS));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 // ha renderelni kell
			 } else if (deltaT >= 1) {
				 if (vp != null)
					//vp.paintImmediately(vp.getBounds()); // blokkol a repaint alatt, így mérhető a szükséges idő
					 vp.repaint();
		         frames++;
		         deltaT--;
		    }
			 
			// számolja a képkockákat, fpst
			if (RENDER_TIME) {
				if (System.currentTimeMillis() - timer > 1000) {
					System.out.println(String.format("FPS: %s", frames));
					frames = 0;
					timer += 1000;
				}
			}
		}
	}
	
	/**
	 * Megállítja a renderelést
	 */
	public void stopSignal(){
		stopSignal = true;
	}

}
