package name.hash;

public class NumericCharacterRefernceConverter {

	public static String convert(String str) {
		StringBuilder builder = new StringBuilder();
		String[] split = str.split(";");
		for (int i = 0; i < split.length; i++) {
			System.out.println(split[i]);
			// �������ꂽ�X�g�����O����󔒂�ASCII��������菜���ăr���_�[�ɕ��荞��
			for (int j = 0; isTargetChar(split[i].charAt(j)); j++) {
				builder.append(split[i].charAt(j));
				// �擪��1��������菜��,�V����������𐶐�����.
				// �S�̂̃C���f�b�N�X��1�������Ȃ�̂�,�w��C���f�b�N�X�̒l��1���炷
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
		// �󔒕����Ƃ̈�v
		if (charAt == 0x20) {
			return true;
			// &�����ƈ�v���Ă��Ȃ�
		} else if (charAt != '&') {
			return true;
			// ASCII����0 ~ DEL�Ɉ�v���Ă���
		} else if (charAt > 0x2F && 0x80 > charAt) {
			return true;
		}
		return false;
	}
}
