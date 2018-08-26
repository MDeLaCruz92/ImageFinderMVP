package com.mdelacruz.imagefindermvp.base

interface Presenter<V : BaseView> {
  fun attachView(view: V)
  fun detachView()
}