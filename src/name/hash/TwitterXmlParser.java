package name.hash;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class TwitterXmlParser {
	private List<TweetModel> list = new ArrayList<>();

	public boolean parse(String filepath) {
		try {
			Document doc = new SAXBuilder().build(new File(filepath));
			Element root = doc.getRootElement();
			System.out.println(root.toString());
			for (Element element : root.getChildren()) {
				long id = Long.parseLong(element.getChild("id").getText());
				String text = element.getChild("text").getText();
				String date = element.getChild("created_at").getText();
				String name = element.getChild("user").getChild("name").getText();
				list.add(new TweetModel(id, name, text, date));
			}
		} catch (IOException | JDOMException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void showTweet(){
		for(TweetModel t : list){
			t.show();
		}
	}

}
