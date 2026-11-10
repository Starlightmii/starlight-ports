package com.oplus.systemui.qs.base.widget;

import android.graphics.drawable.GradientDrawable;
import com.android.systemui.plugins.qs.QSTile;
import com.oplus.systemui.qs.base.util.QsColorUtil;
import java.lang.invoke.VarHandle;
import kotlin.Pair;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class QsStaticBackgroundProxy extends QsViewBackgroundProxy {
    private LightStyleStrokeDrawable autoBlurDrawable;
    private GlobalThemeQsStaticBackground globalThemeNonFunctionalBackground;
    private MixColorQsStaticBackground mixColorNonFunctionalBackground;
    private NormalColorQsStaticBackground normalColorNonFunctionalBackground;
    private final QsStaticViewInfoProvider panelInfo;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class MixColorQsStaticBackground implements QsViewBackground {
        public /* synthetic */ QsStaticBackgroundProxy this$0;

        @Override // com.oplus.systemui.qs.base.widget.QsViewBackground
        public /* bridge */ /* synthetic */ Pair getDeformBackgroundColor() {
            return super.getDeformBackgroundColor();
        }

        @Override // com.oplus.systemui.qs.base.widget.QsViewBackground
        public /* bridge */ /* synthetic */ void handleStateChanged(QSTile.State state) {
            super.handleStateChanged(state);
        }

        @Override // com.oplus.systemui.qs.base.widget.QsViewBackground
        public final void onBackgroundAttach() {
            QsStaticBackgroundProxy qsStaticBackgroundProxy = this.this$0;
            qsStaticBackgroundProxy.panelInfo.updateBgOutlineProvider(qsStaticBackgroundProxy.ensureBgOutlineProvider());
            qsStaticBackgroundProxy.panelInfo.updateBackground(QsStaticBackgroundProxy.access$ensureAutoBlurDrawable(qsStaticBackgroundProxy));
        }

        @Override // com.oplus.systemui.qs.base.widget.QsViewBackground
        public final void refreshViewBackground() {
            QsStaticBackgroundProxy qsStaticBackgroundProxy = this.this$0;
            LightStyleStrokeDrawable lightStyleStrokeDrawableAccess$ensureAutoBlurDrawable = QsStaticBackgroundProxy.access$ensureAutoBlurDrawable(qsStaticBackgroundProxy);
            if (QsViewBackgroundKt.updateBlurConfig(lightStyleStrokeDrawableAccess$ensureAutoBlurDrawable.getViewBlurProxy().getBlurConfig(), qsStaticBackgroundProxy.getViewContext())) {
                lightStyleStrokeDrawableAccess$ensureAutoBlurDrawable.getViewBlurProxy().applyBlurConfig();
            }
        }

        @Override // com.oplus.systemui.qs.base.widget.QsViewBackground
        public /* bridge */ /* synthetic */ void setInDeform(boolean z) {
            super.setInDeform(z);
        }
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class NormalColorQsStaticBackground extends BaseQsViewBackground {
        private GradientDrawable maskDrawableNormal;

        public NormalColorQsStaticBackground() {
            super(QsStaticBackgroundProxy.this.panelInfo);
        }

        public final GradientDrawable ensureMaskDrawable() {
            GradientDrawable gradientDrawableCreateOrUpdateGradientDrawable = createOrUpdateGradientDrawable(this.maskDrawableNormal);
            this.maskDrawableNormal = gradientDrawableCreateOrUpdateGradientDrawable;
            return gradientDrawableCreateOrUpdateGradientDrawable;
        }

        @Override // com.oplus.systemui.qs.base.widget.BaseQsViewBackground
        public QsViewOutlineProvider getBgOutlineProvider() {
            return QsStaticBackgroundProxy.this.ensureBgOutlineProvider();
        }

        @Override // com.oplus.systemui.qs.base.widget.BaseQsViewBackground, com.oplus.systemui.qs.base.widget.QsViewBackground
        public /* bridge */ /* synthetic */ Pair getDeformBackgroundColor() {
            return super.getDeformBackgroundColor();
        }

        public final GradientDrawable getMaskDrawableNormal() {
            return this.maskDrawableNormal;
        }

        @Override // com.oplus.systemui.qs.base.widget.BaseQsViewBackground, com.oplus.systemui.qs.base.widget.QsViewBackground
        public /* bridge */ /* synthetic */ void handleStateChanged(QSTile.State state) {
            super.handleStateChanged(state);
        }

        @Override // com.oplus.systemui.qs.base.widget.QsViewBackground
        public final void onBackgroundAttach() {
            QsStaticBackgroundProxy qsStaticBackgroundProxy = QsStaticBackgroundProxy.this;
            qsStaticBackgroundProxy.panelInfo.updateBgOutlineProvider(qsStaticBackgroundProxy.ensureBgOutlineProvider());
            QsStaticViewInfoProvider qsStaticViewInfoProvider = qsStaticBackgroundProxy.panelInfo;
            GradientDrawable gradientDrawableEnsureMaskDrawable = ensureMaskDrawable();
            gradientDrawableEnsureMaskDrawable.setColor(qsStaticBackgroundProxy.panelInfo.getNormalBackGroundColor());
            qsStaticViewInfoProvider.updateBackground(gradientDrawableEnsureMaskDrawable);
        }

        @Override // com.oplus.systemui.qs.base.widget.QsViewBackground
        public final void refreshViewBackground() {
            QsStaticBackgroundProxy qsStaticBackgroundProxy = QsStaticBackgroundProxy.this;
            QsStaticViewInfoProvider qsStaticViewInfoProvider = qsStaticBackgroundProxy.panelInfo;
            GradientDrawable gradientDrawableEnsureMaskDrawable = ensureMaskDrawable();
            gradientDrawableEnsureMaskDrawable.setColor(qsStaticBackgroundProxy.panelInfo.getNormalBackGroundColor());
            gradientDrawableEnsureMaskDrawable.invalidateSelf();
            qsStaticViewInfoProvider.updateBackground(gradientDrawableEnsureMaskDrawable);
        }

        @Override // com.oplus.systemui.qs.base.widget.BaseQsViewBackground, com.oplus.systemui.qs.base.widget.QsViewBackground
        public /* bridge */ /* synthetic */ void setInDeform(boolean z) {
            super.setInDeform(z);
        }

        public final void setMaskDrawableNormal(GradientDrawable gradientDrawable) {
            this.maskDrawableNormal = gradientDrawable;
        }
    }

    public QsStaticBackgroundProxy(QsStaticViewInfoProvider qsStaticViewInfoProvider) {
        super(qsStaticViewInfoProvider);
        this.panelInfo = qsStaticViewInfoProvider;
    }

    public static final LightStyleStrokeDrawable access$ensureAutoBlurDrawable(QsStaticBackgroundProxy qsStaticBackgroundProxy) {
        LightStyleStrokeDrawable lightStyleStrokeDrawable = qsStaticBackgroundProxy.autoBlurDrawable;
        if (lightStyleStrokeDrawable != null) {
            return lightStyleStrokeDrawable;
        }
        LightStyleStrokeDrawable lightStyleStrokeDrawableCreateMixColorDrawableForQsView = QsViewBackgroundKt.createMixColorDrawableForQsView(qsStaticBackgroundProxy.panelInfo.getBackgroundView());
        qsStaticBackgroundProxy.autoBlurDrawable = lightStyleStrokeDrawableCreateMixColorDrawableForQsView;
        return lightStyleStrokeDrawableCreateMixColorDrawableForQsView;
    }

    @Override // com.oplus.systemui.qs.base.widget.QsViewBackgroundProxy
    public QsViewBackground getTargetQsViewBackground() {
        if (QsColorUtil.isGlobalThemeApplied(getViewContext())) {
            this.panelInfo.getClass();
        }
        if (!QsViewBackgroundKt.isMixColorSupport(this.panelInfo)) {
            NormalColorQsStaticBackground normalColorQsStaticBackground = this.normalColorNonFunctionalBackground;
            if (normalColorQsStaticBackground == null) {
                normalColorQsStaticBackground = new NormalColorQsStaticBackground();
            }
            this.normalColorNonFunctionalBackground = normalColorQsStaticBackground;
            return normalColorQsStaticBackground;
        }
        MixColorQsStaticBackground mixColorQsStaticBackground = this.mixColorNonFunctionalBackground;
        if (mixColorQsStaticBackground == null) {
            mixColorQsStaticBackground = new MixColorQsStaticBackground();
            mixColorQsStaticBackground.this$0 = this;
            VarHandle.storeStoreFence();
        }
        this.mixColorNonFunctionalBackground = mixColorQsStaticBackground;
        return mixColorQsStaticBackground;
    }
}
