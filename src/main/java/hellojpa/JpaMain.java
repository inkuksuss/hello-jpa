package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        PersistenceUnitUtil persistenceUnitUtil = emf.getPersistenceUnitUtil();
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("city1", "street", "zip"));

            member.getFavoriteFoods().add("chick");
            member.getFavoriteFoods().add("meet");

            member.getAddressHistory().add(new AddressEntity("old1", "street", "helllo"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "helllo"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("__________________________");
//            Member findMember = em.find(Member.class, member.getId());
//            List<AddressEntity> addressHistory = findMember.getAddressHistory();
//            for (Address address : addressHistory) {
//                System.out.println("address = " + address.getCity());
//            }
//
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println("favoriteFood = " + favoriteFood);
//            }

//            Address homeAddress = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("new city22", homeAddress.getStreet(), homeAddress.getZipcode() ));
//
//            findMember.getFavoriteFoods().remove("chick");
//            findMember.getFavoriteFoods().add("한식");

//            findMember.getAddressHistory().remove(new Address("old1", "street", "helllo"));
//            findMember.getAddressHistory().add(new Address("new1", "street", "helllo"));


            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
