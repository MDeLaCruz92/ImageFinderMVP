package com.mdelacruz.imagefindermvp.base

import android.os.Bundle
import android.support.v4.app.Fragment
import com.mdelacruz.imagefindermvp.App
import com.mdelacruz.imagefindermvp.R
import com.mdelacruz.imagefindermvp.injection.AppComponent
import com.mdelacruz.imagefindermvp.util.toast

abstract class BaseFragment : Fragment(), BaseView {

  private var presenter: BasePresenter<*>? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    onFragmentInject()
  }

  protected abstract fun onFragmentInject()

  fun getAppComponent(): AppComponent = App.getInstance().appComponent

  override fun setPresenter(presenter: BasePresenter<*>) {
    this.presenter = presenter
  }

  override fun onError() {
    activity?.toast(R.string.unexpected_error)
  }

  override fun onDestroy() {
    presenter?.detachView()
    presenter = null
    super.onDestroy()
  }
}