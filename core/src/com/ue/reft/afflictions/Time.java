package com.ue.reft.afflictions;

public class Time {
	
	
	public int count;
	public TimeSegment segment;
	
	public enum TimeSegment{
		round, second, minute, hour, day, week, month, year;
	}
	
	public Time(int count, TimeSegment t) {
		this.count = count;
		this.segment = t;
	}
	
	
	
}
