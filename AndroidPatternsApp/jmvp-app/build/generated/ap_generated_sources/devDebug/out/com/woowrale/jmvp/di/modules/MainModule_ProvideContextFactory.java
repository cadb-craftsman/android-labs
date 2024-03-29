// Generated by Dagger (https://google.github.io/dagger).
package com.woowrale.jmvp.di.modules;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class MainModule_ProvideContextFactory implements Factory<Context> {
  private final MainModule module;

  public MainModule_ProvideContextFactory(MainModule module) {
    this.module = module;
  }

  @Override
  public Context get() {
    return provideInstance(module);
  }

  public static Context provideInstance(MainModule module) {
    return proxyProvideContext(module);
  }

  public static MainModule_ProvideContextFactory create(MainModule module) {
    return new MainModule_ProvideContextFactory(module);
  }

  public static Context proxyProvideContext(MainModule instance) {
    return Preconditions.checkNotNull(
        instance.provideContext(), "Cannot return null from a non-@Nullable @Provides method");
  }
}
