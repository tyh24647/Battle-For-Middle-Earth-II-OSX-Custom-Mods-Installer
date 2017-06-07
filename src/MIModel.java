import com.sun.istack.internal.NotNull;

import java.io.File;

/**
 * Created by tyhostager on 6/7/17.
 */
public class MIModel {

    private String appPath;
    private File mod;

    public MIModel() {

    }

    public void setAppPath(@NotNull String appPath) {
        this.appPath = appPath;
    }

    public void setMod(@NotNull File mod) {
        this.mod = mod;
    }

    public void applySelectedModToAppAtPath() {
        /*
        TODO: add bash call to wineskin wrapper inside app path to run the .exe file here!!!
         */
    }
}
