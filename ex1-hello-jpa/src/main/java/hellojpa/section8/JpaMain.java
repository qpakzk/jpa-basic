package hellojpa.section8;

import hellojpa.Member;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member();
            member1.setUsername("member1");
            Member member2 = new Member();
            member2.setUsername("member2");

            em.persist(member1);
            em.persist(member2);

            em.flush();
            em.clear();

            Member reference = em.getReference(Member.class, member2.getId());

            System.out.println("reference = " + reference.getClass());
            System.out.println("Is reference loaded: " + emf.getPersistenceUnitUtil().isLoaded(reference));
            Hibernate.initialize(reference);
            System.out.println("Is reference loaded after forcefully initialized: " + emf.getPersistenceUnitUtil().isLoaded(reference));

            Member findMember1 = em.find(Member.class, member1.getId());
            System.out.println("findMember1.class = " + findMember1.getClass());
            System.out.println("Is findMember1 loaded: " + emf.getPersistenceUnitUtil().isLoaded(findMember1));
            Member findMember2 = em.find(Member.class, member2.getId());
            System.out.println("findMember2.class = " + findMember2.getClass());
            System.out.println("Is findMember2 loaded: " + emf.getPersistenceUnitUtil().isLoaded(findMember2));

            System.out.println("member1 == member2: " + (findMember1.getClass() == findMember2.getClass()));

            System.out.println("findMember1 instance of Member: " + (findMember1 instanceof Member));
            System.out.println("findMember2 instance of Member: " + (findMember2 instanceof Member));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
