package com.hj;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


import java.io.*;
import java.util.List;

public class App {


    public static void main(String[] args) throws DocumentException, IOException {
//        createDom4j(new File("hj.xml"));
//        readXml();

        readString();
    }

    public static void readString() throws DocumentException, IOException {
        String xml ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<root>\n" +
                "    <person attr=\"root noe\" hj=\"123\">\n" +
                "        <people attr=\"child one\">person one child one</people>\n" +
                "        <people attr=\"child two\">person one child two</people>\n" +
                "    <hj12 hj=\"33\"/></person>\n" +
                "    <person attr=\"root two\">\n" +
                "        <people attr=\"child one\">person two child one</people>\n" +
                "        <people attr=\"child two\">person two child two</people>\n" +
                "    </person>\n" +
                "</root>";

        InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
        SAXReader reader = new SAXReader();
        Document read = reader.read(inputStream);


        List selectNodes = read.selectNodes("root/person[@attr=\"root noe\"]");

        for (int i = 0; i < selectNodes.size(); i++) {

            Element element =(Element) selectNodes.get(i);
            element.addElement("hj12").addAttribute("hj","33");

            List attributes = element.attributes();

            element.addAttribute("hj","123");

        }


        StringWriter stringWriter = new StringWriter();
        XMLWriter writer = new XMLWriter(stringWriter);
        writer.write(read);
        writer.close();

        System.out.println(stringWriter.toString());

    }


    public static void readXml() throws DocumentException, IOException {
        SAXReader reader = new SAXReader();
        Document read = reader.read(new File("hj.xml"));



        List selectNodes = read.selectNodes("root/person[@attr=\"root noe\"]");

        for (int i = 0; i < selectNodes.size(); i++) {

            Element element =(Element) selectNodes.get(i);
            element.addElement("hj12").addAttribute("hj","33");

            List attributes = element.attributes();

            element.addAttribute("hj","123");

        }


        StringWriter stringWriter = new StringWriter();
        XMLWriter writer = new XMLWriter(stringWriter);
        writer.write(read);
        writer.close();

        System.out.println(stringWriter.toString());

    }


    public static void createDom4j(File file) {
        try {
            // 创建一个Document实例
            Document doc = DocumentHelper.createDocument();

            // 添加根节点
            Element root = doc.addElement("root");

            // 在根节点下添加第一个子节点
            Element oneChildElement = root.addElement("person").addAttribute("attr", "root noe");
            oneChildElement.addElement("people")
                    .addAttribute("attr", "child one")
                    .addText("person one child one");
            oneChildElement.addElement("people")
                    .addAttribute("attr", "child two")
                    .addText("person one child two");

            // 在根节点下添加第一个子节点
            Element twoChildElement = root.addElement("person").addAttribute("attr", "root two");
            twoChildElement.addElement("people")
                    .addAttribute("attr", "child one")
                    .addText("person two child one");
            twoChildElement.addElement("people")
                    .addAttribute("attr", "child two")
                    .addText("person two child two");

            // xml格式化样式
            // OutputFormat format = OutputFormat.createPrettyPrint(); // 默认样式

            // 自定义xml样式


            // 输出xml文件
            XMLWriter writer = new XMLWriter(new FileOutputStream(file));
            writer.write(doc);
            System.out.println("dom4j CreateDom4j success!");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
