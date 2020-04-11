// Generated code from Butter Knife. Do not modify!
package com.woowrale.jmvp.ui.search.remote;

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

public class RemoteSearchActivity_ViewBinding implements Unbinder {
  private RemoteSearchActivity target;

  @UiThread
  public RemoteSearchActivity_ViewBinding(RemoteSearchActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RemoteSearchActivity_ViewBinding(RemoteSearchActivity target, View source) {
    this.target = target;

    target.inputSearch = Utils.findRequiredViewAsType(source, R.id.input_search, "field 'inputSearch'", EditText.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RemoteSearchActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.inputSearch = null;
    target.recyclerView = null;
    target.toolbar = null;
  }
}
