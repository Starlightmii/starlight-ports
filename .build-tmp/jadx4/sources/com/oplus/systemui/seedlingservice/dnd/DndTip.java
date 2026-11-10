package com.oplus.systemui.seedlingservice.dnd;

import android.content.Context;
import android.os.Bundle;
import com.android.keyguard.OplusLockIconView$;
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
import org.json.JSONException;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DndTip extends NormalTip {
    private static final String DND_EVENT = "DndEvent";
    private static final String DND_SEEDLING_ACTION = "pantanal.intent.business.app.system.DND";
    private static final String DND_SERVICE_ID = "268452000";
    private static final String KEY_DND_MODE_TYPE = "DndModeType";
    private static final String KEY_DND_UPK_DESCRIPTION = "description";
    private static final String KEY_DND_UPK_IMAGE_PATH = "imagePath";
    private static final String KEY_DND_UPK_SHOW_B = "showB";
    private static final String KEY_DND_UPK_UP_OR_DOWN = "upOrDown";
    private static final String TAG = "DndTip";
    private static final int VALUE_DND_MODE_OFF = 0;
    private static final int VALUE_DND_MODE_ON = 1;
    private static final String VALUE_DND_UPK_DOWN = "down";
    private static final String VALUE_DND_UPK_IMAGE_DND_OFF = "assets/images/dnd_off.svg";
    private static final String VALUE_DND_UPK_IMAGE_DND_ON = "assets/images/dnd_on.svg";
    private static final String VALUE_DND_UPK_NONE = "none";
    private static final String VALUE_DND_UPK_SHOW_B = "false";
    private static final String VALUE_DND_UPK_UP = "up";
    public static final DndTip INSTANCE = new DndTip();
    private static Integer dndState = 0;

    private DndTip() {
    }

    private final void handleDndOff() throws JSONException {
        LogUtil.i(TAG, "handleDndOff");
        Bundle bundle = new Bundle();
        bundle.putString(KEY_DND_UPK_UP_OR_DOWN, VALUE_DND_UPK_NONE);
        bundle.putString(KEY_DND_UPK_IMAGE_PATH, VALUE_DND_UPK_IMAGE_DND_OFF);
        bundle.putString(KEY_DND_UPK_SHOW_B, "false");
        Context mContext = getMContext();
        bundle.putString(KEY_DND_UPK_DESCRIPTION, mContext != null ? mContext.getString(R$string.magic_key_state_off) : null);
        UiData uiData = new UiData();
        uiData.put(KEY_DND_UPK_UP_OR_DOWN, VALUE_DND_UPK_NONE);
        uiData.put(KEY_DND_UPK_IMAGE_PATH, VALUE_DND_UPK_IMAGE_DND_OFF);
        uiData.put(KEY_DND_UPK_SHOW_B, "false");
        Context mContext2 = INSTANCE.getMContext();
        uiData.put(KEY_DND_UPK_DESCRIPTION, mContext2 != null ? mContext2.getString(R$string.magic_key_state_off) : null);
        createOrUpdateCard(uiData, bundle);
    }

    private final void handleDndOn() throws JSONException {
        LogUtil.i(TAG, "handleDndOn");
        Bundle bundle = new Bundle();
        bundle.putString(KEY_DND_UPK_UP_OR_DOWN, VALUE_DND_UPK_NONE);
        bundle.putString(KEY_DND_UPK_IMAGE_PATH, VALUE_DND_UPK_IMAGE_DND_ON);
        bundle.putString(KEY_DND_UPK_SHOW_B, "false");
        Context mContext = getMContext();
        bundle.putString(KEY_DND_UPK_DESCRIPTION, mContext != null ? mContext.getString(R$string.magic_key_state_on) : null);
        UiData uiData = new UiData();
        uiData.put(KEY_DND_UPK_UP_OR_DOWN, VALUE_DND_UPK_NONE);
        uiData.put(KEY_DND_UPK_IMAGE_PATH, VALUE_DND_UPK_IMAGE_DND_ON);
        uiData.put(KEY_DND_UPK_SHOW_B, "false");
        Context mContext2 = INSTANCE.getMContext();
        uiData.put(KEY_DND_UPK_DESCRIPTION, mContext2 != null ? mContext2.getString(R$string.magic_key_state_on) : null);
        createOrUpdateCard(uiData, bundle);
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public boolean checkCardInvalid(CardState cardState) {
        return false;
    }

    public final void dump(PrintWriter printWriter) {
        printWriter.println(TAG);
        printWriter.println("\tdndState: " + dndState);
        OplusLockIconView$.ExternalSyntheticOutline0.m(printWriter, "\twaitingCreateCard: ", getWaitingCreateCard());
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public String getEventCode() {
        return DND_EVENT;
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public String getSeedlingAction() {
        return DND_SEEDLING_ACTION;
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public SeedlingCardOptions getSeedlingCardOptions() {
        SeedlingHostEnum seedlingHostEnum = SeedlingHostEnum.StatusBar;
        Boolean bool = Boolean.TRUE;
        return new SeedlingCardOptions((String) null, (String) null, true, (Boolean) null, false, 4, (List) null, MapsKt__MapsJVMKt.mapOf(TuplesKt.to(seedlingHostEnum, bool)), MapsKt__MapsJVMKt.mapOf(TuplesKt.to(seedlingHostEnum, bool)), (CancelPanelActionConfigEnum) null, (Map) null, (Integer) null, 11, false, (Long) null, (Map) null, 61019, (DefaultConstructorMarker) null);
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public String getServiceId() {
        return DND_SERVICE_ID;
    }

    @Override // com.oplus.systemui.seedlingservice.eventmanager.EventListener
    public void onEvent(Event event) throws JSONException {
        LogUtil.i(TAG, "onEvent " + event);
        Bundle info = event.getInfo();
        Integer numValueOf = info != null ? Integer.valueOf(info.getInt(KEY_DND_MODE_TYPE)) : null;
        dndState = numValueOf;
        LogUtil.i(TAG, "dndKeyState=" + numValueOf);
        Integer num = dndState;
        if (num != null && num.intValue() == 0) {
            handleDndOff();
        } else if (num != null && num.intValue() == 1) {
            handleDndOn();
        }
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public void prepareCreateData(UiData uiData, Bundle bundle) {
    }

    @Override // com.oplus.systemui.seedlingservice.tip.NormalTip
    public void prepareUpdateData(UiData uiData, Bundle bundle) {
    }
}
