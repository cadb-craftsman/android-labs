// Generated code from Butter Knife. Do not modify!
package com.woowrale.jmvp.ui.adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.woowrale.jmvp.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ContactsAdapterFilterable$MyViewHolder_ViewBinding implements Unbinder {
  private ContactsAdapterFilterable.MyViewHolder target;

  @UiThread
  public ContactsAdapterFilterable$MyViewHolder_ViewBinding(ContactsAdapterFilterable.MyViewHolder target,
      View source) {
    this.target = target;

    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", TextView.class);
    target.phone = Utils.findRequiredViewAsType(source, R.id.phone, "field 'phone'", TextView.class);
    target.thumbnail = Utils.findRequiredViewAsType(source, R.id.thumbnail, "field 'thumbnail'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ContactsAdapterFilterable.MyViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.name = null;
    target.phone = null;
    target.thumbnail = null;
  }
}
