package com.lottery.test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import com.dsd.lottery.util.FileUtil;

public class AppMain {
    public static boolean judgeCircle(String moves) {
        int j = 0;
        for(int i = 0;i<moves.length();i++)
        {
            char a = moves.charAt(i);
            switch(a)
            {
                case 'L':
                    j++;
                    break;
                case 'R':
                    j--;
                    break;
                case 'U':
                    j++;
                    break;
                case 'D':
                    j--;
                    break;
            }
        }
        return j == 0;
    }
    public static void main(String[] args) {
        String folder = "E:\\github\\Lottery\\src\\main\\resources\\upgrade";
        System.out.println(FileUtil.findFile(new ArrayList<File>(), folder));
        System.out.println(getNFactorial(4));
        int[] candies = {505,8,951,606,475,401,451,903,618,772,760,475,310,417,728,972,646,794,648,315,353,698,55,88,503,798,297,139,879,99,917,38,554,747,561,175,956,373,672,941,267,680,89,902,127,428,545,914,586,349,339,152,185,340,220,547,648,364,939,641,212,422,621,512,338,826,887,813,125,955,4,874,804,868,231,939,114,237,298,606,199,965,972,141,676,90,369,289,628,12,588,236,254,370,920,298,566,888,316,173};
        System.out.println(distributeCandies(candies));
    }

    public static int distributeCandies(int[] candies) {
        int size = candies.length;
        if(size == 0)
        {
            return 0;
        }
        Set<Integer> set = new HashSet<Integer>();
        for(int candie : candies)
        {
            set.add(candie);
        }
        int candSize = set.size();
        return size * candSize / getNFactorial(candSize);
    }
    
    private static int getNFactorial(int n)
    {
        if(n==0)
        {
            return 1;
        }
        return n * getNFactorial(n-1);
    }
}
