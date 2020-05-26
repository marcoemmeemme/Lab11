package it.polito.tdp.rivers.model;
import java.util.*;
import it.polito.tdp.rivers.db.RiversDAO;

public class Model 
{
	private RiversDAO dao=new RiversDAO();
	
	public List<River> getRivers()
	{
		return this.dao.getAllRivers();
	}
	
	public void setFlows(River river)
	{
		this.dao.setFlows(river);
	}
}
