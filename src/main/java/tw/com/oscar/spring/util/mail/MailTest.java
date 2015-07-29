/**
 * MailTest.java
 * Title: DTS Project
 * Copyright: Copyright(c)2015, Acer
 *
 * @author Oscar Wei
 * @since 2015/7/2
 * <p>
 * H i s t o r y
 * 2015/7/21 Oscar Wei v2
 * + Adding Freemarker template features
 * <p>
 * 2015/7/2 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.spring.util.mail;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.*;

/**
 * <p>
 * Title: MailTest.java
 * </p>
 * <strong>Description:</strong> A main class for testing email sending <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Acer Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v2, 2015/7/21
 * @since 2015/7/2
 */
public class MailTest {

    public static void main(String[] args) throws Exception {
        /** Process server info object **/
        SimpleServerInfo serverInfo = new SimpleServerInfo();
        serverInfo.setHost("10.36.1.26");
        /** Process mail info object **/
        SimpleMailInfo mailInfo = new SimpleMailInfo();
        mailInfo.setServerInfo(serverInfo);
        mailInfo.setDebug(Optional.of(true));
        mailInfo.setFrom("Oscar_Wei@acer.com.tw"); //
        // mailInfo.setFromBy(Optional.ofNullable("Oscar Wei"));
        mailInfo.setTo("Oscar_Wei@acer.com.tw");
        // mailInfo.setCc(new String[]{"Oscar_Wei@acer.com.tw"});
        // mailInfo.setBcc(new String[]{"Oscar_Wei@acer.com.tw"});
        // mailInfo.setReplyTo(new String[]{"Oscar_Wei@acer.com.tw"});
        mailInfo.setSubject("Test");
        mailInfo.setContent("Email Testing...");
        // File file = new File("/Users/oscarwei168/Tools/Docs/Acer/Spec/CRM/RewardPoint/Flow_01_TempImportOM_v2.03.vsd");
        // mailInfo.setAttachment(file);

        Configuration cfg = getFreemarkerConfig();
        Template template = cfg.getTemplate("dtsMailSample.ftl");
        // Template template = cfg.getTemplate("dtsRejectMailSample.ftl");
        // Template template = cfg.getTemplate("dtsCompleteMailSample1.ftl");
        // Template template = cfg.getTemplate("dtsCompleteMailSample2.ftl");
        // data-model
        Map<String, Object> root = new HashMap<>();
        root.put("applicant", "Sammi Huang");
        root.put("dutyAgent", "Hank Chen");
        root.put("startDate", new Date());
        root.put("endDate", new Date());
        root.put("description", "那天也申請休假");
        root.put("reason", "有事待處理");
        Writer out = new StringWriter();
        template.process(root, out);
        System.out.println(out.toString());
        mailInfo.setHtmlContent(out.toString());

        MailEngine engine = new MailEngineImpl();
        engine.send(mailInfo);
    }

    private static Configuration getFreemarkerConfig() throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File("/Users/oscarwei168/IdeaProjects/DTS/src/main/resources/template"));
        TemplateLoader templateLoader = new FileTemplateLoader(new ClassPathResource("path").getFile());
        cfg.setTemplateLoader(templateLoader);
        cfg.setDateFormat("yyyy/MM/dd");
        cfg.setDateTimeFormat("yyyy/MM/dd HH:mm");
        cfg.setNumberFormat("###,###.##");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.getDefault());
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setSharedVariable("dtsServerUrl", "http://10.36.4.82:8088/dts/login");
        return cfg;
    }
}
