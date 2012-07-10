package name.hash.test;

import name.hash.TwitterApiConectionService;
import name.hash.TwitterXmlParser;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TwitterXmlPaserTest {
	private TwitterXmlParser parser;

	@Before
	@Test
	public final void testParse() {
		parser = new TwitterXmlParser();
		boolean succses = parser.parse(TwitterApiConectionService.XML_OUTPUT);
		Assert.assertTrue(succses);
	}
	
	@After
	@Test
	public final void testShowTweet(){
		parser.showTweet();
	}
	
	

}
