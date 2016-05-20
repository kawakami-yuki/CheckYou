package models.response.Check;

import models.entity.Check;
import models.service.api.Check.CheckResponseService;

import static play.libs.F.*;

public class ChecksDefaultResponse {
    public Integer code;
    public String status;
    public String message;
    public CheckResponse result;

    public ChecksDefaultResponse(){}

 // レスポンスDTOを取得
    public Option<CheckResponse> response(Check response) {
        return CheckResponseService.use().getCheckResponse(response);
    }

    // BadRequestを取得
    public ChecksDefaultResponse badRequest(String message) {
        return CheckResponseService.use().getBadRequest(message);
    }
}

