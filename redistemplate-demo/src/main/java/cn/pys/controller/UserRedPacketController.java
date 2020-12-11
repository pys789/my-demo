package cn.pys.controller;

import cn.pys.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/redpacket")
public class UserRedPacketController {

    @Autowired
    private UserRedPacketService userRedPacketService;

    @RequestMapping("/grap")
    @ResponseBody
    public Map<String, Object> grapRedPacketByRedis(Integer redPacketId, Long userId) {
        Map<String, Object> resultMap = new HashMap<>();
        Long result = userRedPacketService.grapRedPacketByRedis(redPacketId, userId);
        boolean flag = result > 0;
        resultMap.put("success", flag);
        resultMap.put("userId", userId);
        return resultMap;
    }
}