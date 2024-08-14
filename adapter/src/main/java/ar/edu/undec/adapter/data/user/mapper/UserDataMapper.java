package ar.edu.undec.adapter.data.user.mapper;

import ar.edu.undec.adapter.data.exceptions.FailedMappingException;
import ar.edu.undec.adapter.data.user.models.UserNode;
import user.models.User;

public class UserDataMapper {
    public static User dataCoreMapper(UserNode userNode){
        try {
            return User.getInstance(userNode.getId(),
                    userNode.getName(),
                    userNode.getLastName(),
                    userNode.getEmail(),
                    userNode.getPassword(),
                    userNode.getRole(),
                    userNode.getResetToken(),
                    userNode.getTokenExpiryDate());
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
                    .role(user.getRole())
                    .resetToken(user.getResetToken())
                    .tokenExpiryDate(user.getTokenExpiryDate())
                    .build();
        }catch (RuntimeException exception){
            throw new FailedMappingException("Error mapping from core to node");
        }
    }
}
