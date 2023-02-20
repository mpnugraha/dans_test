package pasca.test.dans.service;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pasca.test.dans.dto.response.BaseResponse;
import pasca.test.dans.dto.response.JobDetailResponse;
import pasca.test.dans.library.JsonUtils;
import pasca.test.dans.outbound.JobOutbound;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobService {
  private final JobOutbound jobOutbound;

  public BaseResponse jobList() {

    BaseResponse baseResponse = new BaseResponse();

    try {
      String jobList = jobOutbound.getJobList();

      if(StringUtils.isEmpty(jobList)){
        throw new Exception();
      }

      baseResponse.setData(JsonUtils.convertJsonInStringToObject(jobList, new TypeReference<>() {}));
      return baseResponse;

    } catch (Exception e) {
      baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());
      baseResponse.setError("Outbound Failed");
      return baseResponse;
    }


  }

  public BaseResponse jobDetail(String jobId) {

    BaseResponse baseResponse = new BaseResponse();

    try {
      String jobList = jobOutbound.getJobDetail(jobId);

      if(StringUtils.isEmpty(jobList)){
        throw new Exception();
      }

      baseResponse.setData(JsonUtils.stringJsonToObject(jobList, JobDetailResponse.class));
      return baseResponse;

    } catch (Exception e) {
      baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());
      baseResponse.setError("Outbound Failed");
      return baseResponse;
    }


  }
}
