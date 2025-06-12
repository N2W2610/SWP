package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Notification;

import java.util.List;

/**
 * Lớp NotificationDAO xử lý thao tác với bảng Notifications.
 */
public class NotificationDAO {
    private EntityManagerFactory emf;

    public NotificationDAO() {
        emf = Persistence.createEntityManagerFactory("HouseRentalPU");
    }

    /**
     * Thêm thông báo mới.
     * @param notification Đối tượng Notification cần thêm.
     * @return true nếu thành công, false nếu thất bại.
     */
    public boolean addNotification(Notification notification) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(notification);
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
     * Lấy danh sách thông báo của người dùng.
     * @param userId Mã người dùng.
     * @return Danh sách Notification.
     */
    public List<Notification> getNotificationsByUserId(int userId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT n FROM Notification n WHERE n.user.id = :userId", Notification.class)
                    .setParameter("userId", userId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Đánh dấu thông báo đã xem.
     * @param notificationId Mã thông báo.
     * @return true nếu thành công, false nếu thất bại.
     */
    public boolean markAsSeen(int notificationId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Notification notification = em.find(Notification.class, notificationId);
            if (notification != null) {
                notification.setSeen(true);
                em.merge(notification);
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

    public void close() {
        if (emf != null) {
            emf.close();
        }
    }
}