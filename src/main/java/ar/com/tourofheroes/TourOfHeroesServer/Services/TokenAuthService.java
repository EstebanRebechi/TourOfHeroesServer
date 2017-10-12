package ar.com.tourofheroes.TourOfHeroesServer.Services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthService {
	static final long EXPIRATIONTIME = 864_000_000; // 10 days
	static final String SECRET = "ThisIsASecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	public static void addAuthentication(HttpServletResponse res, Authentication auth) {
		String JWT = Jwts.builder().setSubject(auth.getName()).claim("authorities", auth.getAuthorities())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if(token != null) {
			// parse the token.
			Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody();
			String userName = claims.getSubject();
			@SuppressWarnings("unchecked")
			ArrayList<LinkedHashMap<String, String>> arr = (ArrayList<LinkedHashMap<String, String>>) claims
					.get("authorities", Collection.class);

			List<GrantedAuthority> authorities = new ArrayList<>();
			arr.forEach(map -> {
				authorities.add(new SimpleGrantedAuthority(map.get("authority")));
			});

			Authentication auth = userName != null
					? new UsernamePasswordAuthenticationToken(userName, null, authorities) : null;
			return auth;
		}
		return null;
	}
}