package com.LBG.problem;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ProblemApplicationTests {

	private  List<Meeting> meetings;
	private  MeetingCreation mc;
	private  Map<LocalDate, List<Meeting>> calender;
	private static final DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	@Before
	public void loadListOfMeetings() {
		mc=new MeetingCreation();
		meetings=new ArrayList<Meeting>();
		calender = new HashMap<>();
	}
	@Test
	public void testPositive() throws FileNotFoundException, ParseException {
		System.out.println("TEst started.");
		mc=new MeetingCreation();
		Map<LocalDate, List<Meeting>> expectedMap=mc.create(new File("src/test/java/input.txt"));
		
		assertNotNull(expectedMap);
		
		List<Meeting> meetings=expectedMap.get(LocalDate.parse("2022-05-02", formatter3));
		assertEquals(meetings.size(), 1);
		assertTrue(meetings.get(0).getName().equals("EMP009"));
	}
	
	@Test
	public void testPastDated() throws FileNotFoundException, ParseException {
		Map<LocalDate, List<Meeting>> expectedMap=mc.create(new File("src/test/java/pastDateTest.txt"));
		assertEquals(expectedMap.size(), 0);
	}
	
	@Test
	public void testOverLapped() throws FileNotFoundException, ParseException {
		Map<LocalDate, List<Meeting>> map=mc.create(new File("src/test/java/overLappedMeetings.txt"));
		assertEquals(map.size(), 1);
	}
	
}
