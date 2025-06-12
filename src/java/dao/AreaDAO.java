package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Area;

import java.util.List;

/**
 * Lớp AreaDAO xử lý thao tác với bảng Areas.
 */
public class AreaDAO {
    private EntityManagerFactory emf;

    public AreaDAO() {
        emf = Persistence.createEntityManagerFactory("HouseRentalPU");
    }

    /**
     * Lấy danh sách tất cả khu vực.
     * @return Danh sách Area.
     */
    public List<Area> getAllAreas() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Area a", Area.class).getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Lấy khu vực theo ID.
     * @param id Mã khu vực.
     * @return Đối tượng Area hoặc null nếu không tìm thấy.
     */
    public Area getAreaById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Area.class, id);
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