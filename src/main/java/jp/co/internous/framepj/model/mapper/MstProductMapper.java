package jp.co.internous.framepj.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jp.co.internous.framepj.model.domain.MstProduct;

/**
 * mst_productにアクセスするDAO
 * @author インターノウス
 *
 */
@Mapper
public interface MstProductMapper {
	/**
	 * 商品情報を取得する
	 * @return 商品情報リスト
	 */
	@Select("select id, product_name, price, image_full_path from mst_product")
	List<MstProduct> find();
	
	/**
	 * キーワード（商品名）を条件に商品情報を取得する
	 * SQLは xml ファイルに記述
	 * @return 商品情報リスト
	 */
	List<MstProduct> findByProductName(@Param("keywords") String[] keywords);
	
	/**
	 * カテゴリとキーワード（商品名）を条件に商品情報を取得する
	 * SQLは xml ファイルに記述
	 * @return 商品情報リスト
	 */
	List<MstProduct> findByCategoryAndProductName(@Param("category") int category, @Param("keywords") String[] keywords);
	
	/**
	 * 商品IDを条件に商品情報を取得する
	 * @param id 商品情報ID
	 * @return 商品情報
	 */
	@Select("select id, product_name_kana, product_description, product_name, price, image_full_path, release_date, release_company from mst_product where id = #{id}")
	MstProduct findById(int id);
	
}
