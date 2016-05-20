package models.response.Check;

import java.util.ArrayList;
import java.util.List;

import models.entity.Check;
import models.setting.CheckYouStatusSetting;
import play.libs.F.Option;
import utils.OptionUtil;


// API用診断結果一覧のレスポンスモデル
public class CheckPagingResponse {


	public Integer code;
    public String status;
    public String message;
    public Integer maxPage;
    public List<CheckResponse> results;

    // BadRequestを取得
    public ChecksDefaultResponse badRequest(String message) {
    	ChecksDefaultResponse result = new ChecksDefaultResponse();
        CheckYouStatusSetting status = CheckYouStatusSetting.NO_RESULT;
        result.code    = status.code;
        result.status  = status.message;
        result.message = message;
        return result;
    }

    public Option<List<CheckResponse>> getCheckResponse(List<Check> response) {
        Option<List<Check>> checks = OptionUtil.apply(response);
        List<CheckResponse> checkRes = new ArrayList<CheckResponse>();
        // 要実装
      for(Check check:checks.get() ){
    	  CheckResponse checkResponse = new CheckResponse(check.id,check.name,check.result,check.created,check.modified);
    	  checkRes.add(checkResponse);
      }
       return OptionUtil.apply(checkRes);
    }
}