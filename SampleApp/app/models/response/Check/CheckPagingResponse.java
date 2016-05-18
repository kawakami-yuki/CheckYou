package models.response.Check;

import java.util.List;

// API用診断結果一覧のレスポンスモデル
public class CheckPagingResponse {

    public Integer code;
    public String status;
    public String message;
    public Integer maxPage;
    public List<CheckResponse> results;

    // BadRequestを取得
    public ChecksDefaultResponse badRequest(String message) {
        return TODO;
    }
}
