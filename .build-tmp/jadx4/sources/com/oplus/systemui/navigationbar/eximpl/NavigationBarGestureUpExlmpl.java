package com.oplus.systemui.navigationbar.eximpl;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.view.MotionEvent;
import androidx.compose.ui.spatial.RectList$;
import androidx.slice.widget.RowView$;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.Dependency;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView$;
import com.android.systemui.inputmethod.ui.viewmodel.ImeSwitcherMenuViewModel$MenuItem$;
import com.android.systemui.navigationbar.NavigationBarGestureUpEx;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.oplus.screenshot.OplusScreenshotManager;
import com.oplus.systemui.navigationbar.gesture.AppSettingsHandler;
import com.oplus.systemui.navigationbar.gesture.NavBarOpenPlatformCustomize;
import com.oplus.systemui.navigationbar.gesture.OplusInputInjector;
import com.oplus.systemui.navigationbar.gesture.manager.OplusNavigationBarManager;
import com.oplus.systemui.navigationbar.gesture.swipeupgesture.GestureUpGuideBarView;
import com.oplus.systemui.navigationbar.gesture.swipeupgesture.SwipeUpGestureManager;
import com.oplus.systemui.navigationbar.gesture.swipeupgesture.SwipeUpGestureManager$$ExternalSyntheticLambda0;
import com.oplus.systemui.navigationbar.gesture.swipeupgesture.SwipeUpGesturePreProcess;
import com.oplus.systemui.navigationbar.observer.SwipeUpGestureBarTypeObserver;
import com.oplus.systemui.navigationbar.observer.SwipeUpGestureKeysModeObserver;
import com.oplus.systemui.navigationbar.utils.NavBarUtils;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.utils.Util;
import com.oplusos.systemui.navigationbar.OplusNavigationBarView;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class NavigationBarGestureUpExlmpl extends NavigationBarGestureUpEx {
    private Context context;
    private GestureUpGuideBarView mGestureUpGuideBarView;

    public NavigationBarGestureUpExlmpl(Context context) {
        this.context = context;
    }

    public final Context getContext() {
        return this.context;
    }

    public boolean getShowGuideBar() {
        return SwipeUpGestureBarTypeObserver.INSTANCE.getSwipeUpGestureBarType() == 0;
    }

    public final void initGestureUpGuideBarView() {
        GestureUpGuideBarView gestureUpGuideBarView;
        Context context = this.context;
        if (!NavBarUtils.isSubDisplayId(context, context.getDisplayId()) || (gestureUpGuideBarView = this.mGestureUpGuideBarView) == null) {
            return;
        }
        gestureUpGuideBarView.init(this.context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onFinishInflate(OplusNavigationBarView oplusNavigationBarView) {
        if (this.mGestureUpGuideBarView == null) {
            this.mGestureUpGuideBarView = (GestureUpGuideBarView) oplusNavigationBarView.findViewById(2131363231);
        }
    }

    /* JADX WARN: Code duplicated, block: B:42:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:77:0x016c  */
    /* JADX WARN: Code duplicated, block: B:98:0x01d5  */
    public void onMotionEvent(MotionEvent motionEvent) {
        String str;
        int i;
        int i2;
        boolean z;
        SwipeUpGestureManager swipeUpGestureManager = SwipeUpGestureManager.getInstance(this.context);
        swipeUpGestureManager.getClass();
        if ((motionEvent.getSource() & 2) == 0) {
            return;
        }
        boolean z2 = ((StatusBarStateController) Dependency.sDependency.getDependencyInner(StatusBarStateController.class)).getState() == 1;
        MotionEvent motionEventOffsetEvent = NavBarUtils.offsetEvent(motionEvent);
        KeyguardStateControllerImpl keyguardStateControllerImpl = OplusNavigationBarManager.getInstance().mKeyguardStateController;
        boolean z3 = keyguardStateControllerImpl != null && keyguardStateControllerImpl.mShowing && z2;
        String str2 = "SwipeUpGestureManager";
        if (motionEventOffsetEvent.getAction() == 0) {
            LogUtil.i(LogUtil.TAG_NAVBAR, "SwipeUpGestureManager", "onInputEvent down event:" + motionEventOffsetEvent);
            if (swipeUpGestureManager.mIsValidTouchEvent) {
                str2 = "SwipeUpGestureManager";
            } else {
                SwipeUpGesturePreProcess swipeUpGesturePreProcess = swipeUpGestureManager.mSwipeUpGesturePreprocess;
                swipeUpGesturePreProcess.getClass();
                if (motionEventOffsetEvent.getDownTime() - motionEventOffsetEvent.getEventTime() == -1) {
                    i2 = 1001;
                } else if (swipeUpGesturePreProcess.mLastEventTime == motionEventOffsetEvent.getEventTime()) {
                    LogUtil.d(LogUtil.TAG_NAVBAR, "SwipeUpGesturePreProcess", "drop the event, because it's an injected event");
                    i2 = 1001;
                } else {
                    swipeUpGesturePreProcess.mLastEventTime = motionEventOffsetEvent.getEventTime();
                    int x = (int) motionEventOffsetEvent.getX();
                    int y = (int) motionEventOffsetEvent.getY();
                    StringBuilder sb = new StringBuilder("mScreenHeight:");
                    RectList$.ExternalSyntheticOutline0.m(swipeUpGesturePreProcess.mScreenHeight, y, " DownY:", " mBottomGestureAreaHeight:", sb);
                    SwipeUpGestureManager swipeUpGestureManager2 = swipeUpGesturePreProcess.mSwipeUpGestureManager;
                    sb.append(swipeUpGestureManager2.getBottomGestureAreaHeight());
                    LogUtil.i(LogUtil.TAG_NAVBAR, "SwipeUpGesturePreProcess", sb.toString());
                    boolean z4 = y >= swipeUpGesturePreProcess.mScreenHeight - swipeUpGestureManager2.getBottomGestureAreaHeight() && x >= 0 && x <= swipeUpGesturePreProcess.mScreenWidth;
                    if (z4) {
                        swipeUpGesturePreProcess.mSwipeActionState = 4000;
                        if (NavBarUtils.isGestureUpMode()) {
                            if (z3) {
                                Context context = swipeUpGesturePreProcess.mContext;
                                Uri uri = Util.ROM_UPDATE_CONTENT_URI;
                                try {
                                    if (!((OplusScreenshotManager) context.getSystemService("color_screenshot")).isScreenshotEdit()) {
                                        z = false;
                                    }
                                } catch (NoSuchMethodError unused) {
                                    LogUtil.e(LogUtil.TAG_COMMON, "Util", "OplusScreenshotManager no method isScreenshotEdit!!");
                                }
                            }
                            float f = (x * 1.0f) / swipeUpGesturePreProcess.mScreenWidth;
                            int swipeUpGestureKeysMode = SwipeUpGestureKeysModeObserver.INSTANCE.getSwipeUpGestureKeysMode();
                            boolean z5 = swipeUpGestureKeysMode == 0;
                            if (swipeUpGestureKeysMode == 0 || swipeUpGestureKeysMode == 3) {
                                if (f < 0.33333334f) {
                                    if (z5) {
                                        swipeUpGesturePreProcess.mSwipeActionState = 4001;
                                    } else {
                                        swipeUpGesturePreProcess.mSwipeActionState = 4003;
                                    }
                                } else if (f < 0.6666667f) {
                                    swipeUpGesturePreProcess.mSwipeActionState = 4002;
                                } else if (z5) {
                                    swipeUpGesturePreProcess.mSwipeActionState = 4003;
                                } else {
                                    swipeUpGesturePreProcess.mSwipeActionState = 4001;
                                }
                            } else if (swipeUpGestureKeysMode == 2) {
                                if (f <= 0.33333334f || f >= 0.6666667f) {
                                    swipeUpGesturePreProcess.mSwipeActionState = 4003;
                                } else {
                                    swipeUpGesturePreProcess.mSwipeActionState = 4002;
                                }
                            }
                            z = true;
                        } else {
                            z = false;
                        }
                        ClipboardOverlayView$.ExternalSyntheticOutline0.m(swipeUpGesturePreProcess.mSwipeActionState, LogUtil.TAG_NAVBAR, "SwipeUpGesturePreProcess", RowView$.ExternalSyntheticOutline0.m("checkFullScreenGesture result ", " mSwipeActionState ", z));
                        if (z) {
                            if (!z3) {
                                int swipeActionState = swipeUpGesturePreProcess.getSwipeActionState();
                                if (SwipeUpGestureKeysModeObserver.INSTANCE.getSwipeUpGestureKeysMode() == 2 && swipeActionState == 4002) {
                                    i2 = 1001;
                                }
                            }
                            if ((AppSettingsHandler.getInstance(swipeUpGesturePreProcess.mContext).mDisableFlags & 16) != 0 || (AppSettingsHandler.getInstance(swipeUpGesturePreProcess.mContext).mDisableFlags & 2) != 0) {
                                LogUtil.i(LogUtil.TAG_NAVBAR, "SwipeUpGesturePreProcess", "disable back gesture because disable flag");
                            } else if (NavBarOpenPlatformCustomize.Companion.getInstance(swipeUpGesturePreProcess.mContext).isNavigationBarDisabled) {
                                LogUtil.i(LogUtil.TAG_NAVBAR, "SwipeUpGesturePreProcess", "NavigationBar is disabled by customized API");
                            } else {
                                i2 = 1002;
                            }
                            i2 = 1000;
                        } else {
                            i2 = 1000;
                        }
                    } else {
                        i2 = 1001;
                    }
                    StringBuilder sbM = OplusLockIconView$.ExternalSyntheticOutline0.m("verifyValidTouchDown isLockScreen:", x, " downX:", " downY:", z3);
                    ImeSwitcherMenuViewModel$MenuItem$.ExternalSyntheticOutline0.m(y, " isBottomRange:", " state:", sbM, z4);
                    ClipboardOverlayView$.ExternalSyntheticOutline0.m(i2, LogUtil.TAG_NAVBAR, "SwipeUpGesturePreProcess", sbM);
                }
                swipeUpGestureManager.mGestureDownState = i2;
                if (i2 == 1000) {
                    OplusInputInjector.getInstance().injectMotionEvent(motionEventOffsetEvent);
                }
                swipeUpGestureManager.mIsValidTouchEvent = swipeUpGestureManager.mGestureDownState == 1002;
                swipeUpGestureManager.mHasMoveEvent = false;
            }
        } else {
            str2 = "SwipeUpGestureManager";
        }
        if (swipeUpGestureManager.mIsValidTouchEvent && swipeUpGestureManager.mIsSendDownEvent) {
            if (motionEventOffsetEvent.getAction() == 3) {
                MotionEvent motionEventObtain = MotionEvent.obtain(motionEventOffsetEvent);
                swipeUpGestureManager.mCancelEvent = motionEventObtain;
                motionEventObtain.setAction(1);
                Handler handler = swipeUpGestureManager.mSwipeUpGestureUIHandler;
                SwipeUpGestureManager$$ExternalSyntheticLambda0 swipeUpGestureManager$$ExternalSyntheticLambda0 = swipeUpGestureManager.mCancelTimeOutRunnable;
                if (handler.hasCallbacks(swipeUpGestureManager$$ExternalSyntheticLambda0)) {
                    handler.removeCallbacks(swipeUpGestureManager$$ExternalSyntheticLambda0);
                }
                swipeUpGestureManager.mSwipeUpGestureUIHandler.postDelayed(swipeUpGestureManager.mCancelTimeOutRunnable, 200L);
            } else if (motionEventOffsetEvent.getAction() == 0) {
                Handler handler2 = swipeUpGestureManager.mSwipeUpGestureUIHandler;
                SwipeUpGestureManager$$ExternalSyntheticLambda0 swipeUpGestureManager$$ExternalSyntheticLambda1 = swipeUpGestureManager.mCancelTimeOutRunnable;
                if (handler2.hasCallbacks(swipeUpGestureManager$$ExternalSyntheticLambda1)) {
                    handler2.removeCallbacks(swipeUpGestureManager$$ExternalSyntheticLambda1);
                }
                if (motionEventOffsetEvent.getEventTime() - motionEventOffsetEvent.getDownTime() == 1 || swipeUpGestureManager.mSwipeUpGesturePreprocess.getLastEventTime() == motionEventOffsetEvent.getEventTime()) {
                    str = str2;
                    LogUtil.i(LogUtil.TAG_NAVBAR, str, "received injected event!");
                    swipeUpGestureManager.mIsValidTouchEvent = false;
                    swipeUpGestureManager.mIsSendDownEvent = false;
                } else {
                    str = str2;
                }
                MotionEvent motionEvent2 = swipeUpGestureManager.mCancelEvent;
                if (motionEvent2 != null && ((motionEvent2.getX() != motionEventOffsetEvent.getX() || swipeUpGestureManager.mCancelEvent.getY() != motionEventOffsetEvent.getY()) && !swipeUpGestureManager.mHasMoveEvent)) {
                    swipeUpGestureManager.addPointerEvent(swipeUpGestureManager.mCancelEvent);
                    swipeUpGestureManager.mIsSendDownEvent = true;
                }
            }
            str = str2;
        } else {
            str = str2;
        }
        if (!swipeUpGestureManager.mIsValidTouchEvent || motionEventOffsetEvent.getAction() == 3 || (motionEventOffsetEvent.getAction() == 0 && swipeUpGestureManager.mIsSendDownEvent)) {
            i = 1;
        } else {
            swipeUpGestureManager.addPointerEvent(motionEventOffsetEvent);
            i = 1;
            swipeUpGestureManager.mIsSendDownEvent = true;
            if (motionEventOffsetEvent.getAction() == 2) {
                swipeUpGestureManager.mHasMoveEvent = true;
            }
        }
        if (motionEventOffsetEvent.getAction() == i) {
            if (swipeUpGestureManager.mGestureDownState == 1000) {
                OplusInputInjector.getInstance().injectMotionEvent(motionEventOffsetEvent);
            }
            LogUtil.i(LogUtil.TAG_NAVBAR, str, "finish this dragging process, reset state");
            swipeUpGestureManager.mCancelEvent = null;
            swipeUpGestureManager.mHasMoveEvent = false;
            swipeUpGestureManager.mIsValidTouchEvent = false;
            swipeUpGestureManager.mIsSendDownEvent = false;
            swipeUpGestureManager.mGestureDownState = 1001;
        }
    }

    public final void setContext(Context context) {
        this.context = context;
    }

    public final void setGestureKeysMode() {
        GestureUpGuideBarView gestureUpGuideBarView = this.mGestureUpGuideBarView;
        if (gestureUpGuideBarView != null) {
            boolean z = SwipeUpGestureKeysModeObserver.INSTANCE.getSwipeUpGestureKeysMode() == 1;
            if (gestureUpGuideBarView.mOnlyHomeBar != z) {
                gestureUpGuideBarView.mOnlyHomeBar = z;
                gestureUpGuideBarView.init(gestureUpGuideBarView.mContext);
                gestureUpGuideBarView.updateButtonViews();
            }
        }
    }

    public void setVisibility(int i) {
        GestureUpGuideBarView gestureUpGuideBarView = this.mGestureUpGuideBarView;
        if (gestureUpGuideBarView != null) {
            gestureUpGuideBarView.setVisibility(i);
        }
    }

    public final void updateSwipeUpGestureDisplaySize(int i) {
        SwipeUpGestureManager.getInstance(this.context).updateSwipeUpGestureDisplaySize(i);
    }
}
