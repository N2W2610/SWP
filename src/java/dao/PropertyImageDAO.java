package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.PropertyImage;

import java.util.List;

/**
 * Lớp PropertyImageDAO xử lý thao tác với bảng PropertyImages.
 */
public class PropertyImageDAO {
    private EntityManagerFactory emf;

    public PropertyImageDAO() {
        emf = Persistence.createEntityManagerFactory("HouseRentalPU");
    }

    /**
     * Thêm hình ảnh cho nhà trọ.
     * @param image Đối tượng PropertyImage cần thêm.
     * @return true nếu thành công, false nếu thất bại.
     */
    public boolean addPropertyImage(PropertyImage image) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(image);
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
     * Lấy danh sách hình ảnh theo mã nhà trọ.
     * @param propertyId Mã nhà trọ.
     * @return Danh sách PropertyImage.
     */
    public List<PropertyImage> getImagesByPropertyId(int propertyId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT i FROM PropertyImage i WHERE i.property.id = :propertyId", PropertyImage.class)
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