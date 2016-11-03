package Classes;

public enum Query {
	// a sql query to to validate username and password combinations
	AUTHENTICATE("SELECT * FROM USER WHERE USERNAME='%s' AND PASSWORD='%s'"),

	// a sql query to select all admin/students/teachers by username
	SELECT_ALL_FROM("SELECT * FROM %s WHERE USERNAME='%s'"),

	// a sql query to select exams which was already scheduled
	SELECT_SCHEDULED_EXAMS(
			"SELECT DISTINCT * FROM EXAM_BOOKINGS INNER JOIN USER_COURSES ON EXAM_BOOKINGS.COURSE_CODE = USER_COURSES.COURSE_CODE "
					+ "WHERE USER_COURSES.USERNAME='%s' ORDER BY EXAM_DATE"),

	// a sql query to get course code all courses being taught or taken by
	// teachers or students respectively
	GET_COURSES("SELECT COURSE_CODE FROM USER_COURSES WHERE USERNAME='%s'"), // X

	// a sql query to get all rooms in organization
	GET_ROOMS("SELECT * FROM ROOMS"), // X

	// a sql query to count users
	SELECT_NUMBER_OF_USERS("SELECT COUNT(*) AS TOTAL FROM USER"),

	// a sql query to select all courses
	SELECT_ALL_COURSES_AVAILABLE("SELECT * FROM COURSES"),

	// a sql query to get all details of teacher
	SELECT_INFO_OF_TEACHERS("SELECT * FROM TEACHER"),

	// a sql query to get all details of students
	SELECT_INFO_OF_STUDENTS("SELECT * FROM STUDENT"),

	// a sql query to add new user
	ADD_USER("INSERT INTO USER (USERNAME, PASSWORD, ROLE) VALUES ('%s', '%s', '%s');"),

	// a sql query to edit password and username
	EDIT_USER_PASSWORD("UPDATE USER SET PASSWORD='%s' WHERE USERNAME='%s'"),

	// a sql query to update first name , last name and user name
	EDIT_USER_NAME("UPDATE '%s' SET FIRSTNAME='%s', LASTNAME='%s'WHERE USERNAME='%s' "),

	// a sql query to update role
	EDIT_ROLE("UPDATE USER SET ROLE='%s' WHERE USERNAME='%s'"),

	// a sql query to insert users into user table
	INSERT_INTO_USER("INSERT INTO USER VALUES ('%s', '%s', '%s')"), // ACCEPTS USERNAME, PASS, ROLE

	// a sql query to insert user's name to role specified table
	INSERT_INTO_ROLE("INSERT INTO %s VALUES ('%s', '%s', '%s')"),

	GET_ROLE("SELECT ROLE FROM USER WHERE USERNAME= '%s'"),

	MODIFY_USER("UPDATE %s SET FIRSTNAME='%s',LASTNAME='%s' WHERE USERNAME='%s'"), CHANGE_PASSWORD(
			"UPDATE USER SET PASSWORD= '%s' WHERE USERNAME='%s'"), 
	
	DELETE_USER("DELETE FROM USER WHERE USERNAME = '%s'"),
	
	GET_SCHEDULED_EXAM("SELECT * FROM EXAM_BOOKINGS WHERE ROOM_NO='%s' AND EXAM_DATE='%s' ORDER BY END_TIME"),

	SCHEDULE_EXAM("INSERT INTO EXAM_BOOKINGS VALUES  ('%s','%s','%s','%s','%s')"),
	
	ADD_ROOM("INSERT INTO ROOMS VALUES ('%s','%s')"),
	
	DELETE_ROOM("DELETE FROM ROOMS WHERE ROOM_NO='%s'"),
	EDIT_ROOM("UPDATE ROOMS SET TYPE='%s' WHERE ROOM_NO='%s'")

	;

	private final String command;

	// constructor to set command
	private Query(String command) {
		this.command = command;
	}

	@Override
	public String toString() {
		return command;
	}

}