package com.example.todo.service;

import com.example.todo.model.AppUser;
import com.example.todo.model.enums.AppUserRole;
import com.example.todo.repository.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class AppUserServiceTest {

    @Mock
    private AppUserRepository appUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ConfirmationTokenService confirmationTokenService;
    private AppUserService underTest;
    private AutoCloseable autoCloseable;



    @BeforeEach
    void setUp() {
        AutoCloseable autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new AppUserService(appUserRepository,bCryptPasswordEncoder,confirmationTokenService);
    }

    @Test
    void willThrowWhenEmailIsFalse() {
        //given
        String email = "onur@onur.com";
        //when
        //then
        assertThatThrownBy(() -> underTest.loadUserByUsername(email))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining("user with email " + email + " not found");
    }

    @Test
    void signUpUser() {
        //given
        AppUser appUser = new AppUser(
        "Onur",
        "Ağbulak",
        "onur@gmail.com",
        "password",
        AppUserRole.USER
        );
        //when

    }
}