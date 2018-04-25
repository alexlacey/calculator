/**
 * 
 * Project 6
 *   
 *   @author Alex Lacey
 *   @version 20171103
 *   
 */

import osu.cse2123.TreeNode;
import java.util.*;

public class Project06 {
	
	private static boolean postfixInput = true; // the user input format (postfix by default)

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String inputString = "";
		System.out.println("No expression is in memory");
		while (true) {
			displayMainMenu();
			String input = in.nextLine();
			if (input.equals("E") || input.equals("e")) inputString = getExpression(in);
			else if (input.equals("S") || input.equals("s")) getDisplayPreference(inputString);
			else if (input.equals("C") || input.equals("c")) getInputFormatPreference(in);
			else if (input.equals("Q") || input.equals("q")) break;
			else System.out.println("ERROR! You must enter one of [E], [S] or [Q]!\n");
		}
		System.out.print("\nGoodbye!");
	}
	
	private static void displayMainMenu() {
		System.out.println();
		System.out.println("Enter your choice: ");
		System.out.println("[S]et the display format");
		System.out.println("[E]nter a new expression");
		System.out.println("[C]hoose an input format");
		System.out.println("[Q]uit");
		System.out.print("> ");
	}
	
	private static void displayDisplayMenu() {
		System.out.println();
		System.out.println("Enter your preferred output display:");
		System.out.println("[P]ostfix");
		System.out.println("[I]nfix");
		System.out.println("p[R]efix");
		System.out.print("> ");
	}
	
	private static void displayInputFormatMenu() {
		System.out.println();
		System.out.println("Enter your preferred input format:");
		System.out.println("[P]ostfix");
		System.out.println("p[R]efix");
		System.out.print("> ");
	}
	
	private static void displayFormat(String inputString, String formatSelection) {
		if (formatSelection.equals("P") || formatSelection.equals("p")) {
			String postString = ExpressionTree.toPostfixString(ExpressionTree.buildTreeFromString(inputString));
			int answer = ExpressionTree.evaluate(ExpressionTree.buildTreeFromString(inputString));
			System.out.print(postString + " = " +  answer);
			System.out.println();
		} else if (formatSelection.equals("I") || formatSelection.equals("i")) {
			String inString = ExpressionTree.toInfixString(ExpressionTree.buildTreeFromString(inputString));
			int answer = ExpressionTree.evaluate(ExpressionTree.buildTreeFromString(inputString));
			System.out.print(inString + " = " +  answer);
			System.out.println();
		} else if (formatSelection.equals("R") || formatSelection.equals("r")) {
			String preString = ExpressionTree.toPrefixString(ExpressionTree.buildTreeFromString(inputString));
			int answer = ExpressionTree.evaluate(ExpressionTree.buildTreeFromString(inputString));
			System.out.print(preString + " = " +  answer);
			System.out.println();
		} else {
			System.out.print("ERROR!  You must enter one of [P], [I] or [R]!");
			System.out.println();
			getDisplayPreference(inputString);
		}
	}
	
	private static String getExpression(Scanner in) {
		if (postfixInput == true) {
			System.out.println();
			System.out.print("Enter your expression in postfix notation: ");
			String inputString = in.nextLine();
			TreeNode<String> root = ExpressionTree.buildTreeFromString(inputString);
			if (root != null) {
				System.out.println(ExpressionTree.toPostfixString(root) + "= " +  ExpressionTree.evaluate(root));
			}
			else {
				System.out.println();
				System.out.println("ERROR! Expression not in postfix notation!");
				getExpression(in);
			}
			return inputString;
		} else {
			System.out.println();
			System.out.print("Enter your expression in prefix notation: ");
			String inputString = in.nextLine();
			TreeNode<String> root = ExpressionTree.buildPrefixTreeFromString(inputString);
			if (root != null) {
				System.out.println(ExpressionTree.toPrefixString(root) + "= " +  ExpressionTree.evaluate(root));
			}
			else {
				System.out.println();
				System.out.println("ERROR! Expression not in prefix notation!");
				getExpression(in);
			}
			return inputString;
		}
	}
	
	private static void getDisplayPreference(String inputString) {
		Scanner input = new Scanner(System.in);
		displayDisplayMenu();
		String formatSelection = input.nextLine();
		displayFormat(inputString, formatSelection);
	}
	
	private static void getInputFormatPreference(Scanner in) {
		displayInputFormatMenu();
		String formatSelection = in.nextLine();
		if (formatSelection.equals("P") || formatSelection.equals("p")) {
			postfixInput = true;
		} else if (formatSelection.equals("R") || formatSelection.equals("r")) {
			postfixInput = false;
		} else {
			System.out.println("ERROR! Not a valid input format selection.");
			getInputFormatPreference(in);
		}
	}
}