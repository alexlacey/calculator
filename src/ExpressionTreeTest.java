import static org.junit.Assert.*;
import org.junit.Test;
import osu.cse2123.TreeNode;

/* I wasn't exactly sure about what the "test plan" files meant, and I don't know how to insert txt files into a zip folder, so I added the plan in annotations to this document directly. */

public class ExpressionTreeTest {
	
	// These are the 10 strings used as input for the trees later. 5 are postfix and 5 are prefix. Each group (postfix and prefix) have one string for each number of operands (1 through 5).
	String aString = "7";
	String bString = "5 14 -";
	String cString = "5 9 10 - *";
	String dString = "4 11 15 - * 20 +";
	String eString = "10 7 1 * * 5 + 2 -";
	String fString = "4";
	String gString = "+ 5 15";
	String hString = "- * 5 10 15";
	String iString = "- * 5 10 + 15 7";
	String jString = "* * + + 3 2 5 7 9";
	
	// building the postfix trees (validated in first test)
	TreeNode<String> a = ExpressionTree.buildTreeFromString(aString);
	TreeNode<String> b = ExpressionTree.buildTreeFromString(bString);
	TreeNode<String> c = ExpressionTree.buildTreeFromString(cString);
	TreeNode<String> d = ExpressionTree.buildTreeFromString(dString);
	TreeNode<String> e = ExpressionTree.buildTreeFromString(eString);
	
	// building the prefix trees (validated in first test)
	TreeNode<String> f = ExpressionTree.buildPrefixTreeFromString(fString);
	TreeNode<String> g = ExpressionTree.buildPrefixTreeFromString(gString);
	TreeNode<String> h = ExpressionTree.buildPrefixTreeFromString(hString);
	TreeNode<String> i = ExpressionTree.buildPrefixTreeFromString(iString);
	TreeNode<String> j = ExpressionTree.buildPrefixTreeFromString(jString);
	
	@Test
	// Test Plan: ensure each one of these is not null
	public void testBuildTreeFromString() {
		
		// postfix trees
		assertNotNull(a);
		assertNotNull(b);
		assertNotNull(c);
		assertNotNull(d);
		assertNotNull(e);
		
		// prefix trees
		assertNotNull(f);
		assertNotNull(g);
		assertNotNull(h);
		assertNotNull(i);
		assertNotNull(j);
	}
	
	@Test
	// Test plan: ensure that each of the trees translates correctly back into the postfix string
	public void testToPostfixString() {
		assertEquals(aString, ExpressionTree.toPostfixString(a));
		assertEquals(bString, ExpressionTree.toPostfixString(b));
		assertEquals(cString, ExpressionTree.toPostfixString(c));
		assertEquals(dString, ExpressionTree.toPostfixString(d));
		assertEquals(eString, ExpressionTree.toPostfixString(e));
	}
	
	@Test
	// Test plan: manually translate all five of the original postfix strings into infix notation, and then ensure the infix function on the trees equals the manually-generated strings
	public void testToInfixString() {
		String kString = "7";
		String lString = "(5 - 14)";
		String mString = "(5 * (9 - 10))";
		String nString = "((4 * (11 - 15)) + 20)";
		String oString = "(((10 * (7 * 1)) + 5) - 2)";
		assertEquals(kString, ExpressionTree.toInfixString(a));
		assertEquals(lString, ExpressionTree.toInfixString(b));
		assertEquals(mString, ExpressionTree.toInfixString(c));
		assertEquals(nString, ExpressionTree.toInfixString(d));
		assertEquals(oString, ExpressionTree.toInfixString(e));
	}
	
	@Test
	// Test plan: ensure that each of the trees prefix trees translates correctly back into the prefix string
	public void testToPrefixString() {
		assertEquals(fString, ExpressionTree.toPrefixString(f));
		assertEquals(gString, ExpressionTree.toPrefixString(g));
		assertEquals(hString, ExpressionTree.toPrefixString(h));
		assertEquals(iString, ExpressionTree.toPrefixString(i));
		assertEquals(jString, ExpressionTree.toPrefixString(j));
	}
	
	@Test
	// Test plan: confirm that the tree generated from the first five strings is able to be evaluated correctly, using the solution calculated manually
	public void testEvaluate() {
		assertEquals(7, ExpressionTree.evaluate(a));
		assertEquals(-9, ExpressionTree.evaluate(b));
		assertEquals(-5, ExpressionTree.evaluate(c));
		assertEquals(4, ExpressionTree.evaluate(d));
		assertEquals(73, ExpressionTree.evaluate(e));
	}

}