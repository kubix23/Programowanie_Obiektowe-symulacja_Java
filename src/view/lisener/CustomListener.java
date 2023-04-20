package view.lisener;

import view.lisener.MoveListener;

import java.util.ArrayList;
import java.util.List;

public interface CustomListener {
    List<MoveListener> listeners = new ArrayList<MoveListener>();
    default void addListener(MoveListener toAdd) {
        listeners.add(toAdd);
    }
    default void update(boolean is) {
        for (MoveListener hl : listeners){
            hl.isMoved(is);
        }

    }
}
