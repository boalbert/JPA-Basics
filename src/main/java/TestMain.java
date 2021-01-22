import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class TestMain {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("Author");

		// Update author
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Author a = em.find(Author.class,5);
		a.setFirstName("Victoria");
		System.out.println(a);
		em.getTransaction().commit();

		// Insert new author
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Author b = new Author("Maja","Carlström", LocalDate.of(1988, 1, 12));
		em.persist(b);
		System.out.println("Maja Id = " + a.getId());
		em.getTransaction().commit();

		// Remove new author
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(Author.class,b.getId()));
		em.getTransaction().commit();

		// Print all authors
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.createQuery("FROM Author",Author.class)
				.getResultStream()
				.forEach(author -> System.out.println(author));
		em.getTransaction().commit();

		// Close resources
		em.close();
		emf.close();

	}
}
