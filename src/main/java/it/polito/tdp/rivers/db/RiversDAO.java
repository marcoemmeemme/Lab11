package it.polito.tdp.rivers.db;

import java.util.LinkedList;
import java.util.List;
import it.polito.tdp.rivers.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class RiversDAO {

	public List<River> getAllRivers() {
		
		final String sql = 
		"SELECT r.id, r.name, AVG(f.flow) as media\n" + 
		"FROM flow AS f, river AS r\n" + 
		"WHERE f.river=r.id\n" + 
		"GROUP BY f.river ";
		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				River r=new River(res.getInt("r.id"), res.getString("r.name"));
				r.setFlowAvg(res.getDouble("media"));
				rivers.add(r);
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	public void setFlows(River river)
	{
		final String sql = "SELECT f.day, f.flow, f.river\n" + 
				"FROM flow f\n" + 
				"WHERE f.river=?\n" + 
				"order BY f.day";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river.getId());
			ResultSet res = st.executeQuery();
			while (res.next()) 
			{
				Flow f=new Flow(res.getDate("f.day").toLocalDate(), res.getFloat("f.flow"), river);
				river.getFlows().add(f);
			}
			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
	}
}
