package com.dsd.lottery.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作工具类
 * 
 * @author daishengda
 *
 */
public class FileUtil {

    /**
     * 缓存字节大小
     */
    public static final int BUFFER_BYTE = 1024 * 12;

    /**
     * 新建文件夹
     * 
     * @param filePath
     * @return
     */
    public static boolean mkdirFolder(String filePath) {
        boolean flag = false;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                flag = file.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 新建文件
     * 
     * @return
     */
    public static boolean newFile(String filePath) {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                File parentFile = file.getParentFile();
                if (!parentFile.exists() && !parentFile.mkdirs()) {
                    return false;
                }
                return file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 创建文件夹
     * 
     * @param path
     * @return
     */
    public static boolean createFolder(String path) {
        try {
            File file = new File(path);
            if (!file.exists() && !file.mkdirs()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 删除文件
     * 
     * @param filePath
     * @return
     */
    public static boolean deleteFile(String filePath) {
        File files = new File(filePath);
        if (files.exists() && !files.delete()) {
            return false;
        }
        return true;
    }

    /**
     * 清空文件夹
     * 
     * @param folderPath
     * @return
     */
    public static boolean clearFolder(String folderPath) {
        File files = new File(folderPath);
        boolean flag = true;
        for (File file : files.listFiles()) {
            if (file.isDirectory()) {
                clearFolder(file.getPath());
            }
            if (!file.delete()) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 查找某个文件夹下前缀是prefix、后缀是suffix的文件
     * 
     * @param folder
     * @param prefix
     * @param suffix
     * @return
     */
    public static List<File> findFile(String folder, String prefix, String suffix) {
        List<File> fileList = new ArrayList<File>();
        File files = new File(folder);
        for (File file : files.listFiles()) {
            if (file.getName().indexOf(prefix) == 0 && file.getName().endsWith(suffix)) {
                fileList.add(file);
            }
        }
        return fileList;
    }

    /**
     * 根据文件夹，查找文件
     * 
     * @param folder 文件夹路径
     * @return
     */
    public static List<File> findFile(List<File> fileList, String folder) {
        File files = new File(folder);
        if (files.isDirectory()) {
            for (File file : files.listFiles()) {
                findFile(fileList, file.getAbsolutePath());
            }
        }
        else
        {
            fileList.add(files); 
        }
        return fileList;
    }

    /**
     * 查找某个文件夹下前缀是prefix的文件
     * 
     * @param prefix
     * @return
     */
    public static List<File> findFile(String folder, String prefix) {
        List<File> fileList = new ArrayList<File>();
        File files = new File(folder);
        for (File file : files.listFiles()) {
            if (file.getName().indexOf(prefix) == 0) {
                fileList.add(file);
            }
        }
        return fileList;
    }

    /**
     * 文件拷贝
     * 
     * @param toPath (目标文件目录)
     * @param fromPath (源文件目录)
     * @return
     */
    public static boolean copyFile(String olePath, String newPath) {
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
        try {
            File file = new File(olePath);
            if (file.exists()) {
                newFile(newPath);
                input = new BufferedInputStream(new FileInputStream(olePath));
                output = new BufferedOutputStream(new FileOutputStream(newPath));
                byte[] buffer = new byte[BUFFER_BYTE];
                int bytesum = 0;
                int byteread;
                while ((byteread = input.read(buffer)) != -1) {
                    bytesum += byteread;
                    System.out.println(bytesum);
                    output.write(buffer, 0, byteread);
                }
                output.flush();
                System.out.println("FileUtil.copyFile() is SUCCESS");
                return true;
            } else {
                System.err.println("file not exists!");
                return false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtil.close(input);
            CloseUtil.close(output);
        }
        return false;
    }

    /**
     * 递归拷贝文件夹
     * 
     * @param olePath
     * @param newPath
     * @return
     */
    public static boolean copyFolder(String oldPath, String newPath) {
        boolean flag = true;
        try {
            // 如果文件夹不存在 则建立新文件夹
            createFolder(newPath);
            File oleFile = new File(oldPath);
            File[] files = oleFile.listFiles();
            for (File file : files) {
                String newTempPath = newPath + File.separator + file.getName();
                // 如果是文件,则直接拷贝
                if (file.isFile()) {
                    if (!copyFile(file.getPath(), newTempPath)) {
                        flag = false;
                    }
                }
                // 如果是子文件夹
                if (file.isDirectory()) {
                    if (!copyFolder(file.getPath(), newTempPath)) {
                        flag = false;
                    }
                }
            }
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }
}
