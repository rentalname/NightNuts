package name.hash;

/**
 * api.twitter.comからタイムラインの情報を取得する際のクエリーを作成する
 * 
 * @author User
 */
public class QueryStringBuilder {
	private long id = -1;
	private String name;
	// タイムラインのNページ目を取得する.0以上の整数
	private int page = -1;
	// 一度に取得するツイートの数.1~200までの整数
	private int count = 10;

	private QueryStringBuilder(String name) {
		this.name = name;
	}

	/*
	 * フィールドの情報に基づいて,クエリー文字列を発行する
	 * screen_nameもしくはuser_idのいずれかが設定さている必要がある
	 */
	public String buildQuery() {
		StringBuilder builder = new StringBuilder("?");
		boolean isNameQueryEnabled = false;
		if (name != null) {
			builder.append("screen_name=");
			builder.append(name);
			isNameQueryEnabled = true;
		}
		if (id > -1) {
			if (isNameQueryEnabled) {
				builder.append('&');
			}
			builder.append("user_id=");
			builder.append(id);
		}
		if (page > -1) {
			builder.append('&');
			builder.append("page=");
			builder.append(page);
		}
		if (count > 0) {
			builder.append('&');
			builder.append("count=");
			builder.append(count);
		}
		return builder.toString();
	}

	/*
	 * ビルダーのインスタンスを生成するファクトリーメソッド
	 */
	public static QueryStringBuilder getBuilder(String name) {
		return new QueryStringBuilder(name);
	}

	public void queryPage(int page) {
		this.page = page;
	}

	public void queryName(String name) {
		this.name = name;
	}

	public void queryId(long id) {
		this.id = id;
	}

	public void queryCount(int count) {
		this.count = count;
	}
}
