/**
 * 
 */

/**
 * 
 * This Class generates the HTML display messages
 * @author Anirudh
 * @completed 2016-10-25
 * @revised 2016-10-25
 * 
 */
public class Display {
	/**
	 * This variable stores the format of the type of message
	 **/
	protected String format;
	/**
	 * This variable stores all possible type of messages that can exist.
	 **/
	public enum Type
	{
		SUCCESS,
		INFO,
		ERROR,
		WARNING
	}
	/**
	 * This Constructor constructs the object that allows you to create HTML for the type specified in the parameter.
	 * @param type
	 */
	public Display(Type type)
	{
		switch(type)
		{
		case ERROR:
			format = "<div class=\"alert alert-danger alert-dismissible animated fadeInUp\" role=\"alert\">"
					+ "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>"
					+ "<strong>%s</strong></div>"; 
			break;
		case INFO:
			format = "<div class=\"alert alert-info alert-dismissible animated fadeInUp\" role=\"alert\">"
					+ "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>"
					+ "<strong>%s</strong></div>"; 
			break;
		case SUCCESS:
			format = "<div class=\"alert alert-success alert-dismissible animated fadeInUp\" role=\"alert\">"
					+ "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>"
					+ "<strong>%s</strong></div>"; 
			break;
		case WARNING:
			format = "<div class=\"alert alert-warning alert-dismissible animated fadeInUp\" role=\"alert\">"
					+ "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>"
					+ "<strong>%s</strong></div>"; 
			break;
		default:
			break;
			
		}
	}
	
	/**
	 * A call to this method will result in getting the HTML format for the string you want to display using the type discribed in the constructor
	 * @param message
	 * @return
	 */
	public String getHtml(String message)
	{
		return String.format(this.format, message);
	}
	
	
	public static String toDisplayCase(String s) {

	    final String ACTIONABLE_DELIMITERS = " '-/"; // these cause the character following
	                                                 // to be capitalized

	    StringBuilder sb = new StringBuilder();
	    boolean capNext = true;

	    for (char c : s.toCharArray()) {
	        c = (capNext)
	                ? Character.toUpperCase(c)
	                : Character.toLowerCase(c);
	        sb.append(c);
	        capNext = (ACTIONABLE_DELIMITERS.indexOf((int) c) >= 0); // explicit cast not needed
	    }
	    return sb.toString();
	}
	
}
