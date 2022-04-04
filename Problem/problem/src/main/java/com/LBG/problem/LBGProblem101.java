package com.LBG.problem;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LBGProblem101 {
	
	// private static final TemporalAmount month="2;

	public static void main(String[] args) throws ParseException, FileNotFoundException {
		MeetingCreation mc=new MeetingCreation();
		Map<LocalDate, List<Meeting>> map=mc.create(new File("src/main/resources/input.txt"));
		mc.printMeeting();
}
}