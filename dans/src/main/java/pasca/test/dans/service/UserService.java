package pasca.test.dans.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pasca.test.dans.dao.UserRepository;
import pasca.test.dans.dto.request.LoginRequest;
import pasca.test.dans.dto.response.BaseResponse;
import pasca.test.dans.entity.User;
import pasca.test.dans.library.JwtUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final JwtUtils jwtUtils;

  public BaseResponse login(LoginRequest request) {

    BaseResponse baseResponse = new BaseResponse();
    User user = userRepository.findFirstByUsername(request.getUsername());

    if(Objects.isNull(user) || !request.getPassword().equals(user.getPassword())){
      baseResponse.setStatus(HttpStatus.NOT_FOUND.value());
      baseResponse.setError("User login failed");
      return baseResponse;
    }

    String token = jwtUtils.generateJwt(user);
    Map<String , Object> data = new HashMap<>();
    data.put("accessToken", token);

    baseResponse.setData(data);
    return baseResponse;
  }
}
