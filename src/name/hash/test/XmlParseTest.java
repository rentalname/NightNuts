package name.hash.test;

import junit.framework.Assert;
import name.hash.TwitterApiConectionService;
import name.hash.TwitterXmlParser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class XmlParseTest {
	TwitterApiConectionService connecter;
	private TwitterXmlParser parser;

	@Before
	public void testConnect() {
		connecter = new TwitterApiConectionService();
		Assert.assertEquals(connecter.connect(), true);
	}

	@Test
	public final void testParse() {
		parser = new TwitterXmlParser();
		boolean succses = parser.parse(TwitterApiConectionService.XML_OUTPUT);
		Assert.assertTrue(succses);
	}

	@After
	public final void testShowTweet() {
		parser.showTweet();
	}
}
