package name.hash.test;

import junit.framework.Assert;
import name.hash.TwitterApiConectionService;

import org.junit.Ignore;
import org.junit.Test;
@Ignore
public class ConectionTest extends TwitterApiConectionService {
	@Test
	public void testConnect() {
		TwitterApiConectionService connecter = new TwitterApiConectionService();
		Assert.assertEquals(connecter.connect(), true);
	}
}
