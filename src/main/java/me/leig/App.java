package me.leig;

import com.rw.testutil.main.MainMethod;
import me.leig.electronicschoolbadge.bean.Config;
import me.leig.electronicschoolbadge.comm.ConfigResolve;
import me.leig.electronicschoolbadge.reader.BadgeReader;
import org.apache.log4j.Logger;

/**
 * 工程入口
 *
 */
public class App {

    private static Logger log = Logger.getLogger(App.class);

    public static void main( String[] args ) {

        ConfigResolve configResolve = new ConfigResolve();

        Config config = configResolve.readConfigFile();

        MainMethod.Resolve();

        BadgeReader badgeReader = new BadgeReader(config);

        badgeReader.startRead();

    }

}
