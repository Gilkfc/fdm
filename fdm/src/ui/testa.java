package ui;

import bibliothek.extension.gui.dock.theme.EclipseTheme;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
 

import bibliothek.gui.DockController;
import bibliothek.gui.DockFrontend;
import bibliothek.gui.Dockable;
import bibliothek.gui.dock.DefaultDockable;
import bibliothek.gui.dock.SplitDockStation;
import bibliothek.gui.dock.event.DockFrontendAdapter;
import bibliothek.gui.dock.station.split.SplitDockProperty;

import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class testa
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        
        DockFrontend frontend = new DockFrontend(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        EclipseTheme theme = new EclipseTheme();
        frontend.getController().setTheme(theme);
 
        SplitDockStation station = new SplitDockStation();
        frame.add(station);
        frontend.addRoot("split", station);
 
        /* Let's create some Dockables */
        Dockable red = createDockable("Red", Color.RED);
        Dockable green = createDockable("Green", Color.GREEN);
        Dockable blue = createDockable("Blue", Color.BLUE);
 
        frontend.addDockable("red", red);
        frontend.addDockable("green", green);
        frontend.addDockable("blue", blue);
 
        
        SplitDockProperty center = new SplitDockProperty(0, 0, 1, 1);
        station.drop(red, center);
        station.drop(green, center);
        station.drop(blue, center);
        
 
        frontend.setShowHideAction(true);
 
        JMenu menu = new JMenu("Panels");
        menu.add(createMenuItem(red, frontend));
        menu.add(createMenuItem(green, frontend));
        menu.add(createMenuItem(blue, frontend));
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        frame.setSize(900, 500);
        frame.setVisible(true);
    }
 
    private static Dockable createDockable(String title, Color color)
    {
        DefaultDockable dockable = new DefaultDockable();
        dockable.setTitleText(title);
        JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setBackground(color);
        dockable.add(panel);
        return dockable;
    }
 
    private static JMenuItem createMenuItem(final Dockable observed, final DockFrontend frontend)
    {
        final JCheckBoxMenuItem item = new JCheckBoxMenuItem(observed.getTitleText());
 
        frontend.addFrontendListener(new DockFrontendAdapter()
        {
            @Override
            public void shown(DockFrontend frontend, Dockable dockable)
            {
                if (dockable == observed)
                {
                    item.setSelected(true);
                }
            }
 
            @Override
            public void hidden(DockFrontend fronend, Dockable dockable)
            {
                if (dockable == observed)
                {
                    item.setSelected(false);
                }
            }
        });
 
        item.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (item.isSelected())
                {
                    frontend.show(observed);
                }
                else
                {
                    frontend.hide(observed);
                }
            }
        });
        item.setSelected(frontend.isShown(observed));
        return item;
    }
}
