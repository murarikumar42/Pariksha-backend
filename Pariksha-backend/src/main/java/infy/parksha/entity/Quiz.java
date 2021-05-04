package infy.parksha.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Quiz implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long quizId;
	private String quizTitle;
	private String quizDescription;
	private boolean isPublished;
	//@ManyToOne(targetEntity = Category.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	//@JoinColumn(name = "cat_id",referencedColumnName = "catId")
	private Category category;
}
