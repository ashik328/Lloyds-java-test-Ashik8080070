package com.LBG.problem;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MeetingCreation {
	private static Meeting meeting;
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	private static final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
	private static final DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	static Map<LocalDate, List<Meeting>> calender = new HashMap<>();
	
	public Map<LocalDate, List<Meeting>> create(File file) throws FileNotFoundException, ParseException {
		//File file = new File("src/main/resources/input.txt");
		Scanner sc = new Scanner(file);
		while (sc.hasNext()) {
			String meetHrs = sc.nextLine();
			String stAndEndDt[] = meetHrs.split(" ");

			while (sc.hasNext()) {
				String notes = sc.nextLine();
				String meetingInfo[] = notes.split(" ");
				String meetTime = sc.nextLine();
				String meetArr[] = meetTime.split(" ");
				
				LocalDateTime meetSt = parseDate(meetArr[0] + " " + stAndEndDt[0], formatter2);
				LocalDateTime meetEnd = parseDate(meetArr[0] + " " + stAndEndDt[1], formatter2);
				
				meeting = endDate(meetArr[0], meetArr[1], meetArr[2], meetingInfo[2]);
				LocalDate mtStDt= meeting.getMtStartDate();
				LocalDateTime curMeetstDt=meeting.getStDt();
				LocalDateTime curMeetendDt=meeting.getEndDt();
				if(!(curMeetstDt.compareTo(LocalDateTime.now()) >=0 && curMeetstDt.compareTo(meetEnd) <=0)) {
					continue;
				}
				if(!(curMeetstDt.compareTo(meetSt) >=0 && curMeetstDt.compareTo(meetEnd) <=0)) {
					continue;
				}
				else if(!(curMeetendDt.compareTo(meetSt) >=0 && curMeetendDt.compareTo(meetEnd) <=0)) {
					continue;
				}
				else if(curMeetendDt.compareTo(curMeetstDt)==0) {
					continue;
				}
				if(calender.isEmpty()) {
					addMeeting(mtStDt);
				}
				else {
					commonValidation(mtStDt, curMeetstDt, curMeetendDt);
				}
			}
			//printMeeting();
		}
		return calender;
	}
		public Meeting endDate(String stDate, String endate, String hrs, String name) throws ParseException {
			LocalDateTime stDtTime = parseDate(stDate + " " + endate);
			long hours = Long.valueOf(hrs);
			LocalDateTime endDtTime = stDtTime.plusHours(Long.valueOf(hrs));
			LocalDate meetStDt=LocalDate.parse(stDate, formatter3);
			//LocalDateTime meetStDt = ld.atStartOfDay();
			return new Meeting.MeetingBuilder().stDt(stDtTime).endDt(endDtTime).hrs(hours).name(name).startTime(stDtTime)
					.endTime(endDtTime)
					.mtStartDate(meetStDt).build();
		}

		public void commonValidation(LocalDate meetingDate, LocalDateTime stDt, LocalDateTime endDt) {
			List<Meeting> meetList = calender.get(meetingDate);
			if (meetList == null) {
				addMeeting(meetingDate);
			} else {
				List<Meeting> newMeetingsForTheDate = new ArrayList<>();
				Iterator<Meeting> meetings = meetList.iterator();
				while (meetings.hasNext()) {
					Meeting meet = meetings.next();
					if (stDt.compareTo(meet.getStDt()) >= 0 && stDt.compareTo(meet.getEndDt()) < 0) {
						return;
					} else if (endDt.compareTo(meet.getStDt()) > 0 && endDt.compareTo(meet.getEndDt()) <= 0) {
						return;
					} else if (meet.getStDt().compareTo(stDt) >= 0 && meet.getEndDt().compareTo(endDt) <= 0) {
						return;
					}
				}

				newMeetingsForTheDate.add(meeting);
				addMeeting(meetingDate, newMeetingsForTheDate);
			}
		}

		public final LocalDateTime parseDate(String date) {
			return LocalDateTime.parse(date, formatter);
		}

		public final LocalDateTime parseDate(String date, DateTimeFormatter formatter) {
			return LocalDateTime.parse(date, formatter);
		}

		public void addMeeting(LocalDate meetingDate) {
			List<Meeting> meetingList = new ArrayList<>();
			meetingList.add(meeting);
			calender.put(meetingDate, meetingList);
		}

		public void addMeeting(LocalDate meetingDate, List<Meeting> newList) {
			List<Meeting> meetingList = calender.get(meetingDate);
			meetingList.addAll(newList);
			calender.put(meetingDate, meetingList);
		}

		public void printMeeting() {
			LinkedHashSet<?> ls=calender.keySet().stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
			ls.stream().forEach(e-> {
				System.out.println(e);
				List<Meeting> sortedList=calender.get(e).stream().sorted(Comparator.comparing(Meeting::getStartTime))
				.collect(Collectors.toList());
				for(Meeting emp:sortedList) {
			System.out.println(emp);
				}
		});
	}
	}
