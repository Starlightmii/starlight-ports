package com.oplus.systemui.qs.observer;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import com.oplus.systemui.qs.tiles.Iris5Tile$mIris5MotionFluencySwitchCallback$1;
import com.oplus.systemui.qs.tiles.Iris5Tile$mIris5VideoDisplaySwitchCallback$1;
import com.oplus.systemui.qs.tiles.Iris5Tile$mIris5VideoSuperResolutionSwitchCallback$1;
import com.oplus.systemui.qs.tiles.Iris5Tile$mPowerTemperatureControlFluencyCallback$1;
import com.oplus.systemui.qs.tiles.Iris5Tile$mPowerTemperatureControlOsieCallback$1;
import com.oplus.systemui.qs.tiles.Iris5Tile$mPowerTemperatureControlVideosrCallback$1;
import com.oplus.systemui.qs.utils.Iris5Util;
import java.util.ArrayList;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class Iris5SwitchAndTemperatureControlObserver {
    public static volatile Iris5SwitchAndTemperatureControlObserver sInstance;
    public ContentResolver mContentResolver;
    public ArrayList mIris5MotionFluencySwitchCallbacks;
    public ArrayList mIris5VideoDisplaySwitchCallbacks;
    public final AnonymousClass1 mIris5VideoDisplaySwitchObserver;
    public ArrayList mIris5VideoSuperResolutionSwitchCallbacks;
    public final AnonymousClass1 mIris5VideoSuperResolutionSwitchObserver;
    public final AnonymousClass1 mMotionFluencySwitchObserver;
    public ArrayList mPowerTemperatureControlFluencyCallbacks;
    public final AnonymousClass1 mPowerTemperatureControlFluencyObserver;
    public final AnonymousClass1 mPowerTemperatureControlHbmObserver;
    public ArrayList mPowerTemperatureControlOsieCallbacks;
    public final AnonymousClass1 mPowerTemperatureControlOsieObserver;
    public ArrayList mPowerTemperatureControlVideosrCallbacks;
    public final AnonymousClass1 mPowerTemperatureControlVideosrObserver;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface BaseCallback {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Iris5MotionFluencySwitchCallback extends BaseCallback {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Iris5VideoDisplaySwitchCallback extends BaseCallback {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Iris5VideoSuperResolutionSwitchCallback extends BaseCallback {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface PowerTemperatureControlFluencyCallback extends BaseCallback {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface PowerTemperatureControlOsieCallback extends BaseCallback {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface PowerTemperatureControlVideosrCallback extends BaseCallback {
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.oplus.systemui.qs.observer.Iris5SwitchAndTemperatureControlObserver$1] */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.oplus.systemui.qs.observer.Iris5SwitchAndTemperatureControlObserver$1] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.oplus.systemui.qs.observer.Iris5SwitchAndTemperatureControlObserver$1] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.oplus.systemui.qs.observer.Iris5SwitchAndTemperatureControlObserver$1] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.oplus.systemui.qs.observer.Iris5SwitchAndTemperatureControlObserver$1] */
    /* JADX WARN: Type inference failed for: r0v5, types: [com.oplus.systemui.qs.observer.Iris5SwitchAndTemperatureControlObserver$1] */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.oplus.systemui.qs.observer.Iris5SwitchAndTemperatureControlObserver$1] */
    public Iris5SwitchAndTemperatureControlObserver() {
        final int i = 0;
        this.mIris5VideoDisplaySwitchObserver = new ContentObserver(this, new Handler()) { // from class: com.oplus.systemui.qs.observer.Iris5SwitchAndTemperatureControlObserver.1
            public final /* synthetic */ Iris5SwitchAndTemperatureControlObserver this$0;

            {
                this.this$0 = this;
            }

            private final void onChange$com$oplus$systemui$qs$observer$Iris5SwitchAndTemperatureControlObserver$6(boolean z) {
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                int i2 = 0;
                switch (i) {
                    case 0:
                        ArrayList arrayList = this.this$0.mIris5VideoDisplaySwitchCallbacks;
                        if (arrayList != null && arrayList.size() != 0) {
                            ArrayList arrayList2 = this.this$0.mIris5VideoDisplaySwitchCallbacks;
                            int size = arrayList2.size();
                            while (i2 < size) {
                                Object obj = arrayList2.get(i2);
                                i2++;
                                ((Iris5Tile$mIris5VideoDisplaySwitchCallback$1) ((Iris5VideoDisplaySwitchCallback) obj)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 1:
                        ArrayList arrayList3 = this.this$0.mIris5MotionFluencySwitchCallbacks;
                        if (arrayList3 != null && arrayList3.size() != 0) {
                            ArrayList arrayList4 = this.this$0.mIris5MotionFluencySwitchCallbacks;
                            int size2 = arrayList4.size();
                            while (i2 < size2) {
                                Object obj2 = arrayList4.get(i2);
                                i2++;
                                ((Iris5Tile$mIris5MotionFluencySwitchCallback$1) ((Iris5MotionFluencySwitchCallback) obj2)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 2:
                        ArrayList arrayList5 = this.this$0.mIris5VideoSuperResolutionSwitchCallbacks;
                        if (arrayList5 != null && arrayList5.size() != 0) {
                            ArrayList arrayList6 = this.this$0.mIris5VideoSuperResolutionSwitchCallbacks;
                            int size3 = arrayList6.size();
                            while (i2 < size3) {
                                Object obj3 = arrayList6.get(i2);
                                i2++;
                                ((Iris5Tile$mIris5VideoSuperResolutionSwitchCallback$1) ((Iris5VideoSuperResolutionSwitchCallback) obj3)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 3:
                        ArrayList arrayList7 = this.this$0.mPowerTemperatureControlVideosrCallbacks;
                        if (arrayList7 != null && arrayList7.size() != 0) {
                            ArrayList arrayList8 = this.this$0.mPowerTemperatureControlVideosrCallbacks;
                            int size4 = arrayList8.size();
                            while (i2 < size4) {
                                Object obj4 = arrayList8.get(i2);
                                i2++;
                                ((Iris5Tile$mPowerTemperatureControlVideosrCallback$1) ((PowerTemperatureControlVideosrCallback) obj4)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 4:
                        ArrayList arrayList9 = this.this$0.mPowerTemperatureControlOsieCallbacks;
                        if (arrayList9 != null && arrayList9.size() != 0) {
                            ArrayList arrayList10 = this.this$0.mPowerTemperatureControlOsieCallbacks;
                            int size5 = arrayList10.size();
                            while (i2 < size5) {
                                Object obj5 = arrayList10.get(i2);
                                i2++;
                                ((Iris5Tile$mPowerTemperatureControlOsieCallback$1) ((PowerTemperatureControlOsieCallback) obj5)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 5:
                        break;
                    default:
                        ArrayList arrayList11 = this.this$0.mPowerTemperatureControlFluencyCallbacks;
                        if (arrayList11 != null && arrayList11.size() != 0) {
                            ArrayList arrayList12 = this.this$0.mPowerTemperatureControlFluencyCallbacks;
                            int size6 = arrayList12.size();
                            while (i2 < size6) {
                                Object obj6 = arrayList12.get(i2);
                                i2++;
                                ((Iris5Tile$mPowerTemperatureControlFluencyCallback$1) ((PowerTemperatureControlFluencyCallback) obj6)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                }
            }
        };
        final int i2 = 1;
        this.mMotionFluencySwitchObserver = new ContentObserver(this, new Handler()) { // from class: com.oplus.systemui.qs.observer.Iris5SwitchAndTemperatureControlObserver.1
            public final /* synthetic */ Iris5SwitchAndTemperatureControlObserver this$0;

            {
                this.this$0 = this;
            }

            private final void onChange$com$oplus$systemui$qs$observer$Iris5SwitchAndTemperatureControlObserver$6(boolean z) {
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                int i3 = 0;
                switch (i2) {
                    case 0:
                        ArrayList arrayList = this.this$0.mIris5VideoDisplaySwitchCallbacks;
                        if (arrayList != null && arrayList.size() != 0) {
                            ArrayList arrayList2 = this.this$0.mIris5VideoDisplaySwitchCallbacks;
                            int size = arrayList2.size();
                            while (i3 < size) {
                                Object obj = arrayList2.get(i3);
                                i3++;
                                ((Iris5Tile$mIris5VideoDisplaySwitchCallback$1) ((Iris5VideoDisplaySwitchCallback) obj)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 1:
                        ArrayList arrayList3 = this.this$0.mIris5MotionFluencySwitchCallbacks;
                        if (arrayList3 != null && arrayList3.size() != 0) {
                            ArrayList arrayList4 = this.this$0.mIris5MotionFluencySwitchCallbacks;
                            int size2 = arrayList4.size();
                            while (i3 < size2) {
                                Object obj2 = arrayList4.get(i3);
                                i3++;
                                ((Iris5Tile$mIris5MotionFluencySwitchCallback$1) ((Iris5MotionFluencySwitchCallback) obj2)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 2:
                        ArrayList arrayList5 = this.this$0.mIris5VideoSuperResolutionSwitchCallbacks;
                        if (arrayList5 != null && arrayList5.size() != 0) {
                            ArrayList arrayList6 = this.this$0.mIris5VideoSuperResolutionSwitchCallbacks;
                            int size3 = arrayList6.size();
                            while (i3 < size3) {
                                Object obj3 = arrayList6.get(i3);
                                i3++;
                                ((Iris5Tile$mIris5VideoSuperResolutionSwitchCallback$1) ((Iris5VideoSuperResolutionSwitchCallback) obj3)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 3:
                        ArrayList arrayList7 = this.this$0.mPowerTemperatureControlVideosrCallbacks;
                        if (arrayList7 != null && arrayList7.size() != 0) {
                            ArrayList arrayList8 = this.this$0.mPowerTemperatureControlVideosrCallbacks;
                            int size4 = arrayList8.size();
                            while (i3 < size4) {
                                Object obj4 = arrayList8.get(i3);
                                i3++;
                                ((Iris5Tile$mPowerTemperatureControlVideosrCallback$1) ((PowerTemperatureControlVideosrCallback) obj4)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 4:
                        ArrayList arrayList9 = this.this$0.mPowerTemperatureControlOsieCallbacks;
                        if (arrayList9 != null && arrayList9.size() != 0) {
                            ArrayList arrayList10 = this.this$0.mPowerTemperatureControlOsieCallbacks;
                            int size5 = arrayList10.size();
                            while (i3 < size5) {
                                Object obj5 = arrayList10.get(i3);
                                i3++;
                                ((Iris5Tile$mPowerTemperatureControlOsieCallback$1) ((PowerTemperatureControlOsieCallback) obj5)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 5:
                        break;
                    default:
                        ArrayList arrayList11 = this.this$0.mPowerTemperatureControlFluencyCallbacks;
                        if (arrayList11 != null && arrayList11.size() != 0) {
                            ArrayList arrayList12 = this.this$0.mPowerTemperatureControlFluencyCallbacks;
                            int size6 = arrayList12.size();
                            while (i3 < size6) {
                                Object obj6 = arrayList12.get(i3);
                                i3++;
                                ((Iris5Tile$mPowerTemperatureControlFluencyCallback$1) ((PowerTemperatureControlFluencyCallback) obj6)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                }
            }
        };
        final int i3 = 2;
        this.mIris5VideoSuperResolutionSwitchObserver = new ContentObserver(this, new Handler()) { // from class: com.oplus.systemui.qs.observer.Iris5SwitchAndTemperatureControlObserver.1
            public final /* synthetic */ Iris5SwitchAndTemperatureControlObserver this$0;

            {
                this.this$0 = this;
            }

            private final void onChange$com$oplus$systemui$qs$observer$Iris5SwitchAndTemperatureControlObserver$6(boolean z) {
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                int i4 = 0;
                switch (i3) {
                    case 0:
                        ArrayList arrayList = this.this$0.mIris5VideoDisplaySwitchCallbacks;
                        if (arrayList != null && arrayList.size() != 0) {
                            ArrayList arrayList2 = this.this$0.mIris5VideoDisplaySwitchCallbacks;
                            int size = arrayList2.size();
                            while (i4 < size) {
                                Object obj = arrayList2.get(i4);
                                i4++;
                                ((Iris5Tile$mIris5VideoDisplaySwitchCallback$1) ((Iris5VideoDisplaySwitchCallback) obj)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 1:
                        ArrayList arrayList3 = this.this$0.mIris5MotionFluencySwitchCallbacks;
                        if (arrayList3 != null && arrayList3.size() != 0) {
                            ArrayList arrayList4 = this.this$0.mIris5MotionFluencySwitchCallbacks;
                            int size2 = arrayList4.size();
                            while (i4 < size2) {
                                Object obj2 = arrayList4.get(i4);
                                i4++;
                                ((Iris5Tile$mIris5MotionFluencySwitchCallback$1) ((Iris5MotionFluencySwitchCallback) obj2)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 2:
                        ArrayList arrayList5 = this.this$0.mIris5VideoSuperResolutionSwitchCallbacks;
                        if (arrayList5 != null && arrayList5.size() != 0) {
                            ArrayList arrayList6 = this.this$0.mIris5VideoSuperResolutionSwitchCallbacks;
                            int size3 = arrayList6.size();
                            while (i4 < size3) {
                                Object obj3 = arrayList6.get(i4);
                                i4++;
                                ((Iris5Tile$mIris5VideoSuperResolutionSwitchCallback$1) ((Iris5VideoSuperResolutionSwitchCallback) obj3)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 3:
                        ArrayList arrayList7 = this.this$0.mPowerTemperatureControlVideosrCallbacks;
                        if (arrayList7 != null && arrayList7.size() != 0) {
                            ArrayList arrayList8 = this.this$0.mPowerTemperatureControlVideosrCallbacks;
                            int size4 = arrayList8.size();
                            while (i4 < size4) {
                                Object obj4 = arrayList8.get(i4);
                                i4++;
                                ((Iris5Tile$mPowerTemperatureControlVideosrCallback$1) ((PowerTemperatureControlVideosrCallback) obj4)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 4:
                        ArrayList arrayList9 = this.this$0.mPowerTemperatureControlOsieCallbacks;
                        if (arrayList9 != null && arrayList9.size() != 0) {
                            ArrayList arrayList10 = this.this$0.mPowerTemperatureControlOsieCallbacks;
                            int size5 = arrayList10.size();
                            while (i4 < size5) {
                                Object obj5 = arrayList10.get(i4);
                                i4++;
                                ((Iris5Tile$mPowerTemperatureControlOsieCallback$1) ((PowerTemperatureControlOsieCallback) obj5)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 5:
                        break;
                    default:
                        ArrayList arrayList11 = this.this$0.mPowerTemperatureControlFluencyCallbacks;
                        if (arrayList11 != null && arrayList11.size() != 0) {
                            ArrayList arrayList12 = this.this$0.mPowerTemperatureControlFluencyCallbacks;
                            int size6 = arrayList12.size();
                            while (i4 < size6) {
                                Object obj6 = arrayList12.get(i4);
                                i4++;
                                ((Iris5Tile$mPowerTemperatureControlFluencyCallback$1) ((PowerTemperatureControlFluencyCallback) obj6)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                }
            }
        };
        final int i4 = 3;
        this.mPowerTemperatureControlVideosrObserver = new ContentObserver(this, new Handler()) { // from class: com.oplus.systemui.qs.observer.Iris5SwitchAndTemperatureControlObserver.1
            public final /* synthetic */ Iris5SwitchAndTemperatureControlObserver this$0;

            {
                this.this$0 = this;
            }

            private final void onChange$com$oplus$systemui$qs$observer$Iris5SwitchAndTemperatureControlObserver$6(boolean z) {
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                int i5 = 0;
                switch (i4) {
                    case 0:
                        ArrayList arrayList = this.this$0.mIris5VideoDisplaySwitchCallbacks;
                        if (arrayList != null && arrayList.size() != 0) {
                            ArrayList arrayList2 = this.this$0.mIris5VideoDisplaySwitchCallbacks;
                            int size = arrayList2.size();
                            while (i5 < size) {
                                Object obj = arrayList2.get(i5);
                                i5++;
                                ((Iris5Tile$mIris5VideoDisplaySwitchCallback$1) ((Iris5VideoDisplaySwitchCallback) obj)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 1:
                        ArrayList arrayList3 = this.this$0.mIris5MotionFluencySwitchCallbacks;
                        if (arrayList3 != null && arrayList3.size() != 0) {
                            ArrayList arrayList4 = this.this$0.mIris5MotionFluencySwitchCallbacks;
                            int size2 = arrayList4.size();
                            while (i5 < size2) {
                                Object obj2 = arrayList4.get(i5);
                                i5++;
                                ((Iris5Tile$mIris5MotionFluencySwitchCallback$1) ((Iris5MotionFluencySwitchCallback) obj2)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 2:
                        ArrayList arrayList5 = this.this$0.mIris5VideoSuperResolutionSwitchCallbacks;
                        if (arrayList5 != null && arrayList5.size() != 0) {
                            ArrayList arrayList6 = this.this$0.mIris5VideoSuperResolutionSwitchCallbacks;
                            int size3 = arrayList6.size();
                            while (i5 < size3) {
                                Object obj3 = arrayList6.get(i5);
                                i5++;
                                ((Iris5Tile$mIris5VideoSuperResolutionSwitchCallback$1) ((Iris5VideoSuperResolutionSwitchCallback) obj3)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 3:
                        ArrayList arrayList7 = this.this$0.mPowerTemperatureControlVideosrCallbacks;
                        if (arrayList7 != null && arrayList7.size() != 0) {
                            ArrayList arrayList8 = this.this$0.mPowerTemperatureControlVideosrCallbacks;
                            int size4 = arrayList8.size();
                            while (i5 < size4) {
                                Object obj4 = arrayList8.get(i5);
                                i5++;
                                ((Iris5Tile$mPowerTemperatureControlVideosrCallback$1) ((PowerTemperatureControlVideosrCallback) obj4)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 4:
                        ArrayList arrayList9 = this.this$0.mPowerTemperatureControlOsieCallbacks;
                        if (arrayList9 != null && arrayList9.size() != 0) {
                            ArrayList arrayList10 = this.this$0.mPowerTemperatureControlOsieCallbacks;
                            int size5 = arrayList10.size();
                            while (i5 < size5) {
                                Object obj5 = arrayList10.get(i5);
                                i5++;
                                ((Iris5Tile$mPowerTemperatureControlOsieCallback$1) ((PowerTemperatureControlOsieCallback) obj5)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 5:
                        break;
                    default:
                        ArrayList arrayList11 = this.this$0.mPowerTemperatureControlFluencyCallbacks;
                        if (arrayList11 != null && arrayList11.size() != 0) {
                            ArrayList arrayList12 = this.this$0.mPowerTemperatureControlFluencyCallbacks;
                            int size6 = arrayList12.size();
                            while (i5 < size6) {
                                Object obj6 = arrayList12.get(i5);
                                i5++;
                                ((Iris5Tile$mPowerTemperatureControlFluencyCallback$1) ((PowerTemperatureControlFluencyCallback) obj6)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                }
            }
        };
        final int i5 = 4;
        this.mPowerTemperatureControlOsieObserver = new ContentObserver(this, new Handler()) { // from class: com.oplus.systemui.qs.observer.Iris5SwitchAndTemperatureControlObserver.1
            public final /* synthetic */ Iris5SwitchAndTemperatureControlObserver this$0;

            {
                this.this$0 = this;
            }

            private final void onChange$com$oplus$systemui$qs$observer$Iris5SwitchAndTemperatureControlObserver$6(boolean z) {
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                int i6 = 0;
                switch (i5) {
                    case 0:
                        ArrayList arrayList = this.this$0.mIris5VideoDisplaySwitchCallbacks;
                        if (arrayList != null && arrayList.size() != 0) {
                            ArrayList arrayList2 = this.this$0.mIris5VideoDisplaySwitchCallbacks;
                            int size = arrayList2.size();
                            while (i6 < size) {
                                Object obj = arrayList2.get(i6);
                                i6++;
                                ((Iris5Tile$mIris5VideoDisplaySwitchCallback$1) ((Iris5VideoDisplaySwitchCallback) obj)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 1:
                        ArrayList arrayList3 = this.this$0.mIris5MotionFluencySwitchCallbacks;
                        if (arrayList3 != null && arrayList3.size() != 0) {
                            ArrayList arrayList4 = this.this$0.mIris5MotionFluencySwitchCallbacks;
                            int size2 = arrayList4.size();
                            while (i6 < size2) {
                                Object obj2 = arrayList4.get(i6);
                                i6++;
                                ((Iris5Tile$mIris5MotionFluencySwitchCallback$1) ((Iris5MotionFluencySwitchCallback) obj2)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 2:
                        ArrayList arrayList5 = this.this$0.mIris5VideoSuperResolutionSwitchCallbacks;
                        if (arrayList5 != null && arrayList5.size() != 0) {
                            ArrayList arrayList6 = this.this$0.mIris5VideoSuperResolutionSwitchCallbacks;
                            int size3 = arrayList6.size();
                            while (i6 < size3) {
                                Object obj3 = arrayList6.get(i6);
                                i6++;
                                ((Iris5Tile$mIris5VideoSuperResolutionSwitchCallback$1) ((Iris5VideoSuperResolutionSwitchCallback) obj3)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 3:
                        ArrayList arrayList7 = this.this$0.mPowerTemperatureControlVideosrCallbacks;
                        if (arrayList7 != null && arrayList7.size() != 0) {
                            ArrayList arrayList8 = this.this$0.mPowerTemperatureControlVideosrCallbacks;
                            int size4 = arrayList8.size();
                            while (i6 < size4) {
                                Object obj4 = arrayList8.get(i6);
                                i6++;
                                ((Iris5Tile$mPowerTemperatureControlVideosrCallback$1) ((PowerTemperatureControlVideosrCallback) obj4)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 4:
                        ArrayList arrayList9 = this.this$0.mPowerTemperatureControlOsieCallbacks;
                        if (arrayList9 != null && arrayList9.size() != 0) {
                            ArrayList arrayList10 = this.this$0.mPowerTemperatureControlOsieCallbacks;
                            int size5 = arrayList10.size();
                            while (i6 < size5) {
                                Object obj5 = arrayList10.get(i6);
                                i6++;
                                ((Iris5Tile$mPowerTemperatureControlOsieCallback$1) ((PowerTemperatureControlOsieCallback) obj5)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 5:
                        break;
                    default:
                        ArrayList arrayList11 = this.this$0.mPowerTemperatureControlFluencyCallbacks;
                        if (arrayList11 != null && arrayList11.size() != 0) {
                            ArrayList arrayList12 = this.this$0.mPowerTemperatureControlFluencyCallbacks;
                            int size6 = arrayList12.size();
                            while (i6 < size6) {
                                Object obj6 = arrayList12.get(i6);
                                i6++;
                                ((Iris5Tile$mPowerTemperatureControlFluencyCallback$1) ((PowerTemperatureControlFluencyCallback) obj6)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                }
            }
        };
        final int i6 = 5;
        this.mPowerTemperatureControlHbmObserver = new ContentObserver(this, new Handler()) { // from class: com.oplus.systemui.qs.observer.Iris5SwitchAndTemperatureControlObserver.1
            public final /* synthetic */ Iris5SwitchAndTemperatureControlObserver this$0;

            {
                this.this$0 = this;
            }

            private final void onChange$com$oplus$systemui$qs$observer$Iris5SwitchAndTemperatureControlObserver$6(boolean z) {
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                int i7 = 0;
                switch (i6) {
                    case 0:
                        ArrayList arrayList = this.this$0.mIris5VideoDisplaySwitchCallbacks;
                        if (arrayList != null && arrayList.size() != 0) {
                            ArrayList arrayList2 = this.this$0.mIris5VideoDisplaySwitchCallbacks;
                            int size = arrayList2.size();
                            while (i7 < size) {
                                Object obj = arrayList2.get(i7);
                                i7++;
                                ((Iris5Tile$mIris5VideoDisplaySwitchCallback$1) ((Iris5VideoDisplaySwitchCallback) obj)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 1:
                        ArrayList arrayList3 = this.this$0.mIris5MotionFluencySwitchCallbacks;
                        if (arrayList3 != null && arrayList3.size() != 0) {
                            ArrayList arrayList4 = this.this$0.mIris5MotionFluencySwitchCallbacks;
                            int size2 = arrayList4.size();
                            while (i7 < size2) {
                                Object obj2 = arrayList4.get(i7);
                                i7++;
                                ((Iris5Tile$mIris5MotionFluencySwitchCallback$1) ((Iris5MotionFluencySwitchCallback) obj2)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 2:
                        ArrayList arrayList5 = this.this$0.mIris5VideoSuperResolutionSwitchCallbacks;
                        if (arrayList5 != null && arrayList5.size() != 0) {
                            ArrayList arrayList6 = this.this$0.mIris5VideoSuperResolutionSwitchCallbacks;
                            int size3 = arrayList6.size();
                            while (i7 < size3) {
                                Object obj3 = arrayList6.get(i7);
                                i7++;
                                ((Iris5Tile$mIris5VideoSuperResolutionSwitchCallback$1) ((Iris5VideoSuperResolutionSwitchCallback) obj3)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 3:
                        ArrayList arrayList7 = this.this$0.mPowerTemperatureControlVideosrCallbacks;
                        if (arrayList7 != null && arrayList7.size() != 0) {
                            ArrayList arrayList8 = this.this$0.mPowerTemperatureControlVideosrCallbacks;
                            int size4 = arrayList8.size();
                            while (i7 < size4) {
                                Object obj4 = arrayList8.get(i7);
                                i7++;
                                ((Iris5Tile$mPowerTemperatureControlVideosrCallback$1) ((PowerTemperatureControlVideosrCallback) obj4)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 4:
                        ArrayList arrayList9 = this.this$0.mPowerTemperatureControlOsieCallbacks;
                        if (arrayList9 != null && arrayList9.size() != 0) {
                            ArrayList arrayList10 = this.this$0.mPowerTemperatureControlOsieCallbacks;
                            int size5 = arrayList10.size();
                            while (i7 < size5) {
                                Object obj5 = arrayList10.get(i7);
                                i7++;
                                ((Iris5Tile$mPowerTemperatureControlOsieCallback$1) ((PowerTemperatureControlOsieCallback) obj5)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 5:
                        break;
                    default:
                        ArrayList arrayList11 = this.this$0.mPowerTemperatureControlFluencyCallbacks;
                        if (arrayList11 != null && arrayList11.size() != 0) {
                            ArrayList arrayList12 = this.this$0.mPowerTemperatureControlFluencyCallbacks;
                            int size6 = arrayList12.size();
                            while (i7 < size6) {
                                Object obj6 = arrayList12.get(i7);
                                i7++;
                                ((Iris5Tile$mPowerTemperatureControlFluencyCallback$1) ((PowerTemperatureControlFluencyCallback) obj6)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                }
            }
        };
        final int i7 = 6;
        this.mPowerTemperatureControlFluencyObserver = new ContentObserver(this, new Handler()) { // from class: com.oplus.systemui.qs.observer.Iris5SwitchAndTemperatureControlObserver.1
            public final /* synthetic */ Iris5SwitchAndTemperatureControlObserver this$0;

            {
                this.this$0 = this;
            }

            private final void onChange$com$oplus$systemui$qs$observer$Iris5SwitchAndTemperatureControlObserver$6(boolean z) {
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                int i8 = 0;
                switch (i7) {
                    case 0:
                        ArrayList arrayList = this.this$0.mIris5VideoDisplaySwitchCallbacks;
                        if (arrayList != null && arrayList.size() != 0) {
                            ArrayList arrayList2 = this.this$0.mIris5VideoDisplaySwitchCallbacks;
                            int size = arrayList2.size();
                            while (i8 < size) {
                                Object obj = arrayList2.get(i8);
                                i8++;
                                ((Iris5Tile$mIris5VideoDisplaySwitchCallback$1) ((Iris5VideoDisplaySwitchCallback) obj)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 1:
                        ArrayList arrayList3 = this.this$0.mIris5MotionFluencySwitchCallbacks;
                        if (arrayList3 != null && arrayList3.size() != 0) {
                            ArrayList arrayList4 = this.this$0.mIris5MotionFluencySwitchCallbacks;
                            int size2 = arrayList4.size();
                            while (i8 < size2) {
                                Object obj2 = arrayList4.get(i8);
                                i8++;
                                ((Iris5Tile$mIris5MotionFluencySwitchCallback$1) ((Iris5MotionFluencySwitchCallback) obj2)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 2:
                        ArrayList arrayList5 = this.this$0.mIris5VideoSuperResolutionSwitchCallbacks;
                        if (arrayList5 != null && arrayList5.size() != 0) {
                            ArrayList arrayList6 = this.this$0.mIris5VideoSuperResolutionSwitchCallbacks;
                            int size3 = arrayList6.size();
                            while (i8 < size3) {
                                Object obj3 = arrayList6.get(i8);
                                i8++;
                                ((Iris5Tile$mIris5VideoSuperResolutionSwitchCallback$1) ((Iris5VideoSuperResolutionSwitchCallback) obj3)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 3:
                        ArrayList arrayList7 = this.this$0.mPowerTemperatureControlVideosrCallbacks;
                        if (arrayList7 != null && arrayList7.size() != 0) {
                            ArrayList arrayList8 = this.this$0.mPowerTemperatureControlVideosrCallbacks;
                            int size4 = arrayList8.size();
                            while (i8 < size4) {
                                Object obj4 = arrayList8.get(i8);
                                i8++;
                                ((Iris5Tile$mPowerTemperatureControlVideosrCallback$1) ((PowerTemperatureControlVideosrCallback) obj4)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 4:
                        ArrayList arrayList9 = this.this$0.mPowerTemperatureControlOsieCallbacks;
                        if (arrayList9 != null && arrayList9.size() != 0) {
                            ArrayList arrayList10 = this.this$0.mPowerTemperatureControlOsieCallbacks;
                            int size5 = arrayList10.size();
                            while (i8 < size5) {
                                Object obj5 = arrayList10.get(i8);
                                i8++;
                                ((Iris5Tile$mPowerTemperatureControlOsieCallback$1) ((PowerTemperatureControlOsieCallback) obj5)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                    case 5:
                        break;
                    default:
                        ArrayList arrayList11 = this.this$0.mPowerTemperatureControlFluencyCallbacks;
                        if (arrayList11 != null && arrayList11.size() != 0) {
                            ArrayList arrayList12 = this.this$0.mPowerTemperatureControlFluencyCallbacks;
                            int size6 = arrayList12.size();
                            while (i8 < size6) {
                                Object obj6 = arrayList12.get(i8);
                                i8++;
                                ((Iris5Tile$mPowerTemperatureControlFluencyCallback$1) ((PowerTemperatureControlFluencyCallback) obj6)).this$0.refreshState((Object) null);
                            }
                            break;
                        }
                        break;
                }
            }
        };
    }

    public static Iris5SwitchAndTemperatureControlObserver getInstance() {
        if (sInstance == null) {
            synchronized (Iris5SwitchAndTemperatureControlObserver.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new Iris5SwitchAndTemperatureControlObserver();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public final void register(Context context, BaseCallback baseCallback) {
        if (baseCallback == null) {
            return;
        }
        if (this.mContentResolver == null) {
            this.mContentResolver = context.getApplicationContext().getContentResolver();
        }
        if (baseCallback instanceof Iris5VideoDisplaySwitchCallback) {
            if (this.mIris5VideoDisplaySwitchCallbacks == null) {
                this.mIris5VideoDisplaySwitchCallbacks = new ArrayList();
            }
            if (this.mIris5VideoDisplaySwitchCallbacks.size() == 0) {
                this.mContentResolver.registerContentObserver(Settings.Secure.getUriFor(Iris5Util.KEY_VIDEO_SWITCH), false, this.mIris5VideoDisplaySwitchObserver);
            }
            if (!this.mIris5VideoDisplaySwitchCallbacks.contains(baseCallback)) {
                this.mIris5VideoDisplaySwitchCallbacks.add((Iris5VideoDisplaySwitchCallback) baseCallback);
            }
        }
        if (baseCallback instanceof Iris5MotionFluencySwitchCallback) {
            if (this.mIris5MotionFluencySwitchCallbacks == null) {
                this.mIris5MotionFluencySwitchCallbacks = new ArrayList();
            }
            if (this.mIris5MotionFluencySwitchCallbacks.size() == 0) {
                this.mContentResolver.registerContentObserver(Settings.Secure.getUriFor(Iris5Util.KEY_OSIE_MOTION_FLUENCY_SWITCH), false, this.mMotionFluencySwitchObserver);
            }
            if (!this.mIris5MotionFluencySwitchCallbacks.contains(baseCallback)) {
                this.mIris5MotionFluencySwitchCallbacks.add((Iris5MotionFluencySwitchCallback) baseCallback);
            }
        }
        if (baseCallback instanceof Iris5VideoSuperResolutionSwitchCallback) {
            if (this.mIris5VideoSuperResolutionSwitchCallbacks == null) {
                this.mIris5VideoSuperResolutionSwitchCallbacks = new ArrayList();
            }
            if (this.mIris5VideoSuperResolutionSwitchCallbacks.size() == 0) {
                this.mContentResolver.registerContentObserver(Settings.Secure.getUriFor(Iris5Util.KEY_CUSTOMIZE_MULTIMEDIA_VIDEO_SUPER_RESOLUTION), false, this.mIris5VideoSuperResolutionSwitchObserver);
            }
            if (!this.mIris5VideoSuperResolutionSwitchCallbacks.contains(baseCallback)) {
                this.mIris5VideoSuperResolutionSwitchCallbacks.add((Iris5VideoSuperResolutionSwitchCallback) baseCallback);
            }
        }
        if (baseCallback instanceof PowerTemperatureControlVideosrCallback) {
            if (this.mPowerTemperatureControlVideosrCallbacks == null) {
                this.mPowerTemperatureControlVideosrCallbacks = new ArrayList();
            }
            if (this.mPowerTemperatureControlVideosrCallbacks.size() == 0) {
                this.mContentResolver.registerContentObserver(Settings.Secure.getUriFor("customize_power_temperature_control_videosr"), false, this.mPowerTemperatureControlVideosrObserver);
            }
            if (!this.mPowerTemperatureControlVideosrCallbacks.contains(baseCallback)) {
                this.mPowerTemperatureControlVideosrCallbacks.add((PowerTemperatureControlVideosrCallback) baseCallback);
            }
        }
        if (baseCallback instanceof PowerTemperatureControlOsieCallback) {
            if (this.mPowerTemperatureControlOsieCallbacks == null) {
                this.mPowerTemperatureControlOsieCallbacks = new ArrayList();
            }
            if (this.mPowerTemperatureControlOsieCallbacks.size() == 0) {
                this.mContentResolver.registerContentObserver(Settings.Secure.getUriFor("customize_power_temperature_control_osie"), false, this.mPowerTemperatureControlOsieObserver);
            }
            if (!this.mPowerTemperatureControlOsieCallbacks.contains(baseCallback)) {
                this.mPowerTemperatureControlOsieCallbacks.add((PowerTemperatureControlOsieCallback) baseCallback);
            }
        }
        if (baseCallback instanceof PowerTemperatureControlFluencyCallback) {
            if (this.mPowerTemperatureControlFluencyCallbacks == null) {
                this.mPowerTemperatureControlFluencyCallbacks = new ArrayList();
            }
            if (this.mPowerTemperatureControlFluencyCallbacks.size() == 0) {
                this.mContentResolver.registerContentObserver(Settings.Secure.getUriFor("customize_power_temperature_control_motion_fluency"), false, this.mPowerTemperatureControlFluencyObserver);
            }
            if (this.mPowerTemperatureControlFluencyCallbacks.contains(baseCallback)) {
                return;
            }
            this.mPowerTemperatureControlFluencyCallbacks.add((PowerTemperatureControlFluencyCallback) baseCallback);
        }
    }

    /* JADX WARN: Failed to analyze thrown exceptions
    java.util.ConcurrentModificationException
    	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1013)
    	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:967)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:130)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
     */
    public final void unRegister(BaseCallback baseCallback) {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        ArrayList arrayList4;
        ArrayList arrayList5;
        ArrayList arrayList6;
        if (baseCallback == null || this.mContentResolver == null) {
            return;
        }
        if ((baseCallback instanceof Iris5VideoDisplaySwitchCallback) && (arrayList6 = this.mIris5VideoDisplaySwitchCallbacks) != null) {
            arrayList6.remove(baseCallback);
            if (this.mIris5VideoDisplaySwitchCallbacks.size() == 0) {
                this.mContentResolver.unregisterContentObserver(this.mIris5VideoDisplaySwitchObserver);
            }
        }
        if ((baseCallback instanceof Iris5MotionFluencySwitchCallback) && (arrayList5 = this.mIris5MotionFluencySwitchCallbacks) != null) {
            arrayList5.remove(baseCallback);
            if (this.mIris5MotionFluencySwitchCallbacks.size() == 0) {
                this.mContentResolver.unregisterContentObserver(this.mMotionFluencySwitchObserver);
            }
        }
        if ((baseCallback instanceof Iris5VideoSuperResolutionSwitchCallback) && (arrayList4 = this.mIris5VideoSuperResolutionSwitchCallbacks) != null) {
            arrayList4.remove(baseCallback);
            if (this.mIris5VideoSuperResolutionSwitchCallbacks.size() == 0) {
                this.mContentResolver.unregisterContentObserver(this.mIris5VideoSuperResolutionSwitchObserver);
            }
        }
        if ((baseCallback instanceof PowerTemperatureControlVideosrCallback) && (arrayList3 = this.mPowerTemperatureControlVideosrCallbacks) != null) {
            arrayList3.remove(baseCallback);
            if (this.mPowerTemperatureControlVideosrCallbacks.size() == 0) {
                this.mContentResolver.unregisterContentObserver(this.mPowerTemperatureControlVideosrObserver);
            }
        }
        if ((baseCallback instanceof PowerTemperatureControlOsieCallback) && (arrayList2 = this.mPowerTemperatureControlOsieCallbacks) != null) {
            arrayList2.remove(baseCallback);
            if (this.mPowerTemperatureControlOsieCallbacks.size() == 0) {
                this.mContentResolver.unregisterContentObserver(this.mPowerTemperatureControlOsieObserver);
            }
        }
        if (!(baseCallback instanceof PowerTemperatureControlFluencyCallback) || (arrayList = this.mPowerTemperatureControlFluencyCallbacks) == null) {
            return;
        }
        arrayList.remove(baseCallback);
        if (this.mPowerTemperatureControlFluencyCallbacks.size() == 0) {
            this.mContentResolver.unregisterContentObserver(this.mPowerTemperatureControlFluencyObserver);
        }
    }
}
