package com.oplus.systemui.statusbar.pipeline.mobile.ui.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;
import androidx.car.app.serialization.Bundler$;
import com.android.systemui.statusbar.pipeline.mobile.data.OplusNetworkTypeText;
import com.android.systemui.statusbar.pipeline.mobile.domain.model.OplusNetworkTypeTextModel;
import com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.LocationBasedMobileViewModel;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.oplus.systemui.statusbar.phone.signal.widget.OplusModernStatusBarMobileView;
import com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconViewModel;
import com.oplus.systemui.statusbar.pipeline.mobile.ui.viewmodel.OplusMobileIconsViewModel;
import com.oplus.systemui.statusbar.pipeline.mobile.utils.OplusNetworkTypeTextSpanHelper;
import com.oplusos.systemui.common.typeface.SystemTypefaceHelper;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public class OplusStatusBarMobileViewBinder extends AbstractOplusStatusBarMobileViewBinder {
    public static final Companion Companion = new Companion();
    private static final String TAG = "OplusStatusBarMobileViewBinder";
    private final Flow typefaceCallback;

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public final class Companion {
    }

    /* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
    public class Os17Binding extends AbstractOplusStatusBarMobileViewBinder$Binding {
        private String lastNetworkTypeTextName;
        private final TextView mobileRoamingText;
        private final ImageView mobileTypeImage;
        private final TextView mobileTypeText;
        private final SystemTypefaceHelper systemTypefaceHelper;

        public Os17Binding(ViewGroup viewGroup, LocationBasedMobileViewModel locationBasedMobileViewModel, OplusMobileIconViewModel oplusMobileIconViewModel) {
            super(OplusStatusBarMobileViewBinder.this, viewGroup, locationBasedMobileViewModel, oplusMobileIconViewModel);
            this.mobileTypeText = (TextView) viewGroup.requireViewById(2131363991);
            this.mobileRoamingText = (TextView) viewGroup.requireViewById(2131363978);
            this.systemTypefaceHelper = SystemTypefaceHelper.Companion.getInstance(OplusStatusBarMobileViewBinder.this.getContext());
            this.mobileTypeImage = (ImageView) viewGroup.findViewById(2131363990);
        }

        public static final void access$setMobileTypeTextTypeface(Os17Binding os17Binding, Typeface typeface, int i, boolean z) {
            TextView textView = os17Binding.mobileTypeText;
            if (z) {
                typeface = os17Binding.systemTypefaceHelper.getNetworkIconTextTypeface(typeface, i, 25);
            }
            textView.setTypeface(typeface);
        }

        public void bindNetworkTypeText(OplusNetworkTypeTextModel oplusNetworkTypeTextModel) {
            ImageView imageView;
            OplusNetworkTypeText networkTypeText;
            boolean z;
            if (oplusNetworkTypeTextModel != null && (imageView = this.mobileTypeImage) != null && (networkTypeText = oplusNetworkTypeTextModel.getNetworkTypeText()) != null && (((z = networkTypeText instanceof OplusNetworkTypeText.Tigo5GConnected)) || (networkTypeText instanceof OplusNetworkTypeText.Tigo5GNotRestricted))) {
                Space roamingSpace = getRoamingSpace();
                if (roamingSpace != null) {
                    roamingSpace.setVisibility(0);
                }
                this.mobileTypeText.setVisibility(8);
                this.mobileTypeText.setText((CharSequence) null);
                imageView.setImageResource(z ? 2131236732 : 2131236734);
                imageView.setContentDescription(oplusNetworkTypeTextModel.getContentDescription() != 0 ? imageView.getContext().getString(oplusNetworkTypeTextModel.getContentDescription()) : Bundler$.ExternalSyntheticOutline0.m(networkTypeText.getPrefix(), networkTypeText.getSuffix()));
                imageView.setVisibility(0);
                return;
            }
            ImageView imageView2 = this.mobileTypeImage;
            if (imageView2 != null) {
                imageView2.setVisibility(8);
            }
            Space roamingSpace2 = getRoamingSpace();
            if (roamingSpace2 != null) {
                roamingSpace2.setVisibility(0);
            }
            OplusNetworkTypeText networkTypeText2 = oplusNetworkTypeTextModel != null ? oplusNetworkTypeTextModel.getNetworkTypeText() : null;
            TextView textView = this.mobileTypeText;
            if (networkTypeText2 == null) {
                textView.setText((CharSequence) null);
                this.mobileTypeText.setContentDescription(null);
                this.mobileTypeText.setVisibility(8);
            } else {
                OplusNetworkTypeTextSpanHelper oplusNetworkTypeTextSpanHelper = OplusNetworkTypeTextSpanHelper.INSTANCE;
                OplusStatusBarMobileViewBinder oplusStatusBarMobileViewBinder = OplusStatusBarMobileViewBinder.this;
                oplusNetworkTypeTextSpanHelper.applyToTextView(textView, oplusNetworkTypeTextModel, oplusStatusBarMobileViewBinder.getNetworkTypeTextPrefixDimenRes(), oplusStatusBarMobileViewBinder.getNetworkTypeTextSuffixDimenRes());
                this.mobileTypeText.setContentDescription(oplusNetworkTypeTextModel.getContentDescription() != 0 ? this.mobileTypeText.getContext().getString(oplusNetworkTypeTextModel.getContentDescription()) : Bundler$.ExternalSyntheticOutline0.m(networkTypeText2.getPrefix(), networkTypeText2.getSuffix()));
                this.mobileTypeText.setVisibility(0);
            }
        }

        public final void bindRoamingText(boolean z) {
            if (!z) {
                this.mobileRoamingText.setVisibility(8);
                return;
            }
            Space roamingSpace = getRoamingSpace();
            if (roamingSpace != null) {
                roamingSpace.setVisibility(0);
            }
            this.mobileRoamingText.setVisibility(0);
        }

        @Override // com.oplus.systemui.statusbar.pipeline.mobile.ui.view.AbstractOplusStatusBarMobileViewBinder$Binding
        public void bindSubclassFlows(CoroutineScope coroutineScope) {
            OplusStatusBarMobileViewBinder oplusStatusBarMobileViewBinder = OplusStatusBarMobileViewBinder.this;
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new bindSubclassFlows.1(this, oplusStatusBarMobileViewBinder, (Continuation) null), 3);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new bindSubclassFlows.2(this, oplusStatusBarMobileViewBinder, (Continuation) null), 3);
            BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new bindSubclassFlows.3(this, (Continuation) null), 3);
        }

        public final TextView getMobileTypeText() {
            return this.mobileTypeText;
        }

        @Override // com.oplus.systemui.statusbar.pipeline.mobile.ui.view.AbstractOplusStatusBarMobileViewBinder$Binding
        public final void initViews(int i) {
            super.initViews(i);
            bindNetworkTypeText((OplusNetworkTypeTextModel) getOplusViewModel().getNetworkTypeText().getValue());
            bindRoamingText(((Boolean) getOplusViewModel().isRoaming().getValue()).booleanValue());
        }

        @Override // com.oplus.systemui.statusbar.pipeline.mobile.ui.view.AbstractOplusStatusBarMobileViewBinder$Binding
        public final void updateTint(int i) {
            super.updateTint(i);
            this.mobileTypeText.setTextColor(i);
            this.mobileRoamingText.setTextColor(i);
            ImageView imageView = this.mobileTypeImage;
            if (imageView != null) {
                imageView.clearColorFilter();
                imageView.setImageTintList(ColorStateList.valueOf(i));
            }
        }
    }

    public OplusStatusBarMobileViewBinder(Context context, OplusMobileIconsViewModel oplusMobileIconsViewModel) {
        super(context, oplusMobileIconsViewModel);
        this.typefaceCallback = FlowConflatedKt.conflatedCallbackFlow(new typefaceCallback.1(context, (Continuation) null));
    }

    public AbstractOplusStatusBarMobileViewBinder$Binding createBinding(ViewGroup viewGroup, LocationBasedMobileViewModel locationBasedMobileViewModel, OplusMobileIconViewModel oplusMobileIconViewModel) {
        return new Os17Binding(viewGroup, locationBasedMobileViewModel, oplusMobileIconViewModel);
    }

    public OplusModernStatusBarMobileView fromContext(Context context) {
        return (OplusModernStatusBarMobileView) LayoutInflater.from(context).inflate(2131559401, (ViewGroup) null);
    }

    public int getNetworkTypeTextPrefixDimenRes() {
        return 2131173073;
    }

    public int getNetworkTypeTextSuffixDimenRes() {
        return 2131173074;
    }
}
