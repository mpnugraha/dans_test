package pasca.test.dans.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@EqualsAndHashCode
@ToString
@Data
@Document("user")
public class User {

  @Id
  @Field("_id")
  private String id;

  @Field("username")
  private String username;

  @Field("password")
  private String password;

}
