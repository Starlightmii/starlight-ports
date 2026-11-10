package com.oplus.systemui.qs.tileex;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import androidx.car.app.SurfaceContainer$;
import androidx.car.app.serialization.Bundler$;
import com.android.systemui.animation.Expandable;
import com.android.systemui.plugins.qs.QSTile;
import com.android.systemui.qs.external.CustomTile;
import com.android.systemui.qs.tileimpl.QSTileImpl;
import com.android.systemui.settings.UserTracker;
import com.oplus.systemui.qs.base.util.QsColorfulConfigUtil;
import com.oplus.systemui.qs.detail.interactor.OplusQSDetailInteractor;
import com.oplusos.systemui.common.inline.SystemUIInlineViewHelper;
import com.oplusos.systemui.common.util.LogUtil;
import java.lang.invoke.VarHandle;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Supplier;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class CustomTileEx extends QSTileExImpl {
    public static final Companion Companion = new Companion();
    private static final String TAG = "CustomTileEx";
    private static final String TILE_BATTERYLOTTIE_PREFIX = "qs_battery_lottie";
    private static final String TILE_EYEPROTECT_LOTTIE_PREFIX = "qs_eyeprotect_lottie";
    private final Map<String, Integer> colorfulConfig;
    private final Lazy detailAdapter$delegate;
    private final Lazy detailInteractor$delegate;
    private final Map<String, Pair<String, Integer>> lottieConfig;
    private final ConcurrentHashMap<String, TileServiceConfig> tileSpecToTileDetail;
    private final UserTracker userTracker;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class TileServiceConfig {
        private final String detailCategory;
        private final ComponentName detailComponent;
        public final int detailHeightRes;

        public TileServiceConfig(String str, ComponentName componentName, int i) {
            this.detailCategory = str;
            this.detailComponent = componentName;
            this.detailHeightRes = i;
        }

        public final String component1() {
            return this.detailCategory;
        }

        public final ComponentName component2() {
            return this.detailComponent;
        }

        public final TileServiceConfig copy(String str, ComponentName componentName, int i) {
            return new TileServiceConfig(str, componentName, i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TileServiceConfig)) {
                return false;
            }
            TileServiceConfig tileServiceConfig = (TileServiceConfig) obj;
            return Intrinsics.areEqual(this.detailCategory, tileServiceConfig.detailCategory) && Intrinsics.areEqual(this.detailComponent, tileServiceConfig.detailComponent) && this.detailHeightRes == tileServiceConfig.detailHeightRes;
        }

        public final String getDetailCategory() {
            return this.detailCategory;
        }

        public final ComponentName getDetailComponent() {
            return this.detailComponent;
        }

        public final int getDetailHeightRes() {
            return this.detailHeightRes;
        }

        public final int getDialogHeight(Context context) {
            int i;
            Object objValueOf;
            if (this.detailCategory == null || this.detailComponent == null || (i = this.detailHeightRes) == 0) {
                LogUtil.i$default(LogUtil.TAG_QS, CustomTileEx.TAG, "getDialogHeight: return default dut to " + this, null, 8);
                return context.getResources().getDimensionPixelSize(2131171658);
            }
            try {
                objValueOf = Result.constructor-impl(Integer.valueOf(context.getPackageManager().getResourcesForApplication(this.detailComponent.getPackageName()).getDimensionPixelSize(i)));
            } catch (Throwable th) {
                objValueOf = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Throwable th2 = Result.exceptionOrNull-impl(objValueOf);
            if (th2 != null) {
                LogUtil.e(LogUtil.TAG_QS, CustomTileEx.TAG, "getDialogHeight: occur exception, return default", th2);
                objValueOf = Integer.valueOf(context.getResources().getDimensionPixelSize(2131171658));
            }
            return ((Number) objValueOf).intValue();
        }

        public final int hashCode() {
            String str = this.detailCategory;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            ComponentName componentName = this.detailComponent;
            return Integer.hashCode(this.detailHeightRes) + ((iHashCode + (componentName != null ? componentName.hashCode() : 0)) * 31);
        }

        public final boolean isDetailComponentAvailable(Context context) {
            ComponentName componentName;
            return (this.detailCategory == null || (componentName = this.detailComponent) == null || !SystemUIInlineViewHelper.isInlineServiceAvailable(context, componentName)) ? false : true;
        }

        public String toString() {
            String str = this.detailCategory;
            ComponentName componentName = this.detailComponent;
            StringBuilder sb = new StringBuilder("TileServiceConfig(detailCategory=");
            sb.append(str);
            sb.append(", detailComponent=");
            sb.append(componentName);
            sb.append(", detailHeightRes=");
            return SurfaceContainer$.ExternalSyntheticOutline0.m(this.detailHeightRes, ")", sb);
        }
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.tileex.CustomTileEx$getTileServiceConfig$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class C02731 implements Function {
        public /* synthetic */ String $tileSpec;
        public /* synthetic */ CustomTileEx this$0;

        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            CustomTileEx customTileEx = this.this$0;
            String str = this.$tileSpec;
            Companion companion = CustomTileEx.Companion;
            return customTileEx.createTileServiceConfig(str);
        }
    }

    public CustomTileEx(QSTileExParams qSTileExParams, OplusQSDetailInteractor.Factory factory, UserTracker userTracker) {
        super(qSTileExParams);
        this.userTracker = userTracker;
        CustomTileEx$$ExternalSyntheticLambda0 customTileEx$$ExternalSyntheticLambda0 = new CustomTileEx$$ExternalSyntheticLambda0();
        customTileEx$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.detailAdapter$delegate = LazyKt__LazyJVMKt.lazy(customTileEx$$ExternalSyntheticLambda0);
        CustomTileEx$$ExternalSyntheticLambda1 customTileEx$$ExternalSyntheticLambda1 = new CustomTileEx$$ExternalSyntheticLambda1();
        customTileEx$$ExternalSyntheticLambda1.f$0 = factory;
        customTileEx$$ExternalSyntheticLambda1.f$1 = this;
        VarHandle.storeStoreFence();
        this.detailInteractor$delegate = LazyKt__LazyJVMKt.lazy(customTileEx$$ExternalSyntheticLambda1);
        this.tileSpecToTileDetail = new ConcurrentHashMap<>();
        this.lottieConfig = MapsKt__MapsKt.mapOf(new Pair[]{TuplesKt.to("custom(com.oplus.eyeprotect/.services.EyeProtectTileService)", new Pair(TILE_EYEPROTECT_LOTTIE_PREFIX, 17)), TuplesKt.to("custom(com.oplus.battery/com.oplus.powermanager.powersave.PowerSaveTileService)", new Pair(TILE_BATTERYLOTTIE_PREFIX, 16))});
        int i = QsColorfulConfigUtil.QS_COLORFUL_ICON_ACTIVE_YELLOW_COLOR;
        Pair pair = TuplesKt.to("custom(com.oplus.eyeprotect/.services.EyeProtectTileService)", Integer.valueOf(i));
        Pair pair2 = TuplesKt.to("custom(com.oplus.battery/com.oplus.powermanager.powersave.PowerSaveTileService)", Integer.valueOf(i));
        int i2 = QsColorfulConfigUtil.QS_COLORFUL_ICON_ACTIVE_BLUE_COLOR;
        this.colorfulConfig = MapsKt__MapsKt.mapOf(new Pair[]{pair, pair2, TuplesKt.to("custom(com.oplus.battery/com.oplus.powermanager.wirelesscharg.WirelessReverseControService)", Integer.valueOf(i2)), TuplesKt.to("custom(com.coloros.oshare/com.oplus.oshare.OshareTileService)", Integer.valueOf(i2)), TuplesKt.to("custom(com.android.settings/com.oplus.partners.dolby.DolbyTileService)", Integer.valueOf(QsColorfulConfigUtil.QS_COLORFUL_ICON_ACTIVE_BLACK_COLOR))});
    }

    private final CustomTileDetailAdapter getDetailAdapter() {
        return (CustomTileDetailAdapter) this.detailAdapter$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OplusQSDetailInteractor getDetailInteractor() {
        return (OplusQSDetailInteractor) this.detailInteractor$delegate.getValue();
    }

    public final TileServiceConfig createTileServiceConfig(String str) {
        ComponentName componentFromSpec = CustomTile.getComponentFromSpec(str);
        Context context = getContext();
        componentFromSpec.getClass();
        Bundle tileServiceMetadata = SystemUIInlineViewHelper.getTileServiceMetadata(context, componentFromSpec);
        String qSDetailCategory = SystemUIInlineViewHelper.getQSDetailCategory(tileServiceMetadata);
        ComponentName qSDetailComponent = SystemUIInlineViewHelper.getQSDetailComponent(tileServiceMetadata);
        int qSDetailHeightResID = SystemUIInlineViewHelper.getQSDetailHeightResID(tileServiceMetadata);
        if (qSDetailCategory != null && qSDetailComponent != null) {
            SystemUIInlineViewHelper.setViewFactory$default(qSDetailCategory);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            anonymousClass1.$detailComponent = qSDetailComponent;
            VarHandle.storeStoreFence();
            SystemUIInlineViewHelper.setComponentProvider(qSDetailCategory, anonymousClass1);
        }
        TileServiceConfig tileServiceConfig = new TileServiceConfig(qSDetailCategory, qSDetailComponent, qSDetailHeightResID);
        String tag = getTAG();
        if (LogUtil.getNormalDebug() && LogUtil.isTagValid(LogUtil.TAG_QS)) {
            StringBuilder sbM = Bundler$.ExternalSyntheticOutline0.m(tag, LogUtil.SEPARATOR);
            sbM.append("createTileServiceConfig: " + tileServiceConfig);
            Log.d("SystemUi--QuickSettings", sbM.toString());
        }
        return tileServiceConfig;
    }

    public final TileServiceConfig getTileServiceConfig(String str) {
        ConcurrentHashMap<String, TileServiceConfig> concurrentHashMap = this.tileSpecToTileDetail;
        C02731 c02731 = new C02731();
        c02731.this$0 = this;
        c02731.$tileSpec = str;
        VarHandle.storeStoreFence();
        return concurrentHashMap.computeIfAbsent(str, c02731);
    }

    @Override // com.oplus.systemui.qs.tileex.QSTileExImpl
    public boolean handleSecondaryClick(QSTile qSTile, Message message, Handler handler) {
        Expandable expandable;
        if (super.handleSecondaryClick(qSTile, message, handler)) {
            return true;
        }
        if (qSTile.getState().state == 0) {
            LogUtil.i$default(LogUtil.TAG_QS, getTAG(), "handleSecondaryClick: unavailable state, ignore", null, 8);
            return false;
        }
        if (!getTileServiceConfig(qSTile.getTileSpec()).isDetailComponentAvailable(getContext())) {
            LogUtil.i$default(LogUtil.TAG_QS, getTAG(), "handleSecondaryClick: no dual target, ignore", null, 8);
            return false;
        }
        if (getDetailInteractor().isShowing()) {
            LogUtil.i$default(LogUtil.TAG_QS, getTAG(), "handleSecondaryClick: qs detail is showing, ignore", null, 8);
            return true;
        }
        getDetailAdapter().setTag(qSTile.getTileSpec());
        CustomTileDetailAdapter detailAdapter = getDetailAdapter();
        QSTileImpl qSTileImpl = qSTile instanceof QSTileImpl ? (QSTileImpl) qSTile : null;
        detailAdapter.setSettingsIntent(qSTileImpl != null ? qSTileImpl.getLongClickIntent() : null);
        Object obj = message.obj;
        WeakReference weakReference = obj instanceof WeakReference ? (WeakReference) obj : null;
        View view = (weakReference == null || (expandable = (Expandable) weakReference.get()) == null) ? null : expandable.getView();
        getDetailInteractor().show(view);
        LogUtil.i$default(LogUtil.TAG_QS, getTAG(), "handleSecondaryClick: tileSpec=" + qSTile.getTileSpec() + ", expandableView=" + view, null, 8);
        return true;
    }

    @Override // com.oplus.systemui.qs.tileex.QSTileExImpl
    public void postHandleUpdateState(QSTile qSTile, QSTile.State state, Handler handler) {
        super.postHandleUpdateState(qSTile, state, handler);
        state.dualTarget = getTileServiceConfig(qSTile.getTileSpec()).isDetailComponentAvailable(getContext());
        Pair<String, Integer> pair = this.lottieConfig.get(qSTile.getTileSpec());
        if (pair != null) {
            state.lottiePrefix = (String) pair.getFirst();
            state.lottieSupport = ((Number) pair.getSecond()).intValue();
        }
        Integer num = this.colorfulConfig.get(qSTile.getTileSpec());
        if (num != null) {
            state.colorfulConfig = num.intValue();
        }
    }

    public final void updateTileServiceConfig(String str) {
        this.tileSpecToTileDetail.put(str, createTileServiceConfig(str));
    }

    /* JADX INFO: renamed from: com.oplus.systemui.qs.tileex.CustomTileEx$createTileServiceConfig$1, reason: invalid class name */
    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class AnonymousClass1 implements Supplier {
        public /* synthetic */ ComponentName $detailComponent;

        @Override // java.util.function.Supplier
        public final ComponentName get() {
            return this.$detailComponent;
        }
    }
}
