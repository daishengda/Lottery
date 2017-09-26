package com.dsd.lottery.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.dsd.lottery.constant.LotteryConst;


public class LotteryMain {
	
	public static void main(String[] args) {
/*		String[] splitArray= {
					"2593058188093949127583464856103461483191",
					"7593658188093949127583464856103461483156",
					"8478688188093949127583464856103461483145",
					"6545678188093949127583464856103461483165",
					"5565678178693349127583464856103461483178"};*/
		int MAX = 1;
		String[] splitArray= {
				"2593058188093949127583464856103461483191",
				"7593658188093949127583464856103461483156",
				"8478688188093949127583464856103461483145",
				"6545678188093949127583464856103461483165",
				"5565678178693349127583464856103461483178"};
		int length = MAX;
		String[][] arrayList = new String[length][5];
		for(int i= 0;i<splitArray.length;i++)
		{
			String split = splitArray[i];
			for(int j=0;j<length;j++)
			{
				arrayList[j][i] = split.substring(j, j+1);
			}
		}
		String[] arrays =  parse();
/*		Random rand=new Random();
		for(int i=0;i<arrays.length;i++)
		{
			arrays[i] = String.valueOf((int)(rand.nextDouble()*(100000-10000)+10000));
		}*/
		int size = arrays.length;
		for(int i = 0;i < size;i++)
		{
			if(i + MAX < size)
			{
				startImpotLotterySplit(arrays, i , i+MAX, arrayList);
			}
		}

		System.out.println();
	}
	
	private static void startImpotLotterySplit(String[] arrays,int index,int maxStage,String[][] arrayList){
		char codeChar;
		String code;
		boolean[] arrayStr = {true,true,true,true,true};
		int aindex = 0;
		for (int i = index;i<maxStage;i++) {
			code = arrays[i];
			for (int j = 0; j < code.length(); j++) {
				codeChar = code.charAt(j);
				if(!arrayList[aindex][j].equals(""+codeChar))
				{
					arrayStr[j] = false;
					continue;
				}
			}
			aindex++;
		}
		for(boolean flag : arrayStr)
		{
			if(flag)
				System.out.println(arrays[index]);
		}
	}
	
	@SuppressWarnings("resource")
	public static String[] parse()
	{
		String[] arrays = new String[360856];
		String path = "C:\\Users\\daishengda\\Desktop\\lottery.txt";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(path),LotteryConst.DEFAULT_CHARTSET_NAME));
			String line;
			String[] array;
			int i = 0;
			while((line=br.readLine())!=null)
			{
				array = line.split(LotteryConst.SEPARATOR);
				if(array.length<2)
				{
					array = line.split(LotteryConst.EMTY_SEPARATOR);
				}
				if(array.length >= 2)
				{
					arrays[i++] = array[1];
				}
			}
			System.out.println(i);
		}catch (IOException e) {
			e.printStackTrace();
		}
		return arrays;
	}

}
