package models.response.Check;

import java.util.Date;

//API用診断結果のレスポンスモデル
public class CheckResponse {
 private Long id;
 private String name;
 private String result;
 private Date created;
 private Date modified;

 public CheckResponse() { }
 public CheckResponse(Long id, String name, String result, Date created, Date modified) {
     this.id = id;
     this.name = name;
     this.result = result;
     this.created = created;
     this.modified = modified;
 }
}

