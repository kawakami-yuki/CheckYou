package models.request.Check;

import org.junit.Test;
import play.data.Form;
import static org.fest.assertions.Assertions.assertThat;
import java.util.HashMap;
import java.util.Map;

import static play.data.Form.form;

public class ResultPostRequestTest {

// OKケース
    /**
     * 正しいId形式
     */
    @Test
    public void testValidationToRightParam() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", "test_v");
        Form<ResultPostRequest> form = form(ResultPostRequest.class).bind(map);
        assertThat(form.hasErrors()).isFalse();
    }

    // 要実装
    // アンダーバーなし（testv）
    @Test
    public void testValidationToNoUnderRightParam() {
    	HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", "testv");
        Form<ResultPostRequest> form = form(ResultPostRequest.class).bind(map);
        assertThat(form.hasErrors()).isFalse();
    }
    // 数字のみ
    @Test
    public void testValidationToNumberParam() {
    	HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", "123");
        Form<ResultPostRequest> form = form(ResultPostRequest.class).bind(map);
        assertThat(form.hasErrors()).isFalse();
    }
    // 数値、英数、アンダーバー混合
    @Test
    public void testValidationToMixRightParam() {
    	HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", "123_abc");
        Form<ResultPostRequest> form = form(ResultPostRequest.class).bind(map);
        assertThat(form.hasErrors()).isFalse();
    }

// NGケース
    /**
     * ハイフンつき
     */
    @Test
    public void testValidationToWrongParamWithHyphen() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "test-v");
        Form<ResultPostRequest> form = form(ResultPostRequest.class).bind(map);
        assertThat(form.hasErrors()).isTrue();
    }

    // 要実装
    // 禁止文字">"を含む
    @Test
    public void testValidationToWrongParamWithInequality() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "test>v");
        Form<ResultPostRequest> form = form(ResultPostRequest.class).bind(map);
        assertThat(form.hasErrors()).isTrue();
    }
    // ひらがなを含む
    @Test
    public void testValidationToWrongParamWithHira() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "あいう");
        Form<ResultPostRequest> form = form(ResultPostRequest.class).bind(map);
        assertThat(form.hasErrors()).isTrue();
    }
}