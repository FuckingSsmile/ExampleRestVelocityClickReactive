package ru.home.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import ru.home.exceptions.VelocityProcessingException;
import ru.home.velocity.TemplateType;
import ru.home.velocity.VelocityContextCreator;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Класс для работы с SQL-запросами и Velocity шаблонами.
 */
@UtilityClass
@Slf4j
public class SqlUtils {

    /**
     * Метод для получения VelocityContext
     *
     * @param velocityContextCreator интерфейс для создания VelocityContext
     * @return VelocityContext, созданный на основе velocityContextCreator
     */
    public VelocityContext getVelocityContext(VelocityContextCreator velocityContextCreator) {
        return velocityContextCreator.toVelocityContext();
    }

    /**
     * Метод для создания SQL-запроса на основе контекста и шаблона.
     *
     * @param context      VelocityContext, содержащий данные для заполнения шаблона
     * @param baseTemplate шаблон Velocity
     * @return String, сгенерированный SQL-запрос
     */
    public static String getSql(VelocityContext context, Template baseTemplate) {
        try (StringWriter writer = new StringWriter()) {
            baseTemplate.merge(context, writer);
            return writer.toString();
        } catch (IOException e) {
            String messageError = "Ошибка ввода-вывода при обработке шаблона";
            log.error(messageError, e);
            throw new VelocityProcessingException(messageError, e);
        } catch (VelocityException e) {
            String messageError = "Ошибка в обработке Velocity";
            log.error(messageError, e);
            throw new VelocityProcessingException(messageError, e);
        }
    }

    public static String getSql(Template baseTemplate) {
        VelocityContext context = new VelocityContext();
        context.put("count", Long.class);

        return getSql(context, baseTemplate);
    }

    /**
     * Метод для получения Velocity шаблона на основе типа поиска и типа шаблона.
     *
     * @param templateType тип шаблона
     * @return Template, Velocity шаблон
     */
    public static Template getVelocityTemplate(TemplateType templateType) {

        String templateDirectory = String.format("sql_template/%s.vm",
                templateType.name().toLowerCase());

        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class",
                ClasspathResourceLoader.class.getName());
        velocityEngine.init();
        try {
            return velocityEngine.getTemplate(templateDirectory, "UTF-8");
        } catch (VelocityException e) {
            String messageError = "Ошибка в обработке Velocity";
            log.error(messageError, e);
            throw new VelocityProcessingException(messageError, e);
        }
    }
}