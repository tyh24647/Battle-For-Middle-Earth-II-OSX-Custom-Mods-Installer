import com.sun.istack.internal.NotNull;

import javax.swing.*;

/**
 * The driver for the application
 *
 * Created by tyhostager on 6/7/17.
 */
public class ModsInstallerDriver {

    public static void main(String[] args) {
        MIModel model = new MIModel();
        MIView view = new MIView();

        init(model, view);
    }

    private static void init(@NotNull MIModel model, @NotNull MIView view) {
        SwingUtilities.invokeLater(() -> new MIController(model, view));
    }
}
