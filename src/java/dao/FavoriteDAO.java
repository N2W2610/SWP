package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Favorite;
import model.User;
import model.Property;

import java.util.List;

/**
 * Lớp FavoriteDAO xử lý thao tác với bảng Favorites.
 */
public class FavoriteDAO {
    private EntityManagerFactory emf;

    public FavoriteDAO() {
        emf = Persistence.createEntityManagerFactory("HouseRentalPU");
    }

    /**
     * Thêm nhà trọ vào danh sách yêu thích.
     * @param user Người dùng.
     * @param property Nhà trọ.
     * @return true nếu thành công, false nếu thất bại.
     */
    public boolean addFavorite(User user, Property property) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Favorite favorite = new Favorite();
            favorite.setUser(user);
            favorite.setProperty(property);
            em.persist(favorite);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    /**
     * Xóa nhà trọ khỏi danh sách yêu thích.
     * @param userId Mã người dùng.
     * @param propertyId Mã nhà trọ.
     * @return true nếu thành công, false nếu thất bại.
     */
    public boolean removeFavorite(int userId, int propertyId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Favorite favorite = em.createQuery("SELECT f FROM Favorite f WHERE f.user.id = :userId AND f.property.id = :propertyId", Favorite.class)
                    .setParameter("userId", userId)
                    .setParameter("propertyId", propertyId)
                    .getSingleResult();
            if (favorite != null) {
                em.remove(favorite);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

    /**
     * Lấy danh sách yêu thích của người dùng.
     * @param userId Mã người dùng.
     * @return Danh sách Favorite.
     */
    public List<Favorite> getFavoritesByUserId(int userId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT f FROM Favorite f WHERE f.user.id = :userId", Favorite.class)
                    .setParameter("userId", userId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void close() {
        if (emf != null) {
            emf.close();
        }
    }
}