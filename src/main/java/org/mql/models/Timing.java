package org.mql.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "timing")
public class Timing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "tim_id")
	private int id;

	@Column(name = "dayname")
	private String dayname;

	@Column(name = "hour")
	private int hour;

	@Column(name = "minute")
	private int minute;

	@Column(name = "duration")
	private int duration;

	public Timing() {
		// TODO Auto-generated constructor stub
	}

	public Timing(String dayname, int hour, int minute, int duration) {
		super();
		this.dayname = dayname;
		this.hour = hour;
		this.minute = minute;
		this.duration = duration;
	}

	public String getDayname() {
		return dayname;
	}

	public int getDuration() {
		return duration;
	}

	public int getHour() {
		return hour;
	}

	public int getId() {
		return id;
	}

	public int getMinute() {
		return minute;
	}

	public void setDayname(String dayname) {
		this.dayname = dayname;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	@Override
	public String toString() {
		return "day : " + dayname + " hour : " + hour;
	}

}
