package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 包含解析后的命令
public class Pair {
    private String command; // 命令名
    private final List<String> parms; // 参数
    private final List<String> option; // 选项参数

    public Pair() {
        parms = new ArrayList<>();
        option = new ArrayList<>();
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void addParms(String p) {
        this.parms.add(p);
    }

    public void addOption(String o) {
        this.option.add(o);
    }

    public String command() {
        return command;
    }

    public List<String> parms() {
        return Collections.unmodifiableList(parms);
    }

    public List<String> option() {
        return Collections.unmodifiableList(option);
    }
}
