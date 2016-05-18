package models.service.api.Check;


import models.entity.Check;
import models.response.Check.CheckResponse;
import models.response.Check.ChecksDefaultResponse;
import models.setting.CheckYouStatusSetting;
import utils.OptionUtil;
import static play.libs.F.*;

// レスポンス用サービスクラス
public class CheckResponseService {

    public static CheckResponseService use() {
        return new CheckResponseService();
    }

    // BadRequestを受け取る
    public ChecksDefaultResponse getBadRequest(String message) {
         // 5/19に
    }

    // CheckからCheckResponseレスポンスへの変換クラス
    public Option<CheckResponse> getCheckResponse(Check response) {
         // 5/19に
    }
}