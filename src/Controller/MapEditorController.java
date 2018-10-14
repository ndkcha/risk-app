package Controller;

import Model.ContinentData;
import Risk.MapEditorDataHolder;
import View.MapEditotView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class MapEditorController {
    MapEditorDataHolder holder = MapEditorDataHolder.getInstance();

    private MapEditotView view;

    MapEditorController() {
        view = new MapEditotView();
        initActionListeners();
    }

    void initAndDisplayView() {
        view.initComponents();
        view.display();
    }

    private void initActionListeners() {
        ActionListener alSaveMap = (ActionEvent e) -> {
              for (Map.Entry<String, ContinentData> continentDataEntry : this.holder.getContinents().entrySet()) {
                  ContinentData continentData = continentDataEntry.getValue();
                  System.out.println(continentData.getName() + " | " + continentData.getControlValue());
              }
        };

        this.view.initPublicListeners(alSaveMap);
    }
}
