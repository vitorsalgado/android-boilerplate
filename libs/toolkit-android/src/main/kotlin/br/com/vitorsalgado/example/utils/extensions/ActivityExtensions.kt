package br.com.vitorsalgado.example.utils.extensions

import android.support.annotation.NonNull
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import br.com.vitorsalgado.example.utils.R

fun AppCompatActivity.setToolbar(@NonNull toolbar: Toolbar, @StringRes title: Int) {
  this.setToolbar(toolbar, getString(title))
}

fun AppCompatActivity.setToolbar(@NonNull toolbar: Toolbar, title: String = "") {
  this.setSupportActionBar(toolbar)

  val actionBar = supportActionBar
    ?: throw IllegalStateException("failed to setup actionBar. actionBar is null.")

  actionBar.setDisplayHomeAsUpEnabled(true)
  actionBar.setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.ic_back))
  actionBar.title = title
}
