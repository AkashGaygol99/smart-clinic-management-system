package com.smartclinic.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

```
private static final String SECRET =
        "1234567890123456789012345678901234567890123456789012345678901234";

private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

public String generateToken(String email) {

    return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(
                    new Date(System.currentTimeMillis() + 86400000)
            )
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
}

public boolean validateToken(String token) {
    try {
        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        return true;
    } catch (Exception e) {
        return false;
    }
}
```

}
