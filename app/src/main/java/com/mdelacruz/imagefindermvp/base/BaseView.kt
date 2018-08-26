package com.mdelacruz.imagefindermvp.base

interface BaseView {
  fun onError()
  fun setPresenter(presenter: BasePresenter<*>)
}