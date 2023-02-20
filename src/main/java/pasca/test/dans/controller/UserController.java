package pasca.test.dans.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pasca.test.dans.dto.request.LoginRequest;
import pasca.test.dans.dto.response.BaseResponse;
import pasca.test.dans.service.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @PostMapping("/login")
  public ResponseEntity<BaseResponse> login(@RequestBody LoginRequest request){

    BaseResponse baseResponse = userService.login(request);

    return ResponseEntity
        .status(baseResponse.getStatus())
        .body(baseResponse);
  }

}
