package pasca.test.dans.controller;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pasca.test.dans.dto.response.BaseResponse;
import pasca.test.dans.library.JwtUtils;
import pasca.test.dans.service.JobService;

@RestController
@RequestMapping("/api/job")
@RequiredArgsConstructor
public class JobController {
  private final JobService jobService;
  private final JwtUtils jwtUtils;

  @GetMapping("/job-list")
  public ResponseEntity<BaseResponse> jobList(
      @RequestHeader(value = "authorization", defaultValue = "") String auth) {
    BaseResponse baseResponse = Objects.nonNull(jwtUtils.verify(auth)) ? jobService.jobList() :
        BaseResponse.builder().error("Access Denied").status(HttpStatus.BAD_REQUEST.value()).build();

    return ResponseEntity
        .status(baseResponse.getStatus())
        .body(baseResponse);
  }

  @RequestMapping("/job-detail")
  public ResponseEntity<BaseResponse> jobDetail(@RequestHeader(value = "authorization", defaultValue = "") String auth,
      @RequestParam String jobId) {
    BaseResponse baseResponse = Objects.nonNull(jwtUtils.verify(auth)) ? jobService.jobDetail(jobId) :
        BaseResponse.builder().error("Access Denied").status(HttpStatus.BAD_REQUEST.value()).build();

    return ResponseEntity
        .status(baseResponse.getStatus())
        .body(baseResponse);
  }

}
