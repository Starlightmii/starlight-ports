package com.oplus.systemui.navigationbar.gesture.sidegesture;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserHandle;
import android.util.Log;
import android.view.OplusScreenDragUtil;
import android.view.OplusWindowManager;
import androidx.activity.result.ActivityResultRegistry$;
import androidx.appcompat.widget.ListPopupWindow$;
import androidx.core.view.DisplayShapeCompat$;
import com.android.internal.policy.SystemBarUtils;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.Dependency;
import com.android.systemui.application.impl.OplusSystemUIApplicationUtils;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.oplus.compatui.OplusCompatDcsUtils$;
import com.oplus.systemui.navigationbar.gesture.subdisplay.ConfigurationOnSubDisplay;
import com.oplus.systemui.navigationbar.observer.NavStateObserver;
import com.oplus.systemui.navigationbar.utils.DisplaySizeUtils;
import com.oplus.systemui.navigationbar.utils.NavBarUtils;
import com.oplus.systemui.navigationbar.utils.SupportOutUtils;
import com.oplusos.systemui.common.sched.OplusSysUiSchedMgr;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.OplusTrackProxyUtil;
import java.io.PrintWriter;
import java.lang.invoke.VarHandle;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class InvalidRegionControl {
    public static final Companion Companion = new Companion();
    private static final String REGION_OPEN_CLOSE_TYPE = "region_open_close_type";
    private static final String SUBTAG = "InvalidRegionControl";
    public int mBottomGestureAreaHeight;
    private final BroadcastReceiver mBroadcastReceiver;
    private Context mContext;
    public final int mDisplayId;
    private String mDisplayIdTag;
    public final boolean mIsOnSubDisplayId;
    private final InvalidRegionControl$mNavStateListener$1 mNavStateListener;
    public int mRotation;
    public int mScreenHeight;
    public int mScreenWidth;
    public int mSideGestureAreaWidthLeft;
    public int mSideGestureAreaWidthRight;
    private SideGestureConfiguration mSideGestureConfiguration;
    private final ArrayList<RectF> mSwipeTouchRegions = new ArrayList<>();
    private final RectF mBottomRect = new RectF();
    private final RectF mLeftRect = new RectF();
    private final RectF mRightRect = new RectF();
    private Rect mSideBarSceneRegion = new Rect();
    private final RectF mFirstRegionAfterExcludeSmartSideBar = new RectF();
    private final RectF mSecondRegionAfterExcludeSmartSideBar = new RectF();
    private final Matrix mMatrix = new Matrix();
    private final RectF mRectF = new RectF();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: renamed from: com.oplus.systemui.navigationbar.gesture.sidegesture.InvalidRegionControl$updateTouchBounds$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Runnable {
        public /* synthetic */ InvalidRegionControl this$0;

        @Override // java.lang.Runnable
        public final void run() {
            InvalidRegionControl invalidRegionControl = this.this$0;
            InvalidRegionControl.access$handleUpdateTouchBounds(invalidRegionControl, invalidRegionControl.mDisplayId);
        }
    }

    public InvalidRegionControl(Context context, SideGestureConfiguration sideGestureConfiguration, int i) {
        this.mContext = context;
        this.mSideGestureConfiguration = sideGestureConfiguration;
        this.mDisplayId = i;
        this.mDisplayIdTag = "display_id_";
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.oplus.systemui.navigationbar.gesture.sidegesture.InvalidRegionControl$mBroadcastReceiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                this.this$0.updateTouchBounds();
            }
        };
        this.mBroadcastReceiver = broadcastReceiver;
        InvalidRegionControl$mNavStateListener$1 invalidRegionControl$mNavStateListener$1 = new InvalidRegionControl$mNavStateListener$1();
        invalidRegionControl$mNavStateListener$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.mNavStateListener = invalidRegionControl$mNavStateListener$1;
        this.mDisplayIdTag = this.mDisplayIdTag + i + " InvalidRegionControl";
        ConfigurationOnSubDisplay.Companion.getInstance(this.mContext).getClass();
        this.mIsOnSubDisplayId = ConfigurationOnSubDisplay.isSubDisplayId(i);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.oplus.intent.action.START_DRAG_WINDOW");
        intentFilter.addAction("com.oplus.intent.action.EXIT_DRAG_WINDOW");
        intentFilter.addAction("com.oplus.intent.action.DRAG_WINDOW_SWTICH_SIDE");
        BroadcastDispatcher.registerReceiver$default((BroadcastDispatcher) Dependency.sDependency.getDependencyInner(BroadcastDispatcher.class), broadcastReceiver, intentFilter, (Executor) null, (UserHandle) null, 0, 60);
        NavStateObserver.INSTANCE.addListener(invalidRegionControl$mNavStateListener$1);
        updateTouchBounds();
    }

    public static final void access$handleUpdateTouchBounds(InvalidRegionControl invalidRegionControl, int i) {
        int i2;
        boolean z = invalidRegionControl.mIsOnSubDisplayId;
        if (!OplusSystemUIApplicationUtils.isMainSystemUiThread()) {
            LogUtil.e$default(8, LogUtil.TAG_NAVBAR, "NoBackGesture", "handleUpdateTouchBounds: Skipping due to non-primary user");
            return;
        }
        invalidRegionControl.mSwipeTouchRegions.clear();
        int navState = NavStateObserver.INSTANCE.getNavState();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "NoBackGesture-->".concat(invalidRegionControl.mDisplayIdTag + " handleUpdateTouchBounds, navState: " + navState + ", mIsOnSubDisplayId: " + z));
        }
        boolean zUpdateInvalidRegion = true;
        if (navState != 0) {
            if (navState == 2) {
                RectF rectF = invalidRegionControl.mBottomRect;
                int i3 = invalidRegionControl.mScreenHeight;
                rectF.set(0.0f, i3 - invalidRegionControl.mBottomGestureAreaHeight, invalidRegionControl.mScreenWidth, i3);
                invalidRegionControl.offsetRect();
                if (!SupportOutUtils.isSystemUiSupportClearBottomGestureArea(invalidRegionControl.mContext)) {
                    invalidRegionControl.mSwipeTouchRegions.add(invalidRegionControl.mBottomRect);
                }
            } else if (navState == 3) {
                if (z) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                        Log.d("SystemUi--NavBar", "NoBackGesture-->update on sub display for SIDE_GESTURE");
                    }
                    invalidRegionControl.updateRegionOnSubDisplay();
                } else {
                    int statusBarHeight = SystemBarUtils.getStatusBarHeight(invalidRegionControl.mContext);
                    Rect rect = new Rect();
                    rect.set(invalidRegionControl.mSideBarSceneRegion);
                    if (rect.isEmpty() || rect.bottom < statusBarHeight || (i2 = rect.top) > invalidRegionControl.mScreenHeight - invalidRegionControl.mBottomGestureAreaHeight) {
                        int iConvertDpToPixel = NavBarUtils.isSupportHotZoneAvoid(invalidRegionControl.mContext) ? DisplaySizeUtils.convertDpToPixel(invalidRegionControl.mContext, 50.0f) : 0;
                        RectF rectF2 = invalidRegionControl.mBottomRect;
                        int i4 = invalidRegionControl.mScreenHeight;
                        rectF2.set(0.0f, i4 - invalidRegionControl.mBottomGestureAreaHeight, invalidRegionControl.mScreenWidth, i4 - iConvertDpToPixel);
                        float f = statusBarHeight;
                        invalidRegionControl.mLeftRect.set(0.0f, f, invalidRegionControl.mSideGestureAreaWidthLeft, invalidRegionControl.mScreenHeight - iConvertDpToPixel);
                        RectF rectF3 = invalidRegionControl.mRightRect;
                        int i5 = invalidRegionControl.mScreenWidth;
                        rectF3.set(i5 - invalidRegionControl.mSideGestureAreaWidthRight, f, i5, invalidRegionControl.mScreenHeight - iConvertDpToPixel);
                        invalidRegionControl.offsetRect();
                        boolean zIsSupportHotZoneAvoid = NavBarUtils.isSupportHotZoneAvoid(invalidRegionControl.mContext);
                        RectF rectF4 = invalidRegionControl.mBottomRect;
                        int i6 = invalidRegionControl.mRotation;
                        if (zIsSupportHotZoneAvoid) {
                            invalidRegionControl.rotationalCoordinateForWorkStation(rectF4, i6);
                            invalidRegionControl.rotationalCoordinateForWorkStation(invalidRegionControl.mLeftRect, invalidRegionControl.mRotation);
                            invalidRegionControl.rotationalCoordinateForWorkStation(invalidRegionControl.mRightRect, invalidRegionControl.mRotation);
                        } else {
                            float f2 = rectF4.top;
                            LogUtil.i$default(LogUtil.TAG_NAVBAR, "NoBackGesture", invalidRegionControl.mDisplayIdTag + " transCoordinateByRotation origin rect=" + rectF4, null, 8);
                            if (i6 == 1) {
                                rectF4.set(0.0f, 0.0f, invalidRegionControl.mScreenHeight - f2, invalidRegionControl.mScreenWidth);
                            } else if (i6 == 2) {
                                rectF4.set(0.0f, 0.0f, invalidRegionControl.mScreenWidth, invalidRegionControl.mScreenHeight - f2);
                            } else if (i6 == 3) {
                                rectF4.set(f2, 0.0f, invalidRegionControl.mScreenHeight, invalidRegionControl.mScreenWidth);
                            }
                            invalidRegionControl.transLeftCoordinateByRotation(invalidRegionControl.mLeftRect, invalidRegionControl.mRotation);
                            invalidRegionControl.transRightCoordinateByRotation(invalidRegionControl.mRightRect, invalidRegionControl.mRotation);
                        }
                        if (!SupportOutUtils.isSystemUiSupportClearBottomGestureArea(invalidRegionControl.mContext)) {
                            invalidRegionControl.mSwipeTouchRegions.add(invalidRegionControl.mBottomRect);
                        }
                        invalidRegionControl.mSwipeTouchRegions.add(invalidRegionControl.mLeftRect);
                        invalidRegionControl.mSwipeTouchRegions.add(invalidRegionControl.mRightRect);
                    } else {
                        rect.set(rect.left, Math.max(i2, statusBarHeight), rect.right, rect.bottom);
                        int i7 = rect.left;
                        int i8 = invalidRegionControl.mScreenWidth;
                        int i9 = i8 / 2;
                        RectF rectF5 = invalidRegionControl.mFirstRegionAfterExcludeSmartSideBar;
                        if (i7 < i9) {
                            float f3 = statusBarHeight;
                            rectF5.set(0.0f, f3, invalidRegionControl.mSideGestureAreaWidthLeft, rect.top);
                            RectF rectF6 = invalidRegionControl.mSecondRegionAfterExcludeSmartSideBar;
                            float f4 = rect.bottom;
                            float f5 = invalidRegionControl.mSideGestureAreaWidthLeft;
                            boolean zIsSupportHotZoneAvoid2 = NavBarUtils.isSupportHotZoneAvoid(invalidRegionControl.mContext);
                            int iConvertDpToPixel2 = invalidRegionControl.mScreenHeight;
                            if (zIsSupportHotZoneAvoid2) {
                                iConvertDpToPixel2 -= DisplaySizeUtils.convertDpToPixel(invalidRegionControl.mContext, 50.0f);
                            }
                            rectF6.set(0.0f, f4, f5, iConvertDpToPixel2);
                            RectF rectF7 = invalidRegionControl.mRightRect;
                            int i10 = invalidRegionControl.mScreenWidth;
                            float f6 = i10 - invalidRegionControl.mSideGestureAreaWidthRight;
                            float f7 = i10;
                            boolean zIsSupportHotZoneAvoid3 = NavBarUtils.isSupportHotZoneAvoid(invalidRegionControl.mContext);
                            int iConvertDpToPixel3 = invalidRegionControl.mScreenHeight;
                            if (zIsSupportHotZoneAvoid3) {
                                iConvertDpToPixel3 -= DisplaySizeUtils.convertDpToPixel(invalidRegionControl.mContext, 50.0f);
                            }
                            rectF7.set(f6, f3, f7, iConvertDpToPixel3);
                            RectF rectF8 = invalidRegionControl.mBottomRect;
                            float f8 = invalidRegionControl.mScreenHeight - invalidRegionControl.mBottomGestureAreaHeight;
                            float f9 = invalidRegionControl.mScreenWidth;
                            boolean zIsSupportHotZoneAvoid4 = NavBarUtils.isSupportHotZoneAvoid(invalidRegionControl.mContext);
                            int iConvertDpToPixel4 = invalidRegionControl.mScreenHeight;
                            if (zIsSupportHotZoneAvoid4) {
                                iConvertDpToPixel4 -= DisplaySizeUtils.convertDpToPixel(invalidRegionControl.mContext, 50.0f);
                            }
                            rectF8.set(0.0f, f8, f9, iConvertDpToPixel4);
                            invalidRegionControl.offsetRect();
                            boolean zIsSupportHotZoneAvoid5 = NavBarUtils.isSupportHotZoneAvoid(invalidRegionControl.mContext);
                            RectF rectF9 = invalidRegionControl.mBottomRect;
                            int i11 = invalidRegionControl.mRotation;
                            if (zIsSupportHotZoneAvoid5) {
                                invalidRegionControl.rotationalCoordinateForWorkStation(rectF9, i11);
                                invalidRegionControl.rotationalCoordinateForWorkStation(invalidRegionControl.mFirstRegionAfterExcludeSmartSideBar, invalidRegionControl.mRotation);
                                invalidRegionControl.rotationalCoordinateForWorkStation(invalidRegionControl.mSecondRegionAfterExcludeSmartSideBar, invalidRegionControl.mRotation);
                                invalidRegionControl.rotationalCoordinateForWorkStation(invalidRegionControl.mRightRect, invalidRegionControl.mRotation);
                            } else {
                                invalidRegionControl.transCoordinateByRotationCommon(rectF9, i11);
                                invalidRegionControl.transCoordinateByRotationCommon(invalidRegionControl.mFirstRegionAfterExcludeSmartSideBar, invalidRegionControl.mRotation);
                                invalidRegionControl.transCoordinateByRotationCommon(invalidRegionControl.mSecondRegionAfterExcludeSmartSideBar, invalidRegionControl.mRotation);
                                invalidRegionControl.transCoordinateByRotationCommon(invalidRegionControl.mRightRect, invalidRegionControl.mRotation);
                            }
                            if (!SupportOutUtils.isSystemUiSupportClearBottomGestureArea(invalidRegionControl.mContext)) {
                                invalidRegionControl.mSwipeTouchRegions.add(invalidRegionControl.mBottomRect);
                            }
                            invalidRegionControl.mSwipeTouchRegions.add(invalidRegionControl.mRightRect);
                            invalidRegionControl.mSwipeTouchRegions.add(invalidRegionControl.mFirstRegionAfterExcludeSmartSideBar);
                            invalidRegionControl.mSwipeTouchRegions.add(invalidRegionControl.mSecondRegionAfterExcludeSmartSideBar);
                        } else {
                            float f10 = statusBarHeight;
                            rectF5.set(i8 - invalidRegionControl.mSideGestureAreaWidthRight, f10, i8, rect.top);
                            RectF rectF10 = invalidRegionControl.mSecondRegionAfterExcludeSmartSideBar;
                            int i12 = invalidRegionControl.mScreenWidth;
                            float f11 = i12 - invalidRegionControl.mSideGestureAreaWidthRight;
                            float f12 = rect.bottom;
                            float f13 = i12;
                            boolean zIsSupportHotZoneAvoid6 = NavBarUtils.isSupportHotZoneAvoid(invalidRegionControl.mContext);
                            int iConvertDpToPixel5 = invalidRegionControl.mScreenHeight;
                            if (zIsSupportHotZoneAvoid6) {
                                iConvertDpToPixel5 -= DisplaySizeUtils.convertDpToPixel(invalidRegionControl.mContext, 50.0f);
                            }
                            rectF10.set(f11, f12, f13, iConvertDpToPixel5);
                            RectF rectF11 = invalidRegionControl.mLeftRect;
                            float f14 = invalidRegionControl.mSideGestureAreaWidthLeft;
                            boolean zIsSupportHotZoneAvoid7 = NavBarUtils.isSupportHotZoneAvoid(invalidRegionControl.mContext);
                            int iConvertDpToPixel6 = invalidRegionControl.mScreenHeight;
                            if (zIsSupportHotZoneAvoid7) {
                                iConvertDpToPixel6 -= DisplaySizeUtils.convertDpToPixel(invalidRegionControl.mContext, 50.0f);
                            }
                            rectF11.set(0.0f, f10, f14, iConvertDpToPixel6);
                            RectF rectF12 = invalidRegionControl.mBottomRect;
                            int i13 = invalidRegionControl.mScreenHeight;
                            rectF12.set(0.0f, i13 - invalidRegionControl.mBottomGestureAreaHeight, invalidRegionControl.mScreenWidth, i13);
                            invalidRegionControl.offsetRect();
                            boolean zIsSupportHotZoneAvoid8 = NavBarUtils.isSupportHotZoneAvoid(invalidRegionControl.mContext);
                            RectF rectF13 = invalidRegionControl.mBottomRect;
                            int i14 = invalidRegionControl.mRotation;
                            if (zIsSupportHotZoneAvoid8) {
                                invalidRegionControl.rotationalCoordinateForWorkStation(rectF13, i14);
                                invalidRegionControl.rotationalCoordinateForWorkStation(invalidRegionControl.mLeftRect, invalidRegionControl.mRotation);
                                invalidRegionControl.rotationalCoordinateForWorkStation(invalidRegionControl.mFirstRegionAfterExcludeSmartSideBar, invalidRegionControl.mRotation);
                                invalidRegionControl.rotationalCoordinateForWorkStation(invalidRegionControl.mSecondRegionAfterExcludeSmartSideBar, invalidRegionControl.mRotation);
                            } else {
                                invalidRegionControl.transCoordinateByRotationCommon(rectF13, i14);
                                invalidRegionControl.transCoordinateByRotationCommon(invalidRegionControl.mLeftRect, invalidRegionControl.mRotation);
                                invalidRegionControl.transCoordinateByRotationCommon(invalidRegionControl.mFirstRegionAfterExcludeSmartSideBar, invalidRegionControl.mRotation);
                                invalidRegionControl.transCoordinateByRotationCommon(invalidRegionControl.mSecondRegionAfterExcludeSmartSideBar, invalidRegionControl.mRotation);
                            }
                            if (!SupportOutUtils.isSystemUiSupportClearBottomGestureArea(invalidRegionControl.mContext)) {
                                invalidRegionControl.mSwipeTouchRegions.add(invalidRegionControl.mBottomRect);
                            }
                            invalidRegionControl.mSwipeTouchRegions.add(invalidRegionControl.mLeftRect);
                            invalidRegionControl.mSwipeTouchRegions.add(invalidRegionControl.mFirstRegionAfterExcludeSmartSideBar);
                            invalidRegionControl.mSwipeTouchRegions.add(invalidRegionControl.mSecondRegionAfterExcludeSmartSideBar);
                        }
                    }
                }
            }
        } else if (z) {
            LogUtil.i$default(LogUtil.TAG_NAVBAR, "NoBackGesture", "update on sub display for VIRTUAL_KEY", null, 8);
            invalidRegionControl.updateRegionOnSubDisplay();
        }
        LogUtil.i$default(LogUtil.TAG_NAVBAR, "NoBackGesture", " " + invalidRegionControl.mDisplayIdTag + "   mSwipeTouchRegions = " + invalidRegionControl.mSwipeTouchRegions, null, 8);
        EventInterceptManager eventInterceptManager = EventInterceptManager.getInstance();
        String strM = ListPopupWindow$.ExternalSyntheticOutline0.m(i, "gesture_displayid_");
        ArrayList<RectF> arrayList = invalidRegionControl.mSwipeTouchRegions;
        boolean zIsEmpty = arrayList.isEmpty();
        Bundle bundle = new Bundle();
        bundle.putFloat(REGION_OPEN_CLOSE_TYPE, 1.0f);
        eventInterceptManager.getClass();
        StringBuilder sbM = ActivityResultRegistry$.ExternalSyntheticOutline0.m("start updateInvalidRegion, regionKey: ", strM, ", region_size: ");
        sbM.append(arrayList.size());
        sbM.append(", disposable: false, isDelete: ");
        sbM.append(zIsEmpty);
        sbM.append(", bundle == null: false");
        LogUtil.d(LogUtil.TAG_NAVBAR, "EventInterceptManager", sbM.toString());
        try {
            OplusWindowManager oplusWindowManager = eventInterceptManager.mOplusWindowManager;
            if (oplusWindowManager != null) {
                zUpdateInvalidRegion = oplusWindowManager.updateInvalidRegion(strM, arrayList, false, zIsEmpty, bundle);
            }
        } catch (Exception e) {
            LogUtil.e(LogUtil.TAG_NAVBAR, "EventInterceptManager", "updateInvalidRegion failed, err: " + e);
        }
        if (zUpdateInvalidRegion) {
            return;
        }
        if (navState == 3 || navState == 2) {
            OplusTrackProxyUtil.onCommon(invalidRegionControl.mContext, "20082002", "update_invalid_region_failed", OplusCompatDcsUtils$.ExternalSyntheticOutline0.m("two_input_cache_event", ((EventInterceptInjectHelper) EventInterceptInjectHelper.INSTANCE.get()).getCacheInputEvent(), "update_gesture_invalid_region_failed", "0"));
        }
    }

    public final void dump(PrintWriter printWriter) {
        printWriter.println(SUBTAG);
        printWriter.println("mRotation: " + this.mRotation + " mSwipeTouchRegions:");
        ArrayList<RectF> arrayList = this.mSwipeTouchRegions;
        int size = arrayList.size();
        int i = 0;
        int i2 = 0;
        while (i2 < size) {
            RectF rectF = arrayList.get(i2);
            i2++;
            int i3 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
                throw null;
            }
            printWriter.println("index: " + i + " rectF:" + rectF);
            i = i3;
        }
        printWriter.println("mSideBarSceneRegion: " + this.mSideBarSceneRegion);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "NoBackGesture-->".concat("dump mRotation: " + this.mRotation + "  ,mSwipeTouchRegions: " + this.mSwipeTouchRegions));
        }
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final SideGestureConfiguration getMSideGestureConfiguration() {
        return this.mSideGestureConfiguration;
    }

    public final void notifyTouchBoundsChange(int i, int i2, int i3, int i4) {
        this.mSideGestureAreaWidthLeft = i;
        this.mSideGestureAreaWidthRight = i2;
        this.mBottomGestureAreaHeight = i3;
        this.mRotation = i4;
        String str = this.mDisplayIdTag;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" onTouchBoundsChange left: ");
        sb.append(i);
        sb.append(", right: ");
        sb.append(i2);
        DisplayShapeCompat$.ExternalSyntheticOutline0.m(i3, i4, ", bottom: ", ",  mRotation: ", sb);
        sb.append(" ");
        LogUtil.i$default(LogUtil.TAG_NAVBAR, "NoBackGesture", sb.toString(), null, 8);
        updateTouchBounds();
    }

    public final void offsetRect() {
        if (OplusScreenDragUtil.isOffsetState()) {
            float scale = OplusScreenDragUtil.getScale();
            int offsetX = OplusScreenDragUtil.getOffsetX();
            int offsetY = OplusScreenDragUtil.getOffsetY();
            RectF rectF = new RectF();
            rectF.set(this.mLeftRect);
            float f = offsetX;
            float f2 = offsetY;
            this.mLeftRect.set((rectF.left * scale) + f, (rectF.top * scale) + f2, (rectF.right * scale) + f, (rectF.bottom * scale) + f2);
            rectF.set(this.mRightRect);
            this.mRightRect.set((rectF.left * scale) + f, (rectF.top * scale) + f2, (rectF.right * scale) + f, (rectF.bottom * scale) + f2);
            rectF.set(this.mBottomRect);
            this.mBottomRect.set((rectF.left * scale) + f, (rectF.top * scale) + f2, (rectF.right * scale) + f, (rectF.bottom * scale) + f2);
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("OplusScreenDragUtil.isOffsetState() = ", "NoBackGesture-->", "SystemUi--NavBar", OplusScreenDragUtil.isOffsetState());
        }
    }

    public final void rotationalCoordinateForWorkStation(RectF rectF, int i) {
        float f = rectF.left;
        float f2 = rectF.top;
        float f3 = rectF.right;
        float f4 = rectF.bottom;
        if (i == 1) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", "InvalidRegionControl-->".concat("transCoordinateByRotationCommonV1 ROTATION_90  src rect = " + rectF));
            }
            this.mMatrix.reset();
            this.mMatrix.setRotate(90.0f);
            this.mMatrix.postTranslate(this.mScreenHeight, 0.0f);
            this.mRectF.set(f, f2, f3, f4);
            this.mMatrix.mapRect(this.mRectF);
            rectF.set(this.mRectF);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", "InvalidRegionControl-->".concat("transCoordinateByRotationCommonV1 ROTATION_90 new rect " + this.mRectF));
                return;
            }
            return;
        }
        if (i == 2) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", "InvalidRegionControl-->".concat("transCoordinateByRotationCommonV1 ROTATION_180  src rect = " + rectF));
            }
            this.mMatrix.reset();
            this.mMatrix.setRotate(180.0f);
            this.mMatrix.postTranslate(this.mScreenWidth, this.mScreenHeight);
            this.mRectF.set(f, f2, f3, f4);
            this.mMatrix.mapRect(this.mRectF);
            rectF.set(this.mRectF);
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
                Log.d("SystemUi--NavBar", "InvalidRegionControl-->".concat("transCoordinateByRotationCommonV1 ROTATION_180 new rect " + this.mRectF));
                return;
            }
            return;
        }
        if (i != 3) {
            return;
        }
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "InvalidRegionControl-->".concat("transCoordinateByRotationCommonV1 ROTATION_270  src rect = " + rectF));
        }
        this.mMatrix.reset();
        this.mMatrix.setRotate(270.0f);
        this.mMatrix.postTranslate(0.0f, this.mScreenWidth);
        this.mRectF.set(f, f2, f3, f4);
        this.mMatrix.mapRect(this.mRectF);
        rectF.set(this.mRectF);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "InvalidRegionControl-->".concat("transCoordinateByRotationCommonV1 ROTATION_270 new rect " + this.mRectF));
        }
    }

    public final void setMContext(Context context) {
        this.mContext = context;
    }

    public final void setMSideGestureConfiguration(SideGestureConfiguration sideGestureConfiguration) {
        this.mSideGestureConfiguration = sideGestureConfiguration;
    }

    public final void transCoordinateByRotationCommon(RectF rectF, int i) {
        float f = rectF.top;
        float f2 = rectF.left;
        float f3 = rectF.bottom;
        float f4 = rectF.right;
        if (i == 1) {
            int i2 = this.mScreenHeight;
            rectF.set(i2 - f3, f2, i2 - f, f4);
        } else if (i == 2) {
            int i3 = this.mScreenWidth;
            int i4 = this.mScreenHeight;
            rectF.set(i3 - f4, i4 - f3, i3 - f2, i4 - f);
        } else {
            if (i != 3) {
                return;
            }
            int i5 = this.mScreenWidth;
            rectF.set(f, i5 - f4, f3, i5 - f2);
        }
    }

    public final void transLeftCoordinateByRotation(RectF rectF, int i) {
        float f = rectF.top;
        float f2 = rectF.right;
        if (i == 1) {
            rectF.set(0.0f, 0.0f, this.mScreenHeight - f, f2);
            return;
        }
        if (i == 2) {
            int i2 = this.mScreenWidth;
            rectF.set(i2 - f2, 0.0f, i2, this.mScreenHeight - f);
        } else {
            if (i != 3) {
                return;
            }
            int i3 = this.mScreenWidth;
            rectF.set(f, i3 - f2, this.mScreenHeight, i3);
        }
    }

    public final void transRightCoordinateByRotation(RectF rectF, int i) {
        float f = rectF.top;
        float f2 = rectF.left;
        if (i == 1) {
            rectF.set(0.0f, f2, this.mScreenHeight - f, this.mScreenWidth);
        } else if (i == 2) {
            rectF.set(0.0f, 0.0f, this.mScreenWidth - f2, this.mScreenHeight - f);
        } else {
            if (i != 3) {
                return;
            }
            rectF.set(f, 0.0f, this.mScreenHeight, this.mScreenWidth - f2);
        }
    }

    public final void updateRegionOnSubDisplay() {
        this.mLeftRect.set(0.0f, 0.0f, this.mSideGestureAreaWidthLeft, this.mScreenHeight);
        RectF rectF = this.mRightRect;
        int i = this.mScreenWidth;
        rectF.set(i - this.mSideGestureAreaWidthLeft, 0.0f, i, this.mScreenHeight);
        transLeftCoordinateByRotation(this.mLeftRect, this.mRotation);
        transRightCoordinateByRotation(this.mRightRect, this.mRotation);
        this.mSwipeTouchRegions.add(this.mLeftRect);
        this.mSwipeTouchRegions.add(this.mRightRect);
    }

    public final void updateSideBarSceneRegion(Region region) {
        this.mSideBarSceneRegion = region.getBounds();
    }

    public final void updateTouchBounds() {
        this.mScreenWidth = this.mSideGestureConfiguration.getScreenWidth(this.mRotation);
        this.mScreenHeight = this.mSideGestureConfiguration.getScreenHeight(this.mRotation);
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_NAVBAR)) {
            Log.d("SystemUi--NavBar", "NoBackGesture-->".concat(this.mDisplayIdTag + " updateScreenSize, mScreenWidth: " + this.mScreenWidth + ", mScreenHeight: " + this.mScreenHeight + " , mRotation : " + this.mRotation));
        }
        Handler backgroundHandler = OplusSysUiSchedMgr.Companion.getInstance().getBackgroundHandler();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        anonymousClass1.this$0 = this;
        VarHandle.storeStoreFence();
        backgroundHandler.post(anonymousClass1);
    }
}
