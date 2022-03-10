package springsecurity.demo.SpringSecurityDemo.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JwtTokenProvider
{
    public static String generateToken(String userName) throws Exception
    {
        Instant now = Instant.now();
        String jwtToken = Jwts.builder()
                .claim("name", userName)
                .setSubject("jwt_token")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(5l, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.RS512, getPrivateKey())
                .compact();

        return jwtToken;
    }

    private static Key getPrivateKey() throws Exception
    {
        ClassLoader classLoader = JwtTokenProvider.class.getClassLoader();
    //    File file = new File(classLoader
   //     		.getResource("D:\\personal project data_1\\data-structure\\SpringSecurityDemo\\src\\main\\resources\\private.key").getFile());
        File file = new File("D:\\\\personal project data_1\\\\data-structure\\\\SpringSecurityDemo\\\\src\\\\main\\\\resources\\\\private.key");
        byte[] rsaPrivateKeyArr = FileUtils.readFileToByteArray(file);
        String rsaPrivateKey = new String(rsaPrivateKeyArr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getMimeDecoder().decode(rsaPrivateKey));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(keySpec);

    }

}
