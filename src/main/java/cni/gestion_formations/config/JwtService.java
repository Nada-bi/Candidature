package cni.gestion_formations.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.security.auth.message.callback.PasswordValidationCallback;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {
    private static final String SECRET_KEY = "9/JV/pbcbq/J8sQ/W7pNXgCsZal+Xlj277rx7s3UiqoEM0udu+Dm18mWMTXj7BY1r54Rh+ioKAvegTxq6gb5J0GwMlW9eTH1vCfPD+TSGo76TWUaUDHXRCUkNpjvATNUsAs6azTSoYQFaV+4VzHY7ggKlGKWlbZzvH4veIQtfGDWn1KGCVsZC2WKN9G3DivX+kz2tFZd+x/2fCKxjMLqsEV13LZgcPZU47VrE+/eHUqTX0++n0zzeuSCAjhVSdv2nvVh99rH+7N/gtfPVrJ9zg+NETsIP6jTczwkfbv1KvOBGxRTQXKE2vft7XWHu6N1K5n+2hOmLbq9JUkDZ0yDxbSXUXQY+h4a8z2Bop9He1Y=";
    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject) ;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String generateToken(UserDetails userDetails){

        return   generateToken(new HashMap<>(),userDetails);
    }
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    )

    {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes) ;
    }
}

