package phoebe.gfx;

public class Render extends Thread {
	
	MainWindow mw;
	
	public Render(MainWindow mw){
		this.mw = mw;
	}

	@Override
	public void run() {
		try {
			this.sleep(33);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mw.vp.repaint();
	}

}
