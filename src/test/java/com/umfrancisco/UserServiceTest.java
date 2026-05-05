package com.umfrancisco;

import com.umfrancisco.exception.DuplicateResourceException;
import com.umfrancisco.exception.ResourceNotFoundException;
import com.umfrancisco.model.User;
import com.umfrancisco.repository.UserRepository;
import com.umfrancisco.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User sampleUser;

    @BeforeEach
    void setUp() {
    	sampleUser = new User(1L, "johndoe", "1234", "john@example.com", "São Paulo", "Brazil");
    }

    @Test
    void getAllUsers_returnsUserList() {
        when(userRepository.findAll()).thenReturn(List.of(sampleUser));

        List<User> result = userService.getAllUsers();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getUsername()).isEqualTo("johndoe");
    }

    @Test
    void getUserById_existingId_returnsUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(sampleUser));

        User result = userService.getUserById(1L);

        assertThat(result.getEmail()).isEqualTo("john@example.com");
    }

    @Test
    void getUserById_missingId_throwsException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void createUser_newUser_savesAndReturns() {
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(sampleUser)).thenReturn(sampleUser);

        User result = userService.createUser(sampleUser);

        assertThat(result.getId()).isEqualTo(1L);
        verify(userRepository, times(1)).save(sampleUser);
    }

    @Test
    void createUser_duplicateUsername_throwsException() {
        when(userRepository.existsByUsername("johndoe")).thenReturn(true);

        assertThatThrownBy(() -> userService.createUser(sampleUser))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("johndoe");
    }

    @Test
    void deleteUser_existingId_deletesSuccessfully() {
        when(userRepository.existsById(1L)).thenReturn(true);

        assertThatCode(() -> userService.deleteUser(1L)).doesNotThrowAnyException();
        verify(userRepository).deleteById(1L);
    }

    @Test
    void deleteUser_missingId_throwsException() {
        when(userRepository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> userService.deleteUser(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
