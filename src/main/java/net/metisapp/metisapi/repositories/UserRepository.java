package net.metisapp.metisapi.repositories;

import net.metisapp.metisapi.entities.MetisUser;
import net.metisapp.metisapi.entities.ProtocolNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.HashMap;
import java.util.Calendar;

public interface UserRepository extends JpaRepository<MetisUser, Integer>{
        @Query("SELECT p FROM MetisUser p WHERE p.email = :email AND p.doctor = :doctor")
        List<MetisUser> checkDoctor(@Param("email") String email, @Param("doctor") MetisUser doctor);

        @Query("SELECT p FROM MetisUser p WHERE p.role = PATIENT")
        List<MetisUser> getAllPatients();

        @Query("SELECT p FROM MetisUser p WHERE p.id = :id AND p.doctor = :doctor")
        List<MetisUser> checkDoctor(@Param("id") long id, @Param("doctor") MetisUser doctor);

        @Query("SELECT p FROM MetisUser p WHERE p.email LIKE :email% AND p.phone LIKE :phone% AND p.name LIKE %:name% AND p.doctor.email = :doctor AND p.role LIKE :role")
        List<MetisUser> searchUser(@Param("email") String email, @Param("phone") String phone, @Param("name") String name, @Param("doctor") String doctor, @Param("role") String role, Pageable pageable);

        @Query("SELECT p FROM MetisUser p WHERE p.email LIKE :email% AND p.phone LIKE :phone% AND p.name LIKE %:name% AND p.role LIKE :role")
        List<MetisUser> searchUser(@Param("email") String email, @Param("phone") String phone, @Param("name") String name, @Param("role") String role, Pageable pageable);

        @Query("FROM MetisUser WHERE email = :email")
        MetisUser findByEmail(@Param("email") String email);

        @Query("SELECT count(*) FROM MetisUser p WHERE p.email LIKE :email% AND p.phone LIKE :phone% AND p.name LIKE %:name% AND p.doctor.email = :doctor AND p.role LIKE :role")
        long countPatients(@Param("email") String email, @Param("phone") String phone, @Param("name") String name, @Param("doctor") String doctor, @Param("role") String role);

        @Query("SELECT count(*) FROM MetisUser p WHERE p.email LIKE :email% AND p.phone LIKE :phone% AND p.name LIKE %:name% AND p.role LIKE :role")
        long countPatients(@Param("email") String email, @Param("phone") String phone, @Param("name") String name, @Param("role") String role);

        @Query("SELECT u FROM MetisUser u WHERE u.id = :id")
        MetisUser findById(@Param("id") long id);

        @Modifying
        @Query("UPDATE MetisUser u SET u.name = :name, u.email = :email, u.phone = :phone, u.TC_no = :tcno, u.HES_code = :hescode, u.role = :role, u.protocolNumbers = :pNums WHERE u.id = :id")
        int updatePatient(@Param("name") String name, @Param("email") String email, @Param("phone") String phone, @Param("tcno") String tcno, @Param("hescode") String hescode, @Param("role") String role, @Param("pNums") HashMap<String, Calendar> pNumbers, @Param("id") long id);

        @Modifying
        @Query("UPDATE MetisUser u SET u.name = :name, u.email = :email, u.phone = :phone, u.TC_no = :tcno, u.HES_code = :hescode, u.locale = :locale WHERE u.id = :id")
        int updateUser(@Param("name") String name, @Param("email") String email, @Param("phone") String phone, @Param("tcno") String tcno, @Param("hescode") String hescode, @Param("locale") String locale, @Param("id") long id);

        @Modifying
        @Query("UPDATE MetisUser u SET password = :password WHERE email = :email")
        void updatePassword(@Param("email") String email, @Param("password") String pass);

        @Modifying
        @Query("UPDATE MetisUser u SET HES_id_token = :token, HES_expiry = :expiry WHERE email = :email")
        void updateHesToken(@Param("email") String email, @Param("token") String token, @Param("expiry") Calendar expiry);

        @Modifying
        @Query("UPDATE MetisUser u SET zoom_access_token = :access_token, zoom_refresh_token = :refresh_token, zoom_expiry = :expiry WHERE email = :email")
        void updateZoomTokens(@Param("email") String email, @Param("access_token") String access_token, @Param("refresh_token") String refresh_token, @Param("expiry") Calendar expiry);

        @Modifying
        @Query("DELETE FROM MetisUser WHERE id = :id AND doctor = :doctor")
        void deletePatient(@Param("id") long id, @Param("doctor") MetisUser doctor);

        @Modifying
        @Query("UPDATE MetisUser u SET safe = :safe WHERE id = :id")
        void updateCovidStatus(@Param("id") long id, @Param("safe") boolean safe);
}
