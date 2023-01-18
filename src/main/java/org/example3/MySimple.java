package org.example3;


import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.java.JRawCommand;
import net.mamoe.mirai.message.data.MessageChain;
import org.jetbrains.annotations.NotNull;

public final class MySimple extends JRawCommand {
    public static final MySimple INSTANCE = new MySimple();

    private MySimple() {
        super(Plugin.INSTANCE, "test"); // 使用插件主类对象作为指令拥有者；设置主指令名为 "test"
        // 可选设置如下属性
        setUsage("/test"); // 设置用法，这将会在 /help 中展示
        setDescription("这是一个测试指令"); // 设置描述，也会在 /help 中展示
        setPrefixOptional(true); // 设置指令前缀是可选的，即使用 `test` 也能执行指令而不需要 `/test`
    }

    @Override
    public void onCommand(@NotNull CommandSender sender, @NotNull MessageChain args) {
        System.out.println("args");

        // 处理指令
    }
}