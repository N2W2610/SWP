package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Room;

import java.util.List;

/**
 * Lớp RoomDAO xử lý thao tác CRUD cho bảng Rooms.
 */
public class RoomDAO {
    private EntityManagerFactory emf;

    public RoomDAO() {
        emf = Persistence.createEntityManagerFactory("HouseRentalPU");
    }

    /**
     * Thêm phòng trọ mới.
     * @param room Đối tượng Room cần thêm.
     * @return true nếu thành công, false nếu thất bại.
     */
    public boolean addRoom(Room room) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(room);
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
     * Lấy danh sách tất cả phòng trọ.
     * @return Danh sách Room.
     */
    public List<Room> getAllRooms() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT r FROM Room r", Room.class).getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Lấy phòng trọ theo ID.
     * @param id Mã phòng trọ.
     * @return Đối tượng Room hoặc null nếu không tìm thấy.
     */
    public Room getRoomById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Room.class, id);
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