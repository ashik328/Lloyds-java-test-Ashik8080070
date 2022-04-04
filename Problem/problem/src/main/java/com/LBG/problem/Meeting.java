package com.LBG.problem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Meeting {

	private LocalDateTime stDt;
	private LocalDateTime endDt;
	private long hrs;
	private String name;
	private String startTime;
	private String endTime;
	private LocalDate mtStartDate;
	private static final DateTimeFormatter formatter1=DateTimeFormatter.ofPattern("HH:mm");
	
	public Meeting(MeetingBuilder meetBuilder) {
		super();
		this.stDt = meetBuilder.stDt;
		this.endDt = meetBuilder.endDt;
		this.hrs = meetBuilder.hrs;
		this.name = meetBuilder.name;
		this.startTime = meetBuilder.startTime;
		this.endTime = meetBuilder.endTime;
		this.mtStartDate=meetBuilder.mtStartDate;
	}

	public LocalDateTime getStDt() {
		return stDt;
	}

	public void setStDt(LocalDateTime stDt) {
		this.stDt = stDt;
	}

	public LocalDateTime getEndDt() {
		return endDt;
	}

	public void setEndDt(LocalDateTime endDt) {
		this.endDt = endDt;
	}

	public long getHrs() {
		return hrs;
	}

	public void setHrs(long hrs) {
		this.hrs = hrs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public static DateTimeFormatter getFormatter1() {
		return formatter1;
	}
	
	public LocalDate getMtStartDate() {
		return mtStartDate;
	}

	public void setMtStartDate(LocalDate mtStartDate) {
		this.mtStartDate = mtStartDate;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.mtStartDate.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this==obj)
			return true;
		if(obj==null || obj.getClass()!=this.getClass())
			return false;
		Meeting meet = (Meeting) obj;
		
		return (meet.mtStartDate==this.mtStartDate && meet.name.equals(this.name));
	}



	public static final class MeetingBuilder {
		private LocalDateTime stDt;
		private LocalDateTime endDt;
		private long hrs;
		private String name;
		private String startTime;
		private String endTime;
		private LocalDate mtStartDate;
		
		public MeetingBuilder() {
			
		}
		
		public MeetingBuilder stDt(final LocalDateTime stDt) {
			this.stDt=stDt;
			return this;
		}
		public MeetingBuilder endDt(final LocalDateTime endDt) {
			this.endDt=endDt;
			return this;
		}
		public MeetingBuilder hrs(final long hrs) {
			this.hrs=hrs;
			return this;
		}
		public MeetingBuilder name(final String name) {
			this.name=name;
			return this;
		}
		public MeetingBuilder startTime(final LocalDateTime startTime) {
			if(startTime ==null)
				throw new IllegalArgumentException("Meeting Start time is blank");
			this.startTime=startTime.format(formatter1);
			return this;
		}
		public MeetingBuilder endTime(final LocalDateTime endTime) {
			if(endTime ==null)
				throw new IllegalArgumentException("Meeting End time is blank");
			this.endTime=endTime.format(formatter1);
			return this;
		}
		
		public MeetingBuilder mtStartDate(final LocalDate mtStartDate) {
			this.mtStartDate=mtStartDate;
			return this;
		}
		
		public Meeting build() {
			validate();
			return new Meeting(this);
		}
		
		public void validate() {
			if(this.stDt == null || this.endDt==null || this.name==null || this.mtStartDate==null)
				throw new IllegalArgumentException("Mandatory fields are blank. Please check");
		}
	}
		@Override
		public String toString() {
			return this.startTime+" "+this.endTime+" "+this.name;
		}
	}

