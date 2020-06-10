/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.LinkedList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 *
 * @author matthew
 */
public class MyListModel implements ListModel<String> {

    List<ListDataListener> listen = new LinkedList<>();
    List<String> data = new LinkedList<>();

    public void addItem(String item) {
        this.data.add(item);
        for (ListDataListener l : listen) {
            l.intervalAdded(new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, data.size()-1, data.size()-1));
        }
    }

    public void removeItem(int index) {
        if (0 <= index && index < data.size()) {
            this.data.remove(index);
            for (ListDataListener l : listen) {
                l.intervalRemoved(new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, index, index));
            }
        }
    }

    @Override
    public int getSize() {
        return this.data.size();
    }

    @Override
    public String getElementAt(int index) {
        return this.data.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        listen.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listen.remove(l);
    }

}
