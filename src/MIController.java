import com.sun.istack.internal.NotNull;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller delegate between the view and model objects--when changes are made on the
 * view object, the changes are then applied to the model, in which the functionality/installation
 * can be initialized.
 *
 * Created by tyhostager on 6/7/17.
 */
public class MIController implements ActionListener {
    private MIModel model;
    private MIView view;

    public MIController(@NotNull MIModel model, @NotNull MIView view) {
        this.model = model;
        this.view = view;

        addListenersToComponents();
    }

    private void addListenersToComponents() {
        view.getAppPathChooser().addActionListener(this);
        view.getModFileChooser().addActionListener(this);
        view.getApplyBtn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source.equals(view.getAppPathChooser()) && view.getAppPathChooser().getCurrentDirectory() != null) {
            model.setAppPath(view.getAppPathChooser().getCurrentDirectory().getPath());
        } else if (source.equals(view.getModFileChooser()) && view.getModFileChooser().getCurrentDirectory() != null) {
            model.setMod(view.getModFileChooser().getSelectedFile());
        } else if (source.equals(view.getApplyBtn())) {
            model.applySelectedModToAppAtPath();
        }
    }
}
