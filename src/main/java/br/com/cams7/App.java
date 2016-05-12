package br.com.cams7;

import java.util.List;

import br.com.cams7.dao.CategoryDAO;
import br.com.cams7.dao.PersonDAO;
import br.com.cams7.dao.ProjectDAO;
import br.com.cams7.domain.Category;
import br.com.cams7.domain.Person;
import br.com.cams7.domain.Project;

public class App {

	private CategoryDAO categoryDAO = new CategoryDAO();
	private PersonDAO personDAO = new PersonDAO();
	private ProjectDAO projectDAO = new ProjectDAO();

	private final String FORMAT_STRING = "%1$-3s\t%2$-30s\t%3$s\n";

	public static void main(String[] args) {
		App app = new App();
		app.printCategoryInfo();
		app.printPersonInfo();
		app.printProjectInfo();
	}

	public void printCategoryInfo() {
		List<Category> catList = categoryDAO.findAll();
		System.out.printf(FORMAT_STRING, "Id", "Pet", "Projeto");
		for (Category category : catList) {
			List<Project> categoryProjects = category.getProjects();
			String categoryProjectsString = " ";
			for (Project project : categoryProjects)
				categoryProjectsString += project.getName() + ". ";

			System.out.printf(FORMAT_STRING, category.getId(), category.getName(),
					category.getProjects().size() + categoryProjectsString);
		}
		System.out.print("\n");
	}

	public void printPersonInfo() {
		List<Person> personList = personDAO.findAll();
		System.out.printf(FORMAT_STRING, "Id", "Pessoa", "Projeto");
		for (Person person : personList) {
			List<Project> personProjects = person.getProjects();
			String personProjectsString = " ";
			for (Project project : personProjects)
				personProjectsString += project.getName() + ". ";

			System.out.printf(FORMAT_STRING, person.getId(), person.getName(),
					person.getProjects().size() + personProjectsString);
		}
		System.out.print("\n");
	}

	public void printProjectInfo() {
		List<Project> projectList = projectDAO.findAll();
		System.out.printf(FORMAT_STRING, "Id", "Projeto", "Pessoa");
		for (Project project : projectList) {
			List<Person> projectPersons = project.getPersons();
			String projectPersonsString = " ";
			for (Person person : projectPersons)
				projectPersonsString += person.getName() + ". ";

			System.out.printf(FORMAT_STRING, project.getId(), project.getName(),
					project.getPersons().size() + projectPersonsString);
		}
		System.out.print("\n");
	}
}
