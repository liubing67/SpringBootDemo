package com.springboot.zXingCode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@Slf4j
public class ZXingController {

    /**
     * 查看二维码
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/seePaymentCode")
    public String seePaymentCode(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        String ctxPath = request.getSession().getServletContext().getRealPath(File.separator) + File.separator + "resources" + File.separator + "images" + File.separator;
        System.out.println(ctxPath);
        ZXingCode.drawLogoQRCode(new File(ctxPath + "qr_logo.png"), new File(ctxPath + "qr_code.png"), "www.baidu.com", 0, 0);
        ZXingCode.mergeImage(ctxPath + "qr_background.png", ctxPath + "qr_patmentCode.png", ctxPath + "qr_code.png", "0", "0");
        return "tPaymentCode/seeQRCode";
    }

    /**
     * 下载二维码页面跳转
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/downPaymentCode")
    public String downPaymentCode(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        return "tPaymentCode/downQRCode";
    }

    /**
     * 下载二维码
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/downQRCode")
    public void downQRCode(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        String sizes = request.getParameter("sizes");
        if (StringUtils.isEmpty(sizes)) {
            throw new Exception( "下载尺寸不能为空！");
        }
        //获取尺寸
        String size[] = sizes.split(",");
        String ctxPath = request.getSession().getServletContext().getRealPath(File.separator) + File.separator + "resources" + File.separator + "images" + File.separator;
        System.out.println(ctxPath);
        for (int i = 0; i < size.length; i++) {
            String s = size[i];
            switch (s) {
                case "size_8":
                    ZXingCode.drawLogoQRCode(new File(ctxPath + "qr_logo.png"), new File(ctxPath + "qr_code.png"),"www.baidu.com", 0, 0);
                    ZXingCode.mergeImage(ctxPath + "qr_background.png", ctxPath + s + ".png", ctxPath + "qr_code.png", "0", "0");
                    break;
                case "size_15":
                    ZXingCode.drawLogoQRCode(new File(ctxPath + "qr_logo.png"), new File(ctxPath + "qr_code.png"), "www.baidu.com",0, 0);
                    ZXingCode.mergeImage(ctxPath + "qr_background.png", ctxPath + s + ".png", ctxPath + "qr_code.png", "0", "0");
                    break;
                case "size_30":
                    ZXingCode.drawLogoQRCode(new File(ctxPath + "qr_logo.png"), new File(ctxPath + "qr_code.png"), "www.baidu.com", 0, 0);
                    ZXingCode.mergeImage(ctxPath + "qr_background.png", ctxPath + s + ".png", ctxPath + "qr_code.png", "0", "0");
                    break;
            }
        }

        downFile(request, response, sizes, ctxPath);
    }

    /**
     * 下载多张图片并zip压缩
     *
     * @param request
     * @param response
     * @param fileNames
     * @param ctxPath
     * @throws IOException
     */
    public static void downFile(HttpServletRequest request, HttpServletResponse response, String fileNames, String ctxPath) throws IOException {
        try {
            // File.separator 就是路径分割反斜杠
            //获取尺寸名称
            String names[] = fileNames.split(",");
            List<File> files = new ArrayList<File>();
            for (int j = 0; j < names.length; j++) {
                String fileName = names[j] + ".png";
                fileName = URLEncoder.encode(fileName, "UTF-8");
                String filedownload = ctxPath + fileName;
                files.add(new File(filedownload));
            }
            downLoadFiles(files, request, response);
        } catch (Exception e) {
            log.info("Error!");
            e.printStackTrace();
        } finally {

        }
    }


    public static HttpServletResponse downLoadFiles(List<File> files, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            /**这个集合就是你想要打包的所有文件，
             * 这里假设已经准备好了所要打包的文件
             */

            //List<File> files = new ArrayList<File>();

            /**创建一个临时压缩文件，
             * 我们会把文件流全部注入到这个文件中
             * 这里的文件你可以自定义是.rar还是.zip
             　　      * 这里的file路径发布到生产环境时可以改为
             */
            File file = new File(request.getSession().getServletContext().getRealPath("/qrcode.zip"));
            if (!file.exists()) {
                file.createNewFile();
            }
            response.reset();
            //response.getWriter()
            //创建文件输出流
            FileOutputStream fous = new FileOutputStream(file);
            /**打包的方法我们会用到ZipOutputStream这样一个输出流,
             * 所以这里我们把输出流转换一下*/
            //            org.apache.tools.zip.ZipOutputStream zipOut
            //                = new org.apache.tools.zip.ZipOutputStream(fous);
            ZipOutputStream zipOut = new ZipOutputStream(fous);
            /**这个方法接受的就是一个所要打包文件的集合，
             * 还有一个ZipOutputStream
             */
            zipFile(files, zipOut);
            zipOut.close();
            fous.close();
            return downloadZip(file, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**直到文件的打包已经成功了，
         * 文件的打包过程被我封装在FileUtil.zipFile这个静态方法中，
         * 稍后会呈现出来，接下来的就是往客户端写数据了
         */
        // OutputStream out = response.getOutputStream();

        return response;
    }

    /**
     * 把接受的全部文件打成压缩包
     *
     * @param List<File>;
     * @param org.apache.tools.zip.ZipOutputStream
     */
    public static void zipFile(List files, ZipOutputStream outputStream) {
        int size = files.size();
        for (int i = 0; i < size; i++) {
            File file = (File) files.get(i);
            zipFile(file, outputStream);
        }
    }

    public static HttpServletResponse downloadZip(File file, HttpServletResponse response) {
        try {
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();

            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                File f = new File(file.getPath());
                f.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    /**
     * 根据输入的文件与输出流对文件进行打包
     *
     * @param File
     * @param org.apache.tools.zip.ZipOutputStream
     */
    public static void zipFile(File inputFile, ZipOutputStream ouputStream) {
        try {
            if (inputFile.exists()) {
                /**如果是目录的话这里是不采取操作的，
                 * 至于目录的打包正在研究中
                 */
                if (inputFile.isFile()) {
                    FileInputStream IN = new FileInputStream(inputFile);
                    BufferedInputStream bins = new BufferedInputStream(IN, 512);
                    //org.apache.tools.zip.ZipEntry
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    ouputStream.putNextEntry(entry);
                    // 向压缩文件中输出数据
                    int nNumber;
                    byte[] buffer = new byte[512];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                    // 关闭创建的流对象
                    bins.close();
                    IN.close();
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        for (int i = 0; i < files.length; i++) {
                            zipFile(files[i], ouputStream);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载单张图片
     *
     * @param request
     * @param response
     * @param TEMPLATE_NAME
     * @param ctxPath
     * @throws IOException
     */
    public static void downPng(HttpServletRequest request, HttpServletResponse response, String TEMPLATE_NAME, String ctxPath) throws IOException {
        OutputStream outp = null;
        FileInputStream in = null;
        try {
            String fileName = TEMPLATE_NAME; // 要下载的模板文件
            // File.separator 就是路径分割反斜杠
            String filedownload = ctxPath + fileName;
            fileName = URLEncoder.encode(fileName, "UTF-8");
            // 要下载的模板所在的绝对路径
            response.reset();
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentType("application/octet-stream;charset=UTF-8");
            outp = response.getOutputStream();
            in = new FileInputStream(filedownload);
            byte[] b = new byte[1024];
            int i = 0;
            while ((i = in.read(b)) > 0) {
                outp.write(b, 0, i);
            }
            outp.flush();
        } catch (Exception e) {
            log.info("Error!");
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
                in = null;
            }
            if (outp != null) {
                outp.close();
                outp = null;
            }
        }
    }
}
