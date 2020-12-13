package com.yigitcolakoglu.Clinic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Date;

public interface UserRepository extends JpaRepository<User, Integer>{
        @Query("SELECT p FROM User p WHERE p.id = :id AND p.doctor = :doctor")
        List<User> checkDoctor(@Param("id") long id,  @Param("doctor") User doctor);

        @Query("SELECT p FROM User p WHERE p.email LIKE :email% AND p.phone LIKE :phone% AND p.name LIKE %:name% AND p.doctor = :doctor AND p.role = :role")
        List<User> searchUser(@Param("email") String email, @Param("phone") String phone, @Param("name") String name, @Param("doctor") User doctor, @Param("role") String role, Pageable pageable);

        @Query("FROM User WHERE email = :email")
        User findByEmail(@Param("email") String email);

        @Query("SELECT count(*) FROM User p WHERE p.email LIKE :email% AND p.phone LIKE :phone% AND p.name LIKE %:name% AND p.doctor = :doctor AND p.role = :role")
        long countPatients(@Param("email") String email, @Param("phone") String phone, @Param("name") String name, @Param("doctor") User doctor, @Param("role") String role);

        @Modifying
        @Query("UPDATE User u SET u.name = :name, u.email = :email, u.phone = :phone, u.TC_no = :tcno, u.HES_code = :hescode WHERE u.id = :id AND u.doctor = :doctor")
        int updatePatient(@Param("name") String name, @Param("email") String email, @Param("phone") String phone, @Param("tcno") String tcno, @Param("hescode") String hescode, @Param("id") long id, @Param("doctor") User doctor);

        @Modifying
        @Query("DELETE FROM User WHERE id = :id AND doctor = :doctor")
        void deletePatient(@Param("id") long id, @Param("doctor") User doctor);
}