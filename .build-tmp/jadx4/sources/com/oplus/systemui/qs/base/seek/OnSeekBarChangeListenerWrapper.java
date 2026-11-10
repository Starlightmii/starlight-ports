package com.oplus.systemui.qs.base.seek;

import com.coui.appcompat.seekbar.COUIVerticalSeekBar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OnSeekBarChangeListenerWrapper implements COUIVerticalSeekBar.OnSeekBarChangeListener {
    private final Set<COUIVerticalSeekBar.OnSeekBarChangeListener> list = new LinkedHashSet();

    public final void addListener(COUIVerticalSeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        if (onSeekBarChangeListener != null) {
            this.list.add(onSeekBarChangeListener);
        }
    }

    public final boolean isEmpty() {
        return this.list.isEmpty();
    }

    public void onProgressChanged(COUIVerticalSeekBar cOUIVerticalSeekBar, int i, boolean z) {
        Iterator<T> it = this.list.iterator();
        while (it.hasNext()) {
            ((COUIVerticalSeekBar.OnSeekBarChangeListener) it.next()).onProgressChanged(cOUIVerticalSeekBar, i, z);
        }
    }

    public void onStartTrackingTouch(COUIVerticalSeekBar cOUIVerticalSeekBar) {
        Iterator<T> it = this.list.iterator();
        while (it.hasNext()) {
            ((COUIVerticalSeekBar.OnSeekBarChangeListener) it.next()).onStartTrackingTouch(cOUIVerticalSeekBar);
        }
    }

    public void onStopTrackingTouch(COUIVerticalSeekBar cOUIVerticalSeekBar) {
        Iterator<T> it = this.list.iterator();
        while (it.hasNext()) {
            ((COUIVerticalSeekBar.OnSeekBarChangeListener) it.next()).onStopTrackingTouch(cOUIVerticalSeekBar);
        }
    }

    public final void removeListener(COUIVerticalSeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        if (onSeekBarChangeListener != null) {
            this.list.remove(onSeekBarChangeListener);
        }
    }
}
