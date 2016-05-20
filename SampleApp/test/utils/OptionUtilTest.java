package utils;

import org.junit.Test;
import play.libs.F;
import java.util.ArrayList;
import java.util.List;
import static org.fest.assertions.Assertions.assertThat;

public class OptionUtilTest {

    @Test
    public void testApplyShouldSome() throws Exception {
        String test1 = "abc";
        F.Option<String> test1Result = OptionUtil.apply(test1);
        assertThat(test1Result.getClass()).isEqualTo(F.Some.class);
        assertThat(test1Result.getClass()).isNotEqualTo(F.None.class);
    }

    @Test
    public void testApplyShouldNone() throws Exception {
        String test2 = null;
        F.Option<String> test2Result = OptionUtil.apply(test2);
        assertThat(test2Result.getClass()).isEqualTo(F.None.class);
        assertThat(test2Result.getClass()).isNotEqualTo(F.Some.class);
    }

    // 要実装
    // リストSome型 "a" "b" "c"（リスト型ある人）
//    @Test
//    public void testApplyShouldListSome() throws Exception {
//    	String a = "a";
//    	String b = "b";
//    	String c = "c";
//    	List<String> test3 = new ArrayList<String>();
//    	test3.add(a);
//    	test3.add(c);
//    	F.Option<List<String>> test3Result = OptionUtil.apply(test3);
//    	assertThat(test3Result.getClass()).isEqualTo(F.None.class);
//        assertThat(test3Result.getClass()).isNotEqualTo(F.Some.class);
//    }
//    // リストNone型（リスト型ある人）
//    @Test
//    public void testApplyShouldListNome() throws Exception {
//    	List<String> test4 = new ArrayList<String>();
//    	test4 = null;
//    	F.Option<List<String>> test4Result = OptionUtil.apply(test4);
//    	assertThat(test4Result.getClass()).isEqualTo(F.None.class);
//        assertThat(test4Result.getClass()).isNotEqualTo(F.Some.class);
//    }
    // 文字列Some型
    @Test
    public void testApplyShouldStringSome() throws Exception {
    	 String test5 = "abc";
         F.Option<String> test5Result = OptionUtil.applyWithString(test5);
         assertThat(test5Result.getClass()).isEqualTo(F.Some.class);
         assertThat(test5Result.getClass()).isNotEqualTo(F.None.class);
    }
    // 文字列None型
    @Test
    public void testApplyShouldStringNone() throws Exception {
        String test5 = null;
        F.Option<String> test5Result = OptionUtil.applyWithString(test5);
        assertThat(test5Result.getClass()).isEqualTo(F.None.class);
        assertThat(test5Result.getClass()).isNotEqualTo(F.Some.class);
    }

}