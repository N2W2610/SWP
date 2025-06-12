package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.User;
import model.Role;

import java.util.List;

/**
 * Lớp UserDAO xử lý thao tác CRUD cho bảng Users.
 */
public class UserDAO {
    private EntityManagerFactory emf;

    public UserDAO() {
        emf = Persistence.createEntityManagerFactory("HouseRentalPU");
    }

    /**
     * Thêm người dùng mới vào cơ sở dữ liệu.
     * @param user Đối tượng User cần thêm.
     * @return true nếu thêm thành công, false nếu thất bại.
     */
    public boolean addUser(User user) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", user.getEmail())
                    .getResultList()
                    .isEmpty()) {
                em.persist(user);
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
     * Lấy thông tin người dùng theo email và mật khẩu.
     * @param email Email người dùng.
     * @param password Mật khẩu (plain text, cần mã hóa trong production).
     * @return Đối tượng User nếu hợp lệ, null nếu không.
     */
    public User getUserByEmailAndPassword(String email, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * Lấy người dùng theo ID.
     * @param id Mã người dùng.
     * @return Đối tượng User hoặc null nếu không tìm thấy.
     */
    public User getUserById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Lấy người dùng theo Google OAuth.
     * @param oauthId ID Google OAuth.
     * @return Đối tượng User hoặc null nếu không tìm thấy.
     */
    public User getUserByGoogleOAuth(String oauthId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.oauthId = :oauthId AND u.oauthProvider = 'google'", User.class)
                    .setParameter("oauthId", oauthId)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    /**
     * Cập nhật thông tin người dùng.
     * @param user Đối tượng User cần cập nhật.
     * @return true nếu thành công, false nếu thất bại.
     */
    public boolean updateUser(User user) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
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