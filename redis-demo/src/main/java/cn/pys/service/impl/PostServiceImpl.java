package cn.pys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.pys.mapper.PostMapper;
import cn.pys.model.Post;
import cn.pys.service.PostService;
import cn.pys.utils.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    JedisUtil redisUtil;

    @Override
    public void initIndexWeekRank() {
        Map<String, Object> map = new HashMap<>();
        map.put("endTime", new Date());
        Date now = new Date();
        Date startTime = DateUtil.offsetDay(now, -7).toJdkDate();
        map.put("startTime", startTime);

        List<Post> posts = postMapper.selectByMap(map);
        if (CollectionUtil.isEmpty(posts)) return;

        for (Post post : posts) {
            String key = "day_rank:" + DateUtil.format(post.getCreateTime(), DatePattern.PURE_DATE_FORMAT);
            long between = DateUtil.between(now, post.getCreateTime(), DateUnit.DAY);
            long expireTime = (7 - between) * 24 * 60;

            redisUtil.zadd(key, post.getCommentCount(), post.getPostId());
            redisUtil.expire(key, (int) expireTime);
            hashCachePost(post);
        }
        unionZset();

    }

    /**
     * 缓存post信息，使得通过postId能得到post信息
     *
     * @param post
     */
    private void hashCachePost(Post post) {
        boolean exists = redisUtil.exists("rank_post_" + post.getPostId());
        if (!exists) {
            long between = DateUtil.between(new Date(), post.getCreateTime(), DateUnit.DAY);
            int expireTime = (int) (7 - between) * 24 * 60;
            redisUtil.hset("rank_post_" + post.getPostId(), "postId", post.getPostId());
            redisUtil.hset("rank_post_" + post.getPostId(), "title", post.getTitle());
            redisUtil.expire("rank_post_" + post.getPostId(), expireTime);
        }
    }

    /**
     * 合并zset到last_week_rank
     */
    private void unionZset() {
        String prefix = "day_rank:";
        List<String> keys = new ArrayList<>();
        Date now = new Date();
        for (int i = -7; i < 0; i++) {
            Date date = DateUtil.offsetDay(now, i).toJdkDate();
            keys.add(prefix + DateUtil.format(date, DatePattern.PURE_DATE_FORMAT));
        }
        redisUtil.zunionstore("last_week_rank", keys.toArray(new String[keys.size()]));
    }

}
