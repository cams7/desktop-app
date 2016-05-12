package br.com.cams7.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa")
public class Person {

	@Id
	// @SequenceGenerator(name = "SEQ_PESSOA", sequenceName = "GEN_PESSOA_ID",
	// allocationSize = 1)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	// "SEQ_PESSOA")
	@Column(name = "id_pessoa")
	private Integer id;

	@Column(name = "nome")
	private String name;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "pessoa_projeto", joinColumns = @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa"), inverseJoinColumns = @JoinColumn(name = "id_projeto", referencedColumnName = "id_projeto"))
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