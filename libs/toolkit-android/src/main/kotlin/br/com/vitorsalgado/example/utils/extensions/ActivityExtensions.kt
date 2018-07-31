package br.com.vitorsalgado.example.utils.extensions

import androidx.annotation.NonNull
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
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
