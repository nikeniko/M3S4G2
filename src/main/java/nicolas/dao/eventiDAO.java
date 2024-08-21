package nicolas.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import nicolas.entities.eventi;
import nicolas.exceptions.NotFoundException;

public class eventiDAO {

    private final EntityManager em;

    public eventiDAO(EntityManager em) {
        this.em = em;
    }

    public void save(eventi evento) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(evento);
        transaction.commit();
        System.out.println("l'evento " + evento.getTitolo() + " è stato salvato");
    }

    public eventi findbyId(long id){
        eventi found = em.find(eventi.class, id);
        if(found == null) throw new NotFoundException(id) ;
        return  found;
    }

    public void findByIdAndDelete(long id) {
        // 0. Cerco lo studente nel db
        eventi found = this.findbyId(id);

        // 1. Chiediamo all'entity manager di fornirci una transazione
        EntityTransaction transaction = em.getTransaction();

        // 2. Facciamo partire la transazione
        transaction.begin();

        // 3. Rimuoviamo lo studente dal Persistence Context (a questo step lo studente non è ancora stato rimosso effettivamente dal db)
        em.remove(found);

        // 4. Concludiamo la transazione (qua lo studente verrà effettivamente rimosso)
        transaction.commit();

        System.out.println("l'evento " + found.getTitolo() + " è stato eliminato correttamente!");
    }
}