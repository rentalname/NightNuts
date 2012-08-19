package name.hash.regressiontest;

import name.hash.model.TweetModel;
import name.hash.model.TwitterListModel;

import org.junit.Assert;
import org.junit.Test;

public class TweetModelAdded4TwitterList {
	TwitterListModel listModel = new TwitterListModel(new String[] { "arser", "brite", "carla" });

	@Test
	public final void TweetModelAddTtest() {
		int preSize = listModel.getSize();
		listModel.addTweetModel(new TweetModel(0, "daisy", "female", "120202"));
		Assert.assertEquals(listModel.getSize(), preSize + 1);
	}

}
