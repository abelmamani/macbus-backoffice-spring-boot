package ar.edu.undec.adapter.data.user.mapper;

import ar.edu.undec.adapter.data.exceptions.FailedMappingException;
import ar.edu.undec.adapter.data.role.mapper.RoleDataMapper;
import ar.edu.undec.adapter.data.user.models.UserNode;
import user.models.User;
import user.models.UserResponseModel;
import java.util.Map;

public class UserDataMapper {
    public static User dataCoreMapper(UserNode userNode){
        try {
            return User.getInstance(userNode.getId(),
                    userNode.getName(),
                    userNode.getLastName(),
                    userNode.getEmail(),
                    userNode.getPassword(),
                    userNode.getResetToken(),
                    userNode.getTokenExpiryDate(),
                    userNode.getStatus(),
                    RoleDataMapper.dataCoreMapper(userNode.getRole()));
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from node to core");
        }
    }
    public static UserNode dataNodeMapper(User user){
        try {
            return UserNode.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .resetToken(user.getResetToken())
                    .tokenExpiryDate(user.getTokenExpiryDate())
                    .status(user.getStatus())
                    .role(RoleDataMapper.dataNodeMapper(user.getRole()))
                    .build();
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from core to node");
        }
    }

    public static UserResponseModel mapToUserResponseModel(Map<String, Object> map) {
        return new UserResponseModel(
                (String) map.get("id"),
                (String) map.get("name"),
                (String) map.get("lastName"),
                (String) map.get("email"),
                (String) map.get("role"),
                (String) map.get("status"));
    }
}
