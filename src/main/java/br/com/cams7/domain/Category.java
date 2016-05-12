package br.com.cams7.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pet")
public class Category {

	@Id
	// @SequenceGenerator(name = "SEQ_PET", sequenceName = "GEN_PET_ID",
	// allocationSize = 1)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	// "SEQ_PET")
	@Column(name = "id_pet")
	private Integer id;

	@Column(name = "nome")
	private String name;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<Project> projects;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

}