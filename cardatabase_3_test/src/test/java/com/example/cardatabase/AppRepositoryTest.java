package com.example.cardatabase;

import com.example.cardatabase.domain.AppUser;
import com.example.cardatabase.domain.AppuserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AppRepositoryTest {
    @Autowired
    AppuserRepository appuserRepository;

    @Test
    @DisplayName("유저 찾기")
    void Userfind() {

        //given
        AppUser appUser = new AppUser("user1","user1","User");
        appuserRepository.save(appUser);
        //when
        Optional<AppUser> foundUser = appuserRepository.findByUsername("user1");
        //then
        assertThat(appuserRepository.findByUsername(appUser.getUsername())).isPresent();
        assertThat(appuserRepository.findByUsername(appUser.getUsername()).get().getRole()).isEqualTo("User");
    }
}
