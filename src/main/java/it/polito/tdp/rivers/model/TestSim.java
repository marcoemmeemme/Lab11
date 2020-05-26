package it.polito.tdp.rivers.model;
public class TestSim {
	public static void main(String args[]) {
		Simulator s=new Simulator();
		Model m=new Model();
		River r=m.getRivers().get(0);
		m.setFlows(r);
		s.setRiver(r);
		s.setK(10);
		s.run();
		System.out.println("Num giorni senza erogazione "+s.getGiorniNonErogati());
		System.out.println("Bacino medio "+s.getBacinoMedio());
	}
}
