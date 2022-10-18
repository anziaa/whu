package Application.component;

import us.codecraft.webmagic.pipeline.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.Scheduler;


@Component
public class TeaSpider {

    @Autowired
    private PageProcessor pageProcessor;

    @Autowired
    private Pipeline pipeline;

    @Autowired
    private Scheduler scheduler;
    private String starUrl = "http://cs.whu.edu.cn/teacher.aspx?showtype=jobtitle&typename=%e6%95%99%e6%8e%88";

    public void doCrawler(){

        Spider.create(pageProcessor)
                .addPipeline(pipeline)
                .setScheduler(scheduler)
                .addUrl(starUrl)
                .start();
    }
}
