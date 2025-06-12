package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Property;

import java.util.List;

/**
 * Lớp PropertyDAO xử lý thao tác CRUD cho bảng Properties.
 */
public class PropertyDAO {
    private EntityManagerFactory emf;

    public PropertyDAO() {
        emf = Persistence.createEntityManagerFactory("HouseRentalPU");
    }

    /**
     * Thêm nhà trọ mới.
     * @param property Đối tượng Property cần thêm.
     * @return true nếu thành công, false nếu thất bại.
     */
    public boolean addProperty(Property property) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(property);
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
     * Lấy danh sách tất cả nhà trọ.
     * @return Danh sách Property.
     */
    public List<Property> getAllProperties() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Property p", Property.class).getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Lấy nhà trọ theo ID.
     * @param id Mã nhà trọ.
     * @return Đối tượng Property hoặc null nếu không tìm thấy.
     */
    public Property getPropertyById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Property.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Tìm kiếm nhà trọ theo khu vực và khoảng giá.
     * @param areaId Mã khu vực (null nếu không lọc).
     * @param minPrice Giá tối thiểu (null nếu không lọc).
     * @param maxPrice Giá tối đa (null nếu không lọc).
     * @return Danh sách Property phù hợp.
     */
    public List<Property> searchProperties(Integer areaId, Double minPrice, Double maxPrice) {
        EntityManager em = emf.createEntityManager();
        try {
            String query = "SELECT p FROM Property p WHERE 1=1";
            if (areaId != null) {
                query += " AND p.area.id = :areaId";
            }
            if (minPrice != null) {
                query += " AND p.price >= :minPrice";
            }
            if (maxPrice != null) {
                query += " AND p.price <= :maxPrice";
            }

            var jpqlQuery = em.createQuery(query, Property.class);
            if (areaId != null) {
                jpqlQuery.setParameter("areaId", areaId);
            }
            if (minPrice != null) {
                jpqlQuery.setParameter("minPrice", minPrice);
            }
            if (maxPrice != null) {
                jpqlQuery.setParameter("maxPrice", maxPrice);
            }

            return jpqlQuery.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Cập nhật nhà trọ.
     * @param property Đối tượng Property cần cập nhật.
     * @return true nếu thành công, false nếu thất bại.
     */
    public boolean updateProperty(Property property) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(property);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
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