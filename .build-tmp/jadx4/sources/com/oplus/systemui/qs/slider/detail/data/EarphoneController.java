package com.oplus.systemui.qs.slider.detail.data;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.android.systemui.keyguard.OplusBlackScreenGestureCallback$;
import com.android.systemui.shade.NotificationPanelViewController$;
import com.google.gson.Gson;
import com.oplusos.systemui.common.util.LogUtil;
import com.oplusos.systemui.common.util.TileHandlerThread;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class EarphoneController {
    public static final String ACTION = "com.oplus.melody.ACTION_FUN_PAGE";
    public static final String ACTIVITY_ID = "activity_id";
    public static final String ACTIVITY_ID_NOISE = "ear_detail";
    public static final String ACTIVITY_ID_SPATIAL = "spatial";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_EAR_LEFT = "ear_left";
    public static final String COLUMN_EAR_RIGHT = "ear_right";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SUPPORTS = "supports";
    public static final String COLUMN_TYPE = "type";
    public static final Companion Companion = new Companion();
    public static final String DEVICE_ID = "device_id";
    public static final String DEVICE_NAME = "device_name";
    public static final String METHOD_NOISE = "melody_method_noise_reduction";
    public static final String METHOD_SPATIAL = "melody_method_spatial";
    public static final String METHOD_WEAR = "melody_method_control_wear";
    public static final String NOISE = "noise";
    public static final String SPATIAL = "spatial";
    public static final String TAG = "EarphoneController";
    public static final String URI_ACTIVE_DEVICE = "content://com.oplus.melody.provider.EarphoneControlProvider/melody_method_active_device";
    public static final String URI_EARPHONE = "content://com.oplus.melody.provider.EarphoneControlProvider";
    public static final String URI_NOISE = "content://com.oplus.melody.provider.EarphoneControlProvider/melody_method_noise_reduction";
    public static final String URI_SPATIAL = "content://com.oplus.melody.provider.EarphoneControlProvider/melody_method_spatial";
    public static final String URI_WEAR = "content://com.oplus.melody.provider.EarphoneControlProvider/melody_method_control_wear";
    private final MutableStateFlow _earState;
    private ActiveDevice activeDevice;
    private final Set<Callback> callbacks;
    private final EarphoneController$contentObserver$1 contentObserver;
    private final Context context;
    private final StateFlow earState;
    private final Handler handler;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public interface Callback {
        default void onActiveDeviceChanged() {
        }

        default void onNoiseReductionTypeChanged() {
        }

        default void onSpatialAudioTypeChanged() {
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [com.oplus.systemui.qs.slider.detail.data.EarphoneController$contentObserver$1] */
    public EarphoneController(Context context) {
        this.context = context;
        final Handler handler = new Handler(TileHandlerThread.Companion.getInstance().getLooper());
        this.handler = handler;
        this.callbacks = new LinkedHashSet();
        this.contentObserver = new ContentObserver(handler) { // from class: com.oplus.systemui.qs.slider.detail.data.EarphoneController$contentObserver$1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z, Uri uri, int i) {
                super.onChange(z, uri, i);
                EarphoneController earphoneController = this.this$0;
                int i2 = 65280 & i;
                if (i2 == 256) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "onChange: onSpatialAudioTypeChanged flags = ", "EarphoneController-->", "SystemUi--QuickSettings");
                    }
                    Iterator it = this.this$0.callbacks.iterator();
                    while (it.hasNext()) {
                        ((EarphoneController.Callback) it.next()).onSpatialAudioTypeChanged();
                    }
                    return;
                }
                if (i2 == 512) {
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "onChange: onNoiseReductionTypeChanged flags = ", "EarphoneController-->", "SystemUi--QuickSettings");
                    }
                    Iterator it2 = this.this$0.callbacks.iterator();
                    while (it2.hasNext()) {
                        ((EarphoneController.Callback) it2.next()).onNoiseReductionTypeChanged();
                    }
                    return;
                }
                if (i2 == 1280) {
                    int i3 = 0;
                    int i4 = (i2 != 1280 || (i & 16) == 0) ? 0 : 2;
                    if (i2 == 1280 && (i & 1) == 1) {
                        i3 = 1;
                    }
                    earphoneController._earState.setValue(Integer.valueOf(i4 | i3));
                    EarphoneController earphoneController2 = this.this$0;
                    if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                        Object value = earphoneController2.getEarState().getValue();
                        StringBuilder sb = new StringBuilder("onChange: wearChanged flats = ");
                        sb.append(i);
                        sb.append(", ");
                        sb.append(value);
                        sb.append(" ");
                        Log.d("SystemUi--QuickSettings", NotificationPanelViewController$.ExternalSyntheticOutline0.m(i4, i3, " ", "EarphoneController-->", sb));
                    }
                }
            }
        };
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow(0);
        this._earState = MutableStateFlow;
        this.earState = FlowKt.asStateFlow(MutableStateFlow);
    }

    private final void setActiveDevice(ActiveDevice activeDevice) {
        if (Intrinsics.areEqual(this.activeDevice, activeDevice)) {
            return;
        }
        observeWearState(this.activeDevice, 0);
        if (activeDevice != null) {
            observeWearState(activeDevice, 1);
        }
        this.activeDevice = activeDevice;
        Iterator<T> it = this.callbacks.iterator();
        while (it.hasNext()) {
            ((Callback) it.next()).onActiveDeviceChanged();
        }
    }

    public final ActiveDevice getActiveDevice() {
        return this.activeDevice;
    }

    public final StateFlow getEarState() {
        return this.earState;
    }

    public final int getNoiseReductionMode(String str) {
        return getType(1, str, URI_NOISE, NOISE);
    }

    public final int getSpatialAudioType(String str) {
        return getType(0, str, URI_SPATIAL, "spatial");
    }

    public final int getType(int i, String str, String str2, String str3) {
        Object obj;
        Map supportType;
        Map supportType2;
        if (str == null) {
            return i;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        ActiveDevice activeDevice = this.activeDevice;
        if (activeDevice != null && (supportType2 = activeDevice.getSupportType()) != null) {
        }
        try {
            Cursor cursorQuery = this.context.getContentResolver().query(Uri.parse(str2), null, COLUMN_ADDRESS, new String[]{str}, null);
            Unit unit = null;
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        int columnIndex = cursorQuery.getColumnIndex(COLUMN_SUPPORTS);
                        String string = cursorQuery.isNull(columnIndex) ? null : cursorQuery.getString(columnIndex);
                        List list = string != null ? (List) new Gson().fromJson(string, new getType.1.1.supportType.1.1().getType()) : null;
                        int columnIndex2 = cursorQuery.getColumnIndex("type");
                        Integer numValueOf = cursorQuery.isNull(columnIndex2) ? null : Integer.valueOf(cursorQuery.getInt(columnIndex2));
                        if (numValueOf != null) {
                            i = numValueOf.intValue();
                        }
                        ActiveDevice activeDevice2 = this.activeDevice;
                        if (activeDevice2 != null && (supportType = activeDevice2.getSupportType()) != null) {
                        }
                        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                            Log.d("SystemUi--QuickSettings", "EarphoneController-->".concat("getType: key = " + str3 + ", type = " + i + ", supportType = " + list));
                        }
                    }
                    CloseableKt.closeFinally(cursorQuery, (Throwable) null);
                    unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(cursorQuery, th);
                        throw th2;
                    }
                }
            }
            obj = Result.constructor-impl(unit);
        } catch (Throwable th3) {
            obj = Result.constructor-impl(ResultKt.createFailure(th3));
        }
        Throwable th4 = Result.exceptionOrNull-impl(obj);
        if (th4 != null) {
            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "getType: e = " + th4);
        }
        Binder.restoreCallingIdentity(jClearCallingIdentity);
        return i;
    }

    public final void observeWearState(ActiveDevice activeDevice, int i) {
        Object obj;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            OplusBlackScreenGestureCallback$.ExternalSyntheticOutline0.m(i, "observeWearState: type = ", "EarphoneController-->", "SystemUi--QuickSettings");
        }
        if (activeDevice == null) {
            return;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Bundle bundle = new Bundle();
            bundle.putString(COLUMN_NAME, activeDevice.getName());
            bundle.putString(COLUMN_ADDRESS, activeDevice.getAddress());
            bundle.putInt("type", i);
            obj = Result.constructor-impl(this.context.getContentResolver().call(Uri.parse(URI_EARPHONE), METHOD_WEAR, (String) null, bundle));
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "observeWearState: e = " + th2);
        }
        Binder.restoreCallingIdentity(jClearCallingIdentity);
    }

    /* JADX WARN: Code duplicated, block: B:50:0x00d2  */
    /* JADX WARN: Instruction removed from duplicated block: B:50:0x00d2, please report this as an issue */
    public final void refreshActiveDevice() {
        Object obj;
        Throwable th;
        Throwable th2;
        ActiveDevice activeDevice;
        Uri uri = Uri.parse(URI_ACTIVE_DEVICE);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        ActiveDevice activeDevice2 = null;
        try {
            Cursor cursorQuery = this.context.getContentResolver().query(uri, null, null, null, null);
            if (cursorQuery != null) {
                try {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            while (true) {
                                int columnIndex = cursorQuery.getColumnIndex(COLUMN_NAME);
                                String string = cursorQuery.isNull(columnIndex) ? null : cursorQuery.getString(columnIndex);
                                if (string != null) {
                                    int columnIndex2 = cursorQuery.getColumnIndex(COLUMN_ADDRESS);
                                    String string2 = cursorQuery.isNull(columnIndex2) ? null : cursorQuery.getString(columnIndex2);
                                    if (string2 != null) {
                                        activeDevice = new ActiveDevice(string, string2, new LinkedHashMap());
                                        try {
                                            if (!LogUtil.getNormalDebug() || !LogUtil.isTagValid(LogUtil.TAG_QS)) {
                                                break;
                                                break;
                                            }
                                            Log.d(LogUtil.TAG + LogUtil.TAG_QS, TAG + LogUtil.SEPARATOR + "refreshActiveDevice: activeDevice != null");
                                            break;
                                        } catch (Throwable th3) {
                                            th2 = th3;
                                            activeDevice2 = activeDevice;
                                            try {
                                                throw th2;
                                            } catch (Throwable th4) {
                                                CloseableKt.closeFinally(cursorQuery, th2);
                                                throw th4;
                                            }
                                        }
                                    }
                                }
                                if (!cursorQuery.moveToNext()) {
                                }
                            }
                            CloseableKt.closeFinally(cursorQuery, (Throwable) null);
                            activeDevice2 = activeDevice;
                        } else {
                            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "refreshActiveDevice: activeDevice = null");
                        }
                        CloseableKt.closeFinally(cursorQuery, (Throwable) null);
                        activeDevice2 = activeDevice;
                    } catch (Throwable th5) {
                        th = th5;
                        activeDevice2 = activeDevice;
                        obj = Result.constructor-impl(ResultKt.createFailure(th));
                    }
                    activeDevice = null;
                    break;
                } catch (Throwable th6) {
                    th2 = th6;
                }
            } else {
                LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "refreshActiveDevice: cursor = null, activeDevice = null");
            }
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th7) {
            th = th7;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
            th = Result.exceptionOrNull-impl(obj);
            if (th != null) {
                LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "refreshActiveDevice: e = " + th);
            }
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            setActiveDevice(activeDevice2);
        }
        th = Result.exceptionOrNull-impl(obj);
        if (th != null) {
            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "refreshActiveDevice: e = " + th);
        }
        Binder.restoreCallingIdentity(jClearCallingIdentity);
        setActiveDevice(activeDevice2);
    }

    public final void refreshSupportType() {
        String address;
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            Log.d("SystemUi--QuickSettings", "EarphoneController-->refreshSupportType: ");
        }
        ActiveDevice activeDevice = this.activeDevice;
        if (activeDevice == null || (address = activeDevice.getAddress()) == null) {
            return;
        }
        getSpatialAudioType(address);
        getNoiseReductionMode(address);
    }

    public final void registerCallback(Callback callback) {
        Object obj;
        boolean zIsEmpty = this.callbacks.isEmpty();
        this.callbacks.add(callback);
        if (zIsEmpty) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "EarphoneController-->registerCallback: ");
            }
            try {
                this.context.getContentResolver().registerContentObserver(Uri.parse(URI_EARPHONE), false, this.contentObserver);
                obj = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "registerCallback: error = " + th2);
            }
        }
    }

    public final void setNoiseReductionMode(ActiveDevice activeDevice, int i) {
        setType(activeDevice, i, METHOD_NOISE);
    }

    public final void setSpatialAudioType(ActiveDevice activeDevice, int i) {
        setType(activeDevice, i, METHOD_SPATIAL);
    }

    public final void setType(ActiveDevice activeDevice, int i, String str) {
        Object obj;
        if (activeDevice == null) {
            return;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Bundle bundle = new Bundle();
            bundle.putString(COLUMN_NAME, activeDevice.getName());
            bundle.putString(COLUMN_ADDRESS, activeDevice.getAddress());
            bundle.putInt("type", i);
            ContentResolver contentResolver = this.context.getContentResolver();
            if (contentResolver != null) {
                contentResolver.call(Uri.parse(URI_EARPHONE), str, (String) null, bundle);
            }
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "EarphoneController-->".concat("setType: method = " + str + ", type = " + i));
            }
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "setType: e = " + th2);
        }
        Binder.restoreCallingIdentity(jClearCallingIdentity);
    }

    public final void unregisterCallback(Callback callback) {
        Object obj;
        this.callbacks.remove(callback);
        if (this.callbacks.isEmpty()) {
            if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
                Log.d("SystemUi--QuickSettings", "EarphoneController-->unregisterCallback: ");
            }
            try {
                this.context.getContentResolver().unregisterContentObserver(this.contentObserver);
                obj = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(obj);
            if (th2 != null) {
                LogUtil.w$default(8, LogUtil.TAG_QS, TAG, "unregisterCallback: error = " + th2);
            }
            setActiveDevice(null);
        }
    }
}
