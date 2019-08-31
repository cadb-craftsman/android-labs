package com.woowrale.mvp.ui.base

import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.woowrale.mvp.data.repository.ApiClient
import retrofit2.Retrofit

open class BaseActivity : AppCompatActivity(), BasePresenter.View {
    val retrofit: Retrofit = ApiClient.getClient()
}

open class BasePresenter<V : BasePresenter.View> {

    lateinit private var view: View

    fun setView(V: View){
       view = V
    }

    fun getView(): View {
        return view
    }

    interface View
}


abstract class BaseFragment : Fragment(), BasePresenter.View


abstract class BaseDialog : DialogFragment()