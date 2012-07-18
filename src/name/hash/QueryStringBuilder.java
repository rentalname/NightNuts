package name.hash;

/**
 * api.twitter.com����^�C�����C���̏����擾����ۂ̃N�G���[���쐬����
 * 
 * @author User
 */
public class QueryStringBuilder {
	private long id = -1;
	private String name;
	// �^�C�����C����N�y�[�W�ڂ��擾����.0�ȏ�̐���
	private int page = -1;
	// ��x�Ɏ擾����c�C�[�g�̐�.1~200�܂ł̐���
	private int count = 10;

	private QueryStringBuilder(String name) {
		this.name = name;
	}

	/*
	 * �t�B�[���h�̏��Ɋ�Â���,�N�G���[������𔭍s����
	 * screen_name��������user_id�̂����ꂩ���ݒ肳�Ă���K�v������
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
	 * �r���_�[�̃C���X�^���X�𐶐�����t�@�N�g���[���\�b�h
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
