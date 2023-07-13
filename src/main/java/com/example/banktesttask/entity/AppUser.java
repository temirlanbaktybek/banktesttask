package com.example.banktesttask.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Класс для работы с аккаунтами пользователей
 */
@Entity
@NoArgsConstructor
@Data
public class AppUser {

    /**
     * Идентификатор аккаунта пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Логин пользователя
     */
    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * Электронная почта
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * Пароль пользователя
     */
    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;

    /**
     * Роль пользователя
     */
    @ElementCollection(fetch = FetchType.EAGER)
    List<AppUserRole> appUserRoles;


}
