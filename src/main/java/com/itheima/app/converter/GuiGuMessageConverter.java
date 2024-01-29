package com.itheima.app.converter;

import com.itheima.app.entity.Person;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @ClassName GuiGuMessageConverter
 * @Description TODO
 * @Author 20609
 * @Date 2023/12/8 14:49
 * @Version 1.0
 */
public class GuiGuMessageConverter implements HttpMessageConverter<Person> {
    //所谓的支持读就是在控制器的方法上标注了@RequestBody注解的参数
    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }
    //支持写person类型的数据
    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return clazz.isAssignableFrom(Person.class);
    }
    //服务器要统计所有的messageConverter都能写出那些内容类型
    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return MediaType.parseMediaTypes("application/x-guigu");
    }

    @Override
    public Person read(Class<? extends Person> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }
    //自定义协议的数据写出
    @Override
    public void write(Person person, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String data = person.getName()+";"+person.getAge();
        OutputStream body = outputMessage.getBody();
        body.write(data.getBytes());
    }
}
