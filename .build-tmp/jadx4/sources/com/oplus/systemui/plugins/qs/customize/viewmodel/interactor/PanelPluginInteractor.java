package com.oplus.systemui.plugins.qs.customize.viewmodel.interactor;

import android.graphics.drawable.Drawable;
import com.android.systemui.plugins.qs.IQSPanelPluginScanner;
import com.android.systemui.plugins.qs.OplusSeparateQSPlugin;
import com.android.systemui.plugins.qs.QsPanelPlugin;
import com.android.systemui.plugins.qs.SpanSizeInPlugin;
import com.oplus.systemui.plugins.qs.ContextInstance;
import com.oplus.systemui.plugins.qs.addtile.AddTileCalculator;
import com.oplus.systemui.plugins.qs.customize.data.model.TileStaticConfig;
import com.oplus.systemui.plugins.qs.customize.data.repo.SepStaticTileConfigRepository;
import com.oplus.systemui.qs.tiles.OplusInnerFakeTile;
import com.oplusos.systemui.common.model.SpanCache;
import com.oplusos.systemui.common.model.SpanSize;
import java.lang.invoke.VarHandle;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlow;
import okio.Path$;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class PanelPluginInteractor {
    private final ContextInstance contextInstance;
    private final StateFlow dataFlow;
    private final OplusSeparateQSPlugin.IStub proxy;
    private final Lazy qsPanelPluginScanner$delegate;
    private final Flow selectableDataFlow;
    private final SepStaticTileConfigRepository staticTileConfigRepo;
    public static final Companion Companion = new Companion();
    private static final SpanSize BIGGER_DEVICE = SpanCache.INSTANCE.spanSize(4, 1);

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    public PanelPluginInteractor(ContextInstance contextInstance, OplusSeparateQSPlugin.IStub iStub, SepStaticTileConfigRepository sepStaticTileConfigRepository) {
        this.contextInstance = contextInstance;
        this.proxy = iStub;
        this.staticTileConfigRepo = sepStaticTileConfigRepository;
        PanelPluginInteractor$$ExternalSyntheticLambda0 panelPluginInteractor$$ExternalSyntheticLambda0 = new PanelPluginInteractor$$ExternalSyntheticLambda0();
        panelPluginInteractor$$ExternalSyntheticLambda0.f$0 = this;
        VarHandle.storeStoreFence();
        this.qsPanelPluginScanner$delegate = LazyKt__LazyJVMKt.lazy(panelPluginInteractor$$ExternalSyntheticLambda0);
        StateFlow dataFlow = getQsPanelPluginScanner().getDataFlow();
        this.dataFlow = dataFlow;
        PanelPluginInteractor$special$$inlined$map$1 panelPluginInteractor$special$$inlined$map$1 = new PanelPluginInteractor$special$$inlined$map$1();
        panelPluginInteractor$special$$inlined$map$1.$this_unsafeTransform$inlined = dataFlow;
        panelPluginInteractor$special$$inlined$map$1.this$0 = this;
        VarHandle.storeStoreFence();
        this.selectableDataFlow = panelPluginInteractor$special$$inlined$map$1;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    public static final Object access$getDefaultSize(PanelPluginInteractor panelPluginInteractor, String str, ContinuationImpl continuationImpl) throws Throwable {
        PanelPluginInteractor$getDefaultSize$1 panelPluginInteractor$getDefaultSize$1;
        panelPluginInteractor.getClass();
        if (continuationImpl instanceof PanelPluginInteractor$getDefaultSize$1) {
            panelPluginInteractor$getDefaultSize$1 = (PanelPluginInteractor$getDefaultSize$1) continuationImpl;
            int i = panelPluginInteractor$getDefaultSize$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                panelPluginInteractor$getDefaultSize$1.label = i - Integer.MIN_VALUE;
            } else {
                panelPluginInteractor$getDefaultSize$1 = new PanelPluginInteractor$getDefaultSize$1(panelPluginInteractor, continuationImpl);
            }
        } else {
            panelPluginInteractor$getDefaultSize$1 = new PanelPluginInteractor$getDefaultSize$1(panelPluginInteractor, continuationImpl);
        }
        Object tileConfig = panelPluginInteractor$getDefaultSize$1.result;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = panelPluginInteractor$getDefaultSize$1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(tileConfig);
            SepStaticTileConfigRepository sepStaticTileConfigRepository = panelPluginInteractor.staticTileConfigRepo;
            panelPluginInteractor$getDefaultSize$1.L$0 = SpillingKt.nullOutSpilledVariable(str);
            panelPluginInteractor$getDefaultSize$1.label = 1;
            tileConfig = sepStaticTileConfigRepository.getTileConfig(str, panelPluginInteractor$getDefaultSize$1);
            if (tileConfig == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                Path$.ExternalSyntheticBUOutline0.m$1("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(tileConfig);
        }
        return ((TileStaticConfig) tileConfig).getDefaultSize();
    }

    public static final CharSequence access$getLabelString(PanelPluginInteractor panelPluginInteractor, String str, QsPanelPlugin qsPanelPlugin) {
        if (qsPanelPlugin.getLabel().length() != 0 || !Intrinsics.areEqual(str, OplusInnerFakeTile.SPEC_DEVICE)) {
            return qsPanelPlugin.getLabel();
        }
        String string = panelPluginInteractor.contextInstance.getPluginContext().getResources().getString(2131955239);
        string.getClass();
        return string;
    }

    public static final Drawable access$getPreviewDrawable(PanelPluginInteractor panelPluginInteractor, QsPanelPlugin qsPanelPlugin, SpanSize spanSize) {
        Drawable previewDrawable = qsPanelPlugin.getPreviewDrawable(new SpanSizeInPlugin(spanSize.getColSpan(), spanSize.getRowSpan()));
        return previewDrawable == null ? AddTileCalculator.INSTANCE.getDevicePreviewDrawable(panelPluginInteractor.contextInstance.getPluginContext()) : previewDrawable;
    }

    private final IQSPanelPluginScanner getQsPanelPluginScanner() {
        return (IQSPanelPluginScanner) this.qsPanelPluginScanner$delegate.getValue();
    }

    public final StateFlow getDataFlow() {
        return this.dataFlow;
    }

    public final Flow getSelectableDataFlow() {
        return this.selectableDataFlow;
    }
}
