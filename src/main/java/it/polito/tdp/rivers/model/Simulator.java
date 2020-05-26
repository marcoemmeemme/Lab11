package it.polito.tdp.rivers.model;

import java.util.*;
import java.util.PriorityQueue;

public class Simulator 
{
		// CODA DEGLI EVENTI
		private PriorityQueue <Evento> queue=new PriorityQueue<>();
		// PARAMETRI DI SIMULAZIONI
		private River river;
		private Integer k;
		private final double SOGLIA=0.95;
		// VALORI DA CALCOLARE 
		private int giorniNonErogati;
		private float bacinoMedio;
		
		// METODI PER IMPOSTARE I PARAMETRI
		public void setRiver(River river) {
			this.river = river;
		}
		public void setK(Integer k) {
			this.k = k;
		}
		// METODI PER RESTITUIRE I RISULTATI
		public int getGiorniNonErogati() {
			return giorniNonErogati;
		}
		public float getBacinoMedio() {
			return bacinoMedio;
		}
		//SIMULAZIONE VERA E PROPRIA
		public void run()
		{
			List<Flow> flows=river.getFlows();
			double Q=k*river.getFlowAvg()*(3600*24*30);
			double C=Q/2;
			this.giorniNonErogati=0;
			this.bacinoMedio=(float) 0.0;
			this.queue.clear();
			for(Flow f: flows)
			{
				Evento e=new Evento(f.getDay(),f.getFlow());
				if(e.getPerc()>=this.SOGLIA)
					e.setFlow((f.getFlow()-8*river.getFlowAvg())*(3600*24));
				else
					e.setFlow((f.getFlow()-0.8*river.getFlowAvg())*(3600*24));
				this.queue.add(e);
			}
			int contatore=queue.size();
			while(!this.queue.isEmpty()) 
			{
				Evento e = this.queue.poll();
				System.out.println(e);
				C+=e.getFlow();
				if(C>Q)
				{	
					e.setFlow(e.getFlow()+(C-Q));
					C=Q;
				}
				if(C<0)
				{
					C=0;
					this.giorniNonErogati++;
				}
				bacinoMedio+=C;
				System.out.println(C);
			}
			this.bacinoMedio/=contatore;
		
	}

}
