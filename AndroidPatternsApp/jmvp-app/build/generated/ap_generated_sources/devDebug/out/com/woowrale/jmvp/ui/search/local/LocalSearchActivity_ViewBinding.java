// Generated code from Butter Knife. Do not modify!
package com.woowrale.jmvp.ui.search.local;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.woowrale.jmvp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LocalSearchActivity_ViewBinding implements Unbinder {
  private LocalSearchActivity target;

  @UiThread
  public LocalSearchActivity_ViewBinding(LocalSearchActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LocalSearchActivity_ViewBinding(LocalSearchActivity target, View source) {
    this.target = target;

    target.inputSearch = Utils.findRequiredViewAsType(source, R.id.input_search, "field 'inputSearch'", EditText.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LocalSearchActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.inputSearch = null;
    target.recyclerView = null;
    target.toolbar = null;
  }
}
