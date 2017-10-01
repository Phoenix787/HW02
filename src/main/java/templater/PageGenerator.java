package templater;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class PageGenerator {
    private final static String HTML_DIR = "templates";
    private static PageGenerator pageGenerator;
    private final Configuration cfg;

    private PageGenerator() {
        cfg = new Configuration(Configuration.VERSION_2_3_22);
    }

    public static PageGenerator getInstance() {
        if (pageGenerator == null) {
            pageGenerator = new PageGenerator();
        }
        return pageGenerator;
    }

    public String getPage(String fileName, Map<String, Object> data) throws IOException {
        Writer stream = new StringWriter();

        cfg.setDirectoryForTemplateLoading(new File(HTML_DIR));
        cfg.setDefaultEncoding("UTF-8");
        try {
            Template template = cfg.getTemplate(fileName);
            template.process(data, stream);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }

}
