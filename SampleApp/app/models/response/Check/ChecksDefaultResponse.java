package models.response.Check;

import models.entity.Check;
import static play.libs.F.*;

public class ChecksDefaultResponse {
    public Integer code;
    public String status;
    public String message;
    public CheckResponse result;

    public ChecksDefaultResponse(){}

    // レスポンスDTOを取得
    public Option<CheckResponse> response(Check response) {
        return TODO;
    }

    // BadRequestを取得
    public ChecksDefaultResponse badRequest(String message) {
        return TODO;
    }
}

