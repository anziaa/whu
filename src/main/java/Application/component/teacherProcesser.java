package Application.component;

import Application.entity.TeaInfo;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.selector.Selector;

import java.util.List;

@Component
public class teacherProcesser implements PageProcessor {

    @Override
    public void process(Page page) {
        Html html =page.getHtml();

        parseTeaInfo(page);

        List<String> links =html.css("div.info").links().all();
        page.addTargetRequests(links);
        page.getResultItems().setSkip(true);


    }

    private void parseTeaInfo(Page page){
            Html html = page.getHtml();
            List<Selectable> name =html.css("table tr td.w1 a","text").nodes();
        System.out.println(name.toString());
        List<Selectable> sex =html.css("table tr td.w2 ","text").nodes();
        System.out.println(sex.toString());
        List<Selectable> post =html.css("table tr td.w4","text").nodes();
        System.out.println(post.toString());
        List<Selectable> dir =html.css("table tr td.w5","text").nodes();
        System.out.println(dir.toString());

for(int i = 0;i<name.size()-1;i++){
        TeaInfo teaInfo = new TeaInfo();

        teaInfo.setName(name.get(i).toString());
        teaInfo.setSex(sex.get(i+1).toString());
        teaInfo.setDir(dir.get(i+1).toString());
        teaInfo.setPost(post.get(i+1).toString());
        page.putField("teaInfo",teaInfo);
}
    }


    public String getType(Object o){
        return o.getClass().toString();
    }


    @Override
    public Site getSite() {
        return Site.me();
    }
}
