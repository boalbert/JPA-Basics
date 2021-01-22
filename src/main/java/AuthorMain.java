import java.time.LocalDate;

public class AuthorMain {
	public static void main(String[] args) {

		AuthorDao authorDao = new AuthorDaoImpl();

		// Create authors
		authorDao.create(new Author("Per Olov", "Enquist", LocalDate.of(1934, 7, 14)));
		authorDao.create(new Author("Elof", "Enquist", LocalDate.of(1903, 2, 7)));
		authorDao.create(new Author("Maja", "Enquist", LocalDate.of(1903, 1, 3)));
		authorDao.create(new Author("Mats", "Enquist", LocalDate.of(1960, 12, 24)));

		// Find author
		System.out.println(authorDao.findByLastName("Enquist"));

		// Rename author
		authorDao.updateLastName(12, "Lidman");

		// Remove author
		authorDao.remove(15);

		// Find by birthdate, range
		System.out.println(authorDao.findByBirthDate(
				LocalDate.of(1902, 1, 1),
				LocalDate.of(1965, 12, 31)));

		// Remove by lastname
		authorDao.removeByLastName("Enquist");
		authorDao.removeByLastName("Andersson");
	}
}
