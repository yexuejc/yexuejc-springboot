package com.yexuejc.springboot.base.security;

import com.yexuejc.base.util.StrUtil;
import com.yexuejc.springboot.base.exception.ClassConvertExeption;
import com.yexuejc.springboot.base.exception.UserNotAuthoriayException;
import com.yexuejc.springboot.base.security.inte.User;
import com.yexuejc.springboot.base.security.inte.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

/**
 * ConsumerJdbcDaoImpl
 *
 * @ClassName: ConsumerJdbcDaoImpl
 * @Description:ConsumerJdbcDaoImpl
 * @author: maxf
 * @date: 2017年12月21日 下午6:17:12
 */
public class UserDetailsManager extends InMemoryUserDetailsManager {

    private final UserService userService;

    public UserDetailsManager(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUser(username);
    }

    protected ConsumerUser loadUser(String username) throws UsernameNotFoundException{
        Object user = userService.getConsumerByUserName(username);
        if (user instanceof User) {
            User consumer = (User) user;
            if (StrUtil.isEmpty(consumer)) {
                throw new UsernameNotFoundException(username);
            }
            // 处理用户权限
            List<GrantedAuthority> authorities = new ArrayList<>();
            if (StrUtil.isEmpty(consumer.getRoles())) {
                throw new UserNotAuthoriayException("用户" + username + "缺少权限");
            }
            for (String role : consumer.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
            ConsumerUser consumerUser = new ConsumerUser(consumer.getMobile(), consumer.getPwd(),
                    consumer.getEnable(), consumer.getNonExpire(), true, consumer.getNonLock(),
                    authorities, consumer.getConsumerId(), null, System.currentTimeMillis());
            return consumerUser;
        } else if (user instanceof ConsumerUser) {
            return (ConsumerUser) user;
        } else {
            throw new ClassConvertExeption("获取登录用户信息返回结果类型必须是com.yexuejc.springboot.base.security.inte.User实现类" +
                    "或者com.yexuejc.springboot.base.security.ConsumerUser继承类");
        }
    }

}
