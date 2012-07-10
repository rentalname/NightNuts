package name.hash.test;

import junit.framework.Assert;
import name.hash.NumericCharacterRefernceConverter;

import org.junit.Test;

public class ConvertTest {

	@Test
	public final void convertTest1() {
		String testString = "&#31532;26&#22238; &#26085;&#26412;";
		String convert = NumericCharacterRefernceConverter.convert(testString);
		Assert.assertEquals(convert, "��26�� ���{");
		System.out.println(convert);
	}

	@Test
	public final void convertTest2() {
		String testString = "&#31532;26&#22238; &#26085;&#26412;Android&#12398;&#20250; &#26481;&#21271;&#25903;&#37096;&#30330;&#20449;&#20250; : ATND http://t.co/1ykTvVd5 @atnd&#12373;&#12435;&#12363;&#12425;";
		String convert = NumericCharacterRefernceConverter.convert(testString);
		Assert.assertEquals(convert, "��26�� ���{Android�̉� ���k�x�����M�� : ATND http://t.co/1ykTvVd5 @atnd���񂩂�");
		System.out.println(convert);
	}

}
