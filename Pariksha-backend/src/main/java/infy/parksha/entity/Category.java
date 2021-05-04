package infy.parksha.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Category implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long catId;
	private String categoryTitle;
	private String categoryDescription;
	//@OneToMany(targetEntity = Quiz.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	//@JoinColumn(name = "cat_id",referencedColumnName = "catId")
	private List<Quiz> listQuiz;
	
	//getters and setters
	
	public Long getId() {
		return catId;
	}
	public void setId(Long id) {
		this.catId = id;
	}
	public String getCategory() {
		return categoryTitle;
	}
	public void setCategory(String category) {
		this.categoryTitle = category;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	
}
