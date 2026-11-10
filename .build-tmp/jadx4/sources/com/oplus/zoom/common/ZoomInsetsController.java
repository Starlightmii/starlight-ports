package com.oplus.zoom.common;

import android.graphics.Rect;
import android.util.SparseArray;
import android.view.InsetsSource;
import android.view.InsetsState;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayInsetsController;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ZoomInsetsController implements DisplayController.OnDisplaysChangedListener {
    private static final String TAG = "ZoomInsetsController";
    private static volatile ZoomInsetsController sInstance;
    private PerDisplay mDefaultDisplay;
    private DisplayInsetsController mDisplayInsetsController;
    private final ArrayList<ImePositionListener> mImeListeners = new ArrayList<>();
    private final SparseArray<PerDisplay> mInsetsPerDisplay = new SparseArray<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface ImePositionListener {
        void onImePositionChanged(int i, boolean z, int i2, boolean z2);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public class PerDisplay implements DisplayInsetsController.OnInsetsChangedListener {
        private static final int MAX_FLOATING_IME_HEIGHT = 44;
        final int mDisplayId;
        private int mImeHeight;
        private boolean mImeShow;
        InsetsState mInsetsState;

        public PerDisplay(int i) {
            this.mDisplayId = i;
        }

        private boolean calcIsFloating(InsetsSource insetsSource) {
            Rect frame = insetsSource.getFrame();
            return frame.height() == 0 || frame.height() <= ZoomUtil.dip2px(ZoomDisplayController.getInstance().getDensity(), 44.0f);
        }

        private void updateImeInsetsState(InsetsState insetsState) {
            InsetsSource insetsSourcePeekSource;
            if (insetsState == null || (insetsSourcePeekSource = insetsState.peekSource(InsetsSource.ID_IME)) == null) {
                return;
            }
            if (insetsSourcePeekSource.getVisibleFrame() != null) {
                insetsSourcePeekSource.getVisibleFrame();
            } else {
                insetsSourcePeekSource.getFrame();
            }
            boolean zIsVisible = insetsSourcePeekSource.isVisible();
            boolean zCalcIsFloating = calcIsFloating(insetsSourcePeekSource);
            int iHeight = zCalcIsFloating ? 0 : insetsSourcePeekSource.getFrame().height();
            if (zIsVisible == this.mImeShow && iHeight == this.mImeHeight) {
                return;
            }
            this.mImeShow = zIsVisible;
            this.mImeHeight = iHeight;
            ZoomInsetsController.this.onImePositionChanged(this.mDisplayId, zIsVisible, iHeight, zCalcIsFloating);
        }

        public int getImeHeight() {
            return this.mImeHeight;
        }

        public void insetsChanged(InsetsState insetsState) {
            updateImeInsetsState(insetsState);
            this.mInsetsState = insetsState;
        }

        public boolean isImeVisible() {
            return this.mImeShow;
        }

        public void register() {
            ZoomInsetsController.this.mDisplayInsetsController.addInsetsChangedListener(this.mDisplayId, this);
        }

        public void unregister() {
            ZoomInsetsController.this.mDisplayInsetsController.removeInsetsChangedListener(this.mDisplayId, this);
        }
    }

    public static ZoomInsetsController getInstance() {
        if (sInstance == null) {
            synchronized (ZoomInsetsController.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new ZoomInsetsController();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onImePositionChanged(int i, boolean z, int i2, boolean z2) {
        for (int size = this.mImeListeners.size() - 1; size >= 0; size--) {
            this.mImeListeners.get(size).onImePositionChanged(i, z, i2, z2);
        }
    }

    public void addImePositionListener(ImePositionListener imePositionListener) {
        this.mImeListeners.add(imePositionListener);
    }

    public int getImeHeight() {
        PerDisplay perDisplay = this.mDefaultDisplay;
        if (perDisplay != null) {
            return perDisplay.getImeHeight();
        }
        return 0;
    }

    public void init(DisplayController displayController, DisplayInsetsController displayInsetsController) {
        this.mDisplayInsetsController = displayInsetsController;
        displayController.addDisplayWindowListener(this);
    }

    public boolean isImeVisible() {
        PerDisplay perDisplay = this.mDefaultDisplay;
        if (perDisplay != null) {
            return perDisplay.isImeVisible();
        }
        return false;
    }

    public void onDisplayAdded(int i) {
        PerDisplay perDisplay = new PerDisplay(i);
        perDisplay.register();
        this.mInsetsPerDisplay.put(i, perDisplay);
        if (i == 0) {
            this.mDefaultDisplay = perDisplay;
        }
    }

    public void onDisplayRemoved(int i) {
        PerDisplay perDisplay = this.mInsetsPerDisplay.get(i);
        if (perDisplay == null) {
            return;
        }
        perDisplay.unregister();
        this.mInsetsPerDisplay.remove(i);
    }

    public void removeImePositionListener(ImePositionListener imePositionListener) {
        this.mImeListeners.remove(imePositionListener);
    }
}
