/**
 * 
 * Expression Tree
 *   
 *   @author Alex Lacey
 *   @version 20171103
 *   
 */

import java.util.Stack;
import osu.cse2123.TreeNode;

public class ExpressionTree {

	public static TreeNode<String> buildTreeFromString(String expr) {
		Stack<TreeNode<String>> exprStack = new Stack<TreeNode<String>>();
		String[] exprArr = expr.split("\\s+");
		for (int i = 0; i< exprArr.length; i++) {
			TreeNode<String> node = new TreeNode<String>(exprArr[i]);
			if (node.getData().equals("+") || node.getData().equals("-") || node.getData().equals("*") || node.getData().equals("/") || node.getData().equals("%")) {
				if (exprStack.size() < 2) return null;
				TreeNode<String> popA = exprStack.pop();
				TreeNode<String> popB = exprStack.pop();
				node.setRightChild(popA);
				node.setLeftChild(popB);
				exprStack.push(node);
			}
			else exprStack.push(node);
		}
		if (exprStack.size() == 1) return exprStack.pop();
		else return null;
	}
	
	public static TreeNode<String> buildPrefixTreeFromString(String expr) { // are the changes from the PostFix tree correctly implemented? NO!
		Stack<TreeNode<String>> exprStack = new Stack<TreeNode<String>>();
		String[] exprArr = expr.split("\\s+");
		for (int i = exprArr.length - 1; i >= 0; i--) {
			TreeNode<String> node = new TreeNode<String>(exprArr[i]); // is this correct (particularly the end of the line)?
			if (node.getData().equals("+") || node.getData().equals("-") || node.getData().equals("*") || node.getData().equals("/") || node.getData().equals("%")) {
				if (exprStack.size() < 2) return null;
				TreeNode<String> popA = exprStack.pop();
				TreeNode<String> popB = exprStack.pop();
				node.setLeftChild(popA);
				node.setRightChild(popB);
				exprStack.push(node);
			}
			else exprStack.push(node);
		}
		if (exprStack.size() == 1) return exprStack.pop();
		else return null;
	}
	
	public static String toPostfixString(TreeNode<String> expr) {
		String output = "";
		if (expr.getLeftChild() != null) output += toPostfixString(expr.getLeftChild()) + " ";
		if (expr.getRightChild() != null) output += toPostfixString(expr.getRightChild()) + " ";
		output += expr.getData().toString() + " ";
		output = output.substring(0, output.length() - 1);
		return output;
	}
	
	public static String toInfixString(TreeNode<String> expr) {
		String output = "";
		if (expr.getLeftChild()!= null) output += "(" + toInfixString(expr.getLeftChild()) + " ";
		output += expr.getData().toString();		
		if (expr.getRightChild()!= null) output += " " + toInfixString(expr.getRightChild()) + ")";
		return output;
	}
	
	public static String toPrefixString(TreeNode<String> expr) {
		String output = expr.getData().toString();
		if (expr.getLeftChild() != null) output += " " + toPrefixString(expr.getLeftChild());
		if (expr.getRightChild() != null) output += " " + toPrefixString(expr.getRightChild());
		return output;
	}

	public static int evaluate(TreeNode<String> expr) {
		if (expr.getData().equals("+") || expr.getData().equals("-") || expr.getData().equals("*") || expr.getData().equals("/") || expr.getData().equals("%")) {
			if (expr.getData().equals("+")) return evaluate(expr.getLeftChild()) + evaluate(expr.getRightChild());
			else if (expr.getData().equals("-")) return evaluate(expr.getLeftChild()) - evaluate(expr.getRightChild());
			else if (expr.getData().equals("*")) return evaluate(expr.getLeftChild()) * evaluate(expr.getRightChild());
			else if (expr.getData().equals("/")) return evaluate(expr.getLeftChild()) / evaluate(expr.getRightChild());
			else return evaluate(expr.getLeftChild()) % evaluate(expr.getRightChild());
		}
		else return Integer.parseInt(expr.getData());
	}
}