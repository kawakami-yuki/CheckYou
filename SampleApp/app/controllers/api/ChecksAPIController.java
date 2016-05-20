package controllers.api;



import java.util.List;

import models.entity.Check;
import models.response.Check.CheckPagingResponse;
import models.response.Check.CheckResponse;
import models.response.Check.ChecksDefaultResponse;
import models.service.api.Check.CheckResponseService;
import models.setting.CheckYouStatusSetting;
import play.libs.F.Option;
import play.libs.Json;
import play.mvc.*;
import utils.ConfigUtil;

public class ChecksAPIController extends Controller {

	// 診断結果取得
	  public static Result checks(Long id) {
	    ChecksDefaultResponse result = new ChecksDefaultResponse();
	    Option<Check> checkOps = new Check(id).unique();
	    if(checkOps.isDefined()) {
	    	Option<CheckResponse> checkResServise = new CheckResponseService().getCheckResponse(checkOps.get());
	      // 要実装（resultのプロパティに値セット）
	    	result.code = CheckYouStatusSetting.OK.code;
	    	result.status = CheckYouStatusSetting.OK.message;
	    	result.message = "test";
	    	result.result = checkResServise.get();
	      // JSON形式に変換して返す
	      return ok(Json.toJson(result));
	    }
	    return (Result) result.badRequest(ConfigUtil.get("checkYou.setting.message.error.noResult").getOrElse(""));
	  }

	  // 診断結果の一覧取得
	  public static Result getList(Integer page) {
		  CheckPagingResponse result = new CheckPagingResponse();
		  Option<List<Check>> checks = new Check().entitiesList(page);
		  if(checks.isDefined()){
			  Option<List<CheckResponse>> checkResServise =result.getCheckResponse(checks.get());
			  result.code = CheckYouStatusSetting.OK.code;
			  result.status = CheckYouStatusSetting.OK.message;
			  result.message = null;
			  result.maxPage = new Check().entitiesMaxPage(page);
			  result.results = checkResServise.get();
			  return ok(Json.toJson(result));
		  }
		  return (Result) result.badRequest(ConfigUtil.get("checkYou.setting.message.error.noResult").getOrElse(""));
	  }
	}