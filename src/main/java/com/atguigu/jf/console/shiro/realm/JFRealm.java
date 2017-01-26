package com.atguigu.jf.console.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.jf.console.baseapi.user.SysOpMapper;
import com.atguigu.jf.console.user.bean.pojo.SysOp;

/**
 * 自定义realm需要集成AuthorizingRealm类：
 * 两个实现方法：
 * 1、doGetAuthenticationInfo：认证的回调方法
 * 2、doGetAuthorizationInfo： 授权的回调方法
 */
public class JFRealm extends AuthorizingRealm{
	
	@Autowired
	private SysOpMapper sysOpMapper;

	/**
	 * 授权：
	 *   1、强制转换principals类型为SimplePrincipalCollection
	 *   2、通过principals获取用户的实体信息
	 *   3、可以根据数据库或者其他的方式进行授权的操作
	 *   4、构造SimpleAuthorizationInfo，并返回
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		//1、强制转换principals类型为SimplePrincipalCollection
		SimplePrincipalCollection sPricipals = (SimplePrincipalCollection) principals;
		
		//2、通过principals获取用户的实体信息
		Object principal = sPricipals.getPrimaryPrincipal();
		System.out.println("principal:" + principal);
		
		// 如果登录用户为admin
		Set<String> roles = new HashSet<>();
		roles.add("user");
		if("admin".equals(principal)){
			// 授权角色admin
			roles.add("admin");
		}
		
		//4、构造SimpleAuthorizationInfo，并返回
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRoles(roles);	// 放入集合
		
		return info;
	}
	
	
	/**
	 * 认证的步骤：
	 * 1、制作了一个表单（用户名和密码），通过表单向后台发送请求。
	 * 2、写Controller的登录doLogin方法，来接收用户名和密码。
	 *    参考Quickstart类。
	 *    执行核心的登录方法： currentUser.login(token);
	 * 3、回调了方法doGetAuthenticationInfo，登录时传入的token类型是UsernamePasswordToken，
	 *    ① token的类型应该被强制转换为UsernamePasswordToken
	 *    ② 从token中获取用户名
	 *    ③ 根据用户名去数据中查询对应的密码
	 *    ④ 需要返回AuthenticationInfo的子类SimpleAuthenticationInfo，并返回
	 *    注意：密码比对交给shiro
	 * 
	 * 加密，MD5（不可逆）：
	 * 1、加工下Realm的配置，
	 *  <property name="credentialsMatcher">
    		<bean class="org.apache.shiro.authc.credential.HashedCredentialsMatcher">
    			<!-- 指定加密算法的名称，这里使用MD5 -->
    			<property name="hashAlgorithmName" value="MD5" />
    			<!-- 指定加密次数 -->
    			<property name="hashIterations" value="1024" />
    		</bean>
    	</property>
	 * 
	 * 2、加盐
	 * ① 可以使用用户名当作盐值。
	 * ② 保证密码即使用户设置的一样，加密后得到结果每次不一样
	 * 
	 * */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		//① token的类型应该被强制转换为UsernamePasswordToken
		UsernamePasswordToken uToken = (UsernamePasswordToken)token;
		
		Object principal = uToken.getPrincipal();	//② 从token中获取用户名
		
		String password = "";	//用来保存密码

		//③ 根据用户名去数据中查询对应的密码
		SysOp sysOp = new SysOp();
		sysOp.setLoginCode((String) principal);	//把用户名放到sysOp对象中，去数据库查登录对象
		SysOp sysOp2 = null;	//用来放从数据库查出来的对象，放到info中，给LoginHandler方法中返回去，放到Session中
		try {
			sysOp2 = sysOpMapper.selectSysOpByNameAndPwd(sysOp);
			if (sysOp2 != null) {
				password = sysOp2.getLoginPasswd();	//给密码赋值
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 定义盐值
		ByteSource salt = ByteSource.Util.bytes(principal);
		
		/**
		 * 	principal:代表着用户的信息，可以放入所有有关用户的对象信息。
		 * 	sysOp2：当前用户对象
		 *  credentials ： 密码
		 *  realmName : 固定写法getName()
		 */
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysOp2, password,salt, getName());
		
		// 返回SimpleAuthenticationInfo对象
		return info;
	}

	
	
	
	
	
	
	/**
	 * 如何获取到加密后的值.
	 * 注册模块中，当用户输入了明文的密码，需要给加密，加密规则同shiro配置的加密规则
	 */
	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		Object credentials = "123456";
		int hashIterations = 1024;
		ByteSource salt = ByteSource.Util.bytes("admin");
		Object obj = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(obj);
	}
}
