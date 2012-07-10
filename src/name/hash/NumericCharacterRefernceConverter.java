package name.hash;

public class NumericCharacterRefernceConverter {

	public static String convert(String str) {
		StringBuilder builder = new StringBuilder();
		String[] split = str.split(";");
		for (int i = 0; i < split.length; i++) {
			System.out.println(split[i]);
			// 分割されたストリングから空白とASCII文字を取り除いてビルダーに放り込む
			for (int j = 0; isTargetChar(split[i].charAt(j)); j++) {
				builder.append(split[i].charAt(j));
				// 先頭の1文字を取り除き,新しい文字列を生成する.
				// 全体のインデックスが1小さくなるので,指定インデックスの値を1減らす
				split[i] = split[i].substring(j-- + 1);
			}
			if (split[i].toLowerCase().indexOf("&#x") != -1) {
				builder.append((char) Integer.parseInt(split[i], 16));
			} else if (split[i].toLowerCase().indexOf("&#") != -1) {
				builder.append((char) Integer.parseInt(split[i].replaceFirst("&#", "")));
			} else {
				builder.append(split[i]);
			}
		}
		return builder.toString();
	}

	private static boolean isTargetChar(char charAt) {
		// 空白文字との一致
		if (charAt == 0x20) {
			return true;
			// &文字と一致していない
		} else if (charAt != '&') {
			return true;
			// ASCII文字0 ~ DELに一致している
		} else if (charAt > 0x2F && 0x80 > charAt) {
			return true;
		}
		return false;
	}
}
