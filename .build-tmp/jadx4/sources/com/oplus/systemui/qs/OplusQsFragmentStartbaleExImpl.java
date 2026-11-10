package com.oplus.systemui.qs;

import com.android.systemui.fragments.FragmentService;
import com.android.systemui.qs.OplusQsFragmentStartableEx;
import com.android.systemui.qs.deprecated.QSFragmentLegacy;
import javax.inject.Provider;

/* JADX INFO: compiled from: go/retraceme fd74bf9514dd0739b92d67a6d48a51eb10aaf8df760d83496b8dcfcf83e5f698 */
/* JADX INFO: loaded from: /home/starlight/Projects/Starlight Ports/.build-tmp/classes4.dex */
public final class OplusQsFragmentStartbaleExImpl extends OplusQsFragmentStartableEx {
    private final Provider qsFragmentLegacyProvider;

    public OplusQsFragmentStartbaleExImpl(Provider provider) {
        this.qsFragmentLegacyProvider = provider;
    }

    public void addFragmentInstantiationProvider(FragmentService fragmentService) {
        fragmentService.addFragmentInstantiationProvider(QSFragmentLegacy.class, this.qsFragmentLegacyProvider);
    }
}
