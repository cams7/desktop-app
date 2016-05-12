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
		List<Category> categories = categoryDAO.findAll();
		System.out.printf(FORMAT_STRING, "Id", "Pet", "Projetos");

		categories.stream().sorted((c1, c2) -> c1.getName().compareTo(c2.getName())).forEach(category -> {
			List<Project> projects = category.getProjects();

			String projectNames = projects.stream().map(Project::getName).reduce(" ", (n1, n2) -> n1 + n2 + ". ");

			System.out.printf(FORMAT_STRING, category.getId(), category.getName(), projects.size() + projectNames);
		});

		System.out.print("\n");
	}

	public void printPersonInfo() {
		List<Person> persons = personDAO.findAll();
		System.out.printf(FORMAT_STRING, "Id", "Pessoa", "Projetos");

		persons.stream().forEach(person -> {
			List<Project> projects = person.getProjects();

			String projectNames = projects.stream().sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
					.map(Project::getName).reduce(" ", (n1, n2) -> n1 + n2 + ". ");

			System.out.printf(FORMAT_STRING, person.getId(), person.getName(), projects.size() + projectNames);
		});

		System.out.print("\n");
	}

	public void printProjectInfo() {
		List<Project> projects = projectDAO.findAll();
		System.out.printf(FORMAT_STRING, "Id", "Projeto", "Pessoas");

		projects.stream().forEach(project -> {
			List<Person> persons = project.getPersons();
			String personNames = persons.stream().map(Person::getName).reduce(" ", (n1, n2) -> n1 + n2 + ". ");

			System.out.printf(FORMAT_STRING, project.getId(), project.getName(),
					project.getPersons().size() + personNames);
		});

		System.out.print("\n");
	}
}
