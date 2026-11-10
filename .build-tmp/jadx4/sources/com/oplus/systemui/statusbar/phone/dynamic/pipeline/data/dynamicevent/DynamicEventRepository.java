package com.oplus.systemui.statusbar.phone.dynamic.pipeline.data.dynamicevent;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import com.oplus.base.ocrscanner.oseesdk.utils.ApplicationUtil$;
import com.oplusos.systemui.common.util.LogUtil;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class DynamicEventRepository {
    public static final Companion Companion = new Companion();
    private static final String EXTRA_ANIM_TYPE = "animType";
    private static final String EXTRA_DESC = "desc";
    public static final String EXTRA_EVENT_TYPE = "eventType";
    private static final String EXTRA_ICON = "icon";
    private static final String EXTRA_IS_ICON_TNT = "isIconTnt";
    private static final String EXTRA_SHOW_TYPE = "showType";
    private static final String EXTRA_SLOT = "slot";
    private static final String EXTRA_TEXT = "text";
    private static final String TAG = "OplusSystemInfoRepository";
    private final MutableStateFlow _dynamicEventFlow;
    private final CoroutineScope bgScope;
    private final Context context;
    private final StateFlow dynamicEventFlow;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public DynamicEventRepository(Context context, CoroutineScope coroutineScope) {
        this.context = context;
        this.bgScope = coroutineScope;
        MutableStateFlow MutableStateFlow = StateFlowKt.MutableStateFlow((Object) null);
        this._dynamicEventFlow = MutableStateFlow;
        this.dynamicEventFlow = MutableStateFlow;
    }

    public static final DynamicEvent access$createDynamicEvent(DynamicEventRepository dynamicEventRepository, String str, int i, Bundle bundle) {
        dynamicEventRepository.getClass();
        try {
            String string = bundle.getString(EXTRA_SLOT, "");
            boolean z = bundle.getBoolean(EXTRA_IS_ICON_TNT, true);
            int i2 = bundle.getInt(EXTRA_TEXT, 0);
            int i3 = bundle.getInt(EXTRA_DESC, 0);
            int i4 = bundle.getInt(EXTRA_SHOW_TYPE, 0);
            int i5 = bundle.getInt(EXTRA_ANIM_TYPE, -1);
            Resources resourcesForApplication = dynamicEventRepository.context.getPackageManager().getResourcesForApplication(str);
            String string2 = i2 != 0 ? resourcesForApplication.getString(i2) : "";
            string2.getClass();
            String string3 = i3 != 0 ? resourcesForApplication.getString(i3) : "";
            string3.getClass();
            Icon icon = (Icon) bundle.getParcelable(EXTRA_ICON, Icon.class);
            Drawable drawableLoadDrawable = icon != null ? icon.loadDrawable(dynamicEventRepository.context) : null;
            if (i4 != 0 && i4 != 1 && i4 != 2 && i4 != 3) {
                LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, TAG, "createDynamicEvent: showType is invalid");
                return null;
            }
            if (drawableLoadDrawable == null) {
                LogUtil.w$default(8, LogUtil.TAG_STATUSBAR, TAG, "createDynamicEvent: drawable is null");
                return null;
            }
            DynamicEvent dynamicEvent = new DynamicEvent(i, string2, string3, i4, drawableLoadDrawable, z, i5, str);
            LogUtil.i$default(LogUtil.TAG_STATUSBAR, TAG, "createDynamicEvent: " + string + " " + dynamicEvent, null, 8);
            return dynamicEvent;
        } catch (Throwable th) {
            Throwable thM = ApplicationUtil$.ExternalSyntheticOutline0.m(th);
            if (thM != null) {
                LogUtil.e$default(8, LogUtil.TAG_STATUSBAR, TAG, "createDynamicEvent error! exception=" + thM);
            }
            return null;
        }
    }

    public final StateFlow getDynamicEventFlow() {
        return this.dynamicEventFlow;
    }

    public final void updateDynamicEvent(String str, int i, Bundle bundle) {
        BuildersKt.launch$default(this.bgScope, (CoroutineContext) null, (CoroutineStart) null, new updateDynamicEvent.1(this, i, str, bundle, (Continuation) null), 3);
    }
}
