package it.polito.tdp.rivers.db;

import it.polito.tdp.rivers.model.River;

public class TestRiversDAO {

	public static void main(String[] args) {
		RiversDAO dao = new RiversDAO();
		River r=dao.getAllRivers().get(0);
		dao.setFlows(r);
		System.out.println(r.getFlows().get(0).getDay().toString());
	}

}
