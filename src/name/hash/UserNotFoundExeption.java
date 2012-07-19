package name.hash;

/**
 * 受信したファイルのヘッダーで404NotFoundエラー を検出した
 * 
 * @author HAｓｈ
 */
public class UserNotFoundExeption extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundExeption(String message) {
		super(message);
	}
}
