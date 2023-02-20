package pasca.test.dans.library;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pasca.test.dans.entity.User;

@Component
@Slf4j
public class JwtUtils {

    private static String secret = "asdfghjkl";
    private static Long expiryDurationInMinutes = 60L;

    public String generateJwt(User user){

        long expiryTime = System.currentTimeMillis() + expiryDurationInMinutes * 60 * 1000;

        Claims claims = Jwts.claims()
                .setIssuer(user.getId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(expiryTime));

        claims.put("userId", user.getId());
        claims.put("username", user.getUsername());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims verify(String authorization) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
        } catch(Exception e) {
            log.info("Access Denied");
            return null;
        }

    }
}
