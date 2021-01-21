import java.time.LocalDate;

public class AuthorMain {
	public static void main(String[] args) {

		AuthorDao authorDao = new AuthorDaoImpl();

		// Insert new author
		authorDao.create(new Author(0, "Bosse", "Andersson", LocalDate.of(1950, 5, 1)));

		// Find author
		System.out.println(authorDao.findByLastName("Andersson"));

		// Rename author
		authorDao.updateLastName(15, "Larsson");

		// Verify rename
		System.out.println(authorDao.findByLastName("Larsson"));

		// Remove author
		authorDao.remove(15);

		// Find by birthdate, range
		System.out.println(authorDao.findByBirthDate(
				LocalDate.of(1901, 1, 1),
				LocalDate.of(1999, 1, 12)));

	}
}
