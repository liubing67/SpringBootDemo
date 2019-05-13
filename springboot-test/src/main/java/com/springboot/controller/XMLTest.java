package com.springboot.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
/**
 *
 * @author y
 */
public class XMLTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws DocumentException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "李四");
        map.put("age", 25);
        
        //System.out.println(map2xml(map));
        
        
        List<Object> list = new ArrayList<Object>();
        list.add("测试1");
        list.add("测试2");
        list.add("测试3");
        
        //System.out.println(list2xml(list,"items"));
        
        List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("name", "张三");
        map1.put("age", 23);
        listMap.add(map1);
        map1 = new HashMap<String,Object>();
        map1.put("name", "李四");
        map1.put("age", 24);
        listMap.add(map1);
        map1 = new HashMap<String,Object>();
        map1.put("name", "王五");
        map1.put("age", 25);
        listMap.add(map1);
        
        System.out.println(listMap2xml(listMap,"users","user"));

//        test4();
    }
    
    
    public static String listMap2xml(List<Map<String,Object>> list,String listRoot,String mapRoot){
        Document doc = DocumentHelper.createDocument();
        
        Element rootEle = doc.addElement("result");
        Element noEle = rootEle.addElement("no");
        Element msgEle = rootEle.addElement("msg");
        
        if(null!=list && !list.isEmpty()){
            noEle.setText("1");
            msgEle.setText("成功获取相关信息");
            
            Element listRootEle = rootEle.addElement(listRoot);
            
            for(Map<String,Object> map:list){
                
                Element mapRootELe = listRootEle.addElement(mapRoot);
                
                Set<Map.Entry<String,Object>> set = map.entrySet();
                Iterator<Map.Entry<String,Object>> iter = set.iterator();
                while(iter.hasNext()){
                    Map.Entry<String,Object> entry = (Map.Entry<String,Object>)iter.next();
                    
                    Element ele = mapRootELe.addElement(entry.getKey());
                    ele.setText(String.valueOf(entry.getValue()));
                }
            }
        }else{
            noEle.setText("0");
            msgEle.setText("没有获取到相关信息");
        }
        
        StringWriter sw = new StringWriter();
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        
        try {
            
            XMLWriter xmlWriter = new XMLWriter(sw, format);
            
            xmlWriter.write(doc);
        } catch (IOException ex) {
            Logger.getLogger(XMLTest.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                sw.close();
            } catch (IOException ex) {
                Logger.getLogger(XMLTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return sw.toString();
    }

    
    public static String list2xml(List<Object> list,String itemRoot){
        Document doc = DocumentHelper.createDocument();
        
        Element rootEle = doc.addElement("result");
        Element noEle = rootEle.addElement("no");
        Element msgEle = rootEle.addElement("msg");
        
        if(null!=list && !list.isEmpty()){
            noEle.setText("1");
            msgEle.setText("成功获取相关信息");
            
            Element itemRootEle = rootEle.addElement(itemRoot);
            
            for(Object obj:list){
                Element ele = itemRootEle.addElement("item");
                ele.setText(String.valueOf(obj));
            }
        }else{
            noEle.setText("0");
            msgEle.setText("没有获取到相关信息");
        }
        
        StringWriter sw = new StringWriter();
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        
        try {
            
            XMLWriter xmlWriter = new XMLWriter(sw, format);
            
            xmlWriter.write(doc);
        } catch (IOException ex) {
            Logger.getLogger(XMLTest.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                sw.close();
            } catch (IOException ex) {
                Logger.getLogger(XMLTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return sw.toString();
    }
    
    public static String map2xml(Map<String, Object> map) {
        Document doc = DocumentHelper.createDocument();
        
        Element rootEle = doc.addElement("result");
        
        Element noEle = rootEle.addElement("no");
        Element msgEle = rootEle.addElement("msg");
        
        if(null!=map && !map.isEmpty()){
            noEle.setText("1");
            msgEle.setText("成功获取相关信息");
            
            Set<Map.Entry<String, Object>> set = map.entrySet();
            Iterator<Map.Entry<String, Object>> iter = set.iterator();
            while(iter.hasNext()){
                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iter.next();
                
                Element ele = rootEle.addElement(entry.getKey());
                ele.setText(String.valueOf(entry.getValue()));
            }
        }else{
            noEle.setText("0");
            msgEle.setText("没有获取到相关信息");
        }
        
        StringWriter sw = new StringWriter();
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        
        try {
            
            XMLWriter xmlWriter = new XMLWriter(sw, format);
            
            xmlWriter.write(doc);
        } catch (IOException ex) {
            Logger.getLogger(XMLTest.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                sw.close();
            } catch (IOException ex) {
                Logger.getLogger(XMLTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return sw.toString();
    }
    
    


    public static void test4() {
        Document doc = DocumentHelper.createDocument();

        Element rootEle = doc.addElement("sudent");

        Element nameEle = rootEle.addElement("name");
        nameEle.setText("张三");

        Element ageEle = rootEle.addElement("age");
        ageEle.setText("25");

        try {
            StringWriter sw = new StringWriter();
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
            XMLWriter xmlWriter = new XMLWriter(sw, format);

            xmlWriter.write(doc);

            System.out.println(sw.toString());
        } catch (IOException ex) {
            Logger.getLogger(XMLTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void test3() throws DocumentException {
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<books>"
                + "    <book>"
                + "        <name>Think in Java</name>"
                + "        <price>120.0</price>"
                + "           <chapters>"
                + "               <c>001</c>"
                + "               <c>002</c>"
                + "               <c>003</c>"
                + "           </chapters>"
                + "    </book>"
                + "    <book>"
                + "        <name>Think in Java2</name>"
                + "        <price>220.0</price>"
                + "    </book>"
                + "</books>";
        Document doc = DocumentHelper.parseText(str);
        Element books = doc.getRootElement();
        List<Element> childEles = books.elements();
        Iterator<Element> iter = childEles.iterator();
        while (iter.hasNext()) {
            Element book = iter.next();

            Element name = book.element("name");
            Element price = book.element("price");

            System.out.println("name:" + name.getText() + ",price:" + price.getText());

            Element chapters = book.element("chapters");
            if (null != chapters) {
                Iterator<Element> chaptersIter = chapters.elementIterator();
                if (null != chaptersIter) {
                    while (chaptersIter.hasNext()) {
                        Element c = chaptersIter.next();
                        System.out.println("===>" + c.getText());
                    }
                }
            }

        }
    }

    public static void test2() throws DocumentException {
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<books>"
                + "    <book>"
                + "        <name>Think in Java</name>"
                + "        <price>120.0</price>"
                + "    </book>"
                + "    <book>"
                + "        <name>Think in Java2</name>"
                + "        <price>220.0</price>"
                + "    </book>"
                + "</books>";

        Document doc = DocumentHelper.parseText(str);

        Element books = doc.getRootElement();

        List<Element> childEles = books.elements();
        Iterator<Element> iter = childEles.iterator();
        while (iter.hasNext()) {
            Element book = iter.next();

            Element name = book.element("name");
            Element price = book.element("price");

            System.out.println("name:" + name.getText() + ",price:" + price.getText());
        }
    }

    public static void test1() throws DocumentException {
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<dzswdjz>"
                + "    <qr_code>"
                + "　　　<nsrsbh>nsrsbh</nsrsbh >"
                + "　　　<retStatus >retStatus</retStatus>"
                + "　　　<img_name>img_name</img_name>"
                + "      <img_byteString>img_byteString</img_byteString>"
                + "   </qr_code>"
                + "</dzswdjz>";
        Document doc = DocumentHelper.parseText(str);

        //获取到父节点
        Element dzswdjz = doc.getRootElement();

        //定位到qr_code节点
        Element qr_code = dzswdjz.element("qr_code");

        Element nsrsbh = qr_code.element("nsrsbh");
        Element retStatus = qr_code.element("retStatus");
        Element img_name = qr_code.element("img_name");
        Element img_byteString = qr_code.element("img_byteString");

        System.out.println("nsrsbh:" + nsrsbh.getText());
        System.out.println("retStatus:" + retStatus.getText());
        System.out.println("img_name:" + img_name.getText());
        System.out.println("img_byteString:" + img_byteString.getText());
    }
}