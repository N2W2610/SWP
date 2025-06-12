package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Review;

import java.util.List;

/**
 * Lớp ReviewDAO xử lý thao tác với bảng Reviews.
 */
public class ReviewDAO {
    private EntityManagerFactory emf;

    public ReviewDAO() {
        emf = Persistence.createEntityManagerFactory("HouseRentalPU");
    }

    /**
     * Thêm đánh giá mới.
     * @param review Đối tượng Review cần thêm.
     * @return true nếu thành công, false nếu thất bại.
     */
    public boolean addReview(Review review) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(review);
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
     * Lấy danh sách đánh giá theo mã nhà trọ.
     * @param propertyId Mã nhà trọ.
     * @return Danh sách Review.
     */
    public List<Review> getReviewsByPropertyId(int propertyId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT r FROM Review r WHERE r.property.id = :propertyId", Review.class)
                    .setParameter("propertyId", propertyId)
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