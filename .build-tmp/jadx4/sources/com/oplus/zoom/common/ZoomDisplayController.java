package com.oplus.zoom.common;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Insets;
import android.util.SparseArray;
import android.view.InsetsState;
import android.view.WindowInsets;
import android.window.DisplayAreaInfo;
import android.window.WindowContainerTransaction;
import com.android.launcher3.icons.IconProvider$;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayLayout;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class ZoomDisplayController {
    public static final int CHANGED_DISPLAY = 2;
    public static final int CHANGED_NONE = 0;
    public static final int CHANGED_ROTATION = 1;
    private static final String TAG = "ZoomDisplayController";
    private static volatile ZoomDisplayController sInstance;
    private Context mContext;
    private DisplayRecord mDefaultDisplay;
    private DisplayController mDisplayController;
    private int mLastScreenHeight;
    private int mLastScreenWidth;
    private ShellTaskOrganizer mShellTaskOrganizer;
    private final ArrayList<DisplayChangedListener> mDisplayChangedListeners = new ArrayList<>();
    private final SparseArray<DisplayRecord> mDisplays = new SparseArray<>();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface DisplayChangedListener {
        default void onDisplayAdded(int i) {
        }

        default void onDisplayChanged(int i, int i2, Configuration configuration) {
        }

        default void onDisplayRemove(int i) {
        }

        default void onFixedRotationFinished(int i) {
        }

        default void onFixedRotationStarted(int i, int i2) {
        }

        default void onRotateDisplay(int i, int i2, int i3, DisplayAreaInfo displayAreaInfo, WindowContainerTransaction windowContainerTransaction) {
        }
    }

    private DisplayRecord getDisplayRecord(int i) {
        synchronized (this.mDisplays) {
            try {
                if (this.mDisplays.get(i) != null) {
                    return this.mDisplays.get(i);
                }
                return this.mDefaultDisplay;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private DisplayRecord getFocusedDisplay() {
        ShellTaskOrganizer shellTaskOrganizer = this.mShellTaskOrganizer;
        ActivityManager.RunningTaskInfo lastFocusedTaskInfo = shellTaskOrganizer != null ? shellTaskOrganizer.getLastFocusedTaskInfo() : null;
        synchronized (this.mDisplays) {
            if (lastFocusedTaskInfo != null) {
                try {
                    if (this.mDisplays.get(lastFocusedTaskInfo.displayId) != null) {
                        return this.mDisplays.get(lastFocusedTaskInfo.displayId);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return this.mDefaultDisplay;
        }
    }

    public static ZoomDisplayController getInstance() {
        if (sInstance == null) {
            synchronized (ZoomDisplayController.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new ZoomDisplayController();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDisplayAdd(int i) {
        for (int size = this.mDisplayChangedListeners.size() - 1; size >= 0; size--) {
            this.mDisplayChangedListeners.get(size).onDisplayAdded(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDisplayChanged(int i, int i2, Configuration configuration) {
        for (int size = this.mDisplayChangedListeners.size() - 1; size >= 0; size--) {
            this.mDisplayChangedListeners.get(size).onDisplayChanged(i, i2, configuration);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDisplayRemove(int i) {
        for (int size = this.mDisplayChangedListeners.size() - 1; size >= 0; size--) {
            this.mDisplayChangedListeners.get(size).onDisplayRemove(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRotateDisplay(int i, int i2, int i3, DisplayAreaInfo displayAreaInfo, WindowContainerTransaction windowContainerTransaction) {
        for (int size = this.mDisplayChangedListeners.size() - 1; size >= 0; size--) {
            this.mDisplayChangedListeners.get(size).onRotateDisplay(i, i2, i3, displayAreaInfo, windowContainerTransaction);
        }
    }

    public float getDensity() {
        if (getFocusedDisplay() != null) {
            return getFocusedDisplay().getDensity();
        }
        return 0.0f;
    }

    public DisplayLayout getDisplayLayout() {
        return getFocusedDisplay() != null ? getFocusedDisplay().getDisplayLayout() : new DisplayLayout();
    }

    public int getLastScreenHeight() {
        return this.mLastScreenHeight;
    }

    public int getLastScreenWidth() {
        return this.mLastScreenWidth;
    }

    public int getRotation() {
        if (getFocusedDisplay() != null) {
            return getFocusedDisplay().getRotation();
        }
        return 0;
    }

    public int getScreenHeight() {
        if (getFocusedDisplay() != null) {
            return getFocusedDisplay().getScreenHeight();
        }
        return 0;
    }

    public int getScreenWidth() {
        if (getFocusedDisplay() != null) {
            return getFocusedDisplay().getScreenWidth();
        }
        return 0;
    }

    public void init(DisplayController displayController, final Context context, ShellTaskOrganizer shellTaskOrganizer) {
        this.mDisplayController = displayController;
        this.mContext = context;
        this.mShellTaskOrganizer = shellTaskOrganizer;
        displayController.addDisplayWindowListener(new DisplayController.OnDisplaysChangedListener() { // from class: com.oplus.zoom.common.ZoomDisplayController.1
            public void onDisplayAdded(int i) {
                synchronized (ZoomDisplayController.this.mDisplays) {
                    try {
                        if (ZoomDisplayController.this.mDisplays.get(i) != null) {
                            return;
                        }
                        LogD.d(ZoomDisplayController.TAG, "onDisplayAdded");
                        DisplayRecord displayRecord = new DisplayRecord(i, 0);
                        displayRecord.setDisplayLayout(ZoomDisplayController.this.mDisplayController.getDisplayContext(i), ZoomDisplayController.this.mDisplayController.getDisplayLayout(i));
                        ZoomDisplayController.this.mDisplays.put(i, displayRecord);
                        if (i == 0) {
                            ZoomDisplayController.this.mDefaultDisplay = displayRecord;
                        }
                        ZoomDisplayController.this.notifyDisplayAdd(i);
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }

            public void onDisplayConfigurationChanged(int i, Configuration configuration) {
                DisplayRecord displayRecord = (DisplayRecord) ZoomDisplayController.this.mDisplays.get(i);
                if (displayRecord == null) {
                    LogD.w(ZoomDisplayController.TAG, "Skipping Display Configuration change on non-added display.");
                    return;
                }
                DisplayLayout displayLayout = ZoomDisplayController.this.mDisplayController.getDisplayLayout(i);
                float density = ZoomDisplayController.this.getDensity();
                int rotation = ZoomDisplayController.this.getRotation();
                int iWidth = displayLayout.width();
                int iHeight = displayLayout.height();
                float fDensity = displayLayout.density();
                int iRotation = displayLayout.rotation();
                displayRecord.setDisplayLayout(ZoomDisplayController.this.mDisplayController.getDisplayContext(i), displayLayout);
                StringBuilder sb = new StringBuilder("onDisplayConfigurationChanged, w:");
                sb.append(ZoomDisplayController.this.mLastScreenWidth);
                sb.append(", h:");
                sb.append(ZoomDisplayController.this.mLastScreenHeight);
                sb.append(", new w:");
                sb.append(iWidth);
                sb.append(", new h:");
                IconProvider$.ExternalSyntheticOutline0.m(density, iHeight, "oldDensity:", ", newDensity:", sb);
                sb.append(fDensity);
                sb.append("context density: ");
                sb.append(context.getResources().getDisplayMetrics().density);
                LogD.d(ZoomDisplayController.TAG, sb.toString());
                int i2 = rotation != iRotation ? 1 : 0;
                if (Math.max(ZoomDisplayController.this.mLastScreenWidth, ZoomDisplayController.this.mLastScreenHeight) != Math.max(iWidth, iHeight) || Math.min(ZoomDisplayController.this.mLastScreenWidth, ZoomDisplayController.this.mLastScreenHeight) != Math.min(iWidth, iHeight) || density != fDensity) {
                    i2 |= 2;
                }
                ZoomDisplayController.this.mLastScreenWidth = iWidth;
                ZoomDisplayController.this.mLastScreenHeight = iHeight;
                ZoomDisplayController.this.notifyDisplayChanged(i, i2, configuration);
            }

            public void onDisplayRemoved(int i) {
                synchronized (ZoomDisplayController.this.mDisplays) {
                    try {
                        if (ZoomDisplayController.this.mDisplays.get(i) == null) {
                            return;
                        }
                        LogD.d(ZoomDisplayController.TAG, "onDisplayRemoved");
                        ZoomDisplayController.this.mDisplays.remove(i);
                        ZoomDisplayController.this.notifyDisplayRemove(i);
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }

            public void onFixedRotationFinished(int i) {
                LogD.d(ZoomDisplayController.TAG, "onFixedRotationFinished");
                for (int size = ZoomDisplayController.this.mDisplayChangedListeners.size() - 1; size >= 0; size--) {
                    ((DisplayChangedListener) ZoomDisplayController.this.mDisplayChangedListeners.get(size)).onFixedRotationFinished(i);
                }
            }

            public void onFixedRotationStarted(int i, int i2) {
                LogD.d(ZoomDisplayController.TAG, "onFixedRotationStarted");
                for (int size = ZoomDisplayController.this.mDisplayChangedListeners.size() - 1; size >= 0; size--) {
                    ((DisplayChangedListener) ZoomDisplayController.this.mDisplayChangedListeners.get(size)).onFixedRotationStarted(i, i2);
                }
            }
        });
        DisplayController displayController2 = this.mDisplayController;
        ZoomDisplayController$$ExternalSyntheticLambda0 zoomDisplayController$$ExternalSyntheticLambda0 = new ZoomDisplayController$$ExternalSyntheticLambda0();
        zoomDisplayController$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        displayController2.addDisplayChangingController(zoomDisplayController$$ExternalSyntheticLambda0);
    }

    public boolean isLandscape() {
        if (getFocusedDisplay() != null) {
            return getFocusedDisplay().isLandscape();
        }
        return false;
    }

    public boolean isStatusbarHide() {
        Insets insetsCalculateInsets;
        Insets insets;
        InsetsState insetsState = this.mDisplayController.getInsetsState(0);
        return insetsState == null || (insetsCalculateInsets = insetsState.calculateInsets(insetsState.getDisplayFrame(), insetsState.getDisplayFrame(), WindowInsets.Type.statusBars(), false)) == (insets = Insets.NONE) || (insetsCalculateInsets != insets && insetsCalculateInsets.top == 0);
    }

    public void registerDisplayChangedListener(DisplayChangedListener displayChangedListener) {
        this.mDisplayChangedListeners.add(displayChangedListener);
        for (int i = 0; i < this.mDisplays.size(); i++) {
            displayChangedListener.onDisplayAdded(this.mDisplays.keyAt(i));
        }
    }

    public void unRegisterDisplayChangedListener(DisplayChangedListener displayChangedListener) {
        this.mDisplayChangedListeners.remove(displayChangedListener);
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    class DisplayRecord {
        private Context mContext;
        private int mDisplayId;
        DisplayLayout mDisplayLayout;

        private DisplayRecord(int i) {
            this.mDisplayId = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDisplayLayout(Context context, DisplayLayout displayLayout) {
            this.mContext = context;
            this.mDisplayLayout = displayLayout;
        }

        public float getDensity() {
            return this.mDisplayLayout.density();
        }

        public DisplayLayout getDisplayLayout() {
            return this.mDisplayLayout;
        }

        public int getRotation() {
            return this.mDisplayLayout.rotation();
        }

        public int getScreenHeight() {
            return this.mDisplayLayout.height();
        }

        public int getScreenWidth() {
            return this.mDisplayLayout.width();
        }

        public boolean isLandscape() {
            return this.mDisplayLayout.isLandscape();
        }

        public /* synthetic */ DisplayRecord(int i, int i2) {
            this(i);
        }
    }

    public int getScreenHeight(int i) {
        DisplayRecord displayRecord = getDisplayRecord(i);
        if (displayRecord != null) {
            return displayRecord.getScreenHeight();
        }
        return 0;
    }

    public int getScreenWidth(int i) {
        DisplayRecord displayRecord = getDisplayRecord(i);
        if (displayRecord != null) {
            return displayRecord.getScreenWidth();
        }
        return 0;
    }
}
