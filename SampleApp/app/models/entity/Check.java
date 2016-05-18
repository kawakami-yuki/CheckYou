package models.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import play.db.ebean.*;
import play.libs.F.Option;

import play.data.validation.*;
import play.data.format.*;
import models.service.Check.CheckModelService;
import models.service.Check.CheckService;

@Entity
public class Check extends Model {

    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @Constraints.Required
    public String result;

    @Formats.DateTime(pattern="yyyy/MM/dd")
    public Date created;

    @Formats.DateTime(pattern="yyyy/MM/dd")
    public Date modified;

    @Transient  // 永続化しないフィールドを定義。Transient付けないとDBのフィールドとして処理されようとしてエラーになる
    private CheckModelService checkModelService = new CheckModelService();

    @Transient
    private CheckService checkService = new CheckService();


    // コンストラクタ設定
    public Check() {}

    public Check(String name) {
        this.name = name;
    }

    // 検索用オブジェクト生成のためのコンストラクタ
    /*
      使用例. id=10のデータを取り出す
      Check check = new Check(10).method();
    */
    public Check(Long id) {
        this.id = id;
    }

    public Check(String name, String result) {
        this.name = name;
        this.result = result;
        this.created = new Date();
        this.modified = new Date();
    }

//    public String result() {
//      // TODO: 返り値、返り値型後で決める
//      // TODO: DB処理など
//        return "TODO";
//    }


    // 結果を保存
    public Option<Check> store() {
        return checkModelService.save(this);
    }

    // idに該当するものを検索
    public Option<Check> unique() {
        return checkModelService.findById(id);
    }

    public Option<List<Check>> entitiesList(Integer page) {
    	// 要実装
    	// CheckModelServiceクラスのメソッド呼び出し
    	return checkModelService.findWithPage(page);
    }

    // 診断結果を取得
    public Option<String> result() {
          //　要実装
    	return checkService.getResult(name);
    }

 // ページ結果を取得
    public Integer entitiesMaxPage(Integer value) {
        // 要実装
        // CheckModelServiceのgetMaxPage呼び出し。最大ページ数取得できない場合、valueを返す
    	Integer maxPage= checkModelService.getMaxPage().getOrElse(value);
    	return maxPage;
    }
}