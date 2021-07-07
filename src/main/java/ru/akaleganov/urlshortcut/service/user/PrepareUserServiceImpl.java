package ru.akaleganov.urlshortcut.service.user;

import java.util.List;
import java.util.function.Supplier;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.akaleganov.urlshortcut.service.dto.UserDTO;
import ru.akaleganov.urlshortcut.service.mapper.UserMapper;

/**
 * сервис для рефакторинга и подготовки пользователя для сохранения
 */
@Service
@RequiredArgsConstructor
public class PrepareUserServiceImpl {

    private final UserMapper userMapper;

    private final List<ValidUserService> validUserServiceList;

    /**
     * сохранение  пользователя в бд
     *
     * @param userDTO
     *         url
     *
     * @return {@link UserDTO}
     */
    public UserDTO prepareUserToSave(UserDTO userDTO) {
        return this.validUserServiceList.stream().map(e -> e.isValid(userDTO))
                                        .filter(e -> e.getErrorMessage() != null).findFirst().orElse(userDTO);
    }

    /**
     * Prepare to update user dto.
     *
     * @param userDTO
     *         the user dto
     * @param save
     *         the save
     *
     * @return the user dto
     */
    public UserDTO prepareToUpdate(UserDTO userDTO, Supplier<UserDTO> save) {
        return this.validUserServiceList.stream().map(e -> e.isValid(userDTO))
                                        .filter(e -> e.getErrorMessage() != null).findFirst().orElse(userDTO);

    }

}
