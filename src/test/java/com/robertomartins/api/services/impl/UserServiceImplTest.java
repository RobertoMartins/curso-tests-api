package com.robertomartins.api.services.impl;

import com.robertomartins.api.domain.User;
import com.robertomartins.api.domain.dto.UserDTO;
import com.robertomartins.api.repositories.UserRepository;
import com.robertomartins.api.services.exceptions.DataIntegratyViolationException;
import com.robertomartins.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "Roberto Martins";
    public static final String EMAIL = "rm@gmail.com";
    public static final String PASSWORD = "1234";
    public static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
    public static final int INDEX = 0;

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);

        User result = service.findById(ID);

        assertNotNull(result);
        assertEquals(User.class, result.getClass());
        assertEquals(result.getId(), ID);
        assertEquals(result.getName(), NAME);
        assertEquals(result.getEmail(), EMAIL);
        assertEquals(result.getPassword(), PASSWORD);
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

        try {
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO, ex.getMessage());
        }

    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(User.class, result.get(INDEX).getClass());
        assertEquals(result.get(INDEX).getId(), ID);
        assertEquals(result.get(INDEX).getName(), NAME);
        assertEquals(result.get(INDEX).getEmail(), EMAIL);
        assertEquals(result.get(INDEX).getPassword(), PASSWORD);

    }

    @Test
    void whenCreateAndReturnSuccess() {
        when(repository.save(any())).thenReturn(user);

        User result = service.create(userDTO);

        assertNotNull(result);
        assertEquals(User.class, result.getClass());
        assertEquals(ID, result.getId());
        assertEquals(result.getName(), NAME);
        assertEquals(result.getEmail(), EMAIL);
        assertEquals(result.getPassword(), PASSWORD);

    }

    @Test
    void whenCreateAndReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);


        try {
            optionalUser.get().setId(2);
            service.create(userDTO);
        } catch (Exception ex) {
            assertEquals(DataIntegratyViolationException.class, ex.getClass());
            assertEquals("Email já cadastrado no sistema", ex.getMessage());
        }

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}