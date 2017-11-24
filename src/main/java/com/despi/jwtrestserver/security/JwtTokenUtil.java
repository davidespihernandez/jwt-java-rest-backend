package com.despi.jwtrestserver.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -3301605591108950415L;

	private String secret;
	private Long expiration;

	public JwtTokenUtil(@Value("${jwt.secret}") String secret,
						@Value("${jwt.expiration}") Long expiration) {
		this.secret = secret;
		this.expiration = expiration;
	}

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		if (StringUtils.isEmpty(token)) {
			return null;
		}
		return Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(Date.from(ZonedDateTime.now().toInstant()));
	}

	private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
		return (lastPasswordReset != null && created.before(lastPasswordReset));
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		final Date createdDate = Date.from(ZonedDateTime.now().toInstant());
		final Date expirationDate = calculateExpirationDate(createdDate);

		System.out.println("doGenerateToken " + createdDate);

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(createdDate)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		if (userDetails instanceof JwtUser) {
			claims.put("userId", ((JwtUser) userDetails).getId());
			claims.put("userName", userDetails.getUsername());
			claims.put("name", ((JwtUser) userDetails).getName());
			claims.put("authorities", ((JwtUser) userDetails).getAuthorities());
		}
		return doGenerateToken(claims, userDetails.getUsername());
	}

	public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
		final Date created = getIssuedAtDateFromToken(token);
		return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
				&& (!isTokenExpired(token));
	}

	public String refreshToken(String token) {
		final Date createdDate = Date.from(ZonedDateTime.now().toInstant());
		final Date expirationDate = calculateExpirationDate(createdDate);

		final Claims claims = getAllClaimsFromToken(token);
		claims.setIssuedAt(createdDate);
		claims.setExpiration(expirationDate);

		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		if (StringUtils.isEmpty(token)) {
			return false;
		}
		JwtUser user = (JwtUser) userDetails;
		final String username = getUsernameFromToken(token);
		final Date created = getIssuedAtDateFromToken(token);
		//final Date expiration = getExpirationDateFromToken(token);
		return (
				username.equals(user.getUsername())
						&& !isTokenExpired(token)
						&& (user.getLastPasswordResetDate() == null || !isCreatedBeforeLastPasswordReset(created, Date.from(user.getLastPasswordResetDate().toInstant())))
		);
	}

	private Date calculateExpirationDate(Date createdDate) {
		return new Date(createdDate.getTime() + expiration * 1000);
	}
}
