package com.example.todo.service;


import com.example.todo.model.ConfirmationToken;
import com.example.todo.repository.ConfirmationTokenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;

class ConfirmationTokenServiceTest {

    @Mock
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Mock
    private ConfirmationToken token;
    private AutoCloseable autoCloseable;
    private ConfirmationTokenService underTest;


    @BeforeEach
    void setUp() {
        AutoCloseable autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ConfirmationTokenService(confirmationTokenRepository);
    }

/*    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }*/

    @Test
    void itShouldSaveConfirmationToken() {
        //given

        //when
        underTest.saveConfirmationToken(token);
        //then
        ArgumentCaptor<ConfirmationToken> tokenArgumentCaptor = ArgumentCaptor.forClass(ConfirmationToken.class);

        verify(confirmationTokenRepository).save(tokenArgumentCaptor.capture());

        ConfirmationToken capturedToken = tokenArgumentCaptor.getValue();

        assertThat(capturedToken).isEqualTo(token);
    }

    @Test
    void itShouldGetToken() {
        //given
        String token = "token";
        //when
        underTest.getToken(token);
        //then
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(confirmationTokenRepository).findByToken(stringArgumentCaptor.capture());

        String capturedString = stringArgumentCaptor.getValue();

        assertThat(capturedString).isEqualTo(token);
    }
}