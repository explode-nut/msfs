package client;

import command.*;
import utils.GlobalDirectory;
import utils.Pair;

import java.util.Scanner;

public class Shell implements Client {
    private Pair lexicalAnalyze(String command) {
        return null;
    }

    private void execute(Pair pair){
        switch (pair.command()) {
            case "ls":
                LsCommand.execute(pair);
                break;
            case "cat":
                CatCommand.execute(pair);
                break;
            case "cd":
                CdCommand.execute(pair);
                break;
            case "mkdir":
                MkdirCommand.execute(pair);
                break;
            case "touch":
                TouchCommand.execute(pair);
                break;
            case "rm":
                RmCommand.execute(pair);
                break;
            default:
                System.out.println("未知命令" + pair.command() + "!");
                System.out.println("输入help查询帮助");
                break;
        }
    }

    private void printLoop() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("msfs:" + GlobalDirectory.CURRENT_DIRECTORY + "$");
            String command = sc.nextLine();
            if (command.isEmpty()) continue;
            if (command.equals("q")) return;
            Pair pair = lexicalAnalyze(command);
            if (pair == null) continue;
            execute(pair);
        }
    }

    public void run() {
        System.out.println("==================欢迎进入msfs模拟文件系统==================");
        System.out.println("=======================输入q退出系统=======================");
        System.out.println("======================输入help查询帮助=====================");
        printLoop();
        System.out.println("=========================使用结束=========================");
    }
}
