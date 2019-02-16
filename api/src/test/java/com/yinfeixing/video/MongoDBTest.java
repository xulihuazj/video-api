package com.yinfeixing.video;

import com.yinfeiixng.video.model.mongo.VideoModel;
import com.yinfeixing.video.core.video.VideoMongoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplicationTests.class)
@WebAppConfiguration
public class MongoDBTest {

    @Resource
    private VideoMongoRepository videoRepositoryImpl;

    @Test
    public void testSave() {
        VideoModel model = new VideoModel();
        model.setVideoName("流浪地球");
        model.setDescribe("被称为“中国第一部真正的科幻电影”，《流浪地球》的成功被赋予了“划时代”的意义，" +
                "也吸引了许多曾经对“科幻片”这个标签不感兴趣的观众走进了电影院。" +
                "《人民日报》点评该片称：“文学艺术离不开生长的土壤，科幻电影也不例外。" +
                "科技的迅速发展，为科幻文学和科幻电影发展提供了沃土。" +
                "影片的成功反映的是电影工业乃至国家的综合实力。”");
        videoRepositoryImpl.save(model);
    }

    @Test
    public void testFindUserByUserName() {
        VideoModel user = videoRepositoryImpl.findVideoByVideoName("周家壕");
        System.out.println(user.toString());
    }

    @Test
    public void testUpdateUser() {
//        User user = new User();
//        user.setUserId(3L);
//        user.setUserName("周家壕");
//        user.setUserPhone("13111112222");
//        userRepository.update(user);
    }

    @Test
    public void testDeleteUser() {
        videoRepositoryImpl.deleteVideoById(3L);
    }
}
