package com.lottery.test;

import java.io.File;
import java.util.ArrayList;
import com.dsd.lottery.util.FileUtil;

public class AppMain {

    public static void main(String[] args) {
        String folder = "E:\\github\\Lottery\\src\\main\\resources\\upgrade";
        System.out.println(FileUtil.findFile(new ArrayList<File>(), folder));
    }

}
