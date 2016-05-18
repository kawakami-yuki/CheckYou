 package models.service.Model;

import java.util.List;

import play.db.ebean.Model;
import play.libs.F.Option;

// モデル向けサービスのインターフェース
 public interface ModelService<T extends Model> {

   // TODO: メソッド要検討
	 public Option<T> findById(Long id);
	 public Option<T> save(T entry);
	 public Option<List<T>> findWithPage(Integer pageSource);
 }
