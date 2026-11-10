package com.oplus.systemui.notification.headsup;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.InputQueue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowInsetsController;
import com.android.internal.view.FloatingActionMode;
import com.android.internal.widget.floatingtoolbar.FloatingToolbar;
import java.lang.invoke.VarHandle;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FloatingActionHelper {
    private final Context context;
    private final FloatingActionHelper$fakeWindow$1 fakeWindow;
    private ActionMode floatingActionMode;
    private View floatingActionModeOriginatingView;
    private FloatingToolbar floatingToolbar;
    private ViewTreeObserver.OnPreDrawListener floatingToolbarPreDrawListener;
    public View view;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class ActionModeCallback2Wrapper extends ActionMode.Callback2 {
        private final ActionMode.Callback wrapped;

        public ActionModeCallback2Wrapper(ActionMode.Callback callback) {
            this.wrapped = callback;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.wrapped.onActionItemClicked(actionMode, menuItem);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.wrapped.onCreateActionMode(actionMode, menu);
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
            this.wrapped.onDestroyActionMode(actionMode);
            if (Intrinsics.areEqual(actionMode, FloatingActionHelper.this.floatingActionMode)) {
                FloatingActionHelper.this.cleanupFloatingActionModeViews();
                FloatingActionHelper.this.floatingActionMode = null;
            }
            View view = FloatingActionHelper.this.view;
            if (view != null) {
                view.requestFitSystemWindows();
            }
        }

        @Override // android.view.ActionMode.Callback2
        public void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
            ActionMode.Callback callback = this.wrapped;
            if (callback instanceof ActionMode.Callback2) {
                ((ActionMode.Callback2) callback).onGetContentRect(actionMode, view, rect);
            } else {
                super.onGetContentRect(actionMode, view, rect);
            }
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            View view = FloatingActionHelper.this.view;
            if (view != null) {
                if (view == null) {
                    view = null;
                }
                view.requestFitSystemWindows();
            }
            return this.wrapped.onPrepareActionMode(actionMode, menu);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oplus.systemui.notification.headsup.FloatingActionHelper$fakeWindow$1] */
    public FloatingActionHelper(final Context context) {
        this.context = context;
        this.fakeWindow = new Window(context) { // from class: com.oplus.systemui.notification.headsup.FloatingActionHelper$fakeWindow$1
            @Override // android.view.Window
            public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
            }

            public final void alwaysReadCloseOnTouchAttr() {
            }

            public final void clearContentView() {
            }

            @Override // android.view.Window
            public final void closeAllPanels() {
            }

            @Override // android.view.Window
            public final void closePanel(int i) {
            }

            @Override // android.view.Window
            public View getCurrentFocus() {
                return null;
            }

            @Override // android.view.Window
            public View getDecorView() {
                View view = this.this$0.view;
                if (view == null) {
                    return null;
                }
                return view;
            }

            @Override // android.view.Window
            public WindowInsetsController getInsetsController() {
                return null;
            }

            @Override // android.view.Window
            public LayoutInflater getLayoutInflater() {
                return LayoutInflater.from(getContext());
            }

            @Override // android.view.Window
            public int getNavigationBarColor() {
                return 0;
            }

            @Override // android.view.Window
            public int getStatusBarColor() {
                return 0;
            }

            @Override // android.view.Window
            public int getVolumeControlStream() {
                return 0;
            }

            @Override // android.view.Window
            public final void invalidatePanelMenu(int i) {
            }

            @Override // android.view.Window
            public final boolean isFloating() {
                return false;
            }

            @Override // android.view.Window
            public final boolean isShortcutKey(int i, KeyEvent keyEvent) {
                return false;
            }

            @Override // android.view.Window
            public final void onActive() {
            }

            @Override // android.view.Window
            public final void onConfigurationChanged(Configuration configuration) {
            }

            public final void onMultiWindowModeChanged() {
            }

            public final void onPictureInPictureModeChanged(boolean z) {
            }

            @Override // android.view.Window
            public final void openPanel(int i, KeyEvent keyEvent) {
            }

            @Override // android.view.Window
            public final View peekDecorView() {
                return null;
            }

            @Override // android.view.Window
            public final boolean performContextMenuIdentifierAction(int i, int i2) {
                return false;
            }

            @Override // android.view.Window
            public final boolean performPanelIdentifierAction(int i, int i2, int i3) {
                return false;
            }

            @Override // android.view.Window
            public final boolean performPanelShortcut(int i, int i2, KeyEvent keyEvent, int i3) {
                return false;
            }

            @Override // android.view.Window
            public final void restoreHierarchyState(Bundle bundle) {
            }

            @Override // android.view.Window
            public final Bundle saveHierarchyState() {
                return null;
            }

            @Override // android.view.Window
            public void setBackgroundDrawable(Drawable drawable) {
            }

            @Override // android.view.Window
            public final void setChildDrawable(int i, Drawable drawable) {
            }

            @Override // android.view.Window
            public final void setChildInt(int i, int i2) {
            }

            @Override // android.view.Window
            public void setContentView(int i) {
            }

            @Override // android.view.Window
            public void setContentView(View view) {
            }

            @Override // android.view.Window
            public void setDecorCaptionShade(int i) {
            }

            @Override // android.view.Window
            public final void setFeatureDrawable(int i, Drawable drawable) {
            }

            @Override // android.view.Window
            public final void setFeatureDrawableAlpha(int i, int i2) {
            }

            @Override // android.view.Window
            public final void setFeatureDrawableResource(int i, int i2) {
            }

            @Override // android.view.Window
            public final void setFeatureDrawableUri(int i, Uri uri) {
            }

            @Override // android.view.Window
            public final void setFeatureInt(int i, int i2) {
            }

            @Override // android.view.Window
            public void setNavigationBarColor(int i) {
            }

            @Override // android.view.Window
            public void setResizingCaptionDrawable(Drawable drawable) {
            }

            @Override // android.view.Window
            public void setStatusBarColor(int i) {
            }

            @Override // android.view.Window
            public void setTitle(CharSequence charSequence) {
            }

            @Override // android.view.Window
            public void setTitleColor(int i) {
            }

            @Override // android.view.Window
            public void setVolumeControlStream(int i) {
            }

            @Override // android.view.Window
            public final boolean superDispatchGenericMotionEvent(MotionEvent motionEvent) {
                return false;
            }

            @Override // android.view.Window
            public final boolean superDispatchKeyEvent(KeyEvent keyEvent) {
                return false;
            }

            @Override // android.view.Window
            public final boolean superDispatchKeyShortcutEvent(KeyEvent keyEvent) {
                return false;
            }

            @Override // android.view.Window
            public final boolean superDispatchTouchEvent(MotionEvent motionEvent) {
                return false;
            }

            @Override // android.view.Window
            public final boolean superDispatchTrackballEvent(MotionEvent motionEvent) {
                return false;
            }

            @Override // android.view.Window
            public final void takeInputQueue(InputQueue.Callback callback) {
            }

            @Override // android.view.Window
            public final void takeKeyEvents(boolean z) {
            }

            @Override // android.view.Window
            public final void takeSurface(SurfaceHolder.Callback2 callback2) {
            }

            @Override // android.view.Window
            public final void togglePanel(int i, KeyEvent keyEvent) {
            }

            @Override // android.view.Window
            public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
            }
        };
    }

    private final void setHandledFloatingActionMode(ActionMode actionMode) {
        ViewTreeObserver viewTreeObserver;
        this.floatingActionMode = actionMode;
        if (actionMode != null) {
            actionMode.invalidate();
        }
        View view = this.floatingActionModeOriginatingView;
        if (view == null || (viewTreeObserver = view.getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnPreDrawListener(this.floatingToolbarPreDrawListener);
    }

    public final void cleanupFloatingActionModeViews() {
        FloatingToolbar floatingToolbar = this.floatingToolbar;
        if (floatingToolbar != null) {
            floatingToolbar.dismiss();
        }
        this.floatingToolbar = null;
        View view = this.floatingActionModeOriginatingView;
        if (view != null) {
            ViewTreeObserver.OnPreDrawListener onPreDrawListener = this.floatingToolbarPreDrawListener;
            if (onPreDrawListener != null) {
                view.getViewTreeObserver().removeOnPreDrawListener(onPreDrawListener);
            }
            this.floatingToolbarPreDrawListener = null;
        }
        this.floatingActionModeOriginatingView = null;
    }

    public final void setView(View view) {
        this.view = view;
    }

    public final ActionMode startActionMode(View view, ActionMode.Callback callback) {
        ActionModeCallback2Wrapper actionModeCallback2Wrapper = new ActionModeCallback2Wrapper(callback);
        ActionMode actionMode = this.floatingActionMode;
        if (actionMode != null) {
            actionMode.finish();
        }
        cleanupFloatingActionModeViews();
        this.floatingToolbar = new FloatingToolbar(this.fakeWindow);
        FloatingActionMode floatingActionMode = new FloatingActionMode(this.context, actionModeCallback2Wrapper, view, this.floatingToolbar);
        this.floatingActionModeOriginatingView = view;
        createFloatingActionMode.1 r4 = new createFloatingActionMode.1();
        r4.$mode = floatingActionMode;
        VarHandle.storeStoreFence();
        this.floatingToolbarPreDrawListener = r4;
        if (!actionModeCallback2Wrapper.onCreateActionMode(floatingActionMode, floatingActionMode.getMenu())) {
            return null;
        }
        setHandledFloatingActionMode(floatingActionMode);
        return floatingActionMode;
    }
}
