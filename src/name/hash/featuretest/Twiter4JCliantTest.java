package name.hash.featuretest;

import name.hash.twitterprovider.Twiter4JCliant;

import org.junit.Test;

public class Twiter4JCliantTest {

	@Test
	public final void testGetUserTimeLine() {
		Twiter4JCliant cliant = Twiter4JCliant.cliantFactory();
		cliant.getUserTimeLine("jihou");
	}

	@Test
	public final void testGetHomeTimeLine() {
		Twiter4JCliant cliant = Twiter4JCliant.cliantFactory();
		cliant.getHomeTimeLine();
	}

	@Test
	public final void testGetNextHomeTimeLine() {
		Twiter4JCliant cliant = Twiter4JCliant.cliantFactory();
		cliant.getNextHomeTimeLine();
	}
}
