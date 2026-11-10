package com.oplus.systemui.seedlingservice.tip;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.coe.expr.Expressions$28$;
import com.oplus.pantanal.seedling.bean.SeedlingCard;
import com.oplus.pantanal.seedling.bean.SeedlingIntent;
import com.oplus.pantanal.seedling.bean.SeedlingIntentFlagEnum;
import com.oplus.pantanal.seedling.intent.IIntentManager;
import com.oplus.pantanal.seedling.intent.IIntentResultCallBack;
import com.oplus.pantanal.seedling.update.ISeedlingDataUpdate;
import com.oplus.pantanal.seedling.update.SeedlingCardOptions;
import com.oplus.pantanal.seedling.util.SeedlingTool;
import com.oplus.systemui.seedlingservice.datamanager.UiData;
import com.oplus.systemui.seedlingservice.eventmanager.EventDispatch;
import com.oplus.systemui.seedlingservice.eventmanager.EventListener;
import com.oplus.systemui.seedlingservice.statemanager.CardState;
import com.oplus.systemui.seedlingservice.statemanager.CardStateManager;
import com.oplus.systemui.seedlingservice.utils.LogUtil;
import com.oplus.systemui.seedlingservice.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public abstract class NormalTip implements CoreModule, EventListener {
    public static final Companion Companion = new Companion(null);
    private static final String KEY_INITDATA = "initData";
    private static final int KEY_MSG_CLEAR_CACHE = 2;
    private static final long MSG_CLEAR_CACHE_DELAY = 1000;
    public static final int MSG_WHAT_SEEDLING_CLICK = 2;
    private static final String TAG = "NormalTip";
    private final NormalTip$cacheHandler$1 cacheHandler;
    private UiData cacheUiData;
    private final CardStateManager cardStateManager = CardStateManager.Companion.getInstance();
    private Context mContext;
    private boolean waitingCreateCard;

    /* JADX WARN: Type inference failed for: r1v0, types: [com.oplus.systemui.seedlingservice.tip.NormalTip$cacheHandler$1] */
    public NormalTip() {
        final Looper looper = ThreadUtil.INSTANCE.getWorkThread().getLooper();
        this.cacheHandler = new Handler(looper) { // from class: com.oplus.systemui.seedlingservice.tip.NormalTip$cacheHandler$1
            @Override // android.os.Handler
            public void handleMessage(Message message) throws JSONException {
                if (message.what == 2) {
                    LogUtil.i("NormalTip", "KEY_MSG_CLEAR_CACHE createCardIntent");
                    this.this$0.setWaitingCreateCard(false);
                    NormalTip normalTip = this.this$0;
                    normalTip.createCardIntent(normalTip.getCacheUiData());
                    this.this$0.setCacheUiData(null);
                }
            }
        };
    }

    public static /* synthetic */ void cancelIntent$default(NormalTip normalTip, Context context, String str, int i, Object obj) {
        if (obj != null) {
            Expressions$28$.ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: cancelIntent");
            return;
        }
        if ((i & 2) != 0) {
            str = "";
        }
        normalTip.cancelIntent(context, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createCardIntent(UiData uiData) throws JSONException {
        Context context = this.mContext;
        if (context == null || uiData == null) {
            return;
        }
        this.waitingCreateCard = true;
        prepareCreateData$default(this, uiData, null, 2, null);
        UiData uiData2 = new UiData();
        uiData2.put(KEY_INITDATA, uiData.build());
        sendIntent(context, uiData2.build());
    }

    public static /* synthetic */ void prepareCreateData$default(NormalTip normalTip, UiData uiData, Bundle bundle, int i, Object obj) {
        if (obj != null) {
            Expressions$28$.ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: prepareCreateData");
            return;
        }
        if ((i & 2) != 0) {
            bundle = null;
        }
        normalTip.prepareCreateData(uiData, bundle);
    }

    public static /* synthetic */ void prepareUpdateData$default(NormalTip normalTip, UiData uiData, Bundle bundle, int i, Object obj) {
        if (obj != null) {
            Expressions$28$.ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: prepareUpdateData");
            return;
        }
        if ((i & 2) != 0) {
            bundle = null;
        }
        normalTip.prepareUpdateData(uiData, bundle);
    }

    private final void sendIntent(Context context, JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("seedling_event", getEventCode());
        SeedlingIntent seedlingIntent = new SeedlingIntent(System.currentTimeMillis(), getSeedlingAction(), SeedlingIntentFlagEnum.START, jSONObject2, jSONObject, getSeedlingCardOptions());
        LogUtil.debug(TAG, "sendIntent " + seedlingIntent);
        IIntentManager.sendSeedling$default(SeedlingTool.INSTANCE, context, seedlingIntent, (IIntentResultCallBack) null, 4, (Object) null);
    }

    public final void cancelIntent(final Context context, final String str) {
        Runnable runnable = new Runnable() { // from class: com.oplus.systemui.seedlingservice.tip.NormalTip$cancelIntent$cancelRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                LogUtil.d("NormalTip", "cancelIntent callingServiceId = " + str);
                int i = 0;
                this.setWaitingCreateCard(false);
                IIntentManager.sendSeedling$default(SeedlingTool.INSTANCE, context, new SeedlingIntent(0L, this.getSeedlingAction(), SeedlingIntentFlagEnum.END, (JSONObject) null, (JSONObject) null, (SeedlingCardOptions) null, 57, (DefaultConstructorMarker) null), (IIntentResultCallBack) null, 4, (Object) null);
                Set<SeedlingCard> setKeySet = this.getCardStateManager().getCardMap().keySet();
                NormalTip normalTip = this;
                ArrayList arrayList = new ArrayList();
                for (Object obj : setKeySet) {
                    if (Intrinsics.areEqual(((SeedlingCard) obj).getServiceId(), normalTip.getServiceId())) {
                        arrayList.add(obj);
                    }
                }
                NormalTip normalTip2 = this;
                int size = arrayList.size();
                while (i < size) {
                    Object obj2 = arrayList.get(i);
                    i++;
                    SeedlingCard seedlingCard = (SeedlingCard) obj2;
                    CardStateManager cardStateManager = normalTip2.getCardStateManager();
                    seedlingCard.getClass();
                    cardStateManager.removeCard(seedlingCard);
                }
                removeMessages(2);
            }
        };
        Looper looperMyLooper = Looper.myLooper();
        ThreadUtil threadUtil = ThreadUtil.INSTANCE;
        if (Intrinsics.areEqual(looperMyLooper, threadUtil.getWorkHandler().getLooper())) {
            runnable.run();
        } else {
            threadUtil.getWorkHandler().post(runnable);
        }
    }

    public void cardUpdateData(SeedlingCard seedlingCard, Bundle bundle) {
        JSONObject jSONObjectBuild;
        LogUtil.i(TAG, "cardUpdateData: " + bundle);
        this.waitingCreateCard = false;
        UiData uiData = this.cacheUiData;
        if (uiData != null && (jSONObjectBuild = uiData.build()) != null) {
            LogUtil.i(TAG, "SeedlingTool.updateData cacheUiData: " + this.cacheUiData);
            ISeedlingDataUpdate.updateData$default(SeedlingTool.INSTANCE, seedlingCard, jSONObjectBuild, (SeedlingCardOptions) null, 4, (Object) null);
            removeMessages(2);
        }
        this.cacheUiData = null;
    }

    public abstract boolean checkCardInvalid(CardState cardState);

    public final void createOrUpdateCard(UiData uiData, Bundle bundle) throws JSONException {
        Object next;
        LogUtil.i(TAG, "cardMap size: " + this.cardStateManager.getCardMap().size() + " cardMap: " + this.cardStateManager.getCardMap() + " waitingCreateCard: " + this.waitingCreateCard);
        Iterator<T> it = this.cardStateManager.getCardMap().keySet().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((SeedlingCard) next).getServiceId(), getServiceId()));
        if (((SeedlingCard) next) != null) {
            prepareUpdateData(uiData, bundle);
            updateData(uiData);
        } else {
            if (!this.waitingCreateCard) {
                this.cacheUiData = null;
                createCardIntent(uiData);
                return;
            }
            prepareUpdateData(uiData, bundle);
            this.cacheUiData = uiData;
            removeMessages(2);
            sendMessageDelayed(obtainMessage(2), MSG_CLEAR_CACHE_DELAY);
        }
    }

    public final UiData getCacheUiData() {
        return this.cacheUiData;
    }

    public final CardStateManager getCardStateManager() {
        return this.cardStateManager;
    }

    public abstract String getEventCode();

    public final Context getMContext() {
        return this.mContext;
    }

    public abstract String getSeedlingAction();

    public abstract SeedlingCardOptions getSeedlingCardOptions();

    public abstract String getServiceId();

    public final boolean getWaitingCreateCard() {
        return this.waitingCreateCard;
    }

    public void onCardCreate(SeedlingCard seedlingCard) {
    }

    public void onDestroy(SeedlingCard seedlingCard) {
    }

    public abstract void prepareCreateData(UiData uiData, Bundle bundle);

    public abstract void prepareUpdateData(UiData uiData, Bundle bundle);

    public final void setCacheUiData(UiData uiData) {
        this.cacheUiData = uiData;
    }

    public final void setMContext(Context context) {
        this.mContext = context;
    }

    public final void setWaitingCreateCard(boolean z) {
        this.waitingCreateCard = z;
    }

    @Override // com.oplus.systemui.seedlingservice.tip.CoreModule
    public void start(Context context) {
        LogUtil.i(TAG, "start");
        this.mContext = context;
        EventDispatch.INSTANCE.addEventListener(getEventCode(), this);
    }

    @Override // com.oplus.systemui.seedlingservice.tip.CoreModule
    public void stop(Context context) {
        LogUtil.i(TAG, "stop");
        EventDispatch.INSTANCE.removeEventListener(getEventCode(), this);
        cancelIntent$default(this, context, null, 2, null);
        this.mContext = null;
        removeMessages(2);
        this.cacheUiData = null;
    }

    public void updateData(UiData uiData) {
        JSONObject jSONObjectBuild;
        if (uiData == null || (jSONObjectBuild = uiData.build()) == null) {
            return;
        }
        LogUtil.i(TAG, "updateData data=" + jSONObjectBuild);
        for (Map.Entry<SeedlingCard, CardState> entry : this.cardStateManager.getCardMap().entrySet()) {
            SeedlingCard key = entry.getKey();
            CardState value = entry.getValue();
            if (checkCardInvalid(value)) {
                LogUtil.i(TAG, "checkCardInvalid=" + key + " state=" + value + " and remove card");
                this.cardStateManager.removeCard(key);
            } else if (Intrinsics.areEqual(key.getServiceId(), getServiceId())) {
                LogUtil.i(TAG, "updateData card=" + key + " state=" + value);
                SeedlingTool.INSTANCE.updateData(key, jSONObjectBuild, getSeedlingCardOptions());
            }
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
