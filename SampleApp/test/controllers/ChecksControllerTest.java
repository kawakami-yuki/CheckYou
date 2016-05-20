package controllers;

import apps.FakeApp;
import models.service.Check.CheckModelServiceTest;

import com.avaje.ebean.Ebean;
import org.junit.Test;
import play.mvc.Result;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;
import static play.test.Helpers.fakeApplication;

public class ChecksControllerTest extends FakeApp {

    // 通常のルートへのアクセス
    @Test
    public void testIndexIsOk() throws Exception {
        Result result = route(fakeRequest(GET, "/"));
        assertThat(status(result)).isEqualTo(OK);
        assertThat(contentType(result)).isEqualTo("text/html");
        assertThat(charset(result)).isEqualTo("utf-8");
        assertThat(contentAsString(result)).contains("Twitterのユーザー名を入れてください");
    }

    // Idつきの/resultへのアクセス
    @Test
    public void testResultIdWithId() throws Exception {
      // 要実装（確認内容はindexアクションテスト内容と一緒、表示されるべき文言はなにか１つ確認すればよい）
    	Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `check` (`id`, `name`, `result`, `created`, `modified`) VALUES ('1',  'test_t',  'test_tさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:56', '2013-08-20 12:34:56');"));
    	Result result = route(fakeRequest(GET, "/result/1"));
    	 assertThat(status(result)).isEqualTo(OK);
    	 assertThat(contentType(result)).isEqualTo("text/html");
    	 assertThat(charset(result)).isEqualTo("utf-8");
    	 assertThat(contentAsString(result)).contains("test_tさん");
    }

    private void prepareSaveData() {
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `check` (`id`, `name`, `result`, `created`, `modified`) VALUES ('1',  'test_a',  'test_aさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:11', '2013-08-20 12:34:56');"));
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `check` (`id`, `name`, `result`, `created`, `modified`) VALUES ('2',  'test_b',  'test_bさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:12', '2013-08-20 12:34:56');"));
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `check` (`id`, `name`, `result`, `created`, `modified`) VALUES ('3',  'test_c',  'test_cさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:13', '2013-08-20 12:34:56');"));
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `check` (`id`, `name`, `result`, `created`, `modified`) VALUES ('4',  'test_d',  'test_dさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:14', '2013-08-20 12:34:56');"));
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `check` (`id`, `name`, `result`, `created`, `modified`) VALUES ('5',  'test_e',  'test_eさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:15', '2013-08-20 12:34:56');"));
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `check` (`id`, `name`, `result`, `created`, `modified`) VALUES ('6',  'test_f',  'test_fさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:16', '2013-08-20 12:34:56');"));
    }

 //ページング付きのアクションテスト（recent）
 // 1ページ目には1件目のデータ、５件目のデータの任意の文字列がそれぞれ含まれる
    @Test
    public void testRecentShouldContainAAndE() throws Exception {
        // 要実装
    	prepareSaveData();
    	Result result = route(fakeRequest(GET, "/recent/1"));
    	assertThat(status(result)).isEqualTo(OK);
   	 	assertThat(contentType(result)).isEqualTo("text/html");
   	 	assertThat(charset(result)).isEqualTo("utf-8");
   	 	assertThat(contentAsString(result)).contains("test_a");
   	 	assertThat(contentAsString(result)).contains("test_e");
    }

    // 1ページ目には６件目のデータの任意の文字列は含まれない
    @Test
    public void testRecentShouldNotContainF() throws Exception {
        // 要実装
    	prepareSaveData();
    	Result result = route(fakeRequest(GET, "/recent/1"));
    	assertThat(status(result)).isEqualTo(OK);
   	 	assertThat(contentType(result)).isEqualTo("text/html");
   	 	assertThat(charset(result)).isEqualTo("utf-8");
   	 	assertThat(contentAsString(result)).isNotEqualTo("test_f");
    }


    // 2ページ目は、６件目のデータの任意の文字列を含む, ５件目のデータの任意の文字列は含まれない
    @Test
    public void testRecentPage2ShouldContainF() throws Exception {
        // 要実装
    	prepareSaveData();
    	Result result = route(fakeRequest(GET, "/recent/2"));
    	assertThat(status(result)).isEqualTo(OK);
   	 	assertThat(contentType(result)).isEqualTo("text/html");
   	 	assertThat(charset(result)).isEqualTo("utf-8");
   	 	assertThat(contentAsString(result)).contains("test_f");
   	 assertThat(contentAsString(result)).isNotEqualTo("test_e");
    }

}