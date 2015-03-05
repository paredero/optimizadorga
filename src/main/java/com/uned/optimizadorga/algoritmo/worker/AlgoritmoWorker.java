package com.uned.optimizadorga.algoritmo.worker;

import java.util.List;

import javax.swing.SwingWorker;

import com.uned.optimizadorga.algoritmo.Algoritmo;
import com.uned.optimizadorga.gui.ProgressDialog;

public class AlgoritmoWorker extends SwingWorker<Long, Integer> {

	Algoritmo algoritmo;
	Thread threadAlgoritmo;
	private ProgressDialog progressDialog;
	
	@Override
	protected Long doInBackground() throws Exception {
		long startTime = System.currentTimeMillis();
		threadAlgoritmo = new Thread(algoritmo);
		threadAlgoritmo.start();
		double totalGeneraciones = algoritmo.getMaxGens();
		double progreso = 0;
		while (progreso <100 && !this.isCancelled()) {
			try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("interrumpido");
            }
			double generacionActual = algoritmo.getGeneracionActual();
			progreso = (generacionActual / totalGeneraciones) * 100;
			publish((int)(progreso));
		}
		if (this.isCancelled()) {
			threadAlgoritmo.interrupt();
		}
		long endTime = System.currentTimeMillis();
		long totalTime = (endTime - startTime)/1000;
		return totalTime;
	}

	


	/* (non-Javadoc)
	 * @see javax.swing.SwingWorker#process(java.util.List)
	 */
	@Override
	protected void process(List<Integer> chunks) {
		this.progressDialog.getProgressBar().setValue(chunks.get(0));
		this.progressDialog.getProgressBar().setString(chunks.get(0)+"%");
	}
	
	




	/* (non-Javadoc)
	 * @see javax.swing.SwingWorker#done()
	 */
	@Override
	protected void done() {
		this.progressDialog.setVisible(false);
	}




	/**
	 * @return the algoritmo
	 */
	public Algoritmo getAlgoritmo() {
		return algoritmo;
	}

	/**
	 * @param algoritmo the algoritmo to set
	 */
	public void setAlgoritmo(Algoritmo algoritmo) {
		this.algoritmo = algoritmo;
	}




	public void setProgressDialog(ProgressDialog progressDialog) {
		this.progressDialog = progressDialog;
	}




	

}
