package br.com.vitorsalgado.example.features.authentication

import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.vitorsalgado.example.R

class LoginActivity : AppCompatActivity() {
  private var mAccountAuthenticatorResponse: AccountAuthenticatorResponse? = null
  private var mResultBundle: Bundle? = null

  companion object {
    private const val EXTRA_ERROR_MESSAGE = "br.com.vitorsalgado.example.features.authentication.LoginActivity.ERROR_MESSAGE"
    private const val EXTRA_IS_NEW_TASK = "br.com.vitorsalgado.example.features.authentication.LoginActivity.NEW_TASK"

    fun newClearIntent(context: Context): Intent {
      val intent = Intent(context, LoginActivity::class.java)
      intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
      intent.putExtra(EXTRA_IS_NEW_TASK, true)

      return intent
    }

    fun newIntent(context: Context): Intent {
      return Intent(context, LoginActivity::class.java)
    }

    fun newIntentWithError(context: Context, error: String): Intent {
      val intent = newClearIntent(context)
      intent.putExtra(EXTRA_ERROR_MESSAGE, error)

      return intent
    }
  }

  fun setAccountAuthenticatorResult(result: Bundle) {
    mResultBundle = result
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(R.layout.login_activity)
    // loaded();
  }

  override fun finish() {
    if (mAccountAuthenticatorResponse != null) {
      if (mResultBundle != null) {
        mAccountAuthenticatorResponse!!.onResult(mResultBundle)
      } else {
        mAccountAuthenticatorResponse!!.onError(AccountManager.ERROR_CODE_CANCELED, "canceled")
      }

      mAccountAuthenticatorResponse = null
    }

    super.finish()
  }
}
