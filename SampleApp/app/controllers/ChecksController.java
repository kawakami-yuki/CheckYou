package controllers;


import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


import models.entity.Check;
import models.request.Check.ResultPostRequest;
import play.*;
import play.data.Form;
import play.mvc.*;
import scala.App;
import utils.ConfigUtil;
import views.html.*;
import play.db.ebean.Model.Finder;
import models.service.Check.*;
import play.libs.F.Option;






public class ChecksController extends Application {

  public static Result index() {
	  // 要実装
	  // 文字列は、application.confで設定したcheckyou.setting.message.title、checkyou.setting.message.questionを渡す
	String title = ConfigUtil.get("checkyou.setting.message.title").get();
	String message = ConfigUtil.get("checkyou.setting.message.question").get();
	Form<ResultPostRequest> bindForm = Form.form(ResultPostRequest.class);
    return ok(index.render(title,message,bindForm));
  }

  public static Result result() {
    Form<ResultPostRequest> bindForm = Form.form(ResultPostRequest.class).bindFromRequest();
    String inputName = bindForm.data().get("name");

    //　バリデーションチェック
    // error.required
    if(bindForm.error("name") != null && bindForm.error("name").message().contains("error.required")) {
        return validationErrorIndexResult("名前欄が空です", bindForm);
    }


    //twitterID形式かチェック
    String regex = "^@";
    Pattern p = Pattern.compile(regex);
    Matcher m = p.matcher(inputName);
    if(m.find()==false){
    	return validationErrorIndexResult("Twitter id形式ではありません", bindForm);
    }

    //１５文字以上は診断できない
    if(inputName.length() >= 15 && bindForm.error("name").message().contains("error.maxLength")){
    	return validationErrorIndexResult("文字数が15文字を超えています", bindForm);
    }

    ResultPostRequest getInputName = new ResultPostRequest();
    getInputName.setName(inputName);
    String userName = getInputName.getName();

    //Checkのメソッドを使用
    Check check0 = new Check(userName);
    String result = check0.result().get();
    Check  check = new Check(userName,result);
    check.store();


    String title = userName + ConfigUtil.get("checkyou.setting.message.resultTitle").get();

    return ok(views.html.result.render(title,check));
  }


  public static Result recent(Integer page) {
    String title = ConfigUtil.get("checkyou.setting.message.recentTitle").get();
    String message = ConfigUtil.get("checkyou.setting.message.recentDescription").get();

    CheckModelService checkMS = new CheckModelService();
    List<Check> entryList=checkMS.findWithPage(page).get();

    //Integer max = 5;
    Option<List<Check>> result = new Check().entitiesList(page);
    Integer maxPage            = new Check().entitiesMaxPage(1);
    return ok(recent.render(title,message,entryList,page,maxPage));
  }

  // バリデーションエラーメッセージを表示し、トップページへ戻る
  private static Result validationErrorIndexResult(String message, Form<ResultPostRequest> form) {
    flash("error", message);
    return badRequest(index.render(
        ConfigUtil.get("checkyou.setting.message.title").getOrElse(""),
        ConfigUtil.get("checkyou.setting.message.question").getOrElse(""),
        form));
  }

 //診断結果ページシェア用
  public static Result resultId(Long id) {
      Option<Check> check        = new Check(id).unique();
      return check.isDefined() ?
          ok(result.render(
                  check.get().name
                  + ConfigUtil.get("checkYou.setting.message.resultTitle").getOrElse(""), check.get()))
              : Application.fail(routes.ChecksController.index(), "error", "診断エラーです");
  }
}