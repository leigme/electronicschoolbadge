package me.leig.electronicschoolbadge.comm;

import com.google.gson.Gson;
import me.leig.electronicschoolbadge.bean.Config;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * 配置文件解析对象
 *
 * @author leig
 */

public class ConfigResolve {

    private Logger log = Logger.getLogger(ConfigResolve.class);

    /**
     * 读出配置文件
     *
     */
    public Config readConfigFile() {

        File file = new File("config/electronicschoolbadge.json");

        FileInputStream is = null;

        StringBuilder stringBuilder = null;

        Config config = new Config();

        try {
            if (file.length() != 0) {
                /**
                 * 文件有内容才去读文件
                 */
                is = new FileInputStream(file);
                InputStreamReader streamReader = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(streamReader);
                String line;
                stringBuilder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                reader.close();
                is.close();

                Gson gson = new Gson();
                config = gson.fromJson(stringBuilder.toString(), Config.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("捕获异常: " + e.getMessage());
        }
        return config;
    }
}
