package Testing;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;


public class Junit_1 {
	   String str1 = new String ("abc");
	   String str2 = new String ("abc");
	   String str3 = null;
	   String str4 = "abc";
	   String str5 = "abc";
	    
	   @Test public void testAssertions1() {
	      assertEquals(str1, str2);}

	   @Test public void testAssertions4() {
	      assertNotNull(str1);}

	   @Test public void testAssertions5() {
	      assertNull(str3);}

	   @Test public void testAssertions6() {
	      assertSame(str4,str5);}

	   @Test public void testAssertions7() {
	      assertNotSame(str1,str3);}
      
}
      
