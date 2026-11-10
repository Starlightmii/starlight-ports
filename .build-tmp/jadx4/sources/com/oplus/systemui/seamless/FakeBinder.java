package com.oplus.systemui.seamless;

import android.util.Log;
import android.view.View;
import com.android.keyguard.OplusLockIconView$;
import com.oplus.systemui.statusbar.phone.KeyguardStatusBarViewExImpl;
import com.oplusos.systemui.common.feature.CustomizeFeatureOption;
import com.oplusos.systemui.common.util.LogUtil;
import java.util.ArrayList;
import kotlin.LazyKt__LazyJVMKt$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class FakeBinder {
    public static final Companion Companion = new Companion();
    private static final String TAG = "FakeBinder";
    private final KeyguardStatusBarViewExImpl keyguardStatusBarViewExImpl;
    private final OplusFakeViewProvider oplusFakeViewProvider;
    private View phoneStatusBarView;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public FakeBinder(OplusFakeViewProvider oplusFakeViewProvider, KeyguardStatusBarViewExImpl keyguardStatusBarViewExImpl) {
        this.oplusFakeViewProvider = oplusFakeViewProvider;
        this.keyguardStatusBarViewExImpl = keyguardStatusBarViewExImpl;
    }

    private final View getKeyguardStatusBarView() {
        return this.keyguardStatusBarViewExImpl.getKeyguardStatusBarView();
    }

    private final View getOperatorNameView() {
        View view;
        if (!CustomizeFeatureOption.sIsShowOperatorName || (view = this.phoneStatusBarView) == null) {
            return null;
        }
        return view.findViewById(2131364287);
    }

    public final void bindQsFakeView(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("bindQsFakeView ", "FakeBinder-->", "SystemUi--Statusbar", z);
        }
        OplusFakeViewProvider oplusFakeViewProvider = this.oplusFakeViewProvider;
        int i = 0;
        if (z) {
            ArrayList<FakeType> fakeKgdStatusBarArray = oplusFakeViewProvider.getFakeKgdStatusBarArray();
            int size = fakeKgdStatusBarArray.size();
            while (i < size) {
                FakeType fakeType = fakeKgdStatusBarArray.get(i);
                i++;
                this.oplusFakeViewProvider.getQsFakeView(fakeType).bindRealView();
            }
            return;
        }
        ArrayList<FakeType> fakeStatusBarArray = oplusFakeViewProvider.getFakeStatusBarArray();
        int size2 = fakeStatusBarArray.size();
        while (i < size2) {
            FakeType fakeType2 = fakeStatusBarArray.get(i);
            i++;
            this.oplusFakeViewProvider.getQsFakeView(fakeType2).bindRealView();
        }
    }

    public final void bindSimpleFakeView() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "FakeBinder-->bindSimpleFakeView");
        }
        ArrayList<FakeType> fakeStatusBarArray = this.oplusFakeViewProvider.getFakeStatusBarArray();
        int size = fakeStatusBarArray.size();
        int i = 0;
        while (i < size) {
            FakeType fakeType = fakeStatusBarArray.get(i);
            i++;
            this.oplusFakeViewProvider.getSimpleFakeView(fakeType).bindRealView();
        }
    }

    public final void clearKgdFakeView() {
        ArrayList<FakeType> fakeKgdStatusBarArray = this.oplusFakeViewProvider.getFakeKgdStatusBarArray();
        int size = fakeKgdStatusBarArray.size();
        int i = 0;
        while (i < size) {
            FakeType fakeType = fakeKgdStatusBarArray.get(i);
            i++;
            this.oplusFakeViewProvider.getQsFakeView(fakeType).clearFakeView();
        }
    }

    public final View getRealView(FakeType fakeType) {
        switch (fakeType.ordinal()) {
            case 0:
                return getOperatorNameView();
            case 1:
                View view = this.phoneStatusBarView;
                if (view != null) {
                    return view.findViewById(2131362567);
                }
                return null;
            case 2:
                View view2 = this.phoneStatusBarView;
                if (view2 != null) {
                    return view2.findViewById(2131365372);
                }
                return null;
            case 3:
                View view3 = this.phoneStatusBarView;
                if (view3 != null) {
                    return view3.findViewById(2131365366);
                }
                return null;
            case 4:
                View view4 = this.phoneStatusBarView;
                if (view4 != null) {
                    return view4.findViewById(2131362994);
                }
                return null;
            case 5:
                return getKeyguardStatusBarView().findViewById(2131363560);
            case 6:
                return getKeyguardStatusBarView().findViewById(2131365377);
            default:
                LazyKt__LazyJVMKt$.ExternalSyntheticBUOutline0.m();
                return null;
        }
    }

    public final void setFakeView(View view) {
        this.phoneStatusBarView = view;
        ArrayList<FakeType> fakeStatusBarArray = this.oplusFakeViewProvider.getFakeStatusBarArray();
        int size = fakeStatusBarArray.size();
        int i = 0;
        while (i < size) {
            FakeType fakeType = fakeStatusBarArray.get(i);
            i++;
            FakeType fakeType2 = fakeType;
            View realView = getRealView(fakeType2);
            this.oplusFakeViewProvider.getQsFakeView(fakeType2).setFakeView(realView, fakeType2);
            this.oplusFakeViewProvider.getSimpleFakeView(fakeType2).setFakeView(realView, fakeType2);
        }
    }

    public final void setKgdFakeView() {
        ArrayList<FakeType> fakeKgdStatusBarArray = this.oplusFakeViewProvider.getFakeKgdStatusBarArray();
        int size = fakeKgdStatusBarArray.size();
        int i = 0;
        while (i < size) {
            FakeType fakeType = fakeKgdStatusBarArray.get(i);
            i++;
            FakeType fakeType2 = fakeType;
            this.oplusFakeViewProvider.getQsFakeView(fakeType2).setFakeView(getRealView(fakeType2), fakeType2);
        }
    }

    public final void unbindQsFakeView(boolean z) {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            OplusLockIconView$.ExternalSyntheticOutline0.m("unbindQsFakeView ", "FakeBinder-->", "SystemUi--Statusbar", z);
        }
        OplusFakeViewProvider oplusFakeViewProvider = this.oplusFakeViewProvider;
        int i = 0;
        if (z) {
            ArrayList<FakeType> fakeKgdStatusBarArray = oplusFakeViewProvider.getFakeKgdStatusBarArray();
            int size = fakeKgdStatusBarArray.size();
            while (i < size) {
                FakeType fakeType = fakeKgdStatusBarArray.get(i);
                i++;
                this.oplusFakeViewProvider.getQsFakeView(fakeType).unbindRealView();
            }
            return;
        }
        ArrayList<FakeType> fakeStatusBarArray = oplusFakeViewProvider.getFakeStatusBarArray();
        int size2 = fakeStatusBarArray.size();
        while (i < size2) {
            FakeType fakeType2 = fakeStatusBarArray.get(i);
            i++;
            this.oplusFakeViewProvider.getQsFakeView(fakeType2).unbindRealView();
        }
    }

    public final void unbindSimpleFakeView() {
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_STATUSBAR)) {
            Log.d("SystemUi--Statusbar", "FakeBinder-->unbindSimpleFakeView");
        }
        ArrayList<FakeType> fakeStatusBarArray = this.oplusFakeViewProvider.getFakeStatusBarArray();
        int size = fakeStatusBarArray.size();
        int i = 0;
        while (i < size) {
            FakeType fakeType = fakeStatusBarArray.get(i);
            i++;
            this.oplusFakeViewProvider.getSimpleFakeView(fakeType).unbindRealView();
        }
    }
}
