package com.mine.chapter04.practice;

import java.io.File;

/**
 * @author CaoY
 * @date 2023-09-07 22:45
 * @description 练习 4.10
 * 编写一个程序，该程序列出一个目录中所有的文件和它们的大小。
 *
 * 注：
 *  1. 笔者认为自己的这个程序设计的还是比较精巧的，也比较清晰的显示出各种文件层次的结构；
 *  2. 为了不透露自己电脑的目录结构，笔者不保留测试时使用的文件的绝对目录，望读者根据自己的情况填写；
 *  3. 注意拿到 File 对象后不要随便搞删除和修改操作，尤其是对于重要文件，要三思而后行；
 *  4. 本质上，对于目录中所有文件的打印采用了树的前序遍历的思想，即：先输出目录名，再输出其中的文件名。
 */
public class Solution04_10 {

    public static void main(String[] args) {
        String catalogue = "传入自己的绝对路径";
        printAllFiles(catalogue);
    }

    /**
     * 打印目录下的所有层次的文件，如果是一个不存在的目录或空目录均会输出相应的提示信息；
     * 如果是一个文件则只打印该文件名。还会在打印出的文件前加上“层次-”，如根目录的层次为 0，依此类推。
     * @param catalogue 字符串形式的目录路径，需要时绝对路径形式，在 Windows 上文件分隔符需要转义
     */
    private static void printAllFiles(String catalogue) {
        File dirFile = new File(catalogue);
        if (!dirFile.exists()) {
            System.out.println("不存在这个目录");
            return;
        }

        printDir(dirFile, 0);
    }

    /**
     * 递归打印目录中的文件，如果是一个空目录，则输出相应提示信息，反之则打印出其中所有层次的内容
     * @param dir   File 类型的目录，也可能是文件，如果是文件的话就直接输出
     * @param count 当前层次数
     */
    private static void printDir(File dir, int count) {
        System.out.print(count + "-" + dir.getName());
        if (!dir.isDirectory()) {
            System.out.println();
            return;
        }

        System.out.print(": ");
        File[] files = dir.listFiles();
        if (files.length == 0) {
            System.out.println("空文件夹");
        } else {
            System.out.println();
            for (File file : files) {
                for (int i = 0; i < count + 1; i++) {
                    System.out.print("\t");
                }
                printDir(file, count + 1);
            }
        }
    }
}
