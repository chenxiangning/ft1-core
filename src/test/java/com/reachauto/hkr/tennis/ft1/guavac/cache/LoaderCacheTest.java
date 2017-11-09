package com.reachauto.hkr.tennis.ft1.guavac.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: chenxiangning
 * Date: 2017-11-09 15:33
 * This is my work in reachauto code.
 * mail:chenxiangning@reachauto.com
 * Description:
 */
public class LoaderCacheTest {
    @Test
    public void democ() {
        LoadingCache userCache = CacheBuilder.newBuilder()
                .maximumSize(10).expireAfterWrite(10, TimeUnit.SECONDS)
                .build(new CacheLoader<String, UserCacheBean>() {
                    @Override
                    public UserCacheBean load(String name) throws Exception {
                        return getUserByName(name);
                    }
                });


        try {
            for (int i = 0; i < 1; i++) {
                UserCacheBean sd = (UserCacheBean) userCache.get("sd");
                System.out.println(sd);
                UserCacheBean asf = (UserCacheBean) userCache.get("asf");
                System.out.println(asf);
                TimeUnit.SECONDS.sleep(2);
            }


            System.out.println("--------------------------");
            System.out.println(userCache.asMap());

            userCache.refresh("asf");


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private UserCacheBean getUserByName(String name) {
        UserCacheBean e11 = new UserCacheBean("sd", 12);
        UserCacheBean e12 = new UserCacheBean("asf", 3);
        UserCacheBean e13 = new UserCacheBean("ewrw", 24);
        UserCacheBean e14 = new UserCacheBean("sdf", 11);

        Map<String, UserCacheBean> database = new HashMap();
        database.put(e11.getName(), e11);
        database.put(e12.getName(), e12);
        database.put(e13.getName(), e13);
        database.put(e14.getName(), e14);

        System.out.println("#####访问数据库#####");

        return database.get(name);
    }
}