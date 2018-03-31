package view;

	/**
	 * Displays the output in the console
	 * could be inline, 
	 * @author Xavier.Lamourec
	 *
	 */
public class View {
	
	/**
	 * displays the output with a line break
	 * @param str
	 */

	public void displayLineBreak(String output) {
		System.out.println(output);
	}
	
	/**
	 * displays the output inline
	 * @param str
	 */
	public void displayInline(String output) {
		System.out.print(output);
	}

	/**
	 * displays the error
	 * @param error
	 */
	public void displayError(String error) {
		System.err.println(error);
	}
}
