
package jp.co.internous.framepj.model.domain;

import java.sql.Timestamp;

/**
 * mst_categoryのドメイン
 * @author インターノウス
 *
 */
public class MstCategory {
	private int id;
	private String CategoryName;
	private String CategoryDescription;
	private Timestamp CreatedAt;
	private Timestamp UpdatedAt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	public String getCategoryDescription() {
		return CategoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		CategoryDescription = categoryDescription;
	}
	public Timestamp getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		CreatedAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return UpdatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		UpdatedAt = updatedAt;
	}
	
}
