import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Demo {

    public static void main(String[] args) throws Exception {

    }

    /**
     * 生成JWT
     */
    @Test
    public void testGenJwt(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","tom");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "aaaaaa")//自定义数字签名算法
                .setClaims(claims) //自定义内容(载荷)
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))//设置有效期为1h
                .compact();
        System.out.println(jwt);
    }

    /**
     * 解析JWT
     */
    @Test
    public void testParseJwt(){
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey("aaaaaa")//指定签名算法解密
                    .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEiLCJ1c2VybmFtZSI6IjEiLCJleHAiOjE3MjQ3MTA4MTB9.gzgEZDNpRpMPfosFxBAmgww_8H6_miuUxii_lSXeSWo")
                    .getBody();
            System.out.println(claims);
        } catch (ExpiredJwtException e) {
            System.err.println("令牌已过期" + e.getMessage());
        } catch (MalformedJwtException e) {
            System.err.println("令牌被篡改: " + e.getMessage());
        } catch (SignatureException e) {
            System.err.println("签名算法错误: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("其他错误: " + e.getMessage());
        }
    }
}
