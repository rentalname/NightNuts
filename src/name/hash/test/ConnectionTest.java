package name.hash.test;

import junit.framework.Assert;
import name.hash.TwitterApiConectionService;

import org.junit.Before;
import org.junit.Test;

public class ConnectionTest {
	TwitterApiConectionService connecter;
	@Before
	@Test
	public void testConnect() {
		connecter = new TwitterApiConectionService();
		Assert.assertEquals(connecter.connect(), true);
	}

}
