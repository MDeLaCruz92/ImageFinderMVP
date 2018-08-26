package com.mdelacruz.imagefindermvp.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mdelacruz.imagefindermvp.App
import com.mdelacruz.imagefindermvp.R
import com.mdelacruz.imagefindermvp.ui.fragment.MainFragment

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    App.getInstance().appComponent.inject(this)
    setContentView(R.layout.activity_main)

    supportFragmentManager
        .beginTransaction()
        .add(R.id.fragment_container, MainFragment.newInstance())
        .commit()
  }
}
