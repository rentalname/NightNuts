package name.hash;

public class QueryStringBuilder {
	private long id = -1;
	private String name;
	private int page = -1;

	public QueryStringBuilder(String name) {
		this.name = name;
	}

	public QueryStringBuilder(long id, String name) {
		this(name);
		this.id = id;
	}

	public QueryStringBuilder(long id, String name, int page) {
		this(id, name);
		this.page = page;
	}

	//デフォルトコンストラクタの呼び出しを禁止する
	@SuppressWarnings("unused")
	private QueryStringBuilder() {
	}

	/*
	 * フィールドの情報に基づいて,クエリー文字列を発行する
	 */
	String buildQuery() {
		StringBuilder builder = new StringBuilder("?");
		if (id > -1) {
			builder.append("user_id=");
			builder.append(id);
		}
		if (name != null) {
			builder.append("screen_name=");
			builder.append(name);
		}
		if (page > -1) {
			builder.append("page=");
			builder.append(page);
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
}
