package command;

import utils.Pair;

public class LsCommand implements Command {
    public static void execute(Pair pair) {
        System.out.println("ls命令");
    }
}
