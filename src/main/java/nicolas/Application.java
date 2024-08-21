package nicolas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import nicolas.dao.eventiDAO;
import nicolas.entities.eventi;
import nicolas.exceptions.NotFoundException;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestioneeventi");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        eventiDAO sd = new eventiDAO(em);

        eventi gigi = new eventi("Gigi", LocalDate.of(2012,8,12), "il brodaio", eventi.TipoEvento.PUBBLICO, 42);
        sd.save(gigi);

        try {
            eventi gigifromDd = sd.findbyId(1);
            System.out.println(gigifromDd);

        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            sd.findByIdAndDelete(52);
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        em.close();
        emf.close();
    }
}