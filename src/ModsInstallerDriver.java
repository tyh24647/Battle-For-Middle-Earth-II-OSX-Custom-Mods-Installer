/**
 * The driver for the application
 *
 * Created by tyhostager on 6/7/17.
 */
public class ModsInstallerDriver {
    public static void main(String[] args) {
        MIModel model = new MIModel();
        MIView view = MIView.init();
        MIController controller = new MIController(model, view);

    }
}
