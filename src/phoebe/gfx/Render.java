package phoebe.gfx;

public class Render extends Thread {
	
	Window mw;
	
	public Render(Window mw){
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
