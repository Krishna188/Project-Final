/**
 * Created by Krishna on 10/19/2016.
 */

import java.util.Date;
import java.util.Random;

public class Exam {
	private int id;
	private Date date;
	private Date startTime;
	private Date endTime;
	private String room;
	private String instructor;
	private String course;
	private boolean isLab;

	// Constructors
	public Exam() {
		id = 0;
		date = null;
		startTime = null;
		endTime = null;
		room = "";
		instructor = "";
		course = "";
		isLab = true;
	}

	public Exam(int id, Date date, Date startTime, Date endTime,
			String room, String instructor, String course,
			boolean isLab) {
		this.id = id;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.room = room;
		this.instructor = instructor;
		this.course = course;
		this.isLab = isLab;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public boolean isLab() {
		return isLab;
	}

	public void setLab(boolean isLab) {
		this.isLab = isLab;
	}

	// Methods
	// Generates Exam ID
	public int generateId() {
		/*
		Random rand = new Random();
		int id = rand.nextInt(9999);
		return id;
		*/
		return 0;
	}
}
