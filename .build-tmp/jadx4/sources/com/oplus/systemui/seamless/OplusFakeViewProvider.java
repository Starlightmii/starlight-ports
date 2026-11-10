package com.oplus.systemui.seamless;

import android.content.Context;
import android.support.v4.content.FileProvider$;
import com.oplus.systemui.seamless.widget.CarrierFakeFrame;
import com.oplus.systemui.seamless.widget.ClockFakeFrame;
import com.oplus.systemui.seamless.widget.NotificationFakeFrame;
import com.oplus.systemui.seamless.widget.StatusBarFakeFrame;
import com.oplus.systemui.seamless.widget.SystemIconFakeFrame;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusFakeViewProvider {
    private static final Companion Companion = new Companion();

    @Deprecated
    public static final String QS = "QS";

    @Deprecated
    public static final String SIMPLE = "Notify";
    private final Context context;
    private final ArrayList<FakeType> fakeAlphaAnimateArray;
    private final ArrayList<FakeType> fakeKgdStatusBarArray;
    private final ArrayList<FakeType> fakeStartSideArray;
    private final ArrayList<FakeType> fakeStatusBarArray;
    private final Map<String, StatusBarFakeFrame> fakeViewMap = new LinkedHashMap();

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public OplusFakeViewProvider(Context context) {
        this.context = context;
        FakeType fakeType = FakeType.FAKE_CARRIER;
        FakeType fakeType2 = FakeType.FAKE_CLOCK;
        FakeType fakeType3 = FakeType.FAKE_NOTIFICATION;
        FakeType fakeType4 = FakeType.FAKE_SYSTEM_ICON;
        this.fakeStatusBarArray = CollectionsKt__CollectionsKt.arrayListOf(new FakeType[]{fakeType, fakeType2, fakeType3, fakeType4, FakeType.FAKE_PRIVACY});
        this.fakeAlphaAnimateArray = CollectionsKt__CollectionsKt.arrayListOf(new FakeType[]{fakeType, fakeType2, fakeType3, fakeType4});
        this.fakeKgdStatusBarArray = CollectionsKt__CollectionsKt.arrayListOf(new FakeType[]{FakeType.KEYGUARD_FAKE_CARRIER, FakeType.KEYGUARD_FAKE_SYSTEM_ICON});
        this.fakeStartSideArray = CollectionsKt__CollectionsKt.arrayListOf(new FakeType[]{fakeType, fakeType2, fakeType3});
    }

    public final StatusBarFakeFrame createFakeView(FakeType fakeType) {
        int iOrdinal = fakeType.ordinal();
        if (iOrdinal == 0) {
            return new CarrierFakeFrame(this.context);
        }
        if (iOrdinal == 1) {
            return new ClockFakeFrame(this.context);
        }
        if (iOrdinal != 2) {
            return iOrdinal != 3 ? new StatusBarFakeFrame(this.context, null, 6, 0) : new SystemIconFakeFrame(this.context);
        }
        return new NotificationFakeFrame(this.context);
    }

    public final ArrayList<FakeType> getFakeAlphaAnimateArray() {
        return this.fakeAlphaAnimateArray;
    }

    public final ArrayList<FakeType> getFakeKgdStatusBarArray() {
        return this.fakeKgdStatusBarArray;
    }

    public final ArrayList<FakeType> getFakeStartSideArray() {
        return this.fakeStartSideArray;
    }

    public final ArrayList<FakeType> getFakeStatusBarArray() {
        return this.fakeStatusBarArray;
    }

    public final StatusBarFakeFrame getQsFakeView(FakeType fakeType) {
        String strM = FileProvider$.ExternalSyntheticOutline0.m("QS_", fakeType.name());
        if (this.fakeViewMap.get(strM) == null) {
            this.fakeViewMap.put(strM, createFakeView(fakeType));
        }
        StatusBarFakeFrame statusBarFakeFrame = this.fakeViewMap.get(strM);
        return statusBarFakeFrame == null ? new StatusBarFakeFrame(this.context, null, 6, 0) : statusBarFakeFrame;
    }

    public final StatusBarFakeFrame getSimpleFakeView(FakeType fakeType) {
        String strM = FileProvider$.ExternalSyntheticOutline0.m("Notify_", fakeType.name());
        if (this.fakeViewMap.get(strM) == null) {
            this.fakeViewMap.put(strM, createFakeView(fakeType));
        }
        StatusBarFakeFrame statusBarFakeFrame = this.fakeViewMap.get(strM);
        return statusBarFakeFrame == null ? new StatusBarFakeFrame(this.context, null, 6, 0) : statusBarFakeFrame;
    }
}
