package com.dsd.lottery.ws;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import com.dsd.lottery.excel.constant.ExcelConstant;
import com.dsd.lottery.excel.facade.ExcelFacade;
import com.dsd.lottery.excel.model.ExcelResult;
import com.dsd.lottery.excel.util.ExcelCreate;
import com.dsd.lottery.model.PageModel;
import com.dsd.lottery.util.CloseUtil;
import com.dsd.lottery.util.FileUtil;
import com.dsd.lottery.util.log.LogUtil;

/**
 * 生成解析数据
 * 
 * @author daishengda
 *
 */
@Path("/excel")
public class WSExcelService {

    /**
     * 解析数据
     * 
     * @param request
     * @return
     */
    @POST
    @Path("/parse")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> parse(@Multipart("file") Attachment file, @Multipart(
            value = "diffStage", type = "text/plain") String diffStage) {
        Map<String, Object> map = new HashMap<String, Object>();
        String template = generateTemplate(file);
        initTemplate(template);
        // cleanHandle();
        if (template != null) {
            try {
                int difStage = StringUtils.isNotEmpty(diffStage) ? Integer.parseInt(diffStage) : 1;
                ExcelFacade facede = new ExcelFacade(template, difStage);
                String toPath = facede.handle();
                map.put("status", new File(toPath).getName());
                return map;
            } catch (Exception e) {
                LogUtil.error(ExcelConstant.LOG_EXCEL_PROJECTID, "生成结果数据失败！", e);
            }
        }
        map.put("status", false);
        return map;
    }

    private void initTemplate(String template) {
        ExcelCreate create = null;
        try {
            create = new ExcelCreate(template, template);
            create.cleanSheetData(1, 10);
            create.write();
        } finally {
            if (create != null) {
                create.close();
            }
        }
    }

    /*
     * private void cleanHandle() { String cleanSwitch =
     * CommonProperties.getCommonValue(PropertiesConst.CLEAN_SWITCH);
     * if("true".equalsIgnoreCase(cleanSwitch)) { String path =
     * this.getClass().getResource("/").getPath();
     * 
     * CleanThread clean = new CleanThread(path); clean.start(); try { String parent = new
     * File(path).getParentFile().getParentFile().getPath(); CleanThread cleanParent = new
     * CleanThread(parent); cleanParent.start(); } catch (Exception e) { // TODO: handle exception }
     * } }
     */

    /**
     * 生成excel模板
     * 
     * @param file
     * @return
     */
    private String generateTemplate(Attachment file) {
        boolean flag = true;
        String remoteName = getRemoteFile();
        LogUtil.info("开始生成excel模板!" + remoteName);
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(file.getDataHandler().getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(remoteName));
            byte[] bytes = new byte[1024 * 512];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
            bos.flush();
            LogUtil.info("生成excel模板成功!" + remoteName);
        } catch (IOException e) {
            LogUtil.error("upload IOException faied! fileName :", e);
            flag = false;
        } finally {
            CloseUtil.close(bis);
            CloseUtil.close(bos);
        }
        return flag ? remoteName : null;
    }

    /**
     * 获取生成的路径
     * 
     * @return
     */
    private String getRemoteFile() {
        String remoteName = ExcelConstant.LOTTER_TEMPLATE_PATH;
        File remotefile = new File(remoteName);
        if (remotefile.exists()) {
            remotefile.delete();
        }
        if (!FileUtil.newFile(ExcelConstant.LOTTER_TEMPLATE_PATH)) {
            LogUtil.error("mkdirs parentFile Faied!" + remotefile.getName());
            return null;
        }
        return remoteName;
    }

    /**
     * 查询文件
     * 
     * @param request
     * @return
     */
    @GET
    @Path("/searchResult")
    @Produces(MediaType.APPLICATION_JSON)
    public PageModel<ExcelResult> searchCombination(@Context HttpServletRequest request) {
        LogUtil.info("start searchResult!");
        File geneRateFile = new File(ExcelConstant.GENERATE_PATH);
        File[] fileArray = geneRateFile.listFiles();
        int count = fileArray.length;
        List<ExcelResult> list = new ArrayList<ExcelResult>();
        if (count > 0) {
            List<File> fileList = Arrays.asList(fileArray);
            Collections.sort(fileList, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return o2.getName().compareTo(o1.getName());
                }
            });
            String fileName;
            // 文件名称
            String filter = request.getParameter("filter");
            for (File file : fileList) {
                fileName = file.getName();
                if (StringUtils.isNotEmpty(filter)) {
                    if (fileName.contains(filter)) {
                        list.add(new ExcelResult(fileName.substring(0, fileName.indexOf(".")),
                                fileName));
                    }
                    continue;
                }
                list.add(new ExcelResult(fileName.substring(0, fileName.indexOf(".")), fileName));
            }
            String pageStr = request.getParameter("page");
            String rowsStr = request.getParameter("rows");
            int rows = StringUtils.isNotEmpty(rowsStr) ? Integer.parseInt(rowsStr) : 10;
            // 偏移量
            int fromIndex =
                    StringUtils.isNotEmpty(pageStr) ? (Integer.parseInt(pageStr) - 1) * rows : 0;
            count = list.size();
            int toIndex = count > rows + fromIndex ? rows + fromIndex : count;
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("offset", fromIndex);
            param.put("rows", rows);
            list = list.subList(fromIndex, toIndex);
        }
        LogUtil.info("end searchResult!");
        return new PageModel<ExcelResult>(count, list);
    }

    /**
     * 下载文件
     * 
     * @param response
     * @throws UnsupportedEncodingException
     */
    @GET
    @Path("/download/{fileName}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public void download(@Context HttpServletResponse response,
            @PathParam("fileName") String fileName) throws UnsupportedEncodingException {
        File file = new File(ExcelConstant.GENERATE_PATH + fileName);
        response.reset();
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Length", Long.toString(file.length()));
        response.setHeader("Content-Disposition",
                "attachment; filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
        InputStream is = null;
        ServletOutputStream sos = null;
        try {
            sos = response.getOutputStream();
            is = new FileInputStream(file);
            byte[] bytes = new byte[1024 * 1024 * 2];
            int len;
            while ((len = is.read(bytes)) > 0) {
                sos.write(bytes, 0, len);
            }
            response.flushBuffer();
            response.setStatus(Response.Status.OK.getStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeStream(is);
            CloseUtil.closeStream(sos);
        }
    }

    /**
     * 下载文件
     * 
     * @param response
     */
    @GET
    @Path("/downloadTemplate")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public void download(@Context HttpServletResponse response) {
        File file = new File(ExcelConstant.DOWNLOAD_TEMPLATE_PATH);
        response.reset();
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Length", Long.toString(file.length()));
        response.setHeader("Content-Disposition", "attachment; filename=lotteryTemplate.xlsx");
        InputStream is = null;
        ServletOutputStream sos = null;
        try {
            sos = response.getOutputStream();
            is = new FileInputStream(file);
            byte[] bytes = new byte[1024 * 1024 * 2];
            int len;
            while ((len = is.read(bytes)) > 0) {
                sos.write(bytes, 0, len);
            }
            response.flushBuffer();
            response.setStatus(Response.Status.OK.getStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.closeStream(is);
            CloseUtil.closeStream(sos);
        }
    }

    /**
     * 删除文件
     * 
     * @param response
     */
    @GET
    @Path("/delete/{fileName}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public boolean delete(@PathParam("fileName") String fileName) {
        File file = new File(ExcelConstant.GENERATE_PATH + fileName);
        boolean flag = false;
        if (file.exists()) {
            flag = file.delete();
        }
        return flag;
    }
}
