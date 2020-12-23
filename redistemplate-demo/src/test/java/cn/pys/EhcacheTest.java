package cn.pys;

import cn.pys.service.UserService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.junit.Test;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import javax.annotation.Resource;

/**
 * @Description
 * @Date 2020/12/23 10:00
 * @Created by pengys
 */
public class EhcacheTest extends BaseTest{

    @Resource
    private EhCacheCacheManager ehCacheCacheManager;

    @Resource
    UserService userService;

    @Test
    public void testEhcache (){
        CacheManager create = CacheManager.create(this.getClass().getResourceAsStream("/ehcache.xml"));
        Cache cache = create.getCache("local");
        cache.put(new Element("key1", "value1"));
        Element element = cache.get("key1");
        System.out.println(element.getObjectKey()+" : "+element.getObjectValue());

    }

    @Test
    public void testEhcache2 (){
        System.out.println(userService.getAll());
        System.out.println(userService.getAll());
        System.out.println(userService.getAll());
        System.out.println(userService.getAll());
    }
}
