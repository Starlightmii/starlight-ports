package com.oplus.systemui.seedlingservice.opthreekey;

import android.content.Context;
import android.os.Bundle;
import com.android.keyguard.OplusLockIconView$;
import com.android.systemui.biometrics.BiometricMessageDeferral$;
import com.oplus.pantanal.seedling.bean.CancelPanelActionConfigEnum;
import com.oplus.pantanal.seedling.bean.SeedlingHostEnum;
import com.oplus.pantanal.seedling.update.SeedlingCardOptions;
import com.oplus.systemui.seedlingservice.R$string;
import com.oplus.systemui.seedlingservice.datamanager.UiData;
import com.oplus.systemui.seedlingservice.eventmanager.Event;
import com.oplus.systemui.seedlingservice.statemanager.CardState;
import com.oplus.systemui.seedlingservice.tip.NormalTip;
import com.oplus.systemui.seedlingservice.utils.LogUtil;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class RingModeTip extends NormalTip {
    private static final int CARD_STATE_LONG_TIME_NO_UPDATE = 7200000;
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IMAGEPATH = "imagePath";
    private static final String KEY_RING_MODE_TYPE = "ringModeType";
    private static final String KEY_UP_OR_DOWN = "upOrDown";
    private static final String PATH_STATE_DOWN = "assets/images/op_ring.json";
    private static final String PATH_STATE_MIDDLE = "assets/images/op_vibrate.json";
    private static final String PATH_STATE_UP = "assets/images/op_silence.json";
    private static final String RING_MODE_EVENT = "ringModeEvent";
    private static final String RING_MODE_SERVICEID = "268451918";
    private static final int RING_MODE_STATE_NONE = -1;
    private static final String SEEDLING_ACTION_SYSTEMUI_RING_MODE = "pantanal.intent.business.app.system.OP_THREE_KEY_DIALOG";
    private static final String TAG = "RingModeTip";
    private static final String VALUE_DOWN = "down";
    private static final String VALUE_NONE = "none";
    private static final String VALUE_UP = "up";
    private static final SeedlingCardOptions volumeCardOptions;
    public static final RingModeTip INSTANCE = new RingModeTip();
    private static int lastRingModeState = -1;

    static {
        SeedlingHostEnum seedlingHostEnum = SeedlingHostEnum.StatusBar;
        Boolean bool = Boolean.TRUE;
        volumeCardOptions = new SeedlingCardOptions((String) null, (String) null, true, (Boolean) null, false, 4, (List) null, MapsKt__MapsJVMKt.mapOf(TuplesKt.to(seedlingHostEnum, bool)), MapsKt__MapsJVMKt.mapOf(TuplesKt.to(seedlingHostEnum, bool)), (CancelPanelActionConfigEnum) null, (Map) null, (Integer) null, 11, false, (Long) null, (Map) null, 61019, (DefaultConstructorMarker) null);
    }

    private RingModeTip() {
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public boolean checkCardInvalid(CardState cardState) {
        return System.currentTimeMillis() - cardState.getLastChangeTime() > 7200000;
    }

    public final void dump(PrintWriter printWriter) {
        printWriter.println(TAG);
        printWriter.println("\tcacheUiData: " + getCacheUiData());
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\twaitingCreateCard: ", getWaitingCreateCard());
        BiometricMessageDeferral$.ExternalSyntheticOutline0.m("\tlastThreeKeyState: ", lastRingModeState, printWriter);
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public String getEventCode() {
        return RING_MODE_EVENT;
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public String getSeedlingAction() {
        return SEEDLING_ACTION_SYSTEMUI_RING_MODE;
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public SeedlingCardOptions getSeedlingCardOptions() {
        return volumeCardOptions;
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public String getServiceId() {
        return RING_MODE_SERVICEID;
    }

    @Override // com.oplus.systemui.seedlingservice.eventmanager.EventListener
    public void onEvent(Event event) throws JSONException {
        LogUtil.i(TAG, "onEvent " + event);
        Bundle info = event.getInfo();
        Object obj = info != null ? info.get(KEY_RING_MODE_TYPE) : null;
        if (Intrinsics.areEqual(obj, 0)) {
            LogUtil.i(TAG, "onEvent RINGER_MODE_SILENT lastRingModeState: " + lastRingModeState);
            Context mContext = getMContext();
            info.putString(KEY_DESCRIPTION, mContext != null ? mContext.getString(R$string.volume_footer_slient) : null);
            info.putString(KEY_IMAGEPATH, PATH_STATE_UP);
            int i = lastRingModeState;
            if (i == 1 || i == 2) {
                info.putString(KEY_UP_OR_DOWN, VALUE_UP);
            } else {
                info.putString(KEY_UP_OR_DOWN, VALUE_NONE);
            }
            lastRingModeState = 0;
        } else if (Intrinsics.areEqual(obj, 1)) {
            LogUtil.i(TAG, "onEvent RINGER_MODE_VIBRATE lastRingModeState: " + lastRingModeState);
            Context mContext2 = getMContext();
            info.putString(KEY_DESCRIPTION, mContext2 != null ? mContext2.getString(R$string.volume_vibrate) : null);
            info.putString(KEY_IMAGEPATH, PATH_STATE_MIDDLE);
            int i2 = lastRingModeState;
            if (i2 == 0) {
                info.putString(KEY_UP_OR_DOWN, VALUE_DOWN);
            } else if (i2 == 2) {
                info.putString(KEY_UP_OR_DOWN, VALUE_UP);
            } else {
                info.putString(KEY_UP_OR_DOWN, VALUE_NONE);
            }
            lastRingModeState = 1;
        } else if (Intrinsics.areEqual(obj, 2)) {
            LogUtil.i(TAG, "onEvent RINGER_MODE_NORMAL lastRingModeState: " + lastRingModeState);
            Context mContext3 = getMContext();
            info.putString(KEY_DESCRIPTION, mContext3 != null ? mContext3.getString(R$string.volume_footer_ring) : null);
            info.putString(KEY_IMAGEPATH, PATH_STATE_DOWN);
            int i3 = lastRingModeState;
            if (i3 == 1 || i3 == 0) {
                info.putString(KEY_UP_OR_DOWN, VALUE_DOWN);
            } else {
                info.putString(KEY_UP_OR_DOWN, VALUE_NONE);
            }
            lastRingModeState = 2;
        }
        UiData uiData = new UiData();
        uiData.put(KEY_DESCRIPTION, info != null ? info.getString(KEY_DESCRIPTION) : null);
        uiData.put(KEY_IMAGEPATH, info != null ? info.getString(KEY_IMAGEPATH) : null);
        createOrUpdateCard(uiData, info);
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public void prepareCreateData(UiData uiData, Bundle bundle) throws JSONException {
        if (uiData != null) {
            uiData.put(KEY_UP_OR_DOWN, VALUE_NONE);
        }
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public void prepareUpdateData(UiData uiData, Bundle bundle) throws JSONException {
        if (uiData != null) {
            uiData.put(KEY_UP_OR_DOWN, bundle != null ? bundle.getString(KEY_UP_OR_DOWN) : null);
        }
    }
}
