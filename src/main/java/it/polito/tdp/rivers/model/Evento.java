package it.polito.tdp.rivers.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
public class Evento implements Comparable <Evento> 
{
	private LocalDate data;
	private double flow;
	private double perc;
	public Evento(LocalDate localDate, double flow) {
		super();
		this.data = localDate;
		this.flow = flow;
		this.perc=Math.random();
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public double getFlow() {
		return flow;
	}
	public void setFlow(double flow) {
		this.flow = flow;
	}
	public double getPerc() {
		return perc;
	}
	
	@Override
	public String toString() {
		return "Evento: " + data + ", " + flow + ", "
				+ perc;
	}
	@Override
	public int compareTo(Evento o) {
		return this.data.compareTo(o.data);
	}
	
}
