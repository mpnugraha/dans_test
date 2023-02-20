package pasca.test.dans.outbound;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author pascanugraha
 */
@Slf4j
@Component
public class JobOutbound {

  public String getJobList() throws Exception {
    URL url = new URL("http://dev3.dansmultipro.co.id/api/recruitment/positions.json");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    return constructResponse(conn);
  }

  public String getJobDetail(String jobId) throws Exception {
    URL url = new URL("http://dev3.dansmultipro.co.id/api/recruitment/positions/" + jobId);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    return constructResponse(conn);
  }

  private String constructResponse (HttpURLConnection conn) throws Exception {
    StringBuilder result = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
      for (String line; (line = reader.readLine()) != null; ) {
        result.append(line);
      }
    }
    return result.toString();
  }

}
