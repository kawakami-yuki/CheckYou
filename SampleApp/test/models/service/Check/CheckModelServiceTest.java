package models.service.Check;

import com.avaje.ebean.Ebean;
import apps.FakeApp;
import models.entity.Check;
import play.libs.F;
import play.libs.F.Option;

import org.junit.Test;
import java.util.List;

import static play.libs.F.*;
import static org.fest.assertions.Assertions.assertThat;

public class CheckModelServiceTest extends FakeApp {

    // 正常系（Some）：1件のレコードから1つ取り出す
    @Test
    public void testFindByIdTo1ReturnSome() throws Exception {
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `check` (`id`, `name`, `result`, `created`, `modified`) VALUES ('1',  'test_t',  'test_tさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:56', '2013-08-20 12:34:56');"));
        Option<Check> model = new CheckModelService().findById(1L);
        assertThat(model.getClass()).isEqualTo(Some.class);
        assertThat(model.get().id).isEqualTo(1L);
        assertThat(model.get().name).isEqualTo("test_t");
        assertThat(model.get().result).isEqualTo("test_tさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。");
    }

    // 要実装
    // 正常系（None）：1件のレコードから該当しないIdのものを取り出す
    @Test
    public void testFindByIdTo2ReturnNone() throws Exception {
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `check` (`id`, `name`, `result`, `created`, `modified`) VALUES ('1',  'test_t',  'test_tさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:56', '2013-08-20 12:34:56');"));
        Option<Check> model = new CheckModelService().findById(2L);
        assertThat(model.getClass()).isEqualTo(None.class);
    }

    // 要実装
    // 異常系（None）：1件のレコードから検索のキーとしてnullを渡す
    @Test
    public void testFindByIdTo1ReturnNull() throws Exception {
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `check` (`id`, `name`, `result`, `created`, `modified`) VALUES ('1',  'test_t',  'test_tさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:56', '2013-08-20 12:34:56');"));
        Option<Check> model = new CheckModelService().findById(null);
        assertThat(model.getClass()).isEqualTo(None.class);
//        assertThat(model.get().id).isNotEqualTo(1L);
//        assertThat(model.get().name).isNotEqualTo("test_t");
//        assertThat(model.get().result).isNotEqualTo("test_tさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。");
    }

    @Test
    public void testSaveRightCase() throws Exception {
    	Check test = new Check(2L);
    	Option<Check> model =new CheckModelService().save(test);
    	assertThat(model.getClass()).isEqualTo(Some.class);
    	assertThat(model.get().id).isEqualTo(2L);
    }

    @Test
    public void testSaveNull() throws Exception {
    	Check test = null;
    	Option<Check> model =new CheckModelService().save(test);
    	assertThat(model.getClass()).isEqualTo(None.class);
    }

    // ページ1に5件存在し、Idが1と5が存在する（Some型、件数、ID=1L, 5Lを確認）
    @Test
    public void testFindWithPage1Contains1And5() throws Exception {

        prepareSaveData();
        // 要実装
        Option<List<Check>> model =new CheckModelService().findWithPage(1);
        assertThat(model.get().size()).isEqualTo(5);
        assertThat(model.getClass()).isEqualTo(Some.class);
        assertThat(model.get().get(0).id).isEqualTo(1L);
        assertThat(model.get().get(4).id).isEqualTo(5L);
    }

    // 要実装（Some型、件数、ID=6Lを確認）
    // ページ2に1件存在し、Idが6が存在している
    @Test
    public void testFindWithPage2Contains6() throws Exception {
    	prepareSaveData();
        // 要実装
        Option<List<Check>> model =new CheckModelService().findWithPage(2);
        assertThat(model.get().size()).isEqualTo(1);
        assertThat(model.getClass()).isEqualTo(Some.class);
        assertThat(model.get().get(0).id).isEqualTo(6L);
    }


    // 要実装
    // ページ3は存在しない
    @Test
    public void testFindWithPage3NoContains() throws Exception {
    	prepareSaveData();
        // 要実装
        Option<List<Check>> model =new CheckModelService().findWithPage(3);
        assertThat(model.getClass()).isEqualTo(Some.class);
        assertThat(model.get().size()).isEqualTo(0);
    }


    private void prepareSaveData() {
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `check` (`id`, `name`, `result`, `created`, `modified`) VALUES ('1',  'test_a',  'test_aさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:11', '2013-08-20 12:34:56');"));
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `check` (`id`, `name`, `result`, `created`, `modified`) VALUES ('2',  'test_b',  'test_bさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:12', '2013-08-20 12:34:56');"));
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `check` (`id`, `name`, `result`, `created`, `modified`) VALUES ('3',  'test_c',  'test_cさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:13', '2013-08-20 12:34:56');"));
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `check` (`id`, `name`, `result`, `created`, `modified`) VALUES ('4',  'test_d',  'test_dさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:14', '2013-08-20 12:34:56');"));
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `check` (`id`, `name`, `result`, `created`, `modified`) VALUES ('5',  'test_e',  'test_eさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:15', '2013-08-20 12:34:56');"));
        Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `check` (`id`, `name`, `result`, `created`, `modified`) VALUES ('6',  'test_f',  'test_fさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:16', '2013-08-20 12:34:56');"));
    }

    // 要実装
    // 1件しかデータがない場合、MaxPageは1（Option型、ページ数確認）
    @Test
    public void testMaxPageWith1Data() throws Exception {
    	Ebean.execute(Ebean.createSqlUpdate("INSERT INTO  `check` (`id`, `name`, `result`, `created`, `modified`) VALUES ('1',  'test_t',  'test_tさんにオススメなPlay frameworkのバージョンは、2.1.3 Javaです。',  '2013-08-20 12:34:56', '2013-08-20 12:34:56');"));
        // 要実装
    	Option<Integer> model =  new CheckModelService().getMaxPage();
    	assertThat(model.getClass()).isEqualTo(Some.class);
    	assertThat(model.get()).isEqualTo(1);
    }
    // 要実装
    // 0件しかデータがない場合、MaxPageは0（Option型、ページ数確認）

    @Test
    public void testMaxPageWithNoData() throws Exception {
        // 要実装
    	Option<Integer> model =  new CheckModelService().getMaxPage();
    	assertThat(model.getClass()).isEqualTo(Some.class);
    	assertThat(model.get()).isEqualTo(0);
    }

    // 要実装
    // 6件データがある場合はMaxPageは2（Option型、ページ数確認）
    @Test
    public void testMaxPageWith6Data() throws Exception {
        // 要実装
    	prepareSaveData();
    	Option<Integer> model =  new CheckModelService().getMaxPage();
    	assertThat(model.getClass()).isEqualTo(Some.class);
    	assertThat(model.get()).isEqualTo(2);
    }
}