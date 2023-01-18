package org.example3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.console.MiraiConsole;
import net.mamoe.mirai.console.command.Command;
import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.Listener;
import net.mamoe.mirai.event.ListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class Plugin extends JavaPlugin {
    public static final Plugin INSTANCE = new Plugin();

    private Plugin() {
        super(new JvmPluginDescriptionBuilder("org.example3.plugin", "1.0-SNAPSHOT").build());
    }

    @Override
    public void onEnable() {

        getLogger().info("Plugin loaded!");
        getLogger().info("testLogger!");
        Command command=MySimple.INSTANCE;
        CommandManager.INSTANCE.registerCommand(command,true);
       // String json = FileUtils.readFileToString(new File("D:\\Artificat\\pf2\\pf2_monster.json"), "UTF-8");

       //读入json文件
        File file=new File("..\\data\\pf2_monster.json");
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode=objectMapper.readValue(file,JsonNode.class);
            jsonNode=jsonNode.get("helpdoc");
            getLogger().info("Load Json successful");
        } catch (IOException e) {
            e.printStackTrace();
        }


        JsonNode finalJsonNode = jsonNode;

        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, event->{
            MessageChain chain=event.getMessage(); // 可获取到消息内容等, 详细查阅 `GroupMessageEvent`
            String message=chain.contentToString();
            message= message.toUpperCase();
            getLogger().info(String.valueOf(message.contains("/PF2")));
            boolean flag=message.contains("/PF2");

            if (flag){
                List< Map.Entry<String, JsonNode>> result=new ArrayList<>();
                Iterator<Map.Entry<String, JsonNode>> it=finalJsonNode.fields();
                String[] args=message.split(" ");
                String findRex=message.substring(5);
                //遍历查找
                while (it.hasNext()&&args.length>=2) {

                    Map.Entry<String, JsonNode> temp = it.next();
                    String key = temp.getKey().replace("pf2 ","");
                    String desc = temp.getValue().asText();
                    if (StringInterpreter.INSTANCE.parse(key,desc,findRex)) {
                        result.add(temp);
                    }

                }
                if (result.size()>1){
                    StringBuffer reply=new StringBuffer();
                    reply.append("结果不唯一，共计"+result.size()+"条，只有当结果唯一时输出\n");
                    reply.append("你需要查找的可能是:\n");
                    for (Map.Entry entry:result){
                        reply.append(entry.getKey()+"\n");
                    }
                    event.getSubject().sendMessage(reply.toString());
                }
                else if (result.size()==0){
                    event.getSubject().sendMessage("没有符合要求的");
                }
                else {
                    Map.Entry<String,JsonNode> entry=result.get(0);
                    event.getSubject().sendMessage(entry.getKey()+"\n"+entry.getValue().asText());
                }
            }

            //event.getSubject().sendMessage("Hello!"); // 回复消息
        });


    }
}