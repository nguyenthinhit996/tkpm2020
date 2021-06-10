package com.minahotel.sourcebackend.security;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.authority.AuthorityUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.minahotel.sourcebackend.common.BeanUtil;
import com.minahotel.sourcebackend.common.DefinationCommon;
import com.minahotel.sourcebackend.pojo.UserCustomize;
import com.minahotel.sourcebackend.services.StaffRepositoryServices;

/**
 * JwtUtilsCustomize is class to create token, it class ordinary(bình thường) not container-managed by Spring IOC
 * so if you use a @bean of spring you must use class {@link BeanUtil} to do that
 * @author Peter
 *
 */
public class JwtUtilsCustomize {
	
	/**
	 * Get accesstoken from use Id
	 * @param idUser
	 * @return access token
	 */
    public static String createAccessTokenByIdUser(String idUser) {
    	
    	StaffRepositoryServices userdetailService = BeanUtil.getBean(StaffRepositoryServices.class);	
    	UserCustomize user = (UserCustomize) userdetailService.loadUserByUsername(idUser);
    	List<String> lsAuthorization =List.copyOf(AuthorityUtils.authorityListToSet(user.getAuthorities()));
    	
        String accessToken = JWT.create()
                .withSubject(user.getIdStaff())
                .withClaim(DefinationCommon.ROLE,lsAuthorization)
                .withClaim(DefinationCommon.AUTHENTICATED,Boolean.TRUE)
                .withClaim(DefinationCommon.FULLNAME,user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME_ACCESSTOKEN))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
        
        return accessToken;
    }
}
